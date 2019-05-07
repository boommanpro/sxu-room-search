package cn.boommanpro.sxu.crawler.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author BoomManPro
 * @create 2018/4/28
 */
@Data
public class SchoolDetailTreeVo {

    private String title;

    private String value;

    private boolean expand;

    private String type;

    private List<SchoolDetailTreeVo> children;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
