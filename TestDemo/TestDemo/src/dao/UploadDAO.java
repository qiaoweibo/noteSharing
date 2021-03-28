package dao;

import entity.Upload;

//用户业务逻辑接口
public interface UploadDAO {
	
	public boolean uploadFile(Upload u);//上传文件
	
	public String[] firstGet(Upload u);//遍历数据库获取全部第一张图
	
	public String[] secondGet(Upload u);//点击获取该图集下的所有图片
	
	public boolean thirdGet(Upload u);//搜索功能
}
