


//校验数据
$("#saveBtn").click(function(){
	var title = $("#title").val();
	var detail = $("#detail").val();
	var userId = $("#userId").val();
	
	if( title == "" ){
		//$("#title").popover();
		layer.msg("标题不能为空");
	}
	else if( detail == "" ){
		//$("#detail").popover();
		layer.msg("数据为空，无法保存");
	}
	else{
		var json = {
				"title":title,
				"detail":detail,
				"userId":userId
		};
		saveData(json);
	}
	
});
//保存数据
function saveData(json){
	$.ajax({
		url:"./content/saveData",
		type:"POST",
		data:json,
		success:function(data){
			//console.log("success");
			layer.msg("保存成功");
		},
		error:function(data){
			//console.log("save failed");
			layer.msg("保存失败");
		}
	});
	
}


var showNavBtn = "writeBtn";//初始为第一个功能选项
//菜单栏变化
function NavBtnChange(get_btn){
	//console.log("get_btn="+get_btn);
	if( get_btn != "writeBtn" ){
		$("#writeNoteForm").css("display","none");
	}
	
	var btnid = "#"+get_btn;
	if(showNavBtn == "writeBtn"){
		$("#writeBtn").removeClass('active');
		$(""+btnid).addClass('active');
	}
	else if(showNavBtn == "lastRecordBtn"){
		$("#lastRecordBtn").removeClass('active');
		$(""+btnid).addClass('active');
	}
	else if(showNavBtn == "todayBtn"){
		$("#todayBtn").removeClass('active');
		$(""+btnid).addClass('active');
	}
	else if(showNavBtn == "yesterdayBtn"){
		$("#yesterdayBtn").removeClass('active');
		$(""+btnid).addClass('active');
	}
	else if(showNavBtn == "moreQueryBtn"){
		$("#moreQueryBtn").removeClass('active');
		$(""+btnid).addClass('active');
	}
	showNavBtn = get_btn;
	
}

