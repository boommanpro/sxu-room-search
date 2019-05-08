package cn.boommanpro.sxu.config;

import cn.boommanpro.sxu.util.CodeUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "inno.permission")
public class SchoolConfigProperties {
    private String schoolMainHost;

    private String schoolHost;

    private String schoolValidate;

    private String schoolJxl;

    private String schoolRoom;

    private String schoolXnxq;

    private String schoolRoomData;

    private String schoolRoomDataReferer;


    private String cookie;

    private String yzmValue;

    /**
     * 格式 format: 2018-03-05
     */
    private String startTime;

    public String getYzmValue() {

        if (num / 5 == 1) {

            yzmValue = codeUtil.getYzmValue(this);
            num = 0;
        }
        num++;
        return yzmValue;
    }

    public String getCookie() {
        if (codeUtil == null) {
            codeUtil=new CodeUtil();
            codeUtil.getYzmValue(this);
            cookie=codeUtil.getCookieStr();
        }
        return codeUtil.getCookieStr();
    }

    private CodeUtil codeUtil;

    private int num=0;



}
