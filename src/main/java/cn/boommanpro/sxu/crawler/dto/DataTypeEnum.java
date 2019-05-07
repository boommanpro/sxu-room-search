package cn.boommanpro.sxu.crawler.dto;

/**
 * @author BoomManPro
 * @create 2018/4/28
 */
public enum DataTypeEnum {
    XQ("xq","校区"),JXL("jxl","教学楼"),ROOM("room","教室");

    private String key;
    private String description;
    DataTypeEnum(String key,String description) {
        this.key=key;
        this.description=description;
    }

    public String getKey() {
        return key;
    }

}
