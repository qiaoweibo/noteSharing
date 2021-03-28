package entity;

public class Users {
	
	private int uid;
	private String username;
	private String password;
	private String gender;//性别
	private String phone;//电话
	private String headportrait;//用户头像
	
	public Users(){
		
	}
	
	

	public Users(int uid, String username, String password, String gender, String phone, String headportrait) {
		//super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.headportrait = headportrait;
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

	public String getPassword() {
		return password;
	}
	
	
   
	public void setPassword(String password) {
		 this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadportrait() {
		return headportrait;
	}

	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}



	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", phone=" + phone + ", headportrait=" + headportrait + "]";
	}
	
	

}
