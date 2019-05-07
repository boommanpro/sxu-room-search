package cn.boommanpro.sxu.crawler.dto;

import lombok.Data;
import me.ghui.fruit.annotations.Pick;

import java.util.List;

/**
 * @author BoomManPro
 * @create 2018/5/2
 */
@Data
public class XxXqPageFruit {

    @Pick(value = "[name ='Sel_XQ'] option")
    private List<XxXq> xxXqList;

    @Data
   public static class XxXq{
        @Pick(attr = "value")
       private String name;
        @Pick
       private String value;
   }
}
