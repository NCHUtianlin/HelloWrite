package com.tianlin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianlin.dao.interfaces.ContentMapper;
import com.tianlin.entity.Content;

/***
 * 处理业务
 * @author hetianlin
 * @date 2020.01.14
 * **/
@Service
public class ContentService {

	@Autowired
	private ContentMapper cMapper;
	
	/**多条件查询
     * **/
    public List<Content> queryMore(Content content){
    	return cMapper.queryMore(content);
    }
	
	public int deleteByPrimaryKey(Integer id){
		
		return cMapper.deleteByPrimaryKey(id);
	}

	public int insert(Content record){
		return cMapper.insert(record);
	}

	public int insertSelective(Content record){
		return cMapper.insertSelective(record);
	}

	public Content selectByPrimaryKey(Integer id){
		return cMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Content record){
		return cMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Content record){
		return cMapper.updateByPrimaryKey(record);
	}
	
}
