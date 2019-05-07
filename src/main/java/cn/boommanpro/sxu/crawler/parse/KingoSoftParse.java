package cn.boommanpro.sxu.crawler.parse;

import cn.boommanpro.sxu.common.StringUtils;
import cn.boommanpro.sxu.crawler.dto.XxXqPageFruit;
import cn.boommanpro.sxu.crawler.model.ListWeekStruct;
import cn.boommanpro.sxu.model.ClassRoomPage;
import cn.boommanpro.sxu.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import me.ghui.fruit.Fruit;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BoomMan
 */
@Log4j2
public class KingoSoftParse {

    /**
     * @return 获得学年学期和校区
     */
    public static Map<String, String> getXxxq(String content) {

        XxXqPageFruit xxXqPageFruit = new Fruit().fromHtml(content, XxXqPageFruit.class);
        Map<String, String> xxxqMap = new HashMap<>();
        for (XxXqPageFruit.XxXq xxXq : xxXqPageFruit.getXxXqList()) {
            if (StringUtils.notBlank(xxXq.getName(), xxXq.getValue())) {
                xxxqMap.put(xxXq.getValue(), xxXq.getName());
            }
        }
        return xxxqMap;
    }

    /**
     * @return 获得学年学期和校区
     */
    public static String getTopSemesterYear(String content) {

        Document document = Jsoup.parse(content);
        Elements links = document.select("option");
        String data;
        for (Element link : links) {
            data = link.text();
            if (data.contains("学年")) {
                return link.text();
            }
        }
        return "暂时获取不到任何数据";
    }

    public static Map<String, String> getListData(String content) {
        //1.Jsoup解析为Document
        Document document = Jsoup.parse(content);

        Map<String, String> listData = new LinkedHashMap<>();
        Elements links = document.select("script");
        String value;
        String data = links.toString();
        data = data.substring(data.indexOf("<option"), data.indexOf("</select>"));
        document = Jsoup.parse(data);
        links = document.select("option");
        for (Element link : links) {
            data = link.text();
            value = link.attr("value");
            if (StringUtils.notBlank(data) && !data.contains("无信息")) {
                listData.put(data, value);
            }
        }

        return listData;
    }

    public static Map<String, String> getPostData(String content) {

        // TODO: 2018/5/2 课表清空可能不一样
        Document document = Jsoup.parse(content);

        Elements links = document.select("table");

        ListWeekStruct weekStruct = new ListWeekStruct();
        List<ListWeekStruct.Week> listWeek = weekStruct.Get_ListWeek();

        /*
         * Size==1 无课表 无活动安排 Size==3 只有活动安排 Size==4 只有课表 Size==6 两者都有
         *
         */
        ClassRoomPage classRoomPage = new Fruit().fromHtml(content, ClassRoomPage.class);

        if (classRoomPage.getTable() != null) {
            int tableSize = classRoomPage.table.size();
            for (int i = 0; i < tableSize; i++) {
                ClassRoomPage.Table table = classRoomPage.table.get(i);
                int columnNumber = table.getColumnNumber();
                switch (columnNumber) {
                    case 1:
                        break;
                    case 13:
                        getWeekData(i, 11, links, listWeek);
                        break;
                    case 6:
                        getWeekData(i, 0, links, listWeek);
                        break;
                    default:
                        if (i != 0) {
                            log.info(columnNumber);
                            log.info("需要查看是否需要重新设计");
                            log.info(content);
                        }
                        break;
                }
            }
        }
        return getListWeekData(listWeek);

    }

    private static Map<String, String> getListWeekData(List<ListWeekStruct.Week> listWeek) {
        //不需要CourseName和TeacherName
        Map<String, String> treeMap = null;
        for (int m = 0; m < listWeek.size(); m++) {
            for (int i = 1; i <= 7; i++) {

                //计算时间操作
                JSONObject contentJson = new JSONObject();
                boolean flag = false;
                String date = null;
                for (int j = 1; j <= 10; j++) {
                    if (listWeek.get(m).GetDay(i).GetCourse(j).isStatue()) {

                        log.trace("第{},周\t星期{}\t第{},节 课程情况\n课程名称{}老师:{}", (m + 1), i, j, listWeek.get(m).GetDay(i).GetCourse(j).getCourse(), listWeek.get(m).GetDay(i).GetCourse(j).getTeacher());
                        flag = true;
                        date = DateUtil.weekNumberToDateStr(m + 1, i);
                        //contentJson.put(j, new DayCourse(ListWeek.get(m).GetDay(i).GetCourse(j).getCourse(), ListWeek.get(m).GetDay(i).GetCourse(j).getTeacher()));
                        JSONObject courseTeacher = new JSONObject();
                        courseTeacher.put(listWeek.get(m).GetDay(i).GetCourse(j).getCourse(), listWeek.get(m).GetDay(i).GetCourse(j).getTeacher());
                        contentJson.put(j, courseTeacher);

                    }
                }
                if (flag) {
                    if (treeMap == null) {
                        treeMap = new TreeMap<>();
                    }
                    treeMap.put(date, contentJson.toString());
                }

            }
        }
        return treeMap;
    }

