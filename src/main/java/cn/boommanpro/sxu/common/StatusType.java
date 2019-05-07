package cn.boommanpro.sxu.common;

/**
 * @author BoomManPro
 * @create 2018/4/24
 */
public  enum StatusType {
    ENABLE(1,"启用"),DISABLE(0,"禁用");

    private int key;
    private String description;

    StatusType(int key, String description) {
        this.key = key;
        this.description = description;
    }

    public int getKey() {
        return key;
    }
}
