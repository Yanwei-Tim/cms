package com.hzjava.monitorcenter.service;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.BusinessRegister;
import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.domain.ExchangePlatform;
import com.hzjava.monitorcenter.domain.ExtLink;
import com.hzjava.monitorcenter.domain.IntLink;
import com.hzjava.monitorcenter.domain.Jbqk;
import com.hzjava.monitorcenter.domain.MonitorAgent;
import com.hzjava.monitorcenter.domain.Sbpz;

/**
 * 配置管理
 * 
 * @author collin.code@gmail.com
 * 
 */
public interface ConfService {
    public String showBusinessCode()throws Exception;
    public String showEquType()throws Exception;
    public String showEquTypes()throws Exception;
	void deleteBusinessRegisters(String[] ids, Account account);

	void deleteEquipments(String[] ids, Account account);

	Map getBizAddModel();

	Map getBizConfIndexModel(int pageIndex);

	Map getBizDetailModel(Long id);

	Map getBizUpdateModel(Long id);

	Map getEquAddModel();

	Map getEquConfIndexModel(int pageIndex);

	Map getEquDetailModel(Long id);

	Map getEquUpdateModel(Long id);

	void newBusinessRegister(BusinessRegister entry, List<FileItem> fileItems);

	void newEquipment(Equipment entry, List fileItems);

	void updateBusinessRegister(BusinessRegister entry, List fileItems);

	void updateEquipment(Equipment entry, List fileItems);

	Map getMaConfIndexModel(int pageIndex);

	Map getMaDetailModel(Long id);

	Map getMaAddModel();

	void newMonitorAgent(MonitorAgent entry, List fileItems);

	Map getMaUpdateModel(Long id);

	void updateMonitorAgent(MonitorAgent entry, List fileItems);

	void deleteMonitorAgents(String[] idsArr, Account account);

	Map getEpConfIndexModel(int pageIndex);

	Map getEpDetailModel(Long id);

	Map getEpAddModel();

	void newExchangePlatform(ExchangePlatform entry, List fileItems);

	Map getEpUpdateModel(Long id);

	void updateExchangePlatform(ExchangePlatform entry, List fileItems);

	void deleteExchangePlatform(String[] idsArr, Account account);

	Map getExtLinkConfIndexModel(int pageIndex);

	Map getExtLinkDetailModel(Long id);

	Map getExtLinkAddModel();

	void newExtLink(ExtLink entry);

	Map getExtLinkUpdateModel(Long id);

	void updateExtLink(ExtLink entry);

	void deleteExtLinks(String[] idsArr, Account account);

	Map getIntLinkConfIndexModel(int pageIndex);

	Map getIntLinkDetailModel(Long id);

	Map getIntLinkAddModel();

	void newIntLink(IntLink entry);

	Map getIntLinkUpdateModel(Long id);

	void updateIntLink(IntLink entry);

	void deleteIntLinks(String[] idsArr, Account account);

	Map getBaseConfDetailModel() throws Exception;

	void updateJbqk(Jbqk entry, List fileItems);

	Map getBackupConfModel();

	List findBizAll();

	List findEquAll();

	Object[] getBlobFromJbqk(Long id, String type) throws Exception;

	Object[] getBlobFromBiz(Long id, String type);

	Map getSbpzIndexModel(int pageIndex);

	Map getSbpzDetailModel(Long id);

	void newSbpz(Sbpz entry);

	void updateSbpz(Sbpz entry);

	void deleteSbpz(String[] idsArr, Account account);
    public String showLinkInt();
    public String showLinkExt();
    public boolean findByIp(String ip) throws Exception;
    public void addBusinessRegister(BusinessRegister businessRegister);
    public void updateBusinessRegister(BusinessRegister businessRegister);
}
