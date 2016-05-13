package com.dengliang.zeus.framework.jdbc.sharding;

import com.dengliang.zeus.framework.jdbc.object.DbResult;

public interface ShardingStrategy {

	public DbResult getShardingTableName(String tableName, Object value);

	public DbResult getShardingTableNameById(String tableName, Object idValue);

	public int getTableNum();

	public int getIdSubNum();

	public int getDbNum();

}
