package cn.boommanpro.sxu.util;




import cn.boommanpro.codebreak.core.CodeBreak;
import cn.boommanpro.sxu.config.SchoolConfigProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CodeUtil {




    public String getCookieStr() {
        return cookieStr;
    }

    public void setCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
    }

    private String cookieStr ="";

    private String yzmValue;

    public String getYzmValue() {
        return yzmValue;
    }

    public String getYzmValue(SchoolConfigProperties schoolConfigProperties) {
        InputStream is=null;
        try {

            String Url= schoolConfigProperties.getSchoolHost()+ schoolConfigProperties.getSchoolValidate();

            URL url = new URL(Url);

            URLConnection urlconnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlconnection;
            httpUrlConnection.setRequestProperty("Host", "bkjw.sxu.edu.cn");
            httpUrlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
            httpUrlConnection.setRequestProperty("Connection", "keep-alive");
            httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/5.7.15319.202 Safari/537.36");
            httpUrlConnection.setRequestProperty("Referer", "http://bkjw.sxu.edu.cn/_data/login.aspx");
//            httpUrlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpUrlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            if (!this.cookieStr.equals("") ) {
                httpUrlConnection.setRequestProperty("Cookie", this.cookieStr);
            }
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();
             is = httpUrlConnection.getInputStream();

            String cookie = httpUrlConnection.getHeaderField("Set-Cookie");
            if (cookie != null) {
                cookie = cookie.substring(0, cookie.indexOf(";"));
                this.cookieStr = cookie;
            }


            String YZM= CodeBreak.codeBreak(is);
            ((HttpURLConnection) urlconnection).disconnect();
            //返回json,验证码的值和cookie值
            yzmValue=YZM;
            return YZM;
        } catch (IOException e) {
            System.out.println("获取验证码出现异常");
//            e.printStackTrace();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return null;
    }
}