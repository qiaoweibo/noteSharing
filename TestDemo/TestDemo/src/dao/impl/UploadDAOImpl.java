package dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Upload;

import dao.UploadDAO;


public class UploadDAOImpl implements UploadDAO {
	//
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

					@SuppressWarnings("unchecked")
					Method method = clazz.getMethod("get" + change(fdname),null);// 根据字段名找到对应的get方法，null表示无参数

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
	//文件上传/用户注册
	public boolean uploadFile(Upload u) {
		// TODO Auto-generated method stub
		Transaction tx = null;   
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();  
            tx = session.beginTransaction();
            session.save(u);
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
//1.遍历mysql,打印所有的imageSetId中imageId为1的图片的地址，标签，描述及其相关信息
//2.点击1中生成的图片，获取并将该图集Id下的所有图片打印出来
//3.搜索（同登录）打印含有与搜索项有关的标签中的图集的第一张图
//4.同2	

	@Override
	public String[] firstGet(Upload u) {
		Transaction tx = null; 
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();  
		tx = session.beginTransaction();//
		String hql = "from Upload";  
	    Query query = session.createQuery(hql);  
	    List<Upload> u1 = query.list();  
	    System.out.println("------------SQL执行完毕---------------");
List list = query.list();
String[] c = new String[1000000];
String[] d = new String[1000000];
System.out.println(u1);
//System.out.println(list);与System.out.println(u1);打印的结果一样
//
System.out.println("````````````");
List filedList = getFiledList(list,"upa");
System.out.println(filedList);
System.out.println("````````````");
//
int i = 1;
	    for (Upload us : u1) {  
	        //System.out.println(us.getImageSetId()); 
//获取全部图片的第一张	
	    	while(i!=-1){
	    		 if(us.getImageId()==1){
	 	        	System.out.println(us.getUpa());
				 	System.out.println(us.getLabel1());
				 	System.out.println(us.getLabel2());
				 	System.out.println(us.getLabel3());
				 	System.out.println(us.getDetaildescription());
	 	        	    		 
	    			 c[i] = us.getUpa();
	    			 }
	    		 
	    		 break;
	    		 }
	    		 i++;

	    }
System.out.println("--------");
//System.out.println(c);//打印结果：[Ljava.lang.String;@6fa84dc5
//for(int j = 1;j<8;j++)
//{System.out.println(c[j]);}//打印出来的数组带有空值
//
List<String> tmp = new ArrayList<String>();
for(String str:c){
    if(str!=null && str.length()!=0){
        tmp.add(str);
    }
}
c = tmp.toArray(new String[0]);
System.out.println("9999999");
for(int j=0;j<3;j++)//j的值过大会导致异常：（数组越界）java.lang.ArrayIndexOutOfBoundsException
{System.out.println(c[j]);
//u.setUpa(c[j]);//此方法只能获得最后一个图片地址
}

//
	    tx.commit();
		//return true;
	    return c;//怎么把多个数组的值都传到FirstAction中
	}

	@Override
	public String[] secondGet(Upload u) {
		Transaction tx = null; 
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();  
		tx = session.beginTransaction();//
		String hql = "from Upload";  
	    Query query = session.createQuery(hql);  
	    List<Upload> u1 = query.list();  
	    System.out.println("------------SQL执行完毕---------------");
List list = query.list();
String[] c = new String[1000000];
System.out.println(u1);
//System.out.println(list);与System.out.println(u1);打印的结果一样
System.out.println("````````````");
List filedList = getFiledList(list,"upa");
System.out.println(filedList);
System.out.println("````````````");
//
int i = 1;
	    for (Upload us : u1) {  
	        //System.out.println(us.getImageSetId()); 
	    	while(i!=-1){
	    		 if(us.getImageSetId()!=-1){
	 	        	System.out.println(us.getUpa());
				 		 	        	    		 
	    			 c[i] = us.getUpa();
	    			 }
	    		 
	    		 break;
	    		 }
	    		 i++;

	    }
System.out.println("--------");

//
List<String> tmp = new ArrayList<String>();
for(String str:c){
    if(str!=null && str.length()!=0){
        tmp.add(str);
    }
}
c = tmp.toArray(new String[0]);
System.out.println("9999999");
for(int j=0;j<5;j++)//j的值过大会导致异常：（数组越界）java.lang.ArrayIndexOutOfBoundsException
{System.out.println(c[j]);
//u.setUpa(c[j]);//此方法只能获得最后一个图片地址
}

//
	    tx.commit();
		//return true;
	    return c;//怎么把多个数组的值都传到FirstAction中
	}

	@Override
	public boolean thirdGet(Upload u) {
		Transaction tx = null;
		String hql = "";
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      		hql = "from Upload where detaildescription=? and username=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getDetaildescription());
			query.setParameter(1, u.getUsername());
			List list = query.list();
System.out.println(list);
			//
			System.out.println("````````````");
			List filedList = getFiledList(list,"upa");
System.out.println(filedList);
			System.out.println("````````````");
			//
			tx.commit();
			if(list.size()>0)
			{				
				return true;
			}
			else
			{				
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
}
	
	
	
    			



