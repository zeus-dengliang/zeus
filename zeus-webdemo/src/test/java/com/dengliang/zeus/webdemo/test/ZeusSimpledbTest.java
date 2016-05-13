package com.dengliang.zeus.webdemo.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dengliang.zeus.webdemo.dao.Dajc01DAO;
import com.dengliang.zeus.webdemo.vo.Dajc01VO;

/**
 * zeus持久层基本连接测试
 */
public class ZeusSimpledbTest {
	private static ApplicationContext context;
	/**
	 * 获取bean
	 * 
	 * @param id
	 * @return
	 */
	public static Object getBean(String id) {
		if (context == null) {
			synchronized (id) {
				context = new ClassPathXmlApplicationContext(
						"applicationContext-simpledb.xml",
						"applicationContext-dao.xml",
						"applicationContext-service.xml");
			}
		}
		return context.getBean(id);
	}
	@Test
	public void testDataSourceInterceptor() {
		// 只读测试
		Dajc01DAO service = (Dajc01DAO) ZeusSimpledbTest.getBean("dajc01DAO");
		try {
			Dajc01VO davo=service.findByPK("e09bb3c4d53c4663b86b427a2b83874a");
			System.out.println("aaaaaaaaaaaaaaaafdfdas"+davo.getCreateEmp()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
