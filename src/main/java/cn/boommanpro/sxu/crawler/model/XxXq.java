package cn.boommanpro.sxu.crawler.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "xx_xq")
public class XxXq {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "value")
    private String value;

    @TableField(value = "create_date")
    private LocalDateTime createDate;

    @TableField(value = "disabled")
    private Integer disabled;

    @TableField(value = "deleted")
    private Integer deleted;

    @TableField(value = "updated")
    private Integer updated;

    @TableField(value = "update_times")
    private Integer updateTimes;

    @TableField(value = "update_date")
    private LocalDateTime updateDate;

    public static final String COL_NAME = "name";

    public static final String COL_VALUE = "value";

    public static final String COL_CREATE_DATE = "create_date";

    public static final String COL_DISABLED = "disabled";

    public static final String COL_DELETED = "deleted";

    public static final String COL_UPDATED = "updated";

    public static final String COL_UPDATE_TIMES = "update_times";

    public static final String COL_UPDATE_DATE = "update_date";

    public XxXq(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private List<XqJxl> xqJxls;

}