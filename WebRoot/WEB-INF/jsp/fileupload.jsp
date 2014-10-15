<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>首页</title>
</head>
<body>
<h1>文件上传测试页面</h1>
	<!-- 提交的表单必须封装，后台取参的时候不能直接取 -->
	<form action="fileupload" method="post" enctype="multipart/form-data" name="uploadfile">
		请选择上传文件1：<input type="file" id="file1" name="file1" /><br/><br/>
		请选择上传文件2：<input type="file" id="file2" name="file2" /><br/><br/>
		<input type="submit" value="上传" />
		<input type="reset" value="重置">
	</form>
	<br/><br/><br/><br/>
	
	<!-- 个人基本信息录入：<br/><br/>
	<form action="insert" method="post" name="savebase">
		姓名：<input type="text" name="name"><br/><br/>
		年龄：<input type="text" name="age"><br/><br/>
		地址：<input type="text" name="addr"><br/><br/>
		性别：<input type="radio" name="sex" value="1" >男
		    <input type="radio" name="sex" value="2" >女<br/><br/>
		   	<input type="submit" value="提交">
		   	<input type="reset" value="重置">
	</form> -->
</body>
</html>