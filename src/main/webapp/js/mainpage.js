


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
		url:"../content/saveData",
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
	$("#writeNoteForm").css("display","block");
	
});
//上一条记录
$("#lastRecordBtn").click(function(){
	if( "lastRecordBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("lastRecordBtn");
	//组装参数
	var pageSize = 1;
	var userId = $("#userId").val();
	var json ={ 
			"pageSize":pageSize,
			"userId":userId
	};
	//请求数据
	$.ajax({
		url:"../content/queryData",
		type:"POST",
		data:json,
		dataType:"JSON",
		async:"false",
		success:function(data){
			var json = data[0];
			console.log("data="+json)
			var html = ""
			+"<div id='titleDiv' class='input-group titleDiv'>"
			+"<span class='input-group-addon'>标题</span>"
			+"<input type='text' class='form-control' id='title' name='title' value='"+json.title+"'/>"
			+"</div>"
			+"<div style='float:right'>"+json.wdate+"</div>"
			+"<div><textarea id='detail' name='detail' rows='15' cols='126' >"+json.detail+"</textarea></div>"
			+"<div id='buttonDiv' >"
			+"<button id='saveBtn_lastR' type=‘button’ class='btn btn-primary'>保存</button>"
			+"<button id='deleteBtn_lastR' type=’button‘ class='btn btn-danger'>删除</button>"
			+"</div>";
			console.log("html="+html);
			//$("#showContent_change").innerHTML = html;
			document.getElementById("showContent_change").innerHTML = html;
		},
		error:function(data){
			
		}
	});
	
	
});
//昨天的记录
$("#yesterdayBtn").click(function(){
	if( "yesterdayBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("yesterdayBtn");
	
	
	
});
//更多记录查询
$("#moreQueryBtn").click(function(){
	if( "moreQueryBtn" == showNavBtn ){
		return ;
	}
	NavBtnChange("moreQueryBtn");
	
});


//查询数据
function queryDataTemplete(json){
	$.ajax({
		url:"../content/queryData",
		type:"POST",
		data:json,
		async:"false",
		success:function(data){
			console.log("data="+data)
			return data;
		},
		error:function(data){
			
		}
	});
}



