package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.model.RoomData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomDataMapper {

    int insertBatchByRoomDataList( @Param("roomDataList") List<RoomData> roomDataList);

}