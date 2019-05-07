package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.model.JxlRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxlRoomMapper {

    void insertBatch( @Param("jxlRoomList") List<JxlRoom> jxlRoomList);


    List<JxlRoom> selectByUpdatedAndDisabled( @Param("updated") int updated, @Param("disabled") int disabled);


    int setDisabledBatch( @Param("jxlRoomIdList") List<Integer> jxlRoomIdList);

    void truncateTable();


}