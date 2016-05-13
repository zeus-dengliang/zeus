package com.dengliang.zeus.webdemo.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dengliang.zeus.framework.dao.ZeusDaoSupport;
import com.dengliang.zeus.framework.jdbc.Exception.FrameworkException;
import com.dengliang.zeus.webdemo.vo.Dajc01VO;

/**
 * 基本资料系统注册表 DAO.
 * <pre>
 * Table Name        : TBDA01
 * Table Description : 基本资料系统注册表
 * Value Object Name : Dajc01VOBase
 * </pre>
 * @author 100755
 */
public abstract class Dajc01DAOBase extends ZeusDaoSupport {
    public static final String APPID = "Dajc01DAOBase".toUpperCase();
    public static final String CLASS_VERSION = "$Id$";
    /* 主键 输入栏位长度 */
    public static final int UUID_LEN = 32;
    /* 系统id 输入栏位长度 */
    public static final int SYSID_LEN = 50;
    /* 系统名 输入栏位长度 */
    public static final int SYSNAME_LEN = 100;
    /* starteam项目 输入栏位长度 */
    public static final int STARTEAMPRO_LEN = 100;
    /* starteam视图 输入栏位长度 */
    public static final int STARTEAMVIEW_LEN = 100;
    /* 创建人 输入栏位长度 */
    public static final int CREATEEMP_LEN = 32;
    /* 创建时间 输入栏位长度 */
    public static final int CREATETIME_LEN = 20;
    /* 修改人 输入栏位长度 */
    public static final int UPDATEEMP_LEN = 32;
    /* 修改时间 输入栏位长度 */
    public static final int UPDATETIME_LEN = 20;

/*----------------------------------------------------------------------------*/
/* Creates new Dajc01VOBase
/*----------------------------------------------------------------------------*/
    /**
     * 构造方法
     */
    public Dajc01DAOBase() {
    }

/*----------------------------------------------------------------------------*/
/* public methods
/*----------------------------------------------------------------------------*/
    
    /**
     * 产生用主键查询语句
     * @param  uuid 主键 - 单笔资料主键
     * @return String SQL语句
     */
    public String getFindByPKSql(String uuid) {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("SELECT * FROM TBDA01");
        sqlStr.append(" WHERE uuid='"+uuid+"' ");
        return sqlStr.toString();
    }

    /**
     * 以主键来查询资料
     * <p>
     * @param  uuid 主键 - 单笔资料主键
     * @return Dajc01VO - 单笔资料
     * @exception SQLException - 资料库错误
     * @exception Exception - 资料库错误
     */
    public Dajc01VO findByPK(String uuid) throws SQLException, Exception {
        String sqlstr = getFindByPKSql(uuid);
        return (Dajc01VO) this.queryObj(sqlstr, 1);
    }

    /**
     * 新增一笔资料 throws
     * <p>
     * @param Dajc01VO - Value Object
     * @return int - 交易笔数
     * @exception DejcUpdateException - 新增时候的错误讯息
     * @exception SQLException - 资料库错误
     */
    public int create(Dajc01VO dajc01VO)
        throws FrameworkException, SQLException {
        this.verify(dajc01VO);
        List<Dajc01VO> list = new ArrayList<Dajc01VO>();
        list.add(dajc01VO);
        return super.createBatch(list)[0];
    }

    /**
     * 新增多笔资料 throws
     * <p>
     * @param dajc01VOList - Value Object
     * @return int - 交易笔数
     * @exception DejcUpdateException -修改时候的错误讯息
     * @exception SQLException - 资料库错误
     */
    public int createList(List<Dajc01VO> dajc01VOList)
        throws FrameworkException, SQLException {
        int[] count = super.createBatch(dajc01VOList);
        return count.length;
    }

    /**
     * 删除资料
     * <p>
     * @param  uuid 主键 - 单笔资料主键
     * @return int - 交易笔数
     * @exception SQLException - 资料库错误
     */
    public int remove(String uuid)
        throws SQLException {
    	Dajc01VO dajc01VO=new Dajc01VO();
		dajc01VO.setUuid(uuid);
        return this.remove(dajc01VO);
    }

