package com.dengliang.zeus.webdemo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dengliang.zeus.webdemo.services.IDajc01Service;
import com.dengliang.zeus.webdemo.services.impl.Dajc01Service;
import com.dengliang.zeus.webdemo.util.UIDGenerator;
import com.dengliang.zeus.webdemo.vo.Dajc01VO;


/**
 * zeus读写分离单元测试
 * @author 100755_邓亮 2015年5月8日
 *
 */
public class ZeusMasterSlaveTest {
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
						"applicationContext-dao.xml",
						"applicationContext-service.xml",
						"applicationContext-Master-Slave.xml");
			}
		}
		return context.getBean(id);
	}
	@Test
	public void slaveTest() {
		// 只读测试
		Dajc01Service service = (Dajc01Service) ZeusMasterSlaveTest.getBean("dajc01Service");
		try {
			List<Dajc01VO> dalist=service.queryAllSystem();
			Assert.assertEquals(null, dalist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	@Test
	public void masterTest() {
		//主库上新增,再查询
		IDajc01Service service = (IDajc01Service) ZeusMasterSlaveTest.getBean("dajc01Service");
		try {
			List<Dajc01VO> list=new ArrayList();
			for (int i = 0; i < 10; i++) {
				Dajc01VO da001 = new Dajc01VO();
				da001.setUuid(UIDGenerator.getUUID());
				da001.setSysId(i+"");
				list.add(da001);
			}
			service.addList(list);
			
			List<Dajc01VO> dalist=service.queryAllSystem();
			Assert.assertNotNull(dalist); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
