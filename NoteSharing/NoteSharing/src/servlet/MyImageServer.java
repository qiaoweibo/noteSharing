package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class MyImageServer
 */
@WebServlet("/MyImageServer")
public class MyImageServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private String path ;

      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyImageServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		path = request.getRealPath("/") + "images\\";
		//path = ServletActionContext.getServletContext().getRealPath("/images");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
System.out.println(path);
        String param = request.getParameter("param");
System.out.println("---------");
        String imgStr2Image1 = imgStr2Image(param);
        out.print(imgStr2Image1);//
System.out.println("---------");
        out.flush();
        out.close();
    }

    //如果正确， 返回 url 不正确返回 error
    public String imgStr2Image(String imgStr) {
        // TODO Auto-generated method stub
        if(imgStr==null){
System.out.println("66666666");
            return "error:imgStr is null----MyImageServer";
        }
System.out.println("66666666");
String path1 = ServletActionContext.getServletContext().getRealPath("/images");

        File file = new File(path1);
        if (!file.exists()) {
            file.mkdir();
        }
System.out.println("66666666");
        Date date = new Date();
        long time = date.getTime();
        String strName = time+".jpg";
        String imgPath = path+strName;
        String imgUrl = "http://192.168.1.108:8080/NoteSharing/images/"+strName;
System.out.println("66666666");
        try {
            byte[] bs = new BASE64Decoder().decodeBuffer(imgStr);

            for (int i = 0; i < bs.length; i++) {
                if (bs[i] < 0) {
                    bs[i] += 256;
                }
            }
System.out.println("66666666");
            OutputStream out = new FileOutputStream(imgPath);
            out.write(bs);
System.out.println("66666666"); 
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error:IOException----MyImageServer";
        }
System.out.println("66666666");
        return imgUrl;

    }
	

}
