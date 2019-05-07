package cn.boommanpro.sxu.crawler.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author BoomManPro
 * @create 2018/5/2
 */
@Data
public class JxlRoomVo {
    private Integer id;



    private String jxlValue;

    private String title;


    private String value;


    private LocalDateTime createDate;


    private Integer disabled;


    private Integer deleted;


    private Integer updated;


    private Integer updateTimes;


    private LocalDateTime updateDate;
}
