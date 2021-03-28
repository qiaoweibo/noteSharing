<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '01.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
			
			$("#myfile").change(function(){
				 	//var file = this.files[0];
	                //var reader = new FileReader();
	                //reader.readAsDataURL(file);
	                //reader.onload = function(e){
	                	//$("#previewImg").attr("src", e.target.result);//可以解决浏览器不能预览图片的问题
				$("#previewImg").attr("src","file:///" + $("#myfile").val());
	                //};
			});
			
			
			
			$("#previewImg").mousemove(function(e){
				la.css({
					top : e.pageY,
					left : e.pageX
				}).html('<img src = "' + this.src + '" />').show();
			}).mouseout(function(){
				la.hide();
			});
		});	
		 
		
	</script>
   
</head>
<body>
	 
	 <form action="ServerToAndroid" method="post" >
	 请选择图片:<input id="myfile" name="myfile" type="file" onchange="showPreview()"/>
	<input type="submit" value="提交"/>${result}
	</form>
	
</body>
</html>