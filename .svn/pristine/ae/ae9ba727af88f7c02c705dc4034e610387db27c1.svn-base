package com.dengliang.zeus.framework.jdbc.util;

import java.util.List;

/**
 * 分页查询使用
 * <pre>
 *  1、开始--		start
 *  2、最大返回结果集 --	length
 *  3、过滤后最大行数	recordsFiltered
 *  4、数据库最大行数	recordsTotal
 *  4、当前页记录	list
 * </pre>
 */
public class PageBean {
	
	/**
	 * 作为返回页面使用的key值（转为JSON对象时的变量）
	 */
	public static final String PAGE_BEAN_KEY = "jlPage";
	
	private int draw;  
	private int start;   
	private int length;  
	private int recordsFiltered;
	private int recordsTotal;
	private List<?> list;

	/**
	 * 默认构造方法
	 */
	public PageBean() {
		super();
	}



	/**
	 * 构造方法
	 * @param draw
	 * @param start
	 * @param length
	 */
	public PageBean(int draw, int start, int length) {
		this.draw = draw;
		this.start = start;
		this.length = length;
	}



	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}



	/**
	 * @return the recordsFiltered
	 */
	public int getRecordsFiltered() {
		return recordsFiltered;
	}


	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}


	/**
	 * @return the recordsTotal
	 */
	public int getRecordsTotal() {
		return recordsTotal;
	}


	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * @return the list
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

	
}
