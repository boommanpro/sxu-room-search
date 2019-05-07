package cn.boommanpro.sxu.crawler.service;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import org.junit.Before;
import org.junit.Test;


public class QuerySchoolDataUtilTest {


    private SchoolConfigProperties schoolConfigProperties;

    @Before
    public void beforeSet(){

    }



    /**
     * 获取校区教学楼信息
     */
    @Test
    public void getXxXqValue() {
        QuerySchoolDataUtil.getXxXqValue(schoolConfigProperties);
    }

    @Test
    public void getXnxqValue() {
    }

    @Test
    public void getAllXqJxlValue() {
    }

    @Test
    public void getAllJxlRoomValue() {
    }

    @Test
    public void getRoomClassData() {
    }

    @Test
    public void getRoomClassDataByJxlRoomList() {
    }

    @Test
    public void getSingleRoomClassTest() {
    }
}