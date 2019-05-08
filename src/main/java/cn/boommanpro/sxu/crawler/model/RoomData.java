package cn.boommanpro.sxu.crawler.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "room_data")
public class RoomData {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "time")
    private String time;

    @TableField(value = "classroom_value")
    private String classroomValue;

    @TableField(value = "json_value")
    private String jsonValue;

    public static final String COL_TIME = "time";

    public static final String COL_CLASSROOM_VALUE = "classroom_value";

    public static final String COL_JSON_VALUE = "json_value";


    public RoomData(String time, String classroomValue, String jsonValue) {
        this.time = time;
        this.classroomValue = classroomValue;
        this.jsonValue = jsonValue;
    }
}