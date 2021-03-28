package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import dao.UploadDAO;
import dao.impl.UploadDAOImpl;
import entity.Upload;

@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String a;
	String b;
	//ActionContext context = ActionContext.getContext();  
	//HttpServletRequest  request1 = ServletActionContext.getRequest();//获得当前的请求对象
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.println("已接收到请求");
//HttpServletRequest  request = ServletActionContext.getRequest();//获得当前的请求对象
Upload u = new Upload();
//用java反射打印出request的类
Class ownerClass = request.getClass();//得到该对象的Class。
System.out.println(ownerClass);
System.out.println(request);
		request.setCharacterEncoding("utf-8");
String detaildescription ;
String username ;
//ServletInputStream username = request.getInputStream();
//System.out.println(username);
//System.out.println(detaildescription);
        //获得磁盘文件条目工厂。  
        DiskFileItemFactory factory = new DiskFileItemFactory();
System.out.println(factory);
        //获取文件上传需要保存的路径，upload文件夹需存在。  
        String path = request.getSession().getServletContext().getRealPath("/images");
System.out.println(path);
String s = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ s +"/images/";
System.out.println(basePath);        
		//设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
        factory.setRepository(new File(path));  
        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
        factory.setSizeThreshold(1024*1024);  
        //上传处理工具类（高水平API上传处理？）  
        ServletFileUpload upload = new ServletFileUpload(factory);  
System.out.println(upload);
DiskFileUpload diskFileUpload = new DiskFileUpload();
        try{  
            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
System.out.println(list);
            for(FileItem item:list){  
                //获取表单属性名字。  
                String name = item.getFieldName();  
                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
                if(item.isFormField()){
                	//新加的
                	if("username".equals(item.getFieldName())){
                        System.out.println("遍历到username ... <br/>");
                        username = new String(item.getString().getBytes(),"UTF-8");
                        a = username;
                        System.out.println(username);
                    }
                	if("detaildescription".equals(item.getFieldName())){
                        System.out.println("遍历到detaildescription ... <br/>");
                        detaildescription = new String(item.getString().getBytes(),"UTF-8");
                        b = detaildescription;
                        System.out.println(detaildescription);
                	}
                    //获取用户具体输入的字符串，  
                    String value = item.getString();  
                    request.setAttribute(name, value);
System.out.println(value);
                }
                
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。  
                else{   
                    //获取路径名  
                    String value = item.getName();  
                    //取到最后一个反斜杠。
System.out.println(value);
                    int start = value.lastIndexOf("\\");  
                    //截取上传文件的 字符串名字。+1是去掉反斜杠。  
                    String filename = value.substring(start+1);
System.out.println(filename);
                    request.setAttribute(name, filename);
String c = basePath + filename;//c为图片地址
//将数据添加到数据库中
int imageSetId = 3;
//u.setImageSetId(imageSetId);
u.setUpa(c);
u.setUsername(a);
u.setDetaildescription(b);
UploadDAO udao =new UploadDAOImpl();
udao.uploadFile(u);
                    /*第三方提供的方法直接写到文件中。 
                     * item.write(new File(path,filename));*/  
                    //收到写到接收的文件中。  
                    OutputStream out = new FileOutputStream(new File(path,filename));  
                    InputStream in = item.getInputStream();  

                    int length = 0;  
                    byte[] buf = new byte[1024];  
                    System.out.println("获取文件总量的容量:"+ item.getSize());  

                    while((length = in.read(buf))!=-1){  
                        out.write(buf,0,length);  
                    }  
                    in.close();  
                    out.close();  
                }  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  

    }  
	

}
