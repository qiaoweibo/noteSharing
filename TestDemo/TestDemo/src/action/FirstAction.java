package action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import dao.UploadDAO;
import dao.impl.UploadDAOImpl;
import entity.Upload;

public class FirstAction extends SuperAction {
	//1
	public String first() throws Exception{
		 this.response.setContentType("text/json;charset=utf-8");
         this.response.setCharacterEncoding("UTF-8");
        //Map<String,String> json = new HashMap<String,String>();
		//Map<String,Object> json = new HashMap<String,Object>();
         JSONArray json = new JSONArray();
String[] c = new String[1000000];		
		Upload u = new Upload();
		UploadDAO udao = new UploadDAOImpl();
		udao.firstGet(u);
System.out.println(udao.firstGet(u));
c = udao.firstGet(u);
		for(int i=0;i<3;i++){
System.out.println(c[i]);
			json.put(c[i]);
		}
		//json.put("message", "获取成功");
		//json.put("upa", u.getUpa());
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
        response.getOutputStream().write(jsonBytes);
        response.getOutputStream().flush();      
        response.getOutputStream().close();
		return null;		
	}
}
