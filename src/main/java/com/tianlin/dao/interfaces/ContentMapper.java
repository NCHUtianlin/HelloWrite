package com.tianlin.dao.interfaces;

import java.util.List;

import com.tianlin.entity.Content;

public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);
    
    /**多条件查询
     * **/
    List<Content> queryMore(Content content);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}