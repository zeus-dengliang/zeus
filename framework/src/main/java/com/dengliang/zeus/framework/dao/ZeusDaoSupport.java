package com.dengliang.zeus.framework.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.dengliang.zeus.framework.jdbc.Exception.FrameworkException;
import com.dengliang.zeus.framework.jdbc.util.PageBean;



/**
 * 
 * Zeus(宙斯持久层),希望他是众神之王
 */
public  class ZeusDaoSupport extends JdbcTemplate {
	private static Logger LOGGER = Logger.getLogger(ZeusDaoSupport.class);
	public static final String DBTYPE_ORACLE="ORACLE";
    public static final String DBTYPE_MYSQL="MYSQL";
	private String dbtype=DBTYPE_ORACLE;
	/**
	 * 构造函数
	 * 
	 */
	public ZeusDaoSupport() {
	}
	/**
	 * @param dbtype the dbtype to set
	 */
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	/**
	 * 该方法用来更新数据库的资料
	 * 
	 * @param sql String SQL语句
	 * @return int 返回值为0时，表示更新失败
	 * @exception SQLException  数据库操作失败
	 */
	public int executeUpdate(final String sql) throws SQLException {
		return super.update(sql);
	}

	/**
	 * 根据输入的sql查询出结果集 结果将以List<Map.的方式返回
	 * 
	 * @param sql
	 * @param limit
	 * @return 结果集
	 */
	public List<Map<String, Object>> queryAllList(final String sql,
			final int limit) {
		super.setMaxRows(limit);
		return super.queryForList(sql);
	}

	/**
	 * 根据输入的sql查询出结果集，结果将以Map的方式返回，如果没有结果，返回null

	 * @param sql   Sql语句
	 * @return Map数组
	 * @throws SQLException 查询数据错误
	 * 
	 */
	public Map queryMap(final String sql) throws SQLException {
		super.setMaxRows(1);
		List<Map<String, Object>> listmap = this.queryForList(sql);
		if (listmap != null && listmap.size() == 1) {
			return listmap.get(0);
		}
		return null;
	}

