package cn.boommanpro.sxu.util;


import cn.boommanpro.sxu.config.SchoolConfigProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BoomMan
 */
public class KingoSoftRequest {

    private static final int REPONSE_SUCCESS = 200;

    private final static Pattern CHARSET_PATTERN = Pattern.compile("charset=\\S*");

    /**
     * 获取学校信息,校区信息、教学楼、教室信息  工具类仅供山西大学使用 参数配置为山西大学
     *
     * @param linkUrl 需要get得到信息的url
     * @return html页面信息
     */
    public static String queryData(String linkUrl, SchoolConfigProperties schoolConfigProperties) {
        URL url;
        URLConnection urlconnection;
        try {
            url = new URL(linkUrl);
            urlconnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlconnection;
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setInstanceFollowRedirects(false);
            httpUrlConnection.setConnectTimeout(10000);
            httpUrlConnection.setRequestProperty("Host", schoolConfigProperties.getSchoolMainHost());
            httpUrlConnection.setRequestProperty("Connection", "keep-alive");
            httpUrlConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko Core/1.53.2372.400 QQBrowser/9.5.10548.400");
            httpUrlConnection.setRequestProperty("Accept",
                    "text/html, application/xhtml+xml, image/jxr, */*");
            httpUrlConnection.setRequestProperty("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.5");
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();
            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode != REPONSE_SUCCESS) {
                System.out.println(" Error===" + responseCode + "\n");
                httpUrlConnection.disconnect();
                System.out.println("连接查询系统！" + "\n");
            } else {
                InputStream is = httpUrlConnection.getInputStream();
                String charset = "gbk";

                Matcher matcher = CHARSET_PATTERN.matcher(httpUrlConnection.getContentType());
                if (matcher.find()) {
                    charset = matcher.group().replace("charset=", "");
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));

                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                return sb.toString();
            }

            httpUrlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }



    /**
     * 获取具体教室情况
     *
     * @param cookie   cookie的Value值
     * @param yzmValue yzm识别后的Value值
     * @param selXNXQ  xnxq值
     * @param selJXL   教学楼Value值
     * @param selROOM  需要查询的教室Value
     * @return 页面的信息
     */
    public static String postRoomValue(String cookie, String yzmValue, String selXNXQ, String selJXL, String selROOM, SchoolConfigProperties schoolConfigProperties) {
        URL url;
        URLConnection urlconnection;
        try {
            url = new URL(schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolRoomData());
            urlconnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlconnection;
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setInstanceFollowRedirects(false);
            String params = "Sel_XNXQ" + "=" + selXNXQ + "&" +
                    "rad_gs" + "=" + "2" + "&" +
                    "txt_yzm" + "=" + yzmValue + "&" +
                    "Sel_XQ" + "=" + "1" + "&" +
                    "Sel_JXL" + "=" + selJXL + "&" +
                    "Sel_ROOM" + "=" + selROOM;
            //学年学期  "20161"
            //表格形式 "2"
            //验证码
            //学期 "2"  //1和2貌似没有关系
            //教学楼 "202"
            //教室 "2020503"
            byte[] bypes = params.getBytes();

            httpUrlConnection.setConnectTimeout(10000);
            httpUrlConnection.setRequestProperty("Host", schoolConfigProperties.getSchoolMainHost());
            httpUrlConnection.setRequestProperty("Connection", "keep-alive");
            httpUrlConnection.setRequestProperty("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
            httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            httpUrlConnection.setRequestProperty("Content-Length", bypes.length + "");
            httpUrlConnection.setRequestProperty("Pragma", "no-cache");
            httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko Core/1.53.2372.400 QQBrowser/9.5.10548.400");
            httpUrlConnection.setRequestProperty("Referer", schoolConfigProperties.getSchoolHost() + schoolConfigProperties.getSchoolRoomDataReferer());
            httpUrlConnection.setRequestProperty("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.5");
            httpUrlConnection.setRequestProperty("Cookie", cookie);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.getOutputStream().write(bypes);
            httpUrlConnection.connect();
            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode != REPONSE_SUCCESS) {
                System.out.println(" Error===" + responseCode);
                httpUrlConnection.disconnect();
                return "教务系统出错，请过段时间尝试！";
            } else {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        httpUrlConnection.getInputStream(), "gbk"));
                String line;

                StringBuilder result = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    public static void main(String[] args) {

        String linkUrl = "http://jwc.sgmtu.edu.cn/";

        SchoolConfigProperties schoolConfigProperties = new SchoolConfigProperties();
        schoolConfigProperties.setSchoolMainHost("jwc.sgmtu.edu.cn");
        String data = queryData(linkUrl, schoolConfigProperties);
        System.out.println(data);

    }

}
