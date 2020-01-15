package com.tianlin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.tianlin.entity.User;
import com.tianlin.service.UserService;

/***
 * 处理用户注册、注销、修改
 * @author hetianlin
 * @date 2020.01.14
 * **/
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 注册用户
	 * **/
	@RequestMapping("/register")
	public void Register(HttpServletRequest request , HttpServletResponse response){
		System.out.println("register");
		User user = new User();
		//接收参数
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		//校验参数
		if( name == null || name == "" || phone == null || phone == "" || pwd == null || pwd == "" ){
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", "1");
			response.setHeader("errorMsg", "数据为空");
			return ;
		}
		
		//校验手机号码是否已注册
		user.setPhone(phone);
		List<User> list = userService.selectAll(user);
		if(list.size() > 0){
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", "2");
			response.setHeader("errorMsg", "该手机号码已注册");
			return ;
		}
		
		user.setName(name);
		user.setPwd(pwd);
		//存储数据
		userService.insert(user);
		System.out.println("用户注册成功");
		
	}
	
	/**
	 * 注销用户
	 * 用户状态改为 -1
	 * **/
	@RequestMapping("/delete")
	public void Delete(HttpServletRequest request , HttpServletResponse response){
		System.out.println("delete");
		//接收参数
		String userId = request.getParameter("userId");
		//校验Id
		if( userId == null || userId == "" ){
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", "1");
			response.setHeader("errorMsg", "没有id");
			return ;
		}
		else if( !userId.matches("^[0,9]*$") ){
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", "2");
			response.setHeader("errorMsg", "错误id");
			return ;
		}
		//将该用户状态修改为注销  state = -1
		User user = new User();
		user.setId(Integer.parseInt(userId));
		user.setState(-1);
		userService.updateByPrimaryKeySelective(user);
		System.out.println("修改完成");
	}
	
	/**
	 * 通过主键查询用户
	 * @throws IOException 
	 * **/
	@RequestMapping("/selectByID")
	public void SelectByID(HttpServletRequest request , HttpServletResponse response) throws IOException{
		System.out.println("selectByID");
		//接收参数
		String userId = request.getParameter("userId");
		//校验Id
		if( userId == null || userId == "" ){
			return ;
		}
		else if( !userId.matches("^[0,9]*$") ){
			return;
		}
		//查询
		int id = Integer.parseInt(userId);
		User user = userService.selectByPrimaryKey(id);
		if( user == null ){
			return;
		}
		//发送JSON数据
		response.getWriter().print(JSON.toJSONString(user));
		
	}
	
	
	/**
	 * 修改用户
	 * **/
	@RequestMapping("/update")
	public void Update(HttpServletRequest request , HttpServletResponse response){
		System.out.println("update");
		User user = new User();
		//接收参数
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		//校验参数
		if( name == null || name == "" || phone == null || phone == "" || pwd == null || pwd == "" ){
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", "1");
			response.setHeader("errorMsg", "数据为空");
			return ;
		}
		
		//校验手机号码是否已注册
		user.setPhone(phone);
		List<User> list = userService.selectAll(user);
		if(list.size() > 0){
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", "2");
			response.setHeader("errorMsg", "该手机号码已注册");
			return ;
		}
		
		user.setName(name);
		user.setPwd(pwd);
		//修改数据
		userService.updateByPrimaryKeySelective(user);
		System.out.println("修改完成");
	}
	
	
	
	
}