    private static void getWeekData(int n, int m, Elements links, List<ListWeekStruct.Week> listWeek) {
        // 创建存储信息的List
        Elements classTr;
        Elements classTd;
        String weekTime;
        String weekPart;
        String course = null;
        String teacher = null;
        classTr = links.get(n).select("tr");
        for (int i = 1; i < classTr.size(); i++) {
            // TODO: 2018/4/17 获取部分非动态 写死的不符合很多学校
            classTd = classTr.get(i).select("td");
            weekTime = classTd.get(m).text();
            weekPart = classTd.get(m + 1).text();
            if (m == 0) {
                if (StringUtils.notBlank(classTd.get(2).text())) {
                    course = classTd.get(2).text();
                    teacher = classTd.get(3).text();
                }
            } else if (m == 11) {
                if (StringUtils.notBlank(classTd.get(0).text())) {
                    course = classTd.get(0).text();
                    teacher = classTd.get(7).text();
                }
            }
            log.trace("Course:{},Teacher:{},WeekTime:{},WeekPart:{}", course, teacher, weekTime, weekPart);
            setDataWeekList(getWeekTime(weekTime), getWeekPart(weekPart), course, teacher, listWeek);
        }

    }

    private static void setDataWeekList(List<Integer> timeList, List<String> partList, String course, String teacher, List<ListWeekStruct.Week> listWeek) {
        boolean[] time = timeListToArrayInt(timeList, partList);


        for (int i = 1; i < listWeek.size() + 1; i++) {
            if (time[i]) {
                listWeek.get(i - 1).SetData(partList.get(0), partList.get(1), course, teacher);
                if (StringUtils.notBlank(partList.get(2))) {
                    listWeek.get(i - 1).SetData(partList.get(0), partList.get(2), course, teacher);
                }
            }
        }

    }


    private static List<Integer> getWeekTime(String weekTime) {
        /*
         * 14-14.---最多5对 1-4,6-13 分为first and sencond second not belive ，not add
         * and stop
         *  WeekTime="1-4,6-13,1-4,6-13,1-4,6-13";
         */


        List<Integer> timeList = new ArrayList<>();

        String regex = "(?<first1>\\d{1,2})\\-{0,1}(?<sencond1>\\d{0,2}),{0,1}(?<first2>\\d{0,2})\\-{0,1}(?<sencond2>\\d{0,2}),{0,1}(?<first3>\\d{0,2})\\-{0,1}(?<sencond3>\\d{0,2}),{0,1}(?<first4>\\d{0,2})\\-{0,1}(?<sencond4>\\d{0,2}),{0,1}(?<first5>\\d{0,2})\\-{0,1}(?<sencond5>\\d{0,2}),{0,1}(?<first6>\\d{0,2})\\-{0,1}(?<sencond6>\\d{0,2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(weekTime);
        matcher.find();
        boolean addStatue = true;
        String first = null;
        String second = null;
        for (int i = 1; i <= 6 && addStatue; i++) {
            first = matcher.group("first" + i);
            second = matcher.group("sencond" + i);
            if (!first.equals("")) {
                timeList.add(Integer.parseInt(first));
                if (!second.equals("")) {
                    timeList.add(Integer.parseInt(second));
                } else {
                    timeList.add(0);
                }
            } else {
                addStatue = false;
            }
        }
        log.trace("timeList:{}", timeList);

        return timeList;
    }

    private static boolean[] timeListToArrayInt(List<Integer> timeList, List<String> partList) {
        boolean[] time = new boolean[30];
        int partSize = partList.size();

        int first = 0;
        int second = 0;
        int timeSize = timeList.size();
        String single = null;
        if (partSize != 3) {
            for (int n = 0; n < timeSize; n += 2) {
                first = timeList.get(n);
                second = timeList.get(n + 1);

                if (second != 0) {
                    for (int i = first; i <= second; i++) {
                        time[i] = true;
                    }
                } else {
                    time[first] = true;
                }
            }
        } else {
            single = partList.get(2);
            for (int n = 0; n < timeSize; n += 2) {
                first = timeList.get(n);
                second = timeList.get(n + 1);
                if (second != 0) {
                    for (int i = first; i <= second; i++) {
                        if (isSingle(i, single) != 0) {
                            time[i] = true;
                        }
                    }
                } else {
                    time[first] = true;
                }
            }
        }
        if (log.isTraceEnabled()) {
            for (int i = 1; i < time.length; i++) {
                if (time[i]) {
                    System.out.println("This is" + i + "周" + "有课");
                } else {
                    System.out.println("This is" + i + "周" + "无课");
                }
            }
        }

        return time;
    }

    private static List<String> getWeekPart(String weekPart) {
        // WeekPart 五[1-2节]单
        log.trace("weekPart:{}", weekPart);
        List<String> partList = new ArrayList<>();
        /*
         * PartList if have three now this is juidge is Single if 1-2返回一 if3-4
         * 返回二 if 5-6 返回三 以此类推
         */
        String weekDay = null;
        String partNumFirst = null;
        String partNumSecond = null;
        String single = null;
        String regex = "(?<WeekDay>[\u4e00-\u9fa5])" + "\\[" + "(?<PartNumfirst>\\d{0,2})"
                + "\\-{0,1}(?<PartNumsecond>\\d{0,2})" + "[\u4e00-\u9fa5]\\]{0,1}" + "(?<Single>[\u4e00-\u9fa5]{0,1})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(weekPart);
        matcher.find();
        weekDay = matcher.group("WeekDay");
        partNumFirst = matcher.group("PartNumfirst");
        partNumSecond = matcher.group("PartNumsecond");
        single = matcher.group("Single");

        partList.add(weekDay);
        partList.add(partNumFirst);
        partList.add(partNumSecond);
        partList.add(single);
        log.trace("PartList:{}", partList);
        return partList;

    }

    private static int isSingle(int weekTime, String single) {
        switch (single) {
            case "单":
                if (weekTime % 2 == 1) {
                    return weekTime;
                } else {
                    return 0;
                }
            case "双":
                if (weekTime % 2 == 0) {
                    return weekTime;
                } else {
                    return 0;
                }
            default:
                return 0;
        }

    }
}
