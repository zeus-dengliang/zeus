package com.dengliang.zeus.framework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IZeusDAOBase {

	/**
	 * 该方法用来更新数据库的资料
	 * 
	 * @param sql
	 *            String SQL语句
	 * @return int 返回值为0时，表示更新失败
	 * @exception SQLException
	 *                数据库操作失败
	 */
	public int executeUpdate(final String sql) throws SQLException;

	/**
	 * 根据输入的sql查询出结果集，结果将以Map数组的方式返回，如果没有结果，返回null
	 * 
	 * @param sql
	 *            Sql语句
	 * @param limit
	 *            如果该值大于0，则限制每次查询笔数。
	 * @return Map数组
	 * @throws SQLException
	 */
	public Map[] queryAllMap(final String sql, final int limit)
			throws SQLException;

	/**
	 * 根据输入的sql查询出结果集 结果将以List<Map.的方式返回
	 * 
	 * @param sql
	 * @param limit
	 * @return 结果集
	 */
	public List<Map<String, Object>> queryAllList(final String sql,
			final int limit);

	/**
	 * 根据输入的sql查询出结果集，结果将以Map的方式返回，如果没有结果，返回null
	 * 
	 * @param sql
	 *            Sql语句
	 * @return Map数组
	 * @throws SQLException
	 *             查询数据错误
	 * 
	 */
	public Map queryMap(final String sql) throws SQLException;

	/**
	 * 根据传入的Sql进行查询，查询结果为Entity,如果没有结果，回传null;
	 * 
	 * @param sql
	 *            String SQL语句
	 * @param index
	 *            int 转换为对象的形式
	 * @return Object 查对对象
	 * @throws SQLException
	 *             查询数据库错误 转换对象错误
	 */
	@SuppressWarnings("unchecked")
	public Object queryObj(final String sql, final int index)
			throws SQLException, Exception;

	/**
	 * 根据传入的Sql进行查询，查询结果为Entity,如果没有结果，回传null;
	 * 
	 * @param object
	 *            Object 传入对象
	 * @param sql
	 *            String SQL语句
	 * @param index
	 *            int 对象转换方式
	 * @return Object 查询结果
	 * @throws SQLException
	 *             查询数据库错误
	 * @throws Exception
	 *             转换对象时出错
	 */
	@SuppressWarnings("unchecked")
	public Object queryObjPsmt(final Object object, final String sql,
			final int index) throws SQLException, Exception;

	/**
	 * 查询数据，返回为LIST
	 * 
	 * @param sql
	 *            String SQL语句
	 * @param index
	 *            int LIST中对象的转换方式
	 * @return List 查询结果
	 * @throws SQLException
	 *             查询错误
	 * @throws Exception
	 *             转换对象错误
	 */
	public List queryObjAll(final String sql, final int index)
			throws SQLException, Exception;


	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS1(ResultSet resultset) throws SQLException,
			Exception;

	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS2(ResultSet resultset) throws SQLException,
			Exception;

	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS3(ResultSet resultset) throws SQLException,
			Exception;

	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS4(ResultSet resultset) throws SQLException,
			Exception;

	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS5(ResultSet resultset) throws SQLException,
			Exception;

	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS6(ResultSet resultset) throws SQLException,
			Exception;

	/**
	 * 该方法用来将获取的ResultSet转变为相对应的VO，此方法需要DAO复写
	 * 
	 * @param resultset
	 *            ResultSet 查询结果集
	 * @return Object 转换后的对象
	 * @throws SQLException
	 *             操作数据库失败
	 * @throws Exception
	 *             操作失败
	 */
	public Object getObjFromRS7(ResultSet resultset) throws SQLException,
			Exception;


	/**
	 * 批处理-新增方法
	 * @param <T>
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public  int[] createBatch( List list) throws SQLException;

	/**
	 * 批处理-更新
	 * @param <T>
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public  int[] updateBatch( List list) throws SQLException;

	/**
	 * 批处理-删除
	 * @param <T>
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public  int[] deleteBatch( List list) throws SQLException;

}
