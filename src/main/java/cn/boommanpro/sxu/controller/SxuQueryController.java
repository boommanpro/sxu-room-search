package cn.boommanpro.sxu.controller;

import cn.boommanpro.sxu.common.CallResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 59232
 */
@RestController
@RequestMapping("base")
public class SxuQueryController {

    //根据时间和教学楼值进行查询

    @PostMapping("query")
    public CallResult baseData(String time, String jxlValue){
        return CallResult.success();
    }
}
