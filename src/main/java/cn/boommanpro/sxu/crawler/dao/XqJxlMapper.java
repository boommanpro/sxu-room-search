package cn.boommanpro.sxu.crawler.dao;


import cn.boommanpro.sxu.crawler.model.XqJxl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XqJxlMapper {


    void insertBatch( @Param("xqJxlList") List<XqJxl> xqJxlList);




    List<String> selectAllJxlValue();

    void truncateTable();



}