package com.dengliang.zeus.webdemo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dengliang.zeus.webdemo.services.IDajc01Service;
import com.dengliang.zeus.webdemo.util.UIDGenerator;
import com.dengliang.zeus.webdemo.vo.Dajc01VO;


//
/**
 * zeus 持久层事务测试 由于打包时要调用 test方法 ,如果测试手工添加@Test
 * @author 100755_邓亮 2015年4月28日
 *
 */
public class ZeusTransactionTest {
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
						"applicationContext-service.xml",
						"applicationContext-transaction.xml");
			}
		}
		return context.getBean(id);
	}
	@Test
	public void addTransactionTest() {
		// 新增测试
		IDajc01Service service = (IDajc01Service) ZeusTransactionTest.getBean("dajc01Service");
		try {
			List<Dajc01VO> list=new ArrayList();
			for (int i = 0; i < 10; i++) {
				Dajc01VO da001 = new Dajc01VO();
				da001.setUuid(UIDGenerator.getUUID());
				da001.setSysId(i+"");
				list.add(da001);
			}
			service.addList(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
