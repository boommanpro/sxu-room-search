package cn.boommanpro.sxu.crawler.service;



import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.crawler.parse.JsoupMethod;
import cn.boommanpro.sxu.crawler.model.JxlRoom;
import cn.boommanpro.sxu.util.CodeUtil;
import cn.boommanpro.sxu.util.DateUtil;
import cn.boommanpro.sxu.util.HttpUtil;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * @author BoomManPro
 */
public class QuerySchoolDataUtil {
    /**
     * @return {"学年学期":"20171","学校校区":{"坞城校区":"1","大东关校区":"2"}}
     */
    public static Map<String, String> getXxXqValue(SchoolConfigProperties schoolConfigProperties) {
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolXnxq();
        String result = HttpUtil.getData(url, schoolConfigProperties);
        JsoupMethod jsoupMethod = new JsoupMethod(result);
        return jsoupMethod.getXxxq();
    }

    public static String getXnxqValue(SchoolConfigProperties schoolConfigProperties) {
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolXnxq();
        String result = HttpUtil.getData(url, schoolConfigProperties);
        JsoupMethod jsoupMethod = new JsoupMethod(result);
        return jsoupMethod.getXnxq();
    }

    public static JSONObject getAllXqJxlValue(SchoolConfigProperties schoolConfigProperties) {
        // TODO: 2018/1/25 返回应该做成hashMap
        JSONObject jsonObject = new JSONObject();
        Map<String, String> xxXqValue = getXxXqValue(schoolConfigProperties);
        Collection<String> values = xxXqValue.values();
        for (Object value : values) {
            jsonObject.put(value, getXqJxlValue((String) value, schoolConfigProperties));
        }
        return jsonObject;
    }

    public static JSONObject getAllJxlRoomValue(SchoolConfigProperties schoolConfigProperties) {
        // TODO: 2018/1/25 返回应该做成hashMap
        JSONObject jsonObject = new JSONObject();
        JSONObject allXqJxlValue = getAllXqJxlValue(schoolConfigProperties);
        Collection values = allXqJxlValue.values();
        for (Object value : values) {
            Collection jxlValues = ((JSONObject) value).values();
            for (Object jxlValue : jxlValues) {
                jsonObject.put(jxlValue, getRoomValue((String) jxlValue, schoolConfigProperties));
            }
        }
        return jsonObject;
    }


    // TODO: 2018/4/23 这里提供的是  schoolConfig和 XqJxl两个参数

    public static Map<String, Map<String, String>> getRoomClassData(SchoolConfigProperties schoolConfigProperties) {
        Map<String, Map<String, String>> mapList = new TreeMap<>();
        JSONObject allRoomValue = getAllJxlRoomValue(schoolConfigProperties);
        Iterator keys = allRoomValue.keys();
        CodeUtil codeUtil = new CodeUtil();
        String yzmValue = codeUtil.getYzmValue(schoolConfigProperties);
        String cookie = codeUtil.getCookieStr();
        int num = 0;  // TODO: 2018/1/25 因为返回值变了,所以中间代码需要修改,后续
        while (keys.hasNext()) {
            Object Sel_JXL = keys.next();
            JSONObject roomValues = (JSONObject) allRoomValue.get(Sel_JXL);
            Collection values = roomValues.values();
            for (Object Sel_ROOM : values) {
//                System.out.println("key="+Sel_JXL+"value="+Sel_ROOM);
                //对于为空的是否需要继续查询
                String s;
                int i = 0;
                /*
                 *   目的是为了多次判断,防止因为网络原因导致空值
                 */
                Map<String, String> stringStringMap;
                do {
                    if (num / 5 == 1) {
//                    System.out.println(YZM_Value);
                        yzmValue = codeUtil.getYzmValue(schoolConfigProperties);
                        num = 0;
                    }
                    s = HttpUtil.postRoomValue(cookie, yzmValue, DateUtil.getTerm(), Sel_JXL.toString(), Sel_ROOM.toString(), schoolConfigProperties);
                    stringStringMap = new JsoupMethod(s).Get_Post_Data();
                    i++;
                    num++;
                } while (stringStringMap == null && i < 5);

//                System.out.println(stringStringMap);

                if (stringStringMap != null) {
                    mapList.put(Sel_ROOM.toString(), stringStringMap);
                }

            }
        }

        return mapList;
    }


    public static Map<String, Map<String, String>> getRoomClassDataByJxlRoomList(SchoolConfigProperties schoolConfigProperties, List<JxlRoom> jxlRoomList) {
        Map<String, Map<String, String>> mapList = new TreeMap<>();

        for (JxlRoom jxlRoom : jxlRoomList) {

            String s;
            int i = 0;
            /*
             *   目的是为了多次判断,防止因为网络原因导致空值
             */
            Map<String, String> stringStringMap;
            do {

                s = HttpUtil.postRoomValue(schoolConfigProperties.getCookie(), schoolConfigProperties.getYzmValue(), DateUtil.getTerm(), jxlRoom.getJxlValue(), jxlRoom.getValue(), schoolConfigProperties);
                stringStringMap = new JsoupMethod(s).Get_Post_Data();
                i++;
            } while (stringStringMap == null && i < 5);

            if (stringStringMap != null) {
                mapList.put(jxlRoom.getValue(), stringStringMap);
            }

        }
        return mapList;
    }










    public static Map<String, String> getSingleRoomClassTest(SchoolConfigProperties schoolConfigProperties) {
        CodeUtil codeUtil = new CodeUtil();
        String YZM_Value = codeUtil.getYzmValue(schoolConfigProperties);
//        System.out.println(YZM_Value);

        String cookie = codeUtil.getCookieStr();
        String term = "20171";
        String selJxl = "101";
        String selRoom = "1010101";
        String s = HttpUtil.postRoomValue(cookie, YZM_Value, term, selJxl, selRoom, schoolConfigProperties);
        // TODO: 2018/1/25 这里解析操作
        JsoupMethod jsoupMethod = new JsoupMethod(s);
        return jsoupMethod.Get_Post_Data();
    }

    private static JSONObject getRoomValue(String jxlValue, SchoolConfigProperties schoolConfigProperties) {
        String Url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolRoom() + "?w=150&id=" + jxlValue;
        String result = HttpUtil.getData(Url, schoolConfigProperties);
        JsoupMethod jsoupMethod = new JsoupMethod(result);
        JSONObject jsonObject = jsoupMethod.Get_List_Data(null, jxlValue);
        return jsonObject.getJSONObject(jxlValue);
    }

    private static JSONObject getXqJxlValue(String schoolZone, SchoolConfigProperties schoolConfigProperties) {
        String url = schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolJxl() + "?w=150&id=" + schoolZone;
        String result = HttpUtil.getData(url, schoolConfigProperties);
        JsoupMethod jsoupMethod = new JsoupMethod(result);
        JSONObject jsonObject = jsoupMethod.Get_List_Data(schoolZone, null);
        return jsonObject.getJSONObject(schoolZone);
    }
}
