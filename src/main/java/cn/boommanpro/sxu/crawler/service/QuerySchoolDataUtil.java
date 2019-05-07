package cn.boommanpro.sxu.crawler.service;


import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.crawler.model.JxlRoom;
import cn.boommanpro.sxu.crawler.parse.KingoSoftParse;
import cn.boommanpro.sxu.util.CodeUtil;
import cn.boommanpro.sxu.util.DateUtil;
import cn.boommanpro.sxu.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author BoomManPro
 */
@Slf4j
public class QuerySchoolDataUtil {
    /**
     * @return {"学年学期":"20171","学校校区":{"坞城校区":"1","大东关校区":"2"}}
     */
    public static Map<String, String> getXxXqValue(SchoolConfigProperties schoolConfigProperties) {
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolXnxq();
        String result = HttpUtil.getData(url, schoolConfigProperties);
        KingoSoftParse kingoSoftParse = new KingoSoftParse(result);
        return kingoSoftParse.getXxxq();
    }

    /**
     * 获取学年学期Map
     * <p>
     * 原方法名称 xnXq
     */
    public static String getTopSemesterYear(SchoolConfigProperties schoolConfigProperties) {
        //拼接url
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolXnxq();
        //http获取页面信息
        String result = HttpUtil.getData(url, schoolConfigProperties);
        //解析获取数据
        return KingoSoftParse.getTopSemesterYear(result);
    }

    public static Map<String, Map<String, String>> getAllXqJxlValue(SchoolConfigProperties schoolConfigProperties) {
        Map<String, String> xxXqValue = getXxXqValue(schoolConfigProperties);
        Map<String, Map<String, String>> xqJxlMap = new HashMap<>(xxXqValue.size());
        Collection<String> values = xxXqValue.values();
        for (String value : values) {
            xqJxlMap.put(value, getXqJxlValue(value, schoolConfigProperties));
        }
        return xqJxlMap;
    }

    public static Map<String, Map<String, String>> getAllJxlRoomValue(SchoolConfigProperties schoolConfigProperties) {

        Map<String, Map<String, String>> allXqJxlValue = getAllXqJxlValue(schoolConfigProperties);
        //必定比这个初始化大小大 随他增加吧
        Map<String, Map<String, String>> jxlRoomMap = new HashMap<>(allXqJxlValue.size());
        Collection<Map<String, String>> values = allXqJxlValue.values();
        for (Map<String, String> value : values) {
            Collection<String> jxlValues = value.values();
            for (String jxlValue : jxlValues) {
                jxlRoomMap.put(jxlValue, getRoomValue(jxlValue, schoolConfigProperties));
            }
        }
        return jxlRoomMap;
    }


    // TODO: 2018/4/23 这里提供的是  schoolConfig和 XqJxl两个参数

    public static Map<String, Map<String, String>> getRoomClassData(SchoolConfigProperties schoolConfigProperties) {
        Map<String, Map<String, String>> mapList = new TreeMap<>();
        Map<String, Map<String, String>> allRoomValue = getAllJxlRoomValue(schoolConfigProperties);
        Set<String> keys = allRoomValue.keySet();
        CodeUtil codeUtil = new CodeUtil();
        String yzmValue = codeUtil.getYzmValue(schoolConfigProperties);
        String cookie = codeUtil.getCookieStr();
        int num = 0;

        for (String key : keys) {
            Map<String, String> roomValues = allRoomValue.get(key);
            Collection<String> values = roomValues.values();
            for (String selRoom : values) {
                log.trace("key:{}value:{}", key, selRoom);
                String s;
                int i = 0;
                /*
                 *   目的是为了多次判断,防止因为网络原因导致空值
                 */
                Map<String, String> stringStringMap;
                do {
                    if (num / 5 == 1) {
                        yzmValue = codeUtil.getYzmValue(schoolConfigProperties);
                        num = 0;
                    }
                    s = HttpUtil.postRoomValue(cookie, yzmValue, DateUtil.getTerm(), key, selRoom, schoolConfigProperties);
                    stringStringMap = new KingoSoftParse(s).getPostData();
                    i++;
                    num++;
                } while (stringStringMap == null && i < 5);

                log.trace("stringStringMap:{}", stringStringMap);
                if (stringStringMap != null) {
                    mapList.put(selRoom, stringStringMap);
                }

            }
        }

        return mapList;
    }


    public static Map<String, Map<String, String>> getRoomClassDataByJxlRoomList(SchoolConfigProperties schoolConfigProperties, List<JxlRoom> jxlRoomList) {
        Map<String, Map<String, String>> mapList = new TreeMap<>();

        for (JxlRoom jxlRoom : jxlRoomList) {

            String queryResult;
            int i = 0;
            /*
             *   目的是为了多次判断,防止因为网络原因导致空值 突破网站限制爬虫
             */
            Map<String, String> stringStringMap;
            do {

                queryResult = HttpUtil.postRoomValue(schoolConfigProperties.getCookie(), schoolConfigProperties.getYzmValue(), DateUtil.getTerm(), jxlRoom.getJxlValue(), jxlRoom.getValue(), schoolConfigProperties);
                stringStringMap = new KingoSoftParse(queryResult).getPostData();
                i++;
            } while (stringStringMap == null && i < 5);

            if (stringStringMap != null) {
                mapList.put(jxlRoom.getValue(), stringStringMap);
            }

        }
        return mapList;
    }

    private static Map<String, String> getRoomValue(String jxlValue, SchoolConfigProperties schoolConfigProperties) {
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolRoom() + "?w=150&id=" + jxlValue;
        String result = HttpUtil.getData(url, schoolConfigProperties);
        return KingoSoftParse.getListData(result, null, jxlValue);
    }

    public static Map<String, String> getXqJxlValue(String schoolZone, SchoolConfigProperties schoolConfigProperties) {
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolJxl() + "?w=150&id=" + schoolZone;
        String result = HttpUtil.getData(url, schoolConfigProperties);
        return KingoSoftParse.getListData(result, schoolZone, null);
    }
}
