package cn.boommanpro.sxu.service;

import cn.boommanpro.sxu.crawler.dao.RoomDataMapper;
import cn.boommanpro.sxu.crawler.dto.RoomDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomDataService {
    @Autowired
    private RoomDataMapper roomDataMapper;

    public List<RoomDataDto> query(String time, String jxlValue){
       return roomDataMapper.listRoomDataDtoByTimeAndJxlValue(time, jxlValue);
    }
}
