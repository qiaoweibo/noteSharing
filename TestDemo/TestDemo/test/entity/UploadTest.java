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

public class UploadTest {
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
		//Upload u = new Upload(1,"","http://192.168.1.100:8080/Scxz/images/img2-lg.jpg");
	Upload u = new Upload(1,1,"http://192.168.1.100:8080/Scxz/images/img0-lg.jpg","两仪式","动漫","带刀","女性",43,"张三");
		session.save(u);
	}

}
