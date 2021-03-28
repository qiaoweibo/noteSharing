package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
//
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import com.mysql.jdbc.Field;

import db.MyHibernateSessionFactory;

import entity.Users;
import dao.UsersDAO;

public class UsersDAOImpl implements UsersDAO {
	//获取数据库中的内容
	//getFiledList此方法无法打印出数据库中的全部信息
	//只能获取表单提交的数据库中存在的相关数据集合
	public static List getFiledList(List list, String filed) {
		//if (CollectionUtils.isEmpty(list))
			//return null;
		List filedList = new ArrayList();
		try {

			for (Object obj : list) {
				Class clazz = obj.getClass();// 获取集合中的对象类型
				Field[] fds = clazz.getDeclaredFields();// 获取他的字段数组
				for (Field field : fds) {// 遍历该数组
					String fdname = field.getName();// 得到字段名，

					Method method = clazz.getMethod("get" + change(fdname),
							null);// 根据字段名找到对应的get方法，null表示无参数

					if (null != method && filed.equals(fdname)) {
						Object val = method.invoke(obj, null);
						filedList.add(val);
					}

				}
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return filedList;
	}

	/**
	 * @param src
	 *            源字符串
	 * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
	 */
	public static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}
	//
    //用户登录
	@Override
	public boolean usersLogin(Users u) {
		// TODO Auto-generated method stub
		//事务对象
				Transaction tx = null;
				String hql = "";
				try
				{
					 //Map<String,String> json=new HashMap<String,String>();
					 
					Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
					tx = session.beginTransaction();
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      		hql = "from Users where username=? and password=? ";
					Query query = session.createQuery(hql);
					query.setParameter(0, u.getUsername());
					query.setParameter(1, u.getPassword());
					List list = query.list();
System.out.println(list);//[Users [uid=92, username=小乔, password=111111, gender=男, phone=, headportrait=null]]
					//
					//System.out.println(list);
					System.out.println("````````````");
					List filedList = getFiledList(list,"uid");
System.out.println(filedList);//打印出来的值形如：[92]
					System.out.println("````````````");
					//
					System.out.println(u.getUid());
System.out.println("--------");
					for (int i = 0; i < filedList.size(); i++) {
					Object[] obj = filedList.toArray();
					System.out.println(obj[i]);
					Object a = obj[i];
					int b = (int)a;					
					u.setUid(b);//b为用户id
					}
					//
					tx.commit();
					if(list.size()>0)
					{
						//json.put("message", "欢迎管理员登陆");
						
						return true;
					}
					else
					{
						//json.put("message", "非法登陆信息！");
						
						return false;
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					return false;
				}
				finally
				{
					if(tx!=null)
					{
						tx = null;
					}
				}
			}
	
	//用户注册
	@Override
	public boolean register(Users u) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		String hql = "";
		try
		{
			 //Map<String,String> json=new HashMap<String,String>();
			 
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=?  ";
			Query query = session.createQuery(hql);
System.out.println(query);
			query.setParameter(0, u.getUsername());
			//query.setParameter(1, u.getPassword());
			List list = query.list();
System.out.println(list);			
			//
			System.out.println("````````````");
			int q = 0;
			//获取数据库中的与表单相对应的用户名集合
			List filedList = getFiledList(list,"username");
System.out.println(filedList);
			for (int i = 0; i < filedList.size(); i++) {
System.out.println(filedList.get(i));
				if(filedList.get(i).equals(u.getUsername())){
					//System.out.println(u.getUsername() + "已存在,请重新注册用户名");
					q = 1;
				}			
			}
			/*
			System.out.println("````````````");
			//
			
			System.out.println(list);
			
			for(int i=0;i<list.size();i++){
			java.lang.reflect.Field[] obj = list.get(i).getClass().getDeclaredFields();
			Object oi = list.get(i);
			for (int j = 0; j < obj.length; j++) {
	               if(!obj[j].isAccessible()){
	                    obj[j].setAccessible(true);
	                }
	                System.out.println(obj[j].get(oi));
	               
	            }
			}
			//
			System.out.println("-----------");
			session.save(u);
			System.out.println(u.getUid());
			//将数据库中的信息打印出来
			query.setParameter(0, u.getUsername());
			//query.setParameter(1, u.getPassword());
			List list1 = query.list();
			System.out.println(list1);
			//
			tx.commit();
			
			
			return true;
			*/
			if(q==1)
            {
            	System.out.println("用户名已存在，请重新注册用户名");
            	return false;
            }
            else
            {
            	session.save(u);           
                tx.commit();
            	System.out.println("注册成功，请登录");
            	return true;
            }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
		//
		
	}
	////修改密码
	public boolean updatepassword(Users u){
			// TODO Auto-generated method stub
			Transaction tx = null;
			
			try
			{
				Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();  
	            tx = session.beginTransaction();
//System.out.println(u.getUsername());
//异常解决后，请加上原密码的验证
//修改密码的流程：用户名和原密码都正确的前提下，修改密码
	            session.update(u);
	            tx.commit();
				return true;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				tx.commit();
				return false;			
			}
			finally
			{
				if(tx!=null)
	            {  
	                tx = null;  
	            } 
			}
		}

	
    			
}


