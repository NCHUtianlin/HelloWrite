<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>main</title>
	<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="../css/mainpage.css" />
	
</head>
<body>
<div class='first_div'>

<!-- 头部 -->
<div class='top_div'>
	欢快记事本  记录美好心情
</div>
<div class='myAccount_div'>
	<a>我的账户</a>&nbsp;
	<a>退出</a>
</div>
<div class='navbar_div'>
	<ul class='nav nav-tabs'>
		<li id='writeBtn' class='active'><a href='#'>编辑</a></li>
		<li id='lastRecordBtn' class=''><a href='#'>上一条记录</a></li>
		<li id='yesterdayBtn' class=''><a href='#'>昨天的记录</a></li>
		<li id='moreQueryBtn' class=''><a href='#'>更多查询</a></li>
	</ul>
</div>
<!-- 内容显示区域 -->
<div class='showContent_div' id="showContentDiv">
	<div id='showContent_change'></div>
	<form id='writeNoteForm'>
	<input type='hidden' id='userId' name='userId' value='${userId}' />
	<div id='titleDiv' class='input-group titleDiv'>
		<span class='input-group-addon'>标题</span>
		<input type='text' class='form-control' id='title' name='title' value=''/>
	</div>
	<div id='buttonDiv' class='buttonDiv'>
		<button id='saveBtn' type="button" class='btn btn-primary'>保存</button>
		<button id='resetBtn' type="button" class='btn btn-danger'>清除</button>
	</div>
	<div>
		<textarea id='detail' name='detail' rows="15" cols="126"  value=''></textarea>
	</div>
	
	</form>
</div>


</div>

<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../plugins/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="../plugins/layer/layer.js"></script>
<script type="text/javascript" src="../js/mainpage.js"></script>
</body>
</html>