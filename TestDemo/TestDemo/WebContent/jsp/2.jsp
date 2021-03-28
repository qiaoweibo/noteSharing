<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>点击获取全部图片</title>
  
</head>
 <body>
  <form action="second" method="post">       
    <hr/>
    <table align="left">
        <tr>
            <td>图集Id：</td>
            <td><input type="text" name="detaildescription" id="detaildescription"></td>
        </tr>
       <tr>
             <td>图片Id：</td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
          <td colspan="1">
            </td>
             <td>
                 <input type="submit" value="登陆"/>
             </td>
         </tr>
     </table>
 </form>
</body>
 </html>