package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.dto.RoomDataDto;
import cn.boommanpro.sxu.crawler.model.RoomData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomDataMapper extends BaseMapper<RoomData> {

    int insertBatchByRoomDataList( @Param("roomDataList") List<RoomData> roomDataList);

    List<RoomDataDto> listRoomDataDtoByTimeAndJxlValue( @Param("time") String time, @Param("jxlValue") String jxlValue);


}