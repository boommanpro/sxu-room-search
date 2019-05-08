package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.model.XqJxl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XqJxlMapper extends BaseMapper<XqJxl> {


    void insertBatch( @Param("xqJxlList") List<XqJxl> xqJxlList);




    List<String> selectAllJxlValue();

    void truncateTable();



}