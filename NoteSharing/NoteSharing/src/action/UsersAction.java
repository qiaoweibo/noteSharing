package action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import dao.UsersDAO;
import dao.impl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{
	/*private InputStream inputStream;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}*/



	private Users user = new Users();
	
	
	
	
	//用户登录
	public String login() throws IOException {
		
		UsersDAO udao = new UsersDAOImpl();
		
		 this.response.setContentType("text/json;charset=utf-8");
         this.response.setCharacterEncoding("UTF-8");
        //Map<String,String> json = new HashMap<String,String>();
		Map<String,Object> json = new HashMap<String,Object>();
		
		if(udao.usersLogin(user))
		{
			//在session中保存登录成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			//inputStream = new ByteArrayInputStream("success".getBytes("UTF-8"));
			json.put("message", "欢迎管理员登陆");
			json.put("Uid", user.getUid());
			json.put("username", user.getUsername());
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
            response.getOutputStream().write(jsonBytes);
            response.getOutputStream().flush();
           
            response.getOutputStream().close();
            
            return null;
          //return "login_success";
		}
		//return "login_success";
		else
		{
			json.put("message", "非法登陆信息！");
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
            response.getOutputStream().write(jsonBytes);
            response.getOutputStream().flush();           
            response.getOutputStream().close();            
            return null;
			//return "login_failure";
		}
	}
	
	//用户注册
		public String register() throws Exception{
			
			this.response.setContentType("text/json;charset=utf-8");
	        this.response.setCharacterEncoding("UTF-8");	         
			Map<String,String> json = new HashMap<String,String>();
			
			Users u = new Users();
			u.setUsername(request.getParameter("username"));
			u.setPassword(request.getParameter("password"));
			u.setGender(request.getParameter("gender"));
			u.setPhone(request.getParameter("phone"));
			u.setHeadportrait(request.getParameter("headportrait"));
			
			UsersDAO udao = new UsersDAOImpl();
			
			//
			
			if(u.getUsername().isEmpty()||u.getPassword().isEmpty())
			{
				System.out.println("用户名或密码不能为空");
				json.put("message","用户名或密码不能为空");
				
			}
			//
			else if(!udao.register(u))
			{
				System.out.println(u.getUsername() + "已存在,请重新注册用户名");
				json.put("message","用户名：" + u.getUsername() + "已存在,请重新注册用户名");
			}
			
			else
			{
				udao.register(u);
System.out.println(request.toString());
System.out.println(response.toString());
				//用户注册的信息用json流保存
				json.put("username", request.getParameter("username"));
				json.put("password", request.getParameter("password"));
System.out.println(response.toString());
System.out.println(request.toString());
			}
			
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
            response.getOutputStream().write(jsonBytes);
            response.getOutputStream().flush();           
            response.getOutputStream().close();
			
			//return "register";
            return null;
		}
		
	
	
	/*
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//用户名不能为空
		 if("".equals(user.getUsername().trim()) ){  
		        this.addFieldError("usernameError", "用户名不能为空!");  
		    }  
		    if(user.getPassword().trim().length()<6){  
		        this.addFieldError("userpasswordError", "密码不能小于6位!");  
		    }  
	}
	*/
	//用户修改密码
	//1.获得传递过来的学生编号
		/*
	public String modify(){
		String uid = request.getParameter("uid");
	    //UsersDAO udao = new UsersDAOImpl();	    
	    //保存在会话中
	    session.setAttribute("modify_students", uid);
	    return "modify_success";	
	    }
	//2.保存修改后的动作	
	 * 
	 */
	public String updatepassword() throws Exception{
    	Users u = new Users();
    	//u.setUid(request.getParameter("uid"));
    	u.setUsername(request.getParameter("username"));
System.out.println(request.getParameter("username"));
    	u.setPassword(request.getParameter("password"));
    	//u.setGender(request.getParameter("gender"));
    	//u.setPhone(request.getParameter("phone"));
    	//u.setHeadportrait(request.getParameter("headportrait"));
    	UsersDAO udao = new UsersDAOImpl();
    	udao.updatepassword(u);
		return null; 
    }

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
