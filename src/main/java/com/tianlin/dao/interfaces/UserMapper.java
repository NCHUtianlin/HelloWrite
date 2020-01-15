package com.tianlin.dao.interfaces;

import java.util.List;

import com.tianlin.entity.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    List<User> selectAll(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}