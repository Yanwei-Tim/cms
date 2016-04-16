package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.EquipmentStatus;
import com.hzjava.monitorcenter.utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-10
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentStatusDaoImpl extends MyDaoSupport implements EquipmentStatusDao {
    @Override
    public void setEntityClass() {
        this.entityClass = EquipmentStatus.class;
    }

    @Override
    public PageResult listByPage(String userName, String status, int pageIndex, int limit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PageResult listByParams(int pageIndex, int pageLength, Date startDate, Date endDate, String deviceName) {
        StringBuffer sb = new StringBuffer(" from EquipmentStatus s where 1=1");
        List params = new ArrayList(4);// 手动指定容量，避免多次扩容
        if(startDate!=null){
            sb.append(" and date_format(createtime,'%d %b %Y %T:%f')>= date_format(?,'%d %b %Y %T:%f')");
            params.add(startDate);
        }
        if(endDate!=null){
            sb.append(" and date_format(createtime,'%d %b %Y %T:%f')<= date_format(?,'%d %b %Y %T:%f')");
            params.add(endDate);
        }
        if (StringUtils.isNotBlank(deviceName)) {
            sb.append(" and deviceId = ?");
            params.add(Long.valueOf(deviceName));
        }
        sb.append(" order by createtime desc");
        String countString = "select count(*) " + sb.toString();
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), pageIndex, pageLength);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }



    @Override
    public PageResult listByParams_1(int pageIndex, int pageLength, Date startDate, Date endDate,String deviceName) {
        StringBuffer sb = new StringBuffer(" from EquipmentStatus s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')>= date_format(?,'%d %b %Y %T:%f')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')<= date_format(?,'%d %b %Y %T:%f')");
			params.add(endDate);
		}
        if (StringUtils.isNotBlank(deviceName)) {
			sb.append(" and deviceId = ?");
			params.add(Long.valueOf(deviceName));
		}
        sb.append(" and type = '1'");
        sb.append(" order by createtime desc");
		String countString = "select count(*) " + sb.toString();
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
    }

    @Override
    public PageResult listByParams_2(int pageIndex, int pageLength, Date startDate, Date endDate,String deviceName) {
         StringBuffer sb = new StringBuffer(" from EquipmentStatus s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')>= date_format(?,'%d %b %Y %T:%f')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')<= date_format(?,'%d %b %Y %T:%f')");
			params.add(endDate);
		}
        if (StringUtils.isNotBlank(deviceName)) {
			sb.append(" and deviceId = ?");
			params.add(Long.valueOf(deviceName));
		}
        sb.append(" and type = '2'");
        sb.append(" order by createtime desc");
		String countString = "select count(*) " + sb.toString();
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
    }

    @Override
    public PageResult listByParams_3(int pageIndex, int pageLength, Date startDate, Date endDate,String deviceName) {
         StringBuffer sb = new StringBuffer(" from EquipmentStatus s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')>= date_format(?,'%d %b %Y %T:%f')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')<= date_format(?,'%d %b %Y %T:%f')");
			params.add(endDate);
		}
        if (StringUtils.isNotBlank(deviceName)) {
			sb.append(" and deviceId = ?");
			params.add(Long.valueOf(deviceName));
		}
        sb.append(" and type = '3'");
        sb.append(" order by createtime desc");
		String countString = "select count(*) " + sb.toString();
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
    }

    @Override
    public PageResult listByParams_4(int pageIndex, int pageLength, Date startDate, Date endDate, String deviceName) {
         StringBuffer sb = new StringBuffer(" from EquipmentStatus s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')>= date_format(?,'%d %b %Y %T:%f')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(createtime,'%d %b %Y %T:%f')<= date_format(?,'%d %b %Y %T:%f')");
			params.add(endDate);
		}
        if (StringUtils.isNotBlank(deviceName)) {
			sb.append(" and deviceId = ?");
			params.add(Long.valueOf(deviceName));
		}
        sb.append(" and type = '0'");
        sb.append(" order by createtime desc");
		String countString = "select count(*) " + sb.toString();
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
    }

}
