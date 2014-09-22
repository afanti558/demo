<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% String path = request.getContextPath(); %></script>
<title>首页</title>
<script type="text/javascript">
/* function upload(){
	var filename=$('#file_1').val();
	alert(filename);
} */
</script>
</head>
<body>
	<form action="upload" method="post" enctype="multipart/form-data" name="uploadfile">
		姓名：<input type="text" name="name"><br/><br/>
		头像：<input type="file" id="file_1" name="file_1" onchange="upload()"/><br/><br/>
		<input type="submit" value="上传" />
	</form>
	<br/><br/><br/><br/>
	
	个人基本信息录入：<br/><br/>
	<form action="insert" method="post" name="savebase">
		姓名：<input type="text" name="name"><br/><br/>
		年龄：<input type="text" name="age"><br/><br/>
		地址：<input type="text" name="addr"><br/><br/>
		性别：<input type="radio" name="sex" value="1" >男
		    <input type="radio" name="sex" value="2" >女<br/><br/>
		   	<input type="submit" value="提交">
		   	<input type="reset" value="重置">
		
	</form>
</body>
</html>