package com.dengliang.zeus.webdemo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.dengliang.zeus.webdemo.dao.Dajc01DAO;
import com.dengliang.zeus.webdemo.services.IDajc01Service;
import com.dengliang.zeus.webdemo.vo.Dajc01VO;



/**
 * <b>功能：</b>基本资料系统注册表 ServiceImpl.
 * <b>日期：</b>Wed Jan 07 16:35:47 CST 2015<br>
 * @author 100755
 */
public class Dajc01Service implements IDajc01Service {
	protected static final int RANDOM_ID = Integer.valueOf(RandomStringUtils.randomNumeric(8));
	private Dajc01DAO dajc01DAO;
	
	private static String STATUS_A="A";//新增
	private static String STATUS_B="B";//审核中
	private static String STATUS_C="C";//上线完成
	private static String STATUS_D="D";//作废
	public void setDajc01DAO(Dajc01DAO dajc01DAO) {
		this.dajc01DAO = dajc01DAO;
	}
	
	/**
	 * 查询所有系统信息
	 * @return 所有系统信息
	 * @throws Exception
	 */
	@Override
	public List<Dajc01VO> queryAllSystem() throws Exception {
		// TODO Auto-generated method stub
		return this.dajc01DAO.queryAllSystem();
	}

	@Override
	public void addList(List<Dajc01VO> list) throws Exception {
		// TODO Auto-generated method stub
		this.dajc01DAO.createBatch(list);
		
	}

	@Override
	public void updateList(List<Dajc01VO> list) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(List<Dajc01VO> list) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
