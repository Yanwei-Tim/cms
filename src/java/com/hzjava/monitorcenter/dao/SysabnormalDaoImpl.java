package com.hzjava.monitorcenter.dao;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.Sysabnormal;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

public class SysabnormalDaoImpl extends MyDaoSupport implements SysabnormalDao {
	private final static Logger logger = Logger.getLogger(SysabnormalDaoImpl.class);

	@Override
	public void setEntityClass() {
		this.entityClass = Sysabnormal.class;
	}

	public PageResult findPage(int pageIndex, int pageLength, Date startDate,
			Date endDate, String eventCode, String objectCode) {
		StringBuffer sb = new StringBuffer(" from Sysabnormal  where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(happenTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(happenTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		if(eventCode!=null){
			sb.append(" and abNormalTypeCode = ?");
			params.add(eventCode);
		}
		if(objectCode!=null){
			sb.append(" and connectObjectCode = ?");
			params.add(objectCode);
		}
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
        if((startDate==null||startDate.equals(""))&&(endDate==null||endDate.equals(""))){
            sb.append(" and date_format(happenTime,'%Y-%m-%d')>= '"+yesterday.trim()+"'");
            sb.append(" and date_format(happenTime,'%Y-%m-%d')<= '"+yesterday.trim()+"'");
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
    public List list(String hql) throws Exception {
        List list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
    @Override
    public List countAll(String queryString)
    {
        Query query = this.getSession().createQuery(queryString);
        List list = query.list();
        return list;
    }
    @Override
    public List countAllMap(String queryString)
    {
        Query query = this.getSession().createQuery(queryString).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }
    /**
     * 统计所有记录的总数
     *
     * @return 总数
     */
    public int countAlls(String queryString)
    {
//        String queryString = "select count(*) from "
//                + getPersistentClass().getName();
        Query query = this.getSession().createQuery(queryString);
        List list = query.list();
        Long result = (Long) list.get(0);
        return result.intValue();
    }
}
