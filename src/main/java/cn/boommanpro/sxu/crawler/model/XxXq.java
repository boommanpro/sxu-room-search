package cn.boommanpro.sxu.crawler.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class XxXq {

    private Integer id;


    private String name;


    private String value;


    private LocalDateTime createDate;


    private Integer disabled;


    private Integer deleted;


    private Integer updated;


    private Integer updateTimes;


    private LocalDateTime updateDate;

    public XxXq(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private List<XqJxl> xqJxls;

}