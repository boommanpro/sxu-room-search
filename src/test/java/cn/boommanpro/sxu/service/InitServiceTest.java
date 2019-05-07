package cn.boommanpro.sxu.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitServiceTest {

    @Autowired
    private InitService initService;

    @Before
    public void setConfig(){

    }

    @Test
    public void doInit(){
        initService.initAllData();
    }
}
