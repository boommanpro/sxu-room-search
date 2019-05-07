package cn.boommanpro.sxu.util;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    @Getter
    @Setter
    private static String term;

    @Getter
    @Setter
    private static String startTime;

    /**
     * 设置日期格式
     */
    private static SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd");



    public static String getNowTime() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    public String afterTime(String Time, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(Time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime().toString();
    }



    public static String weekNumberToDateStr(int weekNumber, int week) {

        /*
         *
         * 将周次换成时间
         *
         */
        int sum = (weekNumber - 1) * 7 + week - 1;

        Calendar newCalendar = new GregorianCalendar();

        Date date = null;


        try {

            date = format.parse(startTime);

        } catch (ParseException e) {

            e.printStackTrace();
        }
        newCalendar.setTime(Objects.requireNonNull(date));


        newCalendar.add(Calendar.DATE, sum);

        date = newCalendar.getTime();


        return format.format(date);


    }


    public String DatetoString(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);

    }

    public String GetStartDate(String dateStr) {
        /*
         * 用正则表达式取出第五位数字
         */

        String half = null;


        String regex = "\\d{4}(?<Half>\\d)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(dateStr);

        matcher.find();

        half = matcher.group("Half");

        return half;

    }

    public List<Integer> DateToWeekList(String endTime) {
        /*
         *以前用到的工具
         *计算两个Date时间差
         */
        Integer Week_Number = null;
        Integer Week = null;
        List<Integer> list = new ArrayList<>();

        // TODO: 2018/1/24 获取时间
//        String startTime = ClassStart.secondhalf.getStart() + " 0:0:0";

        String dateStr2 = endTime + " 0:0:0";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date2 = null, date = null;
        try {
            date2 = format.parse(dateStr2);

            date = format.parse(startTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//            System.out.println("两个日期的差距：" + differentDays(date,date2));

        /*
         * 开始 2017-2-20日为第一周
         * 结束 2017-
         * 时间转换成周次和星期几
         */

        int DifferentDay = differentDays(date, date2);

        if (date2.after(date) || DifferentDay == 0) {
//			System.out.println(DifferentDay);
            if (DifferentDay >= 0) {
                Week_Number = DifferentDay / 7 + 1;
                Week = DifferentDay % 7 + 1;
                list.add(Week_Number);
                list.add(Week);
            }
        }
        return list;


    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
//            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2 - day1;
        }
    }
}
