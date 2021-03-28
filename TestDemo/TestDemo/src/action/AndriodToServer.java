package action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

import com.opensymphony.xwork2.ActionContext;

import action.SuperAction;
import dao.UploadDAO;
import dao.impl.UploadDAOImpl;
import entity.Upload;
import servlet.FileUploadServlet;
//extends SuperAction
public class AndriodToServer extends JakartaMultiPartRequest{
	ActionContext context = ActionContext.getContext();  
    HttpServletRequest  request = ServletActionContext.getRequest();//获得当前的请求对象
    HttpServletResponse response = ServletActionContext.getResponse();
    //文件上传
	public String upload() throws Exception {
System.out.println("已接收到请求");
Upload u = new Upload();
this.response.setContentType("text/json;charset=utf-8");
this.response.setCharacterEncoding("UTF-8");
Map<String,Object> json = new HashMap<String,Object>();
//
//HttpServletRequest request = ;
//HttpServletRequest  request = ServletActionContext.getRequest();//获得当前的请求对象
//ActionContext ct= ActionContext.getContext();
//HttpServletRequest request = (HttpServletRequest)ct.get(ServletActionContext.HTTP_REQUEST);

System.out.println(request);
		request.setCharacterEncoding("utf-8");  
        //获得磁盘文件条目工厂。  
        DiskFileItemFactory factory = new DiskFileItemFactory();
System.out.println(factory);
        //获取文件上传需要保存的路径，upload文件夹需存在。  
        String path = request.getSession().getServletContext().getRealPath("/images");  
System.out.println(path);
        //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
        factory.setRepository(new File(path));
        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
        factory.setSizeThreshold(1024*1024);  
        //上传处理工具类（高水平API上传处理？）  
        ServletFileUpload upload = new ServletFileUpload(factory);
System.out.println(upload);       
            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
System.out.println(list);
System.out.println("!!!!!!");
            for(FileItem item:list){
System.out.println("000000");
                //获取表单属性名字。  
                String name = item.getFieldName();  
                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
                if(item.isFormField()){  
                    //获取用户具体输入的字符串，  
                    String value = item.getString();  
                    request.setAttribute(name, value);
System.out.println("111111");
                }  
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。  
                else{
System.out.println("222222");
                    //获取路径名  
                    String value = item.getName();  
                    //取到最后一个反斜杠。  
                    int start = value.lastIndexOf("\\");  
                    //截取上传文件的 字符串名字。+1是去掉反斜杠。  
                    String filename = value.substring(start+1);
System.out.println(filename);
                    request.setAttribute(name, filename);  

                    
//
//UploadDAO udao =new UploadDAOImpl();
//udao.uploadFile(u);

                    
                }
//
                
                
            }
json.put("message", "上传成功");
System.out.println("------");
byte[] jsonBytes = json.toString().getBytes("utf-8");
response.setContentLength(jsonBytes.length);
response.getOutputStream().write(jsonBytes);
response.getOutputStream().flush();
response.getOutputStream().close();
        
//
                
	return null;		
	}

}
