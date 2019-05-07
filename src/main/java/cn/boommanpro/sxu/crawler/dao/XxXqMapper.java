package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.model.XxXq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface XxXqMapper {

    void insertBatch(@Param("xxXqList") List<XxXq> xxXq);

    List<String> selectAllXqValue();

    void truncateTable();





}