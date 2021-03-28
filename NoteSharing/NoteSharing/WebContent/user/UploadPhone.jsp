<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP '01.jsp' starting page</title>  
</head>
<body>
	 
	 <form action="uploadPhoto" method="post" enctype="multipart/form-data">
	 请选择图片:<input id="myfile" name="myfile" type="file" onchange="showPreview()"/>
	<input type="submit" value="提交"/>${result}
	</form>
	
</body>
</html>