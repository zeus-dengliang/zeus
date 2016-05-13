package com.dengliang.zeus.framework.jdbc.ds;
/**
 * zeus datasour holder
 * @author 100755_邓亮 2015年1月1日
 *
 */
public class DbContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static void setDbNum(String dbNum) {
		contextHolder.set(dbNum);
	}

	public static String getDbNum() {
		return contextHolder.get();
	}

}
