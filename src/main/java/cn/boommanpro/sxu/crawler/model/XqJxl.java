package cn.boommanpro.sxu.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XqJxl {

    private Integer id;


    private String xxxq;


    private String name;


    private String value;


    private LocalDateTime createDate;


    private Integer disabled;


    private Integer deleted;


    private Integer updated;


    private Integer updateTimes;


    private LocalDateTime updateDate;

    private List<JxlRoom> jxlRooms;

    public XqJxl(String xxxq, String name, String value) {
        this.xxxq = xxxq;
        this.name = name;
        this.value = value;
    }
}