//写日记
$("#writeBtn").click(function(){
	if( "writeBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("writeBtn");
	$("#showContent_change").css("display","none");
	$("#writeNoteForm").css("display","block");
	
});
//上一条记录
$("#lastRecordBtn").click(function(){
	if( "lastRecordBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("lastRecordBtn");
	$("#showContent_change").css("display","block");
	queryLastRecordData();
	
});
//今天的记录
$("#todayBtn").click(function(){
	if( "todayBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("todayBtn");
	getOnedayData(0);
	
});
//昨天的记录
$("#yesterdayBtn").click(function(){
	if( "yesterdayBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("yesterdayBtn");
	getOnedayData(1);
	
});
//更多记录查询
$("#moreQueryBtn").click(function(){
	if( "moreQueryBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("moreQueryBtn");
	document.getElementById("showContent_change").innerHTML = "<h1 style='margin:60px 0 0 200px;'>哈哈，更多功能需求请面谈哦！！！</h1>"
	
});


//查询最新的一条记录
function queryLastRecordData(){
	//组装参数
	var pageSize = 1;
	var userId = $("#userId").val();
	var json ={ 
			"pageSize":pageSize,
			"userId":userId
	};
	//请求数据
	$.ajax({
		url:"./content/queryData",
		type:"POST",
		data:json,
		dataType:"JSON",
		async:"false",
		success:function(data){
		if( data != null && data.length > 0 ){
			var json = data[0];
			console.log("data="+json);
			var html = ""
			+"<div id='titleDiv' class='input-group titleDiv'>"
			+"<span class='input-group-addon'>标题</span>"
			+"<input type='text' class='form-control' id='title_lastR' name='title' value='"+json.title+"'/>"
			+"</div>"
			+"<div style='float:right'>"+json.wdate+"</div>"
			+"<div><textarea id='detail_lastR' name='detail' rows='15' cols='126' >"+json.detail+"</textarea></div>"
			+"<div style='margin-top:10px;'>"
			+"<input type='hidden' id='contentId_lastR' value='"+json.id+"'/>"
			+"<button id='saveBtn_lastR' onclick='modifyData()' type=‘button’ class='btn btn-primary' style='margin-right:20px;'>保存</button>"
			+"<button id='deleteBtn_lastR' onclick='delete_lastRData()' type=’button‘ class='btn btn-danger'>删除</button>"
			+"</div>";
			//console.log("html="+html);
			//$("#showContent_change").innerHTML = html;//无效
			document.getElementById("showContent_change").innerHTML = html;
		}
		},
		error:function(data){
			
		}
	});	
}
//上面这个页面中的删除事件    上一条记录的页面
function delete_lastRData(){
	var userId = $("#userId").val();
	var id = $("#contentId_lastR").val();
	var json = {
			"userId":userId,
			"id":id
	};
	deleteData(json);
	if( "lastRecordBtn" == showNavBtn ){
		console.log("刷新");
		queryLastRecordData();
	}
	
}
//修改一条数据
function modifyData(){
	var userId = $("#userId").val();
	var id = $("#contentId_lastR").val();
	var title = $("#title_lastR").val();
	var detail = $("#detail_lastR").val();
	//console.log("title="+title);
	var json = {
			"userId":userId,
			"id":id,
			"title":title,
			"detail":detail
	};
	$.ajax({
		url:"./content/updateData",
		type:"POST",
		data:json,
		success:function(data){
			layer.msg("保存成功");
		},
		error:function(data){
			layer.msg("保存失败");
		}
	});
}
//删除数据
function deleteData(json){	
	var k = -1;
	$.ajax({
		url:"./content/deleteData",
		type:"POST",
		data:json,
		success:function(data){
			//layer.msg("删除成功");
		},
		error:function(data){
			layer.msg("删除失败");
		}
	});

}


//多条件查询 多条记录
function queryDataTemplete(json){
	if(json == ""){
		return ;
	}
	
	document.getElementById("showContent_change").innerHTML = "";
	//请求数据
	$.ajax({
		url:"./content/queryData",
		type:"POST",
		data:json,
		dataType:"JSON",
		async:"false",
		success:function(data){
			if( data == null || data.size == 0 ){
				document.getElementById("showContent_change").innerHTML = "<h1  style='margin:60px 0 0 200px;'>没有数据记录哦</h1>"
					return ;
			}
			
			var html = "<table class='table table-hover table-bordered' style='width:100%'>"
				+"<thead><tr><th hidden='hidden'>id</th><th width='40px'>序号</th><th width='20%'>标题</th><th width='50%'>内容</th><th width='50px'>时间</th><th width='40px'>操作</th></tr></thead>"
				+"<tbody class=''>";
				
	    	
			for(var p in data){
				
				var per = data[p];
				console.log("per="+per);
				html += ""
					+"<tr><td hidden='hidden'>"+per.id+"</td><td>"+(p+1)+"</td><td>"+per.title+"</td><td>"+per.detail+"</td><td>"+per.wdate+"</td>"
					+"<td>"
					+"<button class='btn btn-default'><span class='glyphicon glyphicon-pencil'style='color: rgb(255, 140, 60);'></span></button>"
					+"<button onclick='delete_MoreDataPage("+per.id+")' class='btn btn-default'><span class='glyphicon glyphicon-trash' style='color: rgb(255, 140, 60);'></span></button>"
					+"</td></tr>"
				
			}	
			html += "</tbody></table>";
			document.getElementById("showContent_change").innerHTML = html;
		},
		error:function(data){
			document.getElementById("showContent_change").innerHTML = "<h1  style='margin:60px 0 0 200px;'>没有数据记录哦</h1>"
			layer.msg("查询失败");
		}
	});	
}
//上面这个页面中的删除事件    多数据查询显示页面
function delete_MoreDataPage(id){
	var userId = $("#userId").val();
	var json = {
			"userId":userId,
			"id":id
	};
	deleteData(json);
	if( "yesterdayBtn" == showNavBtn ){
		console.log("刷新");
		getOnedayData(1);
	}
	else if( "todayBtn" == showNavBtn ){
		console.log("刷新");
		getOnedayData(0);
	}
	
}
//查询昨天的记录
function getOnedayData(k){
	var day = new Date();
	day.setDate(day.getDate()-k);
	var oneday = day.getFullYear()+"-"+ day.getMonth()+1 +"-"+day.getDate()
	console.log("oneday="+oneday);
	var json = {
			"userId":$("#userId").val(),
			"wdate":oneday
	};
	queryDataTemplete(json);
	
}

$("#signOutBtn").click(function(){
	var userId = $("#userId").val();
	window.location.href = "./signOut?userId="+userId;
});


