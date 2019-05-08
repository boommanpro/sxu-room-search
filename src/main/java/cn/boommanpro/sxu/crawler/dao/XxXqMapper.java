package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.model.XxXq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface XxXqMapper extends BaseMapper<XxXq> {
    void insertBatch(@Param("xxXqList") List<XxXq> xxXq);

    List<String> selectAllXqValue();

    void truncateTable();





}