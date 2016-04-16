package com.hzjava.monitorcenter.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.LowerSysabnormal;

public class LowerSysabnormalDaoImpl extends MyDaoSupport implements LowerSysabnormalDao {
	private final static Logger logger = Logger.getLogger(LowerSysabnormalDaoImpl.class);

	@Override
	public void setEntityClass() {
		this.entityClass = LowerSysabnormal.class;
	}

	public PageResult findPage(int pageIndex, int pageLength, Date startDate,
			Date endDate, String idSystem) {
		StringBuffer sb = new StringBuffer(" from LowerSysabnormal s where 1=1");
		List params = new ArrayList(2);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(happenTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(happenTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		if(!StringUtils.isBlank(idSystem)){
			sb.append(" and idSystem = ?");
			params.add(idSystem);
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

}
