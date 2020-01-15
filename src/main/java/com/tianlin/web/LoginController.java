package com.tianlin.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.tianlin.entity.User;
import com.tianlin.service.UserService;

/***
 * 接收登陆请求和打开用户注册页面
 * @author hetianlin
 * @date 2020.01.14
 * **/
@Controller
@RequestMapping("/htl")
public class LoginController {
	
	@Autowired
	private UserService userService;
	private static Map<String, Object> CheckCodeMap;
	
	public LoginController() {
		CheckCodeMap = new HashMap<String,Object>();
	}

	/**
	 * 处理登陆验证
	 * @throws IOException 
	 * **/
	@RequestMapping("/login")
	public void doLogin(HttpServletRequest request , HttpServletResponse response) throws IOException{
		
		//接收参数
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		//校验参数
		if( phone == null || phone == "" || pwd == null || pwd == "" || !phone.matches("^\\d{11}$") ){
			response.setStatus(403);
			response.setCharacterEncoding("utf-8");
			response.setHeader("errorCode", "1");
			response.setHeader("errorMsg", "账号密码错误");
			return ;
		}
		else{
			User user = new User();
			user.setPhone(phone);
			user.setPwd(pwd);
			user.setState(0);
			List<User> list = userService.selectAll(user);
			if( list != null && list.size() > 0 ){  //验证通过
				//生成随机字符串充当校验码,登陆主页面时进行校验
				String checkCode = RandomStringUtils.randomAlphabetic(32);
				User p_user = list.get(0);
				//清除原来的或者未清除的记录
				CheckCodeMap.remove(p_user.getId());
				p_user.setCheckCode(checkCode);
				CheckCodeMap.put(""+p_user.getId(), p_user);
				//组装数据
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("checkCode", checkCode);
				jsonObject.put("userId", ""+p_user.getId());
				//发送
				response.getWriter().print(jsonObject);
				return;
			}else{
				response.setStatus(403);
				response.setCharacterEncoding("utf-8");
				response.setHeader("errorCode", "1");
				response.setHeader("errorMsg", "账号密码错误");
				return ;
			}
		}
		
		
	}
	
	/**
	 * 打开注册页面
	 * */
	@RequestMapping("/goRegister")
	public ModelAndView goRegister(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mView = new ModelAndView();
		mView.setViewName("/register");
		return mView;
	}
	
	/**
	 * 进入主页面
	 * **/
	@RequestMapping("/main")
	public ModelAndView mainPage(HttpServletRequest request , HttpServletResponse response){
		System.out.println("main");
		ModelAndView mView = new ModelAndView();
		//接收校验码
		String check = request.getParameter("checkCode");
		//获取id 
		String userId = request.getParameter("userId");
		//验证
		User user = (User) CheckCodeMap.get(userId);
		if(user == null){
			return mView;
		}
		String checkCode = user.getCheckCode();
		if(checkCode != null && checkCode.equals(check) ){
			//通过
			mView.addObject("userId", userId);
			mView.addObject("name", user.getName());
			mView.setViewName("mainPage");
		}
		
		return mView;
	}
	
	/*public static void main(String[] agrs){
		String checkCode = RandomStringUtils.randomAlphabetic(32);
		System.out.println(checkCode);
		checkCode = RandomStringUtils.randomAlphabetic(32);
		System.out.println(checkCode);
		checkCode = RandomStringUtils.randomAlphabetic(32);
		System.out.println(checkCode);
		String phone = "12345678912";
		Long p = Long.parseLong(phone);
		System.out.println(p);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		System.out.println("time="+time);
		
	}*/
	
	

}
