package com.dengliang.zeus.framework.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dengliang.zeus.framework.jdbc.core.ZeusJdbcAccessor;

public class ZeusShardingDaoSupport extends ZeusJdbcAccessor implements	IZeusDAOBase {

	/**
	 * 该方法用来更新数据库的资料
	 * 
	 * @param sql String SQL语句
	 * @return int 返回值为0时，表示更新失败
	 * @exception SQLException  数据库操作失败
	 */
	public int executeUpdate(final String sql) throws SQLException {
		throw new UnsupportedOperationException(
				"sharding  not support this method ");
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
}