    /**
     * 删除多笔资料 throws
     * <p>
     * @param dajc01VOList 欲删除的资料物件
     * @return int - 交易笔数
     * @exception SQLException - 资料库错误
     * @exception DejcUpdateException - 无任何资料被删除
     */
    public int removeList(List<Dajc01VO> dajc01VOList)
        throws FrameworkException, SQLException {
    	 int[] count = super.deleteBatch(dajc01VOList);
         return count.length;
    }

    /**
     * 删除资料 throws
     * <p>
     * @param dajc01VO 欲删除的资料物件
     * @return int - 交易笔数
     * @exception SQLException - 资料库错误
     */
    public int remove(Dajc01VO dajc01VO)
        throws SQLException {
    	List<Dajc01VO> list = new ArrayList<Dajc01VO>();
        list.add(dajc01VO);
        return super.deleteBatch(list)[0];
    }

    /**
     * 修改资料
     * <p>
     * @param dajc01VO 欲修改的资料物件
     * @return int - 交易笔数
     * @exception SQLException - 资料库错误
     * @exception DejcUpdateException - 无任何资料被修改
     */
    public int update(Dajc01VO dajc01VO)
        throws FrameworkException, SQLException {
    	this.verify(dajc01VO);
        List<Dajc01VO> list = new ArrayList<Dajc01VO>();
        list.add(dajc01VO);
        return super.updateBatch(list)[0];
    }
    /**
     * 修改多笔资料 throws
     * <p>
     * @param dajc01VOList 欲修改的资料物件
     * @return int - 交易笔数
     * @exception SQLException - 资料库错误
     * @exception DejcUpdateException - 无任何资料被修改
     */
    public int updateList(List<Dajc01VO> dajc01VOList)
        throws FrameworkException, SQLException {
    	int[] count = super.updateBatch(dajc01VOList);
        return count.length;
    }

    /**
     * 执行 addCreateBatch(Object obj) 时需要用到的 sql
     * 新增资料的 prepareStatement sql<br>
     * 此方法是覆写 JLDeDaoSupport 的方法
     * @return String SQL语句
     * @exception SQLException - 资料库错误
     */
    protected String getCreatePreSql() throws SQLException {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("INSERT INTO TBDA01 (");
		sqlStr.append("uuid, sysId, sysName, ");
		sqlStr.append("starteamPro, starteamView, createEmp, ");
		sqlStr.append("createTime, updateEmp, updateTime");
		sqlStr.append(") values (");
		sqlStr.append("?, ?, ?, ");
		sqlStr.append("?, ?, ?, ");
		sqlStr.append("?, ?, ?");
		sqlStr.append(")");
        return sqlStr.toString();
    }

    /**
     * 执行 addupdateBatch(Object obj) 时需要用到的 sql
     * 修改资料的 prepareStatement sql<br>
     * 此方法是覆写 JLDeDaoSupport 的方法
     * @return String SQL语句
     * @exception SQLException - 资料库错误
     */
    protected String getUpdatePreSql() throws SQLException {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("UPDATE TBDA01 SET ");
		sqlStr.append("uuid=?, sysId=?, sysName=?, ");
		sqlStr.append("starteamPro=?, starteamView=?, createEmp=?, ");
		sqlStr.append("createTime=?, updateEmp=?, updateTime=?");
		sqlStr.append(" WHERE uuid=? ");
		
        return sqlStr.toString();
    }

    /**
     * 执行 addDeleteBatch(Object obj) 时需要用到的 sql
     * 删除资料的 prepareStatement sql<br>
     * 此方法是覆写 JLDeDaoSupport 的方法
     * @return String SQL语句
     * @exception SQLException - 资料库错误
     */
    protected String getDeletePreSql() throws SQLException {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("DELETE FROM TBDA01");
        sqlStr.append(" WHERE uuid=? ");
        return sqlStr.toString();
    }

    /**
     * 执行 addQueryBatch(Object obj) 时需要用到的 sql
     * 查询资料的 prepareStatement sql<br>
     * 此方法是覆写 JLDeDaoSupport 的方法
     * @return String SQL语句
     * @exception SQLException - 资料库错误
     */
    protected String getQueryPreSql() throws SQLException {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("SELECT * FROM TBDA01");
        sqlStr.append(" WHERE uuid=?  ");
        return sqlStr.toString();
    }

