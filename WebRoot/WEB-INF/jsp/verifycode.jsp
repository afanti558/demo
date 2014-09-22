<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>       
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">var webroot = "<c:url value='/'/>";</script>
    <script type="text/javascript" src="<%=path%>/jquery-1.9.1.min.js"></script>
    <title>test verify code</title>  
</head>  
<body>       
     <input id="veryCode" name="veryCode" type="text"/>       
     <img id="imgObj"  alt="" src="xuan/verifyCode"/>       
     <a href="#" onclick="changeImg()">换一张</a>       
     <input type="button" value="验证" onclick="isRightCode()"/>       
     <div id="info"></div>       
</body>       
</html>
<script type="text/javascript">
 function changeImg(){     
    var imgSrc = $("#imgObj");     
    var src = imgSrc.attr("src");     
    imgSrc.attr("src",chgUrl(src));     
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
    return url;     
}     
function isRightCode(){ 
	//alert(document.getElementById("veryCode").getAttribute("value"));
    var code = $("#veryCode").val(); //	获取/添加属性 attribute    
    code = "c=" + code;  
    alert(code);
    $.ajax({     
        type:"POST",     
        url:"resultServlet/validateCode",     
        data:code,     
        success:callback     
    });     
}     
function callback(data){     
    $("#info").html(data);     
}  
</script>