	/**
	 * 根据传入的Sql进行查询，查询结果为Entity,如果没有结果，回传null;
	 * 
	 * @param sql String SQL语句
	 * @param index int 转换为对象的形式
	 * @return Object 查对对象
	 * @throws SQLException 查询数据库错误   转换对象错误
	 */
	@SuppressWarnings("unchecked")
	public Object queryObj(final String sql, final int index)
			throws SQLException, Exception {
		super.setMaxRows(1);
		return super.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet resultset) throws SQLException,
					DataAccessException {
				Object obj = null;
				try {
					if (resultset.next()) {
						switch (index) {
						case 1:
							obj = getObjFromRS1(resultset);
							break;
						case 2:
							obj = getObjFromRS2(resultset);
							break;
						case 3:
							obj = getObjFromRS3(resultset);
							break;
						case 4:
							obj = getObjFromRS4(resultset);
							break;
						case 5:
							obj = getObjFromRS5(resultset);
							break;
						case 6:
							obj = getObjFromRS6(resultset);
							break;
						case 7:
							obj = getObjFromRS7(resultset);
							break;
						default:
							obj = getObjFromRS1(resultset);
							break;
						}
					}
				} catch (Exception e) {
					LOGGER.fatal("queryObjPsmt error" + sql, e);
					throw new FrameworkException("queryObjPsmt error" + sql, e);
				}
				return obj;
			}
		});
	}

	/**
	 * 根据传入的Sql进行查询，查询结果为Entity,如果没有结果，回传null;
	 * 
	 * @param object   Object 传入对象
	 * @param sql    String SQL语句
	 * @param index   int 对象转换方式
	 * @return Object 查询结果
	 * @throws SQLException   查询数据库错误
	 * @throws Exception   转换对象时出错
	 */
	@SuppressWarnings("unchecked")
	public Object queryObjPsmt(final Object object, final String sql,
			final int index) throws SQLException, Exception {
		super.setMaxRows(1);
		return super.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				prepareQuery(pstmt, object);
			}
		}, new ResultSetExtractor() {
			public Object extractData(ResultSet resultset) throws SQLException,
					DataAccessException {
				Object obj = null;
				try {
					if (resultset.next()) {
						switch (index) {
						case 1:
							obj = getObjFromRS1(resultset);
							break;
						case 2:
							obj = getObjFromRS2(resultset);
							break;
						case 3:
							obj = getObjFromRS3(resultset);
							break;
						case 4:
							obj = getObjFromRS4(resultset);
							break;
						case 5:
							obj = getObjFromRS5(resultset);
							break;
						case 6:
							obj = getObjFromRS6(resultset);
							break;
						case 7:
							obj = getObjFromRS7(resultset);
							break;
						default:
							obj = getObjFromRS1(resultset);
							break;
						}
					}
				} catch (Exception e) {
					LOGGER.fatal("queryObjPsmt error" + sql, e);
					throw new FrameworkException("queryObjPsmt error" + sql, e);
				}
				return obj;
			}
		});
	}

	/**
	 * 查询数据，返回为LIST
	 * 
	 * @param sql      String SQL语句
	 * @param index    int LIST中对象的转换方式
	 * @return List 查询结果
	 * @throws SQLException     查询错误
	 * @throws Exception     转换对象错误
	 */
	public List queryObjAll(final String sql, final int index)
			throws SQLException, Exception {
		return this.queryObjAll(sql, -1, index);
	}

	/**
	 * 查询数据
	 * 
	 * @param sql      SQL语句
	 * @param limit      数据库查询对象
	 * @param index      对象转换方式
	 * @return List 查询结果
	 * @throws SQLException        查询数据库错误
	 * @throws Exception     操作失败
	 */
	@SuppressWarnings("unchecked")
	public List queryObjAll(final String sql, final int limit, final int index)
			throws SQLException, Exception {
		super.setMaxRows(limit);
		return  (List)super.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet resultset) throws SQLException,
					DataAccessException {
				List list = new ArrayList();
				Object obj = null;
				try {
					while (resultset.next()) {
						switch (index) {
						case 1:
							obj = getObjFromRS1(resultset);
							break;
						case 2:
							obj = getObjFromRS2(resultset);
							break;
						case 3:
							obj = getObjFromRS3(resultset);
							break;
						case 4:
							obj = getObjFromRS4(resultset);
							break;
						case 5:
							obj = getObjFromRS5(resultset);
							break;
						case 6:
							obj = getObjFromRS6(resultset);
							break;
						case 7:
							obj = getObjFromRS7(resultset);
							break;
						default:
							obj = getObjFromRS1(resultset);
							break;
						}
						list.add(obj);
					}

				} catch (Exception e) {
					LOGGER.fatal("queryObjAll error" + sql, e);
					throw new FrameworkException("queryObjAll error" + sql, e);
				}
				return list;
			}
		});
	}

	

	/* 以下是为了进行预处理语句进行的操作 */
	/**
	 * 新增预处理语句定义，由子类覆写
	 * 
	 * @return 新增预处理的SQL语句
	 * @throws SQLException        新增错误
	 */
	protected String getCreatePreSql() throws SQLException {
		throw new UnsupportedOperationException(
				"getCreatePreSql not Implemented yet ! (executing addBatch..)");
	}

	/**
	 * 修改预处理语句定义，由子类覆写
	 * 
	 * @return 修改的预处理语句
	 * @throws SQLException      操作失败
	 */
	protected String getUpdatePreSql() throws SQLException {
		throw new UnsupportedOperationException(
				"getUpdatePreSql not Implemented yet ! (executing addBatch..)");
	}

	/**
	 * 删除预处理语句定义，由子类覆写
	 * 
	 * @return 删除预处理语句
	 * @throws SQLException       操作失败
	 */
	protected String getDeletePreSql() throws SQLException {
		throw new UnsupportedOperationException(
				"getDeletePreSql not Implemented yet ! (executing addBatch..)");
	}

	/**
	 * 查询预处理语句定义，由子类覆写
	 * 
	 * @return 查询预处理的SQL语句
	 * @throws SQLException         查询数据错误
	 */
	protected String getQueryPreSql() throws SQLException, Exception {

		throw new UnsupportedOperationException(
				"getDeletePreSql not Implemented yet ! (executing addBatch..)");
	}

	/**
	 * 新增预处理语句值的定义，由子类覆写
	 * 
	 * @param obj       新增对象
	 * @throws SQLException         操作失败
	 */
	protected void prepareCreate(PreparedStatement pstmt, Object obj)
			throws SQLException{
		throw new UnsupportedOperationException(
				"prepareCreate not Implemented yet ! (executing addBatch..)");

	}

	/**
	 * 修改预处理语句值的定义，由子类覆写
	 * 
	 * @param obj     修改对象
	 * @throws SQLException    操作失败
	 */
	protected void prepareUpdate(PreparedStatement pstmt, Object obj)
			throws SQLException {
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (executing addBatch..)");

	}

	/**
	 * 删除预处理语句值的定义，由子类覆写
	 * 
	 * @param obj        删除对象
	 * @throws SQLException   操作失败
	 */
	protected void prepareDelete(PreparedStatement pstmt, Object obj)
			throws SQLException{
		throw new UnsupportedOperationException(
				"prepareDelete not Implemented yet ! (executing addBatch..)");

	}

	/**
	 * 查询预处理语句值的定义，由子类覆写
	 * 
	 * @param obj       查询对象
	 * @throws SQLException           操作失败
	 */
	protected void prepareQuery(PreparedStatement pstmt, Object obj)
			throws SQLException {
		throw new UnsupportedOperationException(
				"prepareDelete not Implemented yet ! (executing addBatch..)");

	}

	/**
	 * 批处理-新增方法
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public int[] createBatch(final List list) throws SQLException {
		String sql=this.getCreatePreSql();
		return super.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt, int i) throws SQLException{
					prepareCreate(pstmt, list.get(i));
			}
			public int getBatchSize() {
		            return list.size();
		    }
		});
	}
	/**
	 * 批处理-更新
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public int[] updateBatch(final List list) throws SQLException {
		String sql=this.getUpdatePreSql();
		return super.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt, int i) throws SQLException{
					prepareUpdate(pstmt, list.get(i));
			}
			public int getBatchSize() {
		            return list.size();
		    }
		});
	}
	/**
	 * 批处理-删除
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public int[] deleteBatch(final List list) throws SQLException {
		String sql=this.getDeletePreSql();
		return super.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt, int i) throws SQLException{
					prepareDelete(pstmt, list.get(i));
			}
			public int getBatchSize() {
		            return list.size();
		    }
		});
	}

	public Map[] queryAllMap(String sql, int limit) throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> lisstmap = this.queryAllList(sql,limit);
		Map[] maps = new HashMap[lisstmap.size()];
		return lisstmap.toArray(maps);
	}
    /**以下是dao具体实现类的 字段组装选择器,由继承类再次实现**/
	

	public Object getObjFromRS1(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS1)");
	}

	public Object getObjFromRS2(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS2)");
	}


	public Object getObjFromRS3(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS3)");
	}


	public Object getObjFromRS4(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS4)");
	}


	public Object getObjFromRS5(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS5)");
	}


	public Object getObjFromRS6(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS6)");
	}


	public Object getObjFromRS7(ResultSet resultset) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"prepareUpdate not Implemented yet ! (getObjFromRS7)");
	}
	/**
	 * 
	 * @param sql
	 * @param pageBean
	 * @return 分页数据
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public PageBean queryPageObj(String sql, PageBean pageBean) throws SQLException, Exception{
		return this.queryPageObj(sql, pageBean, 1);
	} 
	/**
	 * 
	 * @param sql
	 * @param pageBean
	 * @param 选择器索引
	 * @return 分页数据
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public PageBean queryPageObj(String sql, PageBean pageBean,final int index) throws SQLException, Exception{
		int recordsTotal=this.queryForInt("select count(*) from ("+sql+") aa");
		pageBean.setRecordsTotal(recordsTotal);
		pageBean.setRecordsFiltered(recordsTotal);
		String pageSQL =this.getPageSQL(sql, dbtype, pageBean.getStart(), pageBean.getLength());
		List<?> list= this.queryObjAll(pageSQL, pageBean.getLength(), index);
		pageBean.setList(list);
		return pageBean;
	} 

	/**
	 * 数据分页查询
	 * 
	 * @param queryString
	 *            :SQL
	 * @param dbType
	 *            :数据库类型
	 * @param startIndex
	 *            ,起始索引
	 * @param pageSize
	 *            ,分页大小
	 * @return
	 */
	private String getPageSQL(String queryString, String dbType,	Integer startIndex, Integer pageSize) {
		String pageSQL = "";
		if (dbType.toUpperCase().equals(DBTYPE_MYSQL)) {
			pageSQL = this.getMySQLPageSQL(queryString, startIndex, pageSize);
		} else if (dbType.toUpperCase().equals(DBTYPE_ORACLE)) {
			pageSQL = this.getOraclePageSQL(queryString, startIndex, pageSize);
		}
		return pageSQL;
	}

	/**
	 * 构造MySQL数据分页SQL
	 * 
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getMySQLPageSQL(String queryString, Integer startIndex,
			Integer pageSize) {
		String result = "";
		if (null != startIndex && null != pageSize) {
			result = queryString + " limit " + startIndex + "," + pageSize;
		} else if (null != startIndex && null == pageSize) {
			result = queryString + " limit " + startIndex;
		} else {
			result = queryString;
		}
		return result;
	}

	/**
	 * 构造 Oracle数据分页SQL
	 * 
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getOraclePageSQL(String queryString, Integer startIndex,
			Integer pageSize) {
		if (StringUtils.isEmpty(queryString)) {
			return null;
		}
		String itemSource = queryString.toLowerCase();
		int endIndex = startIndex + pageSize;
		String endSql = "select * from (select rOraclePageSQL.*,ROWNUM as currentRow from ("
				+ queryString
				+ ") rOraclePageSQL where rownum <="
				+ endIndex
				+ ") where currentRow>" + startIndex;
		return endSql;
	}
}
