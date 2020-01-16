<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢快记事本系统用户注册页面</title>
	<link rel="stylesheet" href="./plugins/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="./plugins/layui2.5.5/css/layui.css" />
	<script type="text/javascript" src="./js/jquery/jquery.min.js"></script>
</head>
<body>
<div style="margin:5% 0 0 35%">
	<form class="layui-form" method='POST' >
		<div class='from-group input-group  input-group-lg'>
			<label for="name">姓名</label>
			<input type='text' class='form-control' id='name' name='name' lay-verify="required|name" placeholder='请输入您的姓名'>
		</div>
		<br/>
		<div class='from-group input-group  input-group-lg'>
			<label for="phone">手机号码</label>
			<input type='text' class='form-control' id='phone' name='phone' lay-verify="required|phone" placeholder='请输入您的姓名'>
		</div>
		<br/>
		<div class='from-group input-group  input-group-lg'>
			<label for="pwd">密码</label>
			<input type='password' class='form-control' id='pwd' name='pwd' lay-verify="required|pwd" placeholder='请输入您的姓名'>
		</div>
		<br/>
		<div class='from-group input-group  input-group-lg'>
			<label for="pwd_check">确认密码</label>
			<input type='password' class='form-control' id='pwd_check' lay-verify="required|pwd_check" placeholder='请输入您的姓名'>
		</div>
		<br/>
		<button type="button" lay-submit="" lay-filter="demo1" id='registerBtn' class='btn btn-primary'>提交</button>
	
	</form>
</div>
<script src="./plugins/layui2.5.5/layui.js" charset="utf-8"></script>
<script type="text/javascript">
layui.use('form', function(){
	var form = layui.form;
	//自定义验证规则
	form.verify({
	  name: function(value){
	    if(value.length < 2){
	      return '请将姓名填写正确';
	    }
	  }
	,phone: [
		    /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\d{8}$/
		    ,'请填写正确的手机号码'
		  ]
	  ,pwd: [
	    /^[\S]{6,12}$/
	    ,'密码必须6到12位，且不能出现空格'
	  ]
	  ,pwd_check: function(value){
		  var pwd = $("#pwd").val();
	     if(value != pwd){
	    	 return '两次密码不一致';
	     }
	  }
	});
	
	 //监听提交
	  form.on('submit(demo1)', function(data){
	    /* layer.alert(JSON.stringify(data.field), {
	      title: '最终的提交信息'
	    })  */
	    var json = (data.field);
	    /* var json = { "name":"热火",
	             "phone":"15180249029",
	             "pwd":"123123"
		}; */
	    console.log("json="+json);
	    $.ajax({
			type:"POST",
			url:"./user/register",
			data:json,
			success:function(data){
				//layer.msg("注册成功");
				layer.open({
			        type: 1
			        ,offset: 'auto'
			        ,id: 'layerDemoAuto' //防止重复弹出
			        ,content: '<div style="padding: 20px 100px;"> 注册成功 </div>'
			        ,btn: '立即登录'
			        ,btnAlign: 'c' //按钮居中
			        ,shade: 0.2 //不显示遮罩
			        ,yes: function(){
			           window.location.href = "./";
			        }
			      });
			},
			error:function(data){
				layer.msg("注册失败"+data);
			}
	    });
	    //return false;
	  });
	
});



/* $("#registerBtn").click(function(){
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
		url:"./user/register",
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
	
}); */





</script>

</body>
</html>