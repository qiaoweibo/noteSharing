package entity;

import java.io.Serializable;
//使用联合主键时，bean类必须序列化
public class Upload implements Serializable{
	
	
	private int imageSetId;//上传的图集id
	private int imageId;//上传图片的id
	private String upa;//上传图片地址
	private String detaildescription;//图片的详细描述
	private String label1;//标签1
	private String label2;//标签2
	private String label3;//标签3
	private int uid;//上传用户的id
	private String username;//上传用户的用户名
	
	public Upload(){
		
	}

	
public Upload(int imageSetId, int imageId, String upa, String detaildescription, String label1, String label2,
			String label3, int uid, String username) {
		//super();
		this.imageSetId = imageSetId;
		this.imageId = imageId;
		this.upa = upa;
		this.detaildescription = detaildescription;
		this.label1 = label1;
		this.label2 = label2;
		this.label3 = label3;
		this.uid = uid;
		this.username = username;
	}

	public int getImageSetId() {
	return imageSetId;
}


public void setImageSetId(int imageSetId) {
	this.imageSetId = imageSetId;
}


public int getImageId() {
	return imageId;
}


public void setImageId(int imageId) {
	this.imageId = imageId;
}


public String getUpa() {
	return upa;
}


public void setUpa(String upa) {
	this.upa = upa;
}


public String getDetaildescription() {
	return detaildescription;
}


public void setDetaildescription(String detaildescription) {
	this.detaildescription = detaildescription;
}


public String getLabel1() {
	return label1;
}


public void setLabel1(String label1) {
	this.label1 = label1;
}


public String getLabel2() {
	return label2;
}


public void setLabel2(String label2) {
	this.label2 = label2;
}


public String getLabel3() {
	return label3;
}


public void setLabel3(String label3) {
	this.label3 = label3;
}


public int getUid() {
	return uid;
}


public void setUid(int uid) {
	this.uid = uid;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


	@Override
public String toString() {
	return "Upload [imageSetId=" + imageSetId + ", imageId=" + imageId + ", upa=" + upa + ", detaildescription="
			+ detaildescription + ", label1=" + label1 + ", label2=" + label2 + ", label3=" + label3 + ", uid=" + uid
			+ ", username=" + username + "]";
}

	//使用复合主键的实体类必须重写equals和hashCode方法。
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detaildescription == null) ? 0 : detaildescription.hashCode());
		result = prime * result + imageId;
		result = prime * result + imageSetId;
		result = prime * result + ((label1 == null) ? 0 : label1.hashCode());
		result = prime * result + ((label2 == null) ? 0 : label2.hashCode());
		result = prime * result + ((label3 == null) ? 0 : label3.hashCode());
		result = prime * result + uid;
		result = prime * result + ((upa == null) ? 0 : upa.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Upload other = (Upload) obj;
		if (detaildescription == null) {
			if (other.detaildescription != null)
				return false;
		} else if (!detaildescription.equals(other.detaildescription))
			return false;
		if (imageId != other.imageId)
			return false;
		if (imageSetId != other.imageSetId)
			return false;
		if (label1 == null) {
			if (other.label1 != null)
				return false;
		} else if (!label1.equals(other.label1))
			return false;
		if (label2 == null) {
			if (other.label2 != null)
				return false;
		} else if (!label2.equals(other.label2))
			return false;
		if (label3 == null) {
			if (other.label3 != null)
				return false;
		} else if (!label3.equals(other.label3))
			return false;
		if (uid != other.uid)
			return false;
		if (upa == null) {
			if (other.upa != null)
				return false;
		} else if (!upa.equals(other.upa))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	
}
