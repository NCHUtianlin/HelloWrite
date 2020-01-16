package com.tianlin.web;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tianlin.entity.Content;
import com.tianlin.service.ContentService;

/***
 * 接收页面请求
 * @author hetianlin
 * @date 2020.01.14
 * **/
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService cService;
	private static Logger logger = Logger.getLogger(ContentController.class);
	
	/***
	 * 请求存储数据
	 * **/
	@RequestMapping("/saveData")
	public void SaveData(HttpServletRequest request , HttpServletResponse response){
		logger.info("hello, I want save data");
		//接收参数
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		//校验数据
		if( userId == null || userId == "" || title == null || title == "" || detail == null || detail == "" ){
			response.setStatus(401);
			response.setHeader("Content-Type", "text/html;charset=utf-8");
			response.setHeader("errorCode", "1");
			response.setHeader("errorMsg", "数据为空");
			return ;
		}
		
		Date date = new Date();
		//Timestamp time = new Timestamp(date.getTime());
		//DateFormat dFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
		//String time = dFormat.format(date);
		//存储数据
		Content content = new Content();
		content.setUserid(Integer.parseInt(userId));
		content.setTitle(title);
		content.setDetail(detail);
		content.setWdate(date);
		content.setState(0);
		cService.insert(content);
		logger.info("存储成功");
	}
	
	/**
	 * 查询数据
	 * @throws IOException 
	 * **/
	@RequestMapping("/queryData")
	public void QueryData(HttpServletRequest request , HttpServletResponse response) throws IOException{
		logger.info("hello, I want query data");
		//接收参数
		String userId = request.getParameter("userId");
		String pageSize = request.getParameter("pageSize");
		String pageNow = request.getParameter("pageNow");
		String title = request.getParameter("title");
		String wdate = request.getParameter("wdate");
		String wdateOlder = request.getParameter("wdateOlder");
		
		if(userId == null){ //没有用户信息
			return ;//拒绝
		}
		
		Content content = new Content();
		
		try {
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			if(wdate != null){
				Date wDateF = dFormat.parse(wdate);
				content.setWdate(wDateF);
			}
			if(wdateOlder != null){
				Date wDateOlderF = dFormat.parse(wdateOlder);
				content.setWdateOlder(wDateOlderF);
			}
			
			content.setUserid(Integer.parseInt(userId));
			content.setTitle(title);
			if(pageNow != null && pageSize != null ){
				content.setPageNow(Integer.parseInt(pageNow)*Integer.parseInt(pageSize));
				content.setPageSize(Integer.parseInt(pageSize));
			}
			if(pageNow == null && pageSize != null){
				content.setPageNow( 0 );
				content.setPageSize(Integer.parseInt(pageSize));
			}
			
		} catch (Exception e) {
			logger.error("组装数据失败："+e);
			return ;
		}
		logger.info("查询条件:"+JSON.toJSONString(content));
		List<Content> list = cService.queryMore(content);
		if( list != null && list.size() > 0 ){
			logger.info("wdate="+list.get(0).getWdate());
			String json = JSON.toJSONString(list , SerializerFeature.WriteDateUseDateFormat);
			response.setHeader("Content-Type", "text/html;charset=utf-8");
			response.getWriter().print(json);
			logger.info("发送数据："+json);
		}
		
	}
	
	/**
	 * 修改数据
	 * **/
	@RequestMapping("/updateData")
	public void UpdateData(HttpServletRequest request , HttpServletResponse response){
		logger.info("hello, I want update data");
		//接收参数
		String userId = request.getParameter("userId");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		
		if(userId == null || userId == "" || id == null || id == "" ){
			return ; 
		}
		//修改数据
		try {
			Content content = new Content();
			content.setId(Long.parseLong(id));
			content.setUserid(Integer.parseInt(userId));
			content.setTitle(title);
			content.setDetail(detail);
			content.setWdate(new Date());
			
			cService.updateByPrimaryKeySelective(content);
			logger.info("已经修改完成："+JSON.toJSONString(content));
		} catch (Exception e) {
			logger.error("修改数据失败："+e);
		}
		
	}
	
	/**
	 * 删除数据
	 * **/
	@RequestMapping("/deleteData")
	public void DeleteData(HttpServletRequest request , HttpServletResponse response){
		logger.info("hello, I want delete data");
		String userId = request.getParameter("userId");
		String id = request.getParameter("id");
		
		if(userId == null || userId == "" || id == null || id == "" ){
			return ; 
		}
		//删除数据--做真删除
		Long Id = Long.parseLong(id);
		cService.deleteByPrimaryKey(Id);
		logger.info("删除数据成功：用户id "+userId+"  数据记录 "+id);
		return ;
	}

}
