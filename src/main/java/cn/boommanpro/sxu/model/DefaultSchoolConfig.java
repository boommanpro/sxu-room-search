package cn.boommanpro.sxu.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultSchoolConfig {
    public static final String ValidateCode = "sys/ValidateCode.aspx";

    /**
     * 教学楼列表获取
     */
    public static final String List_JXL = "ZNPK/Private/List_JXL.aspx";
    /**
     * 教室列表获取
     */
    public static final String List_Room = "ZNPK/Private/List_ROOM.aspx";

    /**
     * 获取学年学期 学校校区 信息
     */
    public static final String XNXQ_XQ = "ZNPK/KBFB_RoomSel.aspx";


    public static final String RoomData = "ZNPK/KBFB_RoomSel_rpt.aspx";

    public static final String RoomDataReferer = "ZNPK/KBFB_RoomSel.aspx";

    public static String host2MainHost(String host){

        String rege="(http|https):\\/\\/(?<MainHost>[\\w\\-_]+(\\.[\\w\\-_]+)+(:[0-9]{1,4})?)/*";

        Pattern p = Pattern.compile(rege);
        Matcher m = p.matcher(host);
        if (m.find()){
            return m.group("MainHost");
        }
        return null;
    }

    public static void main(String[] args) {
        String host="http://www.boomman.qq.com:8080/";
        System.out.println(host2MainHost(host));
    }
}
