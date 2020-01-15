package com.tianlin.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.tianlin.dao.interfaces.UserMapper;
import com.tianlin.entity.User;

/***
 * 处理用户信息
 * @author hetianlin
 * @date 2020.01.14
 * **/
@Service
public class UserService {

	@Resource
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**注销用户
	 * **/
	public int deleteByPrimaryKey(Integer id){
		
		return userMapper.deleteByPrimaryKey(id);
	}

	/**添加用户
	 * */
	public int insert(User record){
		
		return userMapper.insert(record);
	}

	/**根据主键查询用户
	 * **/
	public User selectByPrimaryKey(Integer id){
		
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据条件查询用户
	 * **/
	public List<User> selectAll(User user){
		return userMapper.selectAll(user);
	}

	/**更新用户信息
	 * **/
	public int updateByPrimaryKeySelective(User record){
		
		return userMapper.updateByPrimaryKeySelective(record);
	}

	
	
}
