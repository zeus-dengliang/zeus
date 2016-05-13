package com.dengliang.zeus.framework.jdbc.ds;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
/**
 * 事务管理
 * @author 100755_邓亮 2014年1月1日
 *
 */
public class DbDataSourceTransactionManager  extends DataSourceTransactionManager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1749133882378388130L;
	private static final String Master="master";
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		// TODO Auto-generated method stub
		//默认第0个数据库是主库
		DbContextHolder.setDbNum(Master);
		super.doBegin(transaction, definition);
	}
}
