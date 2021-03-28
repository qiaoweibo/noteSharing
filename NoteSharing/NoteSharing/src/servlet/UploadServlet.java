package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("已接收到请求");
		
		InputStream fileSource  = req.getInputStream();
		//得到服务器的根路径
		String rootPath = req.getRealPath("/");
		
		//指定文件存放路径
		String realPath = rootPath + "/" + "images";
		//定义文件存放的目录，注意 目录也是文件
		File file = new File(realPath);
		//如果目录不存在
		if (!file.isDirectory()) {
		//创建文件上传目录
		file.mkdirs();
		}
		//向newFile文件中写入数据
		File newFile = new File(realPath + "/" + "tempFile");
		//File newFile = new File(realPath);
		//文件存放在Tomcat中项目的根目录下的upload文件夹中
		FileOutputStream outputStream  = new FileOutputStream(newFile);

		byte[] b = new byte[1024];
		int n;
		while((n=fileSource.read(b))!=-1){
		outputStream.write(b,0,n);
		}
		outputStream.close();
		fileSource.close();
		System.out.println("Post..");
	}
}
