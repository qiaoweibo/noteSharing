<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".thumbs a").click(function(){
				var largePath  = $(this).attr("href");
				var largeAlt = $(this).attr("title");
				$("#largeImg").attr({
					src : largePath,
					alt : largeAlt
				});
				return false;
			});
			
		});	
	</script>
  </head>
  
  <body>
	<h2>&nbsp; 文件上传	</h2>
  	<form action="FileUploadServlet.do" method="post" enctype="multipart/form-data">
  		上传文件：<input type="file" name="upload"><br>
  		用户名:	<input type="text" name="username"><br>
  		简介：<input type="text" name="detaildescription"><br>
  		<input type="submit" value="提交">${result}
  	</form>
    
  </body>
</html>
