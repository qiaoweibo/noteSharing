package dao.impl;

import org.junit.Test;

import dao.UsersDAO;
import entity.Users;
import junit.framework.Assert;

public class TestUsersDAOImpl {
	
	@Test
	public void testUsersLogin(){
		//Users u = new Users();
		Users u = new Users(1,"ÕÅÈý","123456","ÄÐ","6778907","http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
		//Users u = new Users(1,"Ð¡»Æ",null,"ÄÐ",null,null);
		//Users u = new Users(3,"zhangsan",null,"ÄÐ",null,null);
		UsersDAO udao = new UsersDAOImpl();
		Assert.assertEquals(true, udao.usersLogin(u));
		
		
		System.out.println(u.getUsername());
	}
	

}
