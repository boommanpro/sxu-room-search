package cn.boommanpro.sxu.service;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.crawler.service.InitSchoolDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {


    @Autowired
    private InitSchoolDataService initSchoolDataService;

    public void initAllData(SchoolConfigProperties schoolConfigProperties){
        initSchoolDataService.initXxXq(schoolConfigProperties);
        initSchoolDataService.initXqJxl(schoolConfigProperties);
        initSchoolDataService.initJxlRoom(schoolConfigProperties);
        initSchoolDataService.initRoomData(schoolConfigProperties);

    }

    public void update2NowData(SchoolConfigProperties schoolConfigProperties){
        initSchoolDataService.initJxlRoomStatus();
        initSchoolDataService.initRoomData(schoolConfigProperties);
    }

}
