package action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

public class ServerToAndroidAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	String[] c = new String[1000000];
	JSONArray json = new JSONArray();
	//
	private  void getFile(String path,int deep){   
        // 获得指定文件对象  
        File file = new File(path);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();   

        for(int i=0;i<array.length;i++)
        {   
            if(array[i].isFile())//如果是文件
            {   
                    for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字  
                System.out.println( array[i].getName());
                c[i] = array[i].getName();
                //System.out.println( c[i]);
                // 输出当前文件的完整路径   
               // System.out.println("#####" + array[i]);   
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下   
                //System.out.println(array[i].getPath());   
            }
            else if(array[i].isDirectory())//如果是文件夹
            {  
                    for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

                    System.out.println( array[i].getName());
                    //System.out.println(array[i].getPath());
                    //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(),deep+1);  
            }
            //System.out.println("TakeFilePathAndName.getFile()");
        }   
    }
	
	//
	public String ServerToAndroid() throws Exception{
		this.response.setContentType("text/json;charset=utf-8");
        this.response.setCharacterEncoding("UTF-8");
		//Map<String,Object> json = new HashMap<String,Object>();
		//设置保存文件的上传路径
      	String path1 = ServletActionContext.getServletContext().getRealPath("/images");
        //System.out.println(path1);
        //将文件转化为json流格式
      	String s = request.getContextPath();
      	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ s +"/images/";   
      	System.out.println(basePath);
      	//以下是获取图片所在的目录
		String path = "C:/Java EE/apache-tomcat-7.0.77/webapps/NoteSharing/images";   
//String path = "D:/images";
      	getFile(path,0);
		File file = new File(path);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();   
        for(int i=0;i<array.length;i++){
        	c[i] = basePath + c[i];
        	//json.put("aaa", c[i]);
        	json.put(c[i]);
        	System.out.println(c[i]);
        }
		
        
        
      	
      	
      	//
      	//
        
		//json.put("message", "欢迎管理员登陆");		
		//
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
        response.getOutputStream().write(jsonBytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
        //
		return null;		
	}
}
