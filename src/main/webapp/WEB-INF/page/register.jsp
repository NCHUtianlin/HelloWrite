<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢快记事本系统用户注册页面</title>
	<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.css" />
	<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
</head>
<body>

<div style="margin:0px auto">
	<form role='form'>
		<div class='from-group input-group  input-group-lg'>
			<label for="name">姓名</label>
			<input type='text' class='form-control' id='name' placeholder='请输入您的姓名'>
		</div>
		<div class='from-group input-group  input-group-lg'>
			<label for="name">手机号码</label>
			<input type='text' class='form-control' id='phone' placeholder='请输入您的姓名'>
		</div>
		<div class='from-group input-group  input-group-lg'>
			<label for="name">密码</label>
			<input type='text' class='form-control' id='pwd' placeholder='请输入您的姓名'>
		</div>
		<div class='from-group input-group  input-group-lg'>
			<label for="name">确认密码</label>
			<input type='text' class='form-control' id='pwd_check' placeholder='请输入您的姓名'>
		</div>
		
		<button type="button" id='registerBtn' class='btn btn-primary'>提交</button>
	
	</form>
</div>

<script type="text/javascript">
$("#registerBtn").click(function(){
	var name = $("#name").val();
	var phone = $("#phone").val();
	var pwd = $("#pwd").val();
	var json = { "name":name,
	             "phone":phone,
	             "pwd":pwd
	};
	console.log("json="+json);
	$.ajax({
		type:"POST",
		url:"../user/register",
		dataType:"JSON",
		data:json,
		success:function(data){
			console.log("success"+data);
			
		},
		
		error:function(data){
			console.log("failed"+data);
		},
		complete:function(xhr,data){ //请求结束后的操作，这里是执行程度最晚的部分
		
		}
		
	});
	
});





</script>

</body>
</html>