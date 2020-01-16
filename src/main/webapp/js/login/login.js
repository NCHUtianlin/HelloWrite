
var checkCode = "";

layui.use('form', function(){
	var form = layui.form;
	//自定义验证规则
	form.verify({
	  phone: [
		    /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\d{8}$/
		    ,'请填写正确的手机号码'
		  ]
	  ,pwd: [
	    /^[\S]{6,12}$/
	    ,'密码不完整'
	  ]
	});
	
	 //监听提交
	  form.on('submit(loginForm)', function(data){
	    var json = (data.field);
	    console.log("json="+json);
	    /*var phone = $("#phone").val();
		var pwd = $("#pwd").val();
		json = {
				"phone":phone,
				"pwd":pwd
		};*/

		
		$.ajax({
			type:"POST",
			url:"./login",
			dataType:"JSON",
			data:json,
			success:function(data){
				console.log("success");
				goMainPage(data);
				
			},
			
			error:function(data){
				layer.msg("账号密码错误");
			}
			
		});
	    
	  });
	
});

/*$("#loginBtn").click(function(){
	
	
		
	
});	*/

//注册
$("#registerBtn").click(function(){
	console.log("打开注册页面");
	window.location.href="./goRegister";
});

//进入主页面
function goMainPage(data){
	
	checkCode = data.checkCode;
	var userId = data.userId;
	console.log("获取checkCode="+checkCode);
	
	/* FORM 提交  */
	var form = document.createElement('form');
	form.action = './main';
	form.method = 'POST';
	var input = document.createElement('input');
	input.type = 'hidden';
	input.name = 'checkCode';
	input.value = checkCode;
	form.appendChild(input);
	var input_id = document.createElement('input');
	input_id.type = 'hidden';
	input_id.name = 'userId';
	input_id.value = userId;
	form.appendChild(input_id);
	$(document.body).append(form);
	form.submit();
	
	/*var xhr = new XMLHttpRequest();
	xhr.open("POST" , "./htl/main" , false );
	xhr.setRequestHeader("checkCode",checkCode);
	xhr.setRequestHeader("userId",userId);
	xhr.send();*/
	
	//$.post("./htl/main" , { "checkCode":checkCode , "userId":userId });


	
}