    /**
     * 执行 addCreateBatch(Object obj) 时需要呼叫的方法
     * @param obj 预处理对象
     * @exception SQLException - 资料库错误
     */
    protected void prepareCreate(PreparedStatement pstmt, Object obj) throws SQLException {
        Dajc01VO dajc01VO = (Dajc01VO) obj;
		pstmt.setString(1, dajc01VO.getUuid());
		pstmt.setString(2, dajc01VO.getSysId());
		pstmt.setString(3, dajc01VO.getSysName());
		pstmt.setString(4, dajc01VO.getStarteamPro());
		pstmt.setString(5, dajc01VO.getStarteamView());
		pstmt.setString(6, dajc01VO.getCreateEmp());
		pstmt.setString(7, dajc01VO.getCreateTime());
		pstmt.setString(8, dajc01VO.getUpdateEmp());
		pstmt.setString(9, dajc01VO.getUpdateTime());
    }

    /**
     * 执行 addUpdateBatch(Object obj) 时需要呼叫的方法
     * @param obj 预处理对象
     * @exception SQLException - 资料库错误
     */
    protected void prepareUpdate(PreparedStatement pstmt,Object obj) throws SQLException {
        Dajc01VO dajc01VO = (Dajc01VO) obj;
		pstmt.setString(1, dajc01VO.getUuid());
		pstmt.setString(2, dajc01VO.getSysId());
		pstmt.setString(3, dajc01VO.getSysName());
		pstmt.setString(4, dajc01VO.getStarteamPro());
		pstmt.setString(5, dajc01VO.getStarteamView());
		pstmt.setString(6, dajc01VO.getCreateEmp());
		pstmt.setString(7, dajc01VO.getCreateTime());
		pstmt.setString(8, dajc01VO.getUpdateEmp());
		pstmt.setString(9, dajc01VO.getUpdateTime());
		pstmt.setString(10, dajc01VO.getUuid());
    }

    /**
     * 执行 addDeleteBatch(Object obj) 时需要呼叫的方法
     * @param obj 预处理对象
     * @exception SQLException - 资料库错误
     */
    protected void prepareDelete(PreparedStatement pstmt,Object obj) throws SQLException {
        Dajc01VO dajc01VO = (Dajc01VO) obj;
		pstmt.setString(1, dajc01VO.getUuid());
    }

    /**
     * 执行 addQueryBatch(Object obj) 时需要呼叫的方法
     * @param obj 预处理对象
     * @exception SQLException - 资料库错误
     */
    protected void prepareQuery(PreparedStatement pstmt,Object obj) throws SQLException {
        Dajc01VO dajc01VO = (Dajc01VO) obj;
		pstmt.setString(1, dajc01VO.getUuid());
    }

    /**
     * 实作父类别
     * <p>
     * @param rs 记录集
     * @return Object 实例对象
     * @exception SQLException - 资料库错误
     * @since $LAST_UPDATE
     */
    public Object getObjFromRS1(ResultSet rs) throws SQLException {
    	Dajc01VO dajc01VO = new Dajc01VO();
		dajc01VO.setUuid(rs.getString("uuid"));
		dajc01VO.setSysId(rs.getString("sysId"));
		dajc01VO.setSysName(rs.getString("sysName"));
		dajc01VO.setStarteamPro(rs.getString("starteamPro"));
		dajc01VO.setStarteamView(rs.getString("starteamView"));
		dajc01VO.setCreateEmp(rs.getString("createEmp"));
		dajc01VO.setCreateTime(rs.getString("createTime"));
		dajc01VO.setUpdateEmp(rs.getString("updateEmp"));
		dajc01VO.setUpdateTime(rs.getString("updateTime"));
		return dajc01VO;
    }

