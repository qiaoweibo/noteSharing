package action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GetFileMessageAction {
	
	 

		/**
		* 获取文件属性
		* @param in InputStream
		* @param imgPath 图片的保存路径
		* @param imgName 图片的名称
		* @return
		* @throws IOException
		*/
		@SuppressWarnings( { "rawtypes", "unchecked", "null" } )
		public static Map saveToImgByInputStream(InputStream in,String imgPath,String imgName,String path) throws IOException{
		//将流转成临时存储文件，拿到属性存储在map里面然后删除临时文件。
		Map map =null;
		FileOutputStream fos=null;
		BufferedImage bufferedImage=null;
		File file,f;
		byte[] b=null;
		FileInputStream fis=null;
		try {
		map =new HashMap();
		// 将上面生成的图片格式字符串 imgStr，还原成图片显示
		file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
		fos=new FileOutputStream(file);
		b = new byte[1024];
		int nRead = 0;
		while ((nRead = in.read(b)) != -1) {
		fos.write(b, 0, nRead);
		}
		fos.flush();
		map.put("size", getPrintSize(file.length()));
		bufferedImage = ImageIO.read(file);
		map.put("width", bufferedImage.getWidth());
		map.put("height", bufferedImage.getHeight());

		//这里为什么要新得到一个流呢，因为上面流写入完了，发现传到存储服务器的文件是空的，个人猜测，流空了，没有深入研究过=.=
		f=new File(imgPath+"/"+imgName);
		fis=new FileInputStream(f);

		//这个方法是将文件传到存储服务器，里面涉及到公司源码，不便透露
		//saveFileToFtpServer(fis, path, imgName);
		//map.put("url",Constant.systemUserCenterFileServerUrl+path+"/"+imgName);
		} catch (Exception e) {
		e.printStackTrace();
		//map.put(UserCenterResultEnum.OPEN_OUTPUTSTREAM_FAILED.getCode(), UserCenterResultEnum.OPEN_OUTPUTSTREAM_FAILED.getDesc());
		} finally {
		bufferedImage = null;
		file=null;
		if(fos!=null){
		fos.close();
		}
		if(fis!=null){
		fis.close();
		fis=null;
		}
		f=null;
		if(in!=null){
		in.close();
		}
		delFolder(imgPath+"/"+imgName);
		}
		return map;
		}

		 

		/**
		* 将文件大小b转为mb
		* @param size
		* @return
		*/
		public static String getPrintSize(long size) {
		//如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
		return String.valueOf(size) + "B";
		} else {
		size = size / 1024;
		}
		//如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		//因为还没有到达要使用另一个单位的时候
		//接下去以此类推
		if (size < 1024) {
		return String.valueOf(size) + "KB";
		} else {
		size = size / 1024;
		}
		//if (size < 1024) {
		//因为如果以MB为单位的话，要保留最后1位小数，
		//因此，把此数乘以100之后再取余
		size = size * 100;
		return String.valueOf((size / 100)) + "."+ String.valueOf((size % 100)) + "MB";
		/*} else {
		//否则如果要以GB为单位的，先除于1024再作同样的处理
		size = size * 100 / 1024;
		return String.valueOf((size / 100)) + "."
		+ String.valueOf((size % 100)) + "GB";
		} */
		}

		 

		/**
		* 删除指定文件或者指定文件夹下的文件
		* @param folderPath 路径
		*/
		public static void delFolder(String folderPath) {
		File myFilePath=null;
		try {
		//delAllFile(folderPath); // 删除完里面所有内容
		myFilePath = new File(folderPath);
		myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
		e.printStackTrace();
		}finally {
		myFilePath=null;
		}
		

	}
	/**
	* 获取文件相信信息
	* @param request HttpServletRequest实例
	* @param imgPath 图片路径
	* @param fileName 图片名
	* @param path ftp保存的位置
	 * @return 
	* @return
	* @throws IOException
	*/
	public static  Map getFileMessage(HttpServletRequest request,String imgPath,String fileName,String path) throws IOException {
	Map map=null;
	if (ServletFileUpload.isMultipartContent(request)){
	//创建ServletFileUpload实例
	ServletFileUpload fileUpload = new ServletFileUpload();
	FileItemIterator iter=null;
	FileItemStream item=null;
	InputStream is = null;
	try {
	//解析request请求 返回FileItemStream的iterator实例
	iter = fileUpload.getItemIterator(request);
	//迭代取出
	while (iter.hasNext()){
	item = iter.next();//获取文件流
	if(!item.isFormField()){

	//为什么要这么取出这个流呢，因为request.getInputStream()中，表单提交上来的不仅仅包含了文件，还带有参数，就算不带参数，也还有request中本身的一些其他东西（ps：没有研究过，但是试过不带参数拿到的流也是不对的。）直接拿会导致读取出来的文件变大，图片读取失败

	//这里主要针对图片来写的，因为我用到的是转成图片，获取图片属性。
	is = item.openStream();
	if (is.available()>0){
	map=saveToImgByInputStream(is, imgPath, fileName,path);
	}
	}
	}
	} catch (Exception e) {
	e.printStackTrace();
	// return is;
	return map;
	} finally {
	fileUpload=null;
	iter=null;
	if(is!=null){
	is.close();
	is=null;
	}
	item=null;
	}
	}
	//return is;
	return map;
	}
}

	
