package cn.boommanpro.sxu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolConfig {
    private Integer schoolNo;

    private String schoolName;

    private String tableName;

    private Integer initStatus;

    private Integer enable;

    private Integer deleted;

    private Integer updateStatus;

    private String hrefSuffix;

    private String schoolMainHost;

    private String schoolHost;

    private String schoolValidate;

    private String schoolJxl;

    private String schoolRoom;

    private String schoolXnxq;

    private String schoolRoomData;

    private String schoolRoomDataReferer;

    private LocalDateTime startTime;
}