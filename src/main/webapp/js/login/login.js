
var checkCode = "";

$("#loginBtn").click(function(){
	
	var account = $("#account").val();
	var password = $("#password").val();
	var json = {
			"phone":'15180249029',//account,
			"pwd":'12'//password
	};

	
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
			console.log("failed");
		}
		
	});
		
	
});	

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








