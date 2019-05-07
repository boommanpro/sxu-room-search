package cn.boommanpro.sxu.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomData {
    private Integer id;

    private String time;

    private String classroomValue;

    private String jsonValue;


    public RoomData(String time, String classroomValue, String jsonValue) {
        this.time = time;
        this.classroomValue = classroomValue;
        this.jsonValue = jsonValue;
    }
}