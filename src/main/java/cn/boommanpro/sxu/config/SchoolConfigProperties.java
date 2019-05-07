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

    public String getYzmValue() {

        if (num / 5 == 1) {

            yzmValue = codeUtil.getYzmValue(this);
            num = 0;
        }
        num++;
        return yzmValue;
    }

    public String getCookie() {
        return codeUtil.getCookieStr();
    }

    private CodeUtil codeUtil;

    private int num=0;

    public void init(){
        codeUtil=new CodeUtil();
        codeUtil.getYzmValue(this);
        cookie=codeUtil.getCookieStr();
    }
}
