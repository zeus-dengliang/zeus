package com.dengliang.zeus.webdemo.dao;

import java.util.List;

import com.dengliang.zeus.webdemo.dao.base.Dajc01DAOBase;
import com.dengliang.zeus.webdemo.vo.Dajc01VO;
/**
 * 基本资料系统注册表 DAO.
 * <pre>
 * Table Name        : TBDA01
 * Table Description : 基本资料系统注册表
 * Value Object Name : Dajc01VO
 * </pre>
 * @see    Dajc01DAOBase
 * @author 100755
 */
public class Dajc01DAO extends Dajc01DAOBase {
	/**
	 * 查询所有系统
	 * @return 查询所有系统
	 * @throws Exception
	 */
	public List<Dajc01VO> queryAllSystem() throws Exception {
		StringBuffer sql=new StringBuffer("select * from tbda01");
		System.out.println("query tbda01");
		return this.queryObjAll(sql.toString(), 0);
	}
}
