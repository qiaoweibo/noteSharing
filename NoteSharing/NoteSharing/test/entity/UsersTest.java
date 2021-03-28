package entity;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsersTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
		
	}
	
	@After
	public void destory(){
		
		transaction.commit();//提交事务
		session.close();//关闭会话
		sessionFactory.close();//关闭会话工厂 
	}
	
	@Test
	public void testSaveUsers(){
		Users u = new Users(1,"张三","123456","男","6778907","http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
		session.save(u);
	}

}
