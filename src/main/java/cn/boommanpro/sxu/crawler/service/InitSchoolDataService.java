package cn.boommanpro.sxu.crawler.service;


import cn.boommanpro.sxu.common.StatusType;
import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.crawler.dao.JxlRoomMapper;
import cn.boommanpro.sxu.crawler.dao.RoomDataMapper;
import cn.boommanpro.sxu.crawler.dao.XqJxlMapper;
import cn.boommanpro.sxu.crawler.dao.XxXqMapper;
import cn.boommanpro.sxu.crawler.model.JxlRoom;
import cn.boommanpro.sxu.crawler.model.RoomData;
import cn.boommanpro.sxu.crawler.model.XqJxl;
import cn.boommanpro.sxu.crawler.model.XxXq;
import cn.boommanpro.sxu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class InitSchoolDataService {
    @Autowired
    private XxXqMapper xxXqMapper;
    @Autowired
    private XqJxlMapper xqJxlMapper;
    @Autowired
    private JxlRoomMapper jxlRoomMapper;
    @Autowired
    private RoomDataMapper roomDataMapper;


    private final  static int NUM=10;




    public void initRoomData(SchoolConfigProperties schoolConfigProperties)  {


        //先清空表数据 会导致现在查询应用不可用

        //建立临时表插入临时表 传入主表

        //插入数据不应一条一条插入  应批量插入

        DateUtil.setStartTime(schoolConfigProperties.getStartTime());
        DateUtil.setTerm(QuerySchoolDataUtil.getTopSemesterYearValue(schoolConfigProperties));


        //查询出所有校区

        List<JxlRoom> jxlRooms = jxlRoomMapper.selectByUpdatedAndDisabled(StatusType.ENABLE.getKey(), StatusType.DISABLE.getKey());

        while (jxlRooms.size()>0){
            List<JxlRoom> jxlRoomList = popJxlRoomList(jxlRooms, NUM);
            Map<String, Map<String, String>> roomClassDataByJxlRoomList = QuerySchoolDataUtil.getRoomClassDataByJxlRoomList(schoolConfigProperties, jxlRoomList);
            batchUpdateRoomData(jxlRoomList, roomClassDataByJxlRoomList, schoolConfigProperties);
        }

    }

    public void batchUpdateRoomData(List<JxlRoom> jxlRoomList, Map<String, Map<String, String>> roomClassDataByJxlRoomList , SchoolConfigProperties schoolConfigProperties){
        List<RoomData> roomDataList = map2RoomDataList(roomClassDataByJxlRoomList);
        if (!roomDataList.isEmpty()){
            roomDataMapper.insertBatchByRoomDataList( roomDataList);
        }
        //这里不仅要插入还要更新jxlRoomMapper的状态
        List<Integer> idList = jxlRoomList2IdList(jxlRoomList);
        jxlRoomMapper.setDisabledBatch(idList);
    }


    private List<RoomData> map2RoomDataList(Map<String, Map<String, String>> roomClassDataByJxlRoomList){
        List<RoomData> roomDataList = new ArrayList<>();
        RoomData roomData;
        Set<Map.Entry<String, Map<String, String>>> entries = roomClassDataByJxlRoomList.entrySet();
        for (Map.Entry<String, Map<String, String>> entry : entries) {
            String classroomValue = entry.getKey();

            Set<Map.Entry<String, String>> timeAndJsonEntries = entry.getValue().entrySet();
            for (Map.Entry<String, String> timeAndJsonEntry : timeAndJsonEntries) {
                roomData=new RoomData(timeAndJsonEntry.getKey(), classroomValue, timeAndJsonEntry.getValue());
                roomDataList.add(roomData);
            }

        }
        return roomDataList;
    }

    private List<Integer> jxlRoomList2IdList(List<JxlRoom> jxlRoomList){
        List<Integer> idList = new ArrayList<>();
        for (JxlRoom jxlRoom: jxlRoomList
             ) {
            idList.add(jxlRoom.getId());
        }
        return idList;
    }

    private List<JxlRoom> popJxlRoomList(List<JxlRoom> jxlRoomList,int num){
        if (num>jxlRoomList.size()){
            num=jxlRoomList.size();
        }
        List<JxlRoom> resultJxlRoomList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            resultJxlRoomList.add(jxlRoomList.remove(0));
        }

        return resultJxlRoomList;
    }










    public void initXxXq( SchoolConfigProperties schoolConfigProperties) {
        xxXqMapper.truncateTable();
        Map<String, String> xxXqValue = QuerySchoolDataUtil.getXxXqValue(schoolConfigProperties);

        List<XxXq> xxXqList = map2XxXqList(xxXqValue);
        xxXqMapper.insertBatch(xxXqList);
    }

    private List<XxXq> map2XxXqList( Map<String, String> xxXqValue){
        List<XxXq> xxXqList = new ArrayList<>();
        XxXq xxXq;
        LocalDateTime localDateTime;
        for (Map.Entry<String, String> next : xxXqValue.entrySet()) {
            localDateTime=LocalDateTime.now();
            xxXq=new XxXq(next.getKey(), next.getValue());
            xxXq.setCreateDate(localDateTime);
            xxXq.setUpdateDate(localDateTime);
            xxXqList.add(xxXq);
        }
        return xxXqList;
    }


    public void initXqJxl(SchoolConfigProperties schoolConfigProperties) {

        //先清空表
        xqJxlMapper.truncateTable();

        //然后从数据库中查询
        List<String> xqValueList = xxXqMapper.selectAllXqValue();

        Map<String, Map<String, String>> allXqJxlJson = QuerySchoolDataUtil.getAllXqJxlValue(schoolConfigProperties);
        List<XqJxl> xqJxlList = json2XqJxlList(xqValueList, allXqJxlJson);

        xqJxlMapper.insertBatch( xqJxlList);

    }


    private List<XqJxl> json2XqJxlList( List<String> xqValueList,  Map<String, Map<String, String>>  allXqJxlJson){
        List<XqJxl> xqJxlList = new ArrayList<>();

        XqJxl xqJxl;
        LocalDateTime localDateTime;
        for (String xqValue : xqValueList) {
            Map<String, String> jxlJson = allXqJxlJson.get(xqValue);

            Set<String> jxls = jxlJson.keySet();
            for (String jxl : jxls) {
                localDateTime = LocalDateTime.now();
                String value = jxlJson.get(jxl);
                xqJxl = new XqJxl(xqValue, jxl, value);
                xqJxl.setCreateDate(localDateTime);
                xqJxl.setUpdateDate(localDateTime);
                xqJxlList.add(xqJxl);
            }
        }
        return xqJxlList;
    }


    public void initJxlRoom(SchoolConfigProperties schoolConfigProperties) {

        //先清空表
        jxlRoomMapper.truncateTable();
        //这里获取需要从数据库获取
        List<String> jxlValueList = xqJxlMapper.selectAllJxlValue();
        Map<String, Map<String, String>> allJxlRoomJson = QuerySchoolDataUtil.getAllJxlRoomValue(schoolConfigProperties);
        List<JxlRoom> jxlRoomList = json2JxlRoomList(jxlValueList, allJxlRoomJson);
        jxlRoomMapper.insertBatch(jxlRoomList);
    }

    private List<JxlRoom> json2JxlRoomList( List<String> jxlValueList,  Map<String, Map<String, String>> allJxlRoomJson){
        List<JxlRoom> jxlRoomList = new ArrayList<>();

        JxlRoom jxlRoom;
        LocalDateTime localDateTime;
        for (String jxlValue : jxlValueList) {
            Map<String, String> roomsJson = allJxlRoomJson.get(jxlValue);
            Set<String> rooms = roomsJson.keySet();
            for (String room : rooms) {
                localDateTime = LocalDateTime.now();
                String value = roomsJson.get(room);
                jxlRoom = new JxlRoom(jxlValue, room, value);
                jxlRoom.setCreateDate(localDateTime);
                jxlRoom.setUpdateDate(localDateTime);
                jxlRoomList.add(jxlRoom);
            }

        }
        return jxlRoomList;
    }

    public void initJxlRoomStatus() {
        jxlRoomMapper.updateAllUpdatedAndDisabled();
    }
}
