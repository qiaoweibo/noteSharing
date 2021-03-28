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
  <title>用户登陆页面</title>
  <style type="text/css">
  h1{text-align:left;}
h4{text-align:left;color:red;}
body{
 background:url('../images/6.jpg');
 
 background-size: 1200px 1020px;  
}
 a{text-decoration:none;font-size:20px;color:black;}
 a:hover{text-decoration:underline;font-size:24px;color:red;}
 </style>
 
</head>
 <body>
 <!-- the IP of my home : http://192.168.1.108:8080/NoteSharing/user/Users_login.jsp -->
  <form action="login" method="post">
<!--<form action="Users_login" method="post"> -->
     <h1>用户登陆页面</h1>
    <h4>装饰中......</h4>    
    <hr/>
    <table align="left">
        <tr>
            <td>账号：</td>
            <td><input type="text" name="username" id="name"></td>
        </tr>
       <tr>
             <td>密码：</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
          <td colspan="1">
            </td>
             <td>
                 <input type="submit" value="登陆"/>
                 <input type="reset" value="重置"/>
                 
                <a href="Users_register.jsp" target="_blank">注册</a>
             </td>
         </tr>
     </table>
 </form>
</body>
 </html>