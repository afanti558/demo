<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=path %>/staticmedia/js/jquery-1.9.1.min.js"></script>
<html>         
    <head>         
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>test verify code</title>    
    </head>    
    <body>         
	    <form action="validatelogin" method="POST" id="loginform" name="loginform">
	       	验&nbsp;证&nbsp;码：<input id="veryCode" name="veryCode" type="text"/>         
	        <img id="imgObj"  alt="" src="xuan/verifyCode"/>       
	        <a href="#" onclick="changeImg()">换一张</a></br>         
	        <input type="button" value="登录" onclick="isRightCode()"/>         
	        <div id="info"></div>
        </form>         
    </body>         
<script type="text/javascript">  
 function changeImg(){       
    var imgSrc = $("#imgObj"); /* Object */
    var src = imgSrc.attr("src");
    var newSrc = chgUrl(src);
    imgSrc.attr("src",newSrc);       
}        
//时间戳       
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳       
function chgUrl(url){       
    var timestamp = (new Date()).valueOf();       
    urlurl = url.substring(0,17);
    if((url.indexOf("&")>=0)){       
        urlurl = url + "×tamp=" + timestamp;       
    }else{       
        urlurl = url + "?timestamp=" + timestamp;       
    }       
    return urlurl;       
}       
function isRightCode(){
    var code = $("#veryCode").val();
    code = "c=" + code;
    $.ajax({       
        type:"POST",       
        url:"validatecode",       
        data:code,       
        success:function(data){
        	alert(data);
        },
        error:function(){
        
		}       
    });       
}       
</script>    
</html>  