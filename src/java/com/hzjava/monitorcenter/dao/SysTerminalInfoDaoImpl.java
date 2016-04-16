package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.SysTerminalInfo;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SysTerminalInfoDaoImpl extends MyDaoSupport implements
		SysTerminalInfoDao {
	private final static Logger logger = Logger
			.getLogger(SysTerminalInfoDaoImpl.class);

	@Override
	public void setEntityClass() {
		this.entityClass = SysTerminalInfo.class;
	}

	public PageResult findPage(int pageIndex, int pageLength, String userName,
			String policeId, String userDepart, String userZone, String ifCancel) {
		StringBuffer sb = new StringBuffer(" from SysTerminalInfo s where 1=1");
		List params = new ArrayList(5);// 手动指定容量，避免多次扩容
		if (!StringUtils.isBlank(policeId)) {
			sb.append(" and policeId = ?");
			params.add(policeId);
		}
		if (!StringUtils.isBlank(userDepart)) {
			sb.append(" and userDepart = ?");
			params.add(userDepart);
		}
		if (!StringUtils.isBlank(userZone)) {
			sb.append(" and userZone = ?");
			params.add(userZone);
		}
		if (!StringUtils.isBlank(ifCancel)) {
			sb.append(" and ifCancel = ?");
			params.add(ifCancel);
		}
		if (!StringUtils.isBlank(userName)) {
			sb.append(" and userName like ?");
			params.add("%" + userName + "%");
		}

		String countString = "select count(*) " + sb.toString();

		sb.append(" order by id desc");
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

    @Override
    public void saveMany(List<SysTerminalInfo> list) throws Exception {
        Session session = getSession();
        Transaction tx= session.beginTransaction(); //使用Hibernate事务处理边界

        for(int i=0;i<list.size();i++) {
            session.save(list.get(i));
            // 以每50个数据作为一个处理单元
            if(i%50==0) {
            // 只是将Hibernate缓存中的数据提交到数据库，保持与数据库数据的同步
                session.flush();
            // 清除内部缓存的全部数据，及时释放出占用的内存
                session.clear();
            }
        }
//        tx.commit();
    }

    @Override
    public void truncate() throws Exception {
        String sql = "truncate systerminalinfo";
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.executeUpdate();
    }

}
