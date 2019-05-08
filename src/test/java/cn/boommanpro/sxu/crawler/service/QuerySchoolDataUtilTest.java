package cn.boommanpro.sxu.crawler.service;

import cn.boommanpro.sxu.config.SchoolConfigProperties;
import cn.boommanpro.sxu.model.SchoolDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class QuerySchoolDataUtilTest {


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
        schoolConfigProperties.setSchoolXnxq(SchoolDetails.XNXQ_XQ);
        schoolConfigProperties.setSchoolMainHost(SchoolDetails.host2MainHost(schoolConfigProperties.getSchoolHost()));
        schoolConfigProperties.setSchoolValidate(SchoolDetails.ValidateCode);
        schoolConfigProperties.setSchoolJxl(SchoolDetails.List_JXL);
        schoolConfigProperties.setSchoolRoom(SchoolDetails.List_Room);
        schoolConfigProperties.setSchoolXnxq(SchoolDetails.XNXQ_XQ);
        schoolConfigProperties.setSchoolRoomData(SchoolDetails.RoomData);
        schoolConfigProperties.setSchoolRoomDataReferer(SchoolDetails.RoomDataReferer);
    }


    /**
     * 永远会获取最新的值
     */
    @Test
    public void getTopSemesterYear() {
        String semesterYearMap = QuerySchoolDataUtil.getTopSemesterYearDescription(schoolConfigProperties);
        log.info("最新数据为:{}! 如果其中没有您想要的学年学期信息，可能是教务系统还未更新，请耐心等待!", semesterYearMap);
    }

    /**
     * 获取校区教学楼信息
     *
     * example: {弦歌大道校区=1, 文明大道校区=2}
     */
    @Test
    public void getXxXqValue() {
        Map<String, String> xxXqValue = QuerySchoolDataUtil.getXxXqValue(schoolConfigProperties);
        log.info("data:{}", xxXqValue);
    }


    /**
     * 校区教学楼信息
     */

    @Test
    public void getAllXqJxlValue() {
        Map<String, Map<String, String>> allXqJxlValue = QuerySchoolDataUtil.getAllXqJxlValue(schoolConfigProperties);
        log.info("data:{}", allXqJxlValue);
    }



    @Test
    public void getXqJxlValue() {
        Map<String, String> xqJxlValue = QuerySchoolDataUtil.getXqJxlValue("1", schoolConfigProperties);
        log.info("data:{}", xqJxlValue);
    }


    /**
     * 教学楼对应Room信息
     */

    @Test
    public void getAllJxlRoomValue() {
        Map<String, Map<String, String>> allJxlRoomValue = QuerySchoolDataUtil.getAllJxlRoomValue(schoolConfigProperties);
        log.info("data:{}", allJxlRoomValue);
    }

    @Test
    public void getRoomClassData() {
        Map<String, Map<String, String>> roomClassData = QuerySchoolDataUtil.getRoomClassData(schoolConfigProperties);
        log.info("data:{}", roomClassData);
    }

    @Test
    public void getRoomClassDataByJxlRoomList() {
    }

    @Test
    public void getSingleRoomClassTest() {
    }


}