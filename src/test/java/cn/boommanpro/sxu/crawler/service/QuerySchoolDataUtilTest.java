package cn.boommanpro.sxu.crawler.service;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.model.SchoolDetails;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class QuerySchoolDataUtilTest {


    private SchoolConfigProperties schoolConfigProperties;

    /**
     * 因为山西大学的教务系统需要内网
     *
     * 遂使用安阳师范学院测试
     *
     * http://jwglxt.aynu.edu.cn/
     */
    @Before
    public void beforeSet(){
        schoolConfigProperties = new SchoolConfigProperties();
        schoolConfigProperties.setSchoolHost("http://jwglxt.aynu.edu.cn/");
        schoolConfigProperties.setSchoolXnxq(SchoolDetails.XNXQ_XQ);
        schoolConfigProperties.setSchoolMainHost(SchoolDetails.host2MainHost(schoolConfigProperties.getSchoolHost()));
        schoolConfigProperties.setSchoolValidate(SchoolDetails.ValidateCode);
        schoolConfigProperties.setSchoolJxl(SchoolDetails.List_JXL);
        schoolConfigProperties.setSchoolRoom(SchoolDetails.List_Room);
        schoolConfigProperties.setSchoolXnxq(SchoolDetails.XNXQ_XQ);
        schoolConfigProperties.setSchoolRoomData(SchoolDetails.RoomData);
        schoolConfigProperties.setSchoolRoomDataReferer(SchoolDetails.RoomDataReferer);
    }


    /**
     *  永远会获取最新的值
     */
    @Test
    public void getXnxqValue() {
        String xnxqValue = QuerySchoolDataUtil.getXnxqValue(schoolConfigProperties);
        log.info("data:{}",xnxqValue);
    }

    /**
     * 获取校区教学楼信息
     */
    @Test
    public void getXxXqValue() {
        Map<String, String> xxXqValue = QuerySchoolDataUtil.getXxXqValue(schoolConfigProperties);
        log.info("data:{}",xxXqValue);
    }


    /**
     * 校区教学楼信息
     */

    @Test
    public void getAllXqJxlValue() {
        JSONObject allXqJxlValue = QuerySchoolDataUtil.getAllXqJxlValue(schoolConfigProperties);
        log.info("data:{}",allXqJxlValue);
    }
    /**
     * 教学楼对应Room信息
     */

    @Test
    public void getAllJxlRoomValue() {
        JSONObject allJxlRoomValue = QuerySchoolDataUtil.getAllJxlRoomValue(schoolConfigProperties);
        log.info("data:{}",allJxlRoomValue);
    }

    @Test
    public void getRoomClassData() {
        Map<String, Map<String, String>> roomClassData = QuerySchoolDataUtil.getRoomClassData(schoolConfigProperties);
        log.info("data:{}",roomClassData);
    }

    @Test
    public void getRoomClassDataByJxlRoomList() {
    }

    @Test
    public void getSingleRoomClassTest() {
    }
}