    /**
     * 实作父类别, 使用 user 传进来的 object, 不另行 new , 以节省执行时间及记忆体<br>
     * 适合临时性的资料运算(通常在运算大量资料时，一些栏位需要暂存在 value object)<br>
     * 此方法只会在执行 findByPK(Object obj,$TABLE.getKeyAttribsParamDef()) 时才被呼叫。
     * <p>
     * @param obj 用户传入的对象
     * @param rs 记录集
     * @return Object 查询结果
     * @exception SQLException - 资料库错误
     * @since $LAST_UPDATE
     */
    protected Object getObjFromRS(Object obj, ResultSet rs) throws SQLException {
    	Dajc01VO dajc01VO = (Dajc01VO) obj;
		dajc01VO.setUuid(rs.getString("uuid"));
		dajc01VO.setSysId(rs.getString("sysId"));
		dajc01VO.setSysName(rs.getString("sysName"));
		dajc01VO.setStarteamPro(rs.getString("starteamPro"));
		dajc01VO.setStarteamView(rs.getString("starteamView"));
		dajc01VO.setCreateEmp(rs.getString("createEmp"));
		dajc01VO.setCreateTime(rs.getString("createTime"));
		dajc01VO.setUpdateEmp(rs.getString("updateEmp"));
		dajc01VO.setUpdateTime(rs.getString("updateTime"));
    	return dajc01VO;
    }

    /**
     * 根据key进行预处理查询
     * @param dajc01VO 传入KEY的对象
     * @return Dajc01VOBase 返回查询结果
     * @exception SQLException - 资料库错误
     * @exception Exception - 资料库错误
     * @since $LAST_UPDATE
     */
    public Dajc01VO findByPKPsmt(Dajc01VO dajc01VO) throws SQLException, Exception {
        return (Dajc01VO) this.queryObjPsmt(dajc01VO, this.getQueryPreSql(), 1);
    }

/* Don't Extend Your Code above , or All changes will lose after next Generating Code */
    /**
     * 检查资料的正确性 (包括长度，型态)
     * @return true - ok; false - hasErr
     */
    public void verify(Dajc01VO dajc01vo) {
    	StringBuffer message = new StringBuffer();
		if (dajc01vo.getUuid() == null || "".equals(dajc01vo.getUuid().trim())) {
			message.append("主键栏位不能为空!");
		}
		if (dajc01vo.getUuid() != null && dajc01vo.getUuid().trim().length() > UUID_LEN){
			message.append("主键栏位的资料长度不得超过[" + UUID_LEN + "]");
		}
		if (dajc01vo.getSysId() != null && dajc01vo.getSysId().trim().length() > SYSID_LEN){
			message.append("系统id栏位的资料长度不得超过[" + SYSID_LEN + "]");
		}
		if (dajc01vo.getSysName() != null && dajc01vo.getSysName().trim().length() > SYSNAME_LEN){
			message.append("系统名栏位的资料长度不得超过[" + SYSNAME_LEN + "]");
		}
		if (dajc01vo.getStarteamPro() != null && dajc01vo.getStarteamPro().trim().length() > STARTEAMPRO_LEN){
			message.append("starteam项目栏位的资料长度不得超过[" + STARTEAMPRO_LEN + "]");
		}
		if (dajc01vo.getStarteamView() != null && dajc01vo.getStarteamView().trim().length() > STARTEAMVIEW_LEN){
			message.append("starteam视图栏位的资料长度不得超过[" + STARTEAMVIEW_LEN + "]");
		}
		if (dajc01vo.getCreateEmp() != null && dajc01vo.getCreateEmp().trim().length() > CREATEEMP_LEN){
			message.append("创建人栏位的资料长度不得超过[" + CREATEEMP_LEN + "]");
		}
		if (dajc01vo.getCreateTime() != null && dajc01vo.getCreateTime().trim().length() > CREATETIME_LEN){
			message.append("创建时间栏位的资料长度不得超过[" + CREATETIME_LEN + "]");
		}
		if (dajc01vo.getUpdateEmp() != null && dajc01vo.getUpdateEmp().trim().length() > UPDATEEMP_LEN){
			message.append("修改人栏位的资料长度不得超过[" + UPDATEEMP_LEN + "]");
		}
		if (dajc01vo.getUpdateTime() != null && dajc01vo.getUpdateTime() .trim().length() > UPDATETIME_LEN){
			message.append("修改时间栏位的资料长度不得超过[" + UPDATETIME_LEN + "]");
		}
		if(message.toString().length() > 0) {
			throw new FrameworkException(message.toString());
		}
    }

} // end of Class Acjc001DAOBase

