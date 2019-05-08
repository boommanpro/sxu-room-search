package cn.boommanpro.sxu.service;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.model.DefaultSchoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitServiceTest {


    private SchoolConfigProperties schoolConfigProperties;

    /**
     * 因为山西大学的教务系统需要内网
     * <p>
     * 遂使用安阳师范学院测试
     * <p>
     * http://jwglxt.aynu.edu.cn/
     */
    @Before
    public void beforeSet() {
        schoolConfigProperties = new SchoolConfigProperties();
        schoolConfigProperties.setSchoolHost("http://jwglxt.aynu.edu.cn/");
        schoolConfigProperties.setSchoolXnxq(DefaultSchoolConfig.XNXQ_XQ);
        schoolConfigProperties.setSchoolMainHost(DefaultSchoolConfig.host2MainHost(schoolConfigProperties.getSchoolHost()));
        schoolConfigProperties.setSchoolValidate(DefaultSchoolConfig.ValidateCode);
        schoolConfigProperties.setSchoolJxl(DefaultSchoolConfig.List_JXL);
        schoolConfigProperties.setSchoolRoom(DefaultSchoolConfig.List_Room);
        schoolConfigProperties.setSchoolXnxq(DefaultSchoolConfig.XNXQ_XQ);
        schoolConfigProperties.setSchoolRoomData(DefaultSchoolConfig.RoomData);
        schoolConfigProperties.setSchoolRoomDataReferer(DefaultSchoolConfig.RoomDataReferer);
        schoolConfigProperties.setStartTime("2019-03-05");
    }

    @Autowired
    private InitService initService;

    @Before
    public void setConfig(){

    }

    @Test
    public void doInit(){
        initService.initAllData(schoolConfigProperties);
    }

    @Test
    public void update2NowData() {
        initService.update2NowData(schoolConfigProperties);
    }
}
