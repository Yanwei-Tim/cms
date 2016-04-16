package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.ParentExchangePlatform;
import com.hzjava.monitorcenter.domain.ProvinceExchangePlatform;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 级联上报所有业务逻辑
 * 
 * @author xiangqi.java@gmail.com
 * 
 */
public interface JlsbService {
	public PageResult listSysruntimeByPage(int pageIndex, int pageLength,
                                           Date startDate, Date endDate);

	public PageResult listSysabnormalByPage(int pageIndex, int pageLength,
                                            Date startDate, Date endDate, String eventCode, String objectCode);

	public PageResult listSysbizstatusByPage(int pageIndex, int pageLength,
                                             Date startDate, Date endDate);

	public PageResult listSysstatusByPage(int pageIndex, int pageLength,
                                          Date startDate, Date endDate);

	public PageResult listLowerSysruntimeByPage(int pageIndex, int pageLength,
                                                Date startDate, Date endDate, String idSystem);

	public PageResult listLowerSysabnormalByPage(int pageIndex, int pageLength,
                                                 Date startDate, Date endDate, String idSystem);

	public PageResult listLowerSysbizstatusByPage(int pageIndex,
                                                  int pageLength, Date startDate, Date endDate, String idSystem);

	public PageResult listLowerSysstatusByPage(int pageIndex, int pageLength,
                                               Date startDate, Date endDate, String idSystem);

	public Map getParentEpConfIndexModel(int pageIndex, int pageLength);

	public Map getProvinceEpConfIndexModel(int pageIndex, int pageLength);

	public void newParentExchangePlatform(ParentExchangePlatform entry,
                                          List fileItems);

	public void newProvinceExchangePlatform(ProvinceExchangePlatform entry, List fileItems);

	public void updateParentExchangePlatform(ParentExchangePlatform entry,
                                             List fileItems);

	public void updateProvinceExchangePlatform(ProvinceExchangePlatform entry, List fileItems);

	public void deleteParentExchangePlatform(String[] idsArr, Account account);

	public void deleteProvinceExchangePlatform(String[] idsArr, Account account);

	public Map getParentEpAddModel();

	public Map getParentEpDetailModel(Long id);

	public Map getParentEpUpdateModel(Long id);

	public Map getProvinceEpAddModel();

	public Map getProvinceEpDetailModel(Long id);

	public Map getProvinceEpUpdateModel(Long id);

	public String findEventAll();

	public String findObjectAll();

    public String findParentExchangePlatformKeyValue();

    public String selectSysQueryService(String objectName,int start, int limit) throws Exception;

    public String monitorSysQueryService(String objectName, String platformId) throws Exception;

    public String deleteSysQueryService(long[] idArray, String platformId) throws Exception;
}
