package com.hzjava.monitorcenter.service;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hzjava.monitorcenter.dao.*;
import com.hzjava.monitorcenter.domain.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.utils.ServiceUtil;
import com.hzjava.monitorcenter.web.SiteContext;

public class ConfServiceImpl implements ConfService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(ConfServiceImpl.class);

    private BusinessCodeDao businessCodeDao;
    private BusinessExchModelDao businessExchModelDao;
    private BusinessRegisterDao businessRegisterDao;
    private EquipmentDao equipmentDao;
    private EquipmentTypeDao equipmentTypeDao;
    private EquipmentTypesDao equipmentTypesDao;
    private ExchangeDirectionDao exchangeDirectionDao;
    private ExchangePlatformDao exchangePlatformDao;
    private ExtLinkDao extLinkDao;
    private ExtLinkPropertyDao extLinkPropertyDao;
    private ExtLinkTypeDao extLinkTypeDao;
    private IntLinkDao intLinkDao;
    private JbqkDao jbqkDao;
    private MonitorAgentDao monitorAgentDao;
    private ProtocolTypeDao protocolTypeDao;
    private UseDepartTypeDao useDepartTypeDao;
    private UserOperLogDao userOperLogDao;
    private SbpzDao sbpzDao;
    private DistrictDao districtDao;
    private TerminalOutLinkDao terminalOutLinkDao;

    public EquipmentTypesDao getEquipmentTypesDao() {
        return equipmentTypesDao;
    }

    public void setEquipmentTypesDao(EquipmentTypesDao equipmentTypesDao) {
        this.equipmentTypesDao = equipmentTypesDao;
    }

    public void setTerminalOutLinkDao(TerminalOutLinkDao terminalOutLinkDao) {
        this.terminalOutLinkDao = terminalOutLinkDao;
    }

    public void setDistrictDao(DistrictDao districtDao) {
        this.districtDao = districtDao;
    }

    public void setSbpzDao(SbpzDao sbpzDao) {
        this.sbpzDao = sbpzDao;
    }
    public String showBusinessCode()throws Exception{
        List<BusinessCode> bcode=businessCodeDao.findAll();
        String json="{success:true,total:"+bcode.size()+",results:[";
        for (int i =0;i<bcode.size();i++){
            json+="{id:'"+bcode.get(i).getCode()+"',name:'"+bcode.get(i).getDescription()+"'},";
        }
        json+="]}";
        return json.replace("},]}","}]}").trim();
    }

    @Override
    /**
     *  //移动警务安全接入系统
     */
    public String showEquType() throws Exception {
        List<EquipmentType> bcode=equipmentTypeDao.findAll();
        String json="{success:true,total:"+bcode.size()+",results:[";
        for (int i =0;i<bcode.size();i++){
            json+="{id:'"+bcode.get(i).getTypeCode()+"',name:'"+bcode.get(i).getTypeName()+"',icon:'x-flag-"+bcode.get(i).getTypeCode()+"S'},";
        }
        json+="]}";
        return json.replace("},]}","}]}").trim();
    }

    /**
     * 边界安全接入平台
     * @return
     * @throws Exception
     */
    @Override
    public String showEquTypes() throws Exception {
        List<EquipmentTypes> bcode=equipmentTypesDao.findAll();
        String json="{success:true,total:"+bcode.size()+",results:[";
        for (int i =0;i<bcode.size();i++){

            json+="{id:'"+bcode.get(i).getTypeCode()+"',name:'"+bcode.get(i).getTypeName()+"',icon:'x-flag-"+bcode.get(i).getTypeCode()+"S'},";
        }
        json+="]}";
        return json.replace("},]}","}]}").trim();
    }

    public void deleteBusinessRegisters(String[] ids, Account account) {
        for (String id : ids) {
            BusinessRegister item = (BusinessRegister) businessRegisterDao
                    .getById(Long.valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("业务 " + item.getBusinessName() + " 删除成功");
            entry.setAuditModule("业务注册管理");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }

        businessRegisterDao.deleteWithDependent(ids);
    }

    public void deleteEquipments(String[] ids, Account account) {
        for (String id : ids) {
            Equipment item = (Equipment) equipmentDao.getById(Long.valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("设备 " + item.getEquName() + " 删除成功");
            entry.setAuditModule("设备配置管理");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }

        equipmentDao.deleteWithDependent(ids);

    }

    public void deleteExchangePlatform(String[] idsArr, Account account) {
        for (String id : idsArr) {
            ExchangePlatform item = (ExchangePlatform) exchangePlatformDao
                    .getById(Long.valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("交换平台 " + item.getPlatformName() + " 删除成功");
            entry.setAuditModule("交换平台配置");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }
        exchangePlatformDao.deleteWithDependent(idsArr);

    }

    public void deleteExtLinks(String[] idsArr, Account account) {

        for (String id : idsArr) {
            ExtLink item = (ExtLink) extLinkDao.getById(Long.valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("外部链路 " + item.getLinkName() + " 删除成功");
            entry.setAuditModule("链路配置");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }
        extLinkDao.deleteWithDependent(idsArr);

    }

    public void deleteIntLinks(String[] idsArr, Account account) {
        for (String id : idsArr) {
            IntLink item = (IntLink) intLinkDao.getById(Long.valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("内部链路 " + item.getLinkName() + " 删除成功");
            entry.setAuditModule("链路配置");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }
        intLinkDao.deleteWithDependent(idsArr);

    }

    public void deleteMonitorAgents(String[] idsArr, Account account) {
        for (String id : idsArr) {
            MonitorAgent item = (MonitorAgent) monitorAgentDao.getById(Long
                    .valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("探针通道 " + item.getAgentName() + " 删除成功");
            entry.setAuditModule("探针通道");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }
        monitorAgentDao.deleteWithDependent(idsArr);

    }

    @SuppressWarnings("rawtypes")
    public List findBizAll() {
        return businessRegisterDao.findAll();
    }

    @SuppressWarnings("rawtypes")
    public List findEquAll() {
        return equipmentDao.findAll();
    }

    @SuppressWarnings({ "rawtypes"})
    public Map getBackupConfModel() {
        // TODO Auto-generated method stub
        Map model = new HashMap();

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getBaseConfDetailModel() throws Exception {
        Map model = new HashMap();
        Jbqk entry = jbqkDao.getJbqk();
        model.put("entry", entry);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getBizAddModel() {
        Map model = new HashMap();
        List businessCodes = businessCodeDao.findAll();
        model.put("businessCodes", businessCodes);

        List businessExchModels = businessExchModelDao.findAll();
        model.put("businessExchModels", businessExchModels);

        List exchangeDirections = exchangeDirectionDao.findAll();
        model.put("exchangeDirections", exchangeDirections);

        List exchProtocols = protocolTypeDao.findAll();
        model.put("exchProtocols", exchProtocols);

        List useDepartTypes = useDepartTypeDao.findAll();
        model.put("useDepartTypes", useDepartTypes);

        List intLinkList = intLinkDao.findAll();
        model.put("intLinkList", intLinkList);

        List districtList = districtDao.getAllParents();
        model.put("districtList", districtList);

        List districtListFirst = districtDao.findChildFirst();
        model.put("districtListFirst", districtListFirst);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getBizConfIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = businessRegisterDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getBizDetailModel(Long id) {
        Map model = new HashMap();
        BusinessRegister item = (BusinessRegister) businessRegisterDao
                .getById(id);
        model.put("item", item);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getBizUpdateModel(Long id) {
        Map model = new HashMap();
        BusinessRegister item = (BusinessRegister) businessRegisterDao
                .getById(id);
        item.setApprovedDepartParent(item.getApprovedDepartParent());
        item.setAuthDepartParent(item.getAuthDepartParent());
        item.setBusinessDepartParent(item.getBusinessDepartParent());
        item.setUseDepartParent(item.getUseDepartParent());
        model.put("item", item);

        List businessCodes = businessCodeDao.findAll();
        model.put("businessCodes", businessCodes);

        List businessExchModels = businessExchModelDao.findAll();
        model.put("businessExchModels", businessExchModels);

        List exchangeDirections = exchangeDirectionDao.findAll();
        model.put("exchangeDirections", exchangeDirections);

        List exchProtocols = protocolTypeDao.findAll();
        model.put("exchProtocols", exchProtocols);

        List useDepartTypes = useDepartTypeDao.findAll();
        model.put("useDepartTypes", useDepartTypes);

        List intLinkList = intLinkDao.findAll();
        model.put("intLinkList", intLinkList);

        List districtList = districtDao.getAllParents();
        model.put("districtList", districtList);

        return model;
    }

    public Object[] getBlobFromBiz(Long id, String type) {
        Object[] obj = businessRegisterDao.getBlob(id, type);
        return obj;
    }

    public Object[] getBlobFromJbqk(Long id, String type) throws Exception {
        Object[] obj = jbqkDao.getBlob(id, type);
        return obj;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEpAddModel() {
        Map model = new HashMap();
        List intLinks = intLinkDao.findAll();
        model.put("intLinks", intLinks);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEpConfIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = exchangePlatformDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEpDetailModel(Long id) {
        Map model = new HashMap();
        ExchangePlatform entry = (ExchangePlatform) exchangePlatformDao
                .getById(id);
        model.put("entry", entry);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEpUpdateModel(Long id) {
        Map model = new HashMap();
        ExchangePlatform entry = (ExchangePlatform) exchangePlatformDao
                .getById(id);
        model.put("entry", entry);

        List intLinks = intLinkDao.findAll();
        model.put("intLinks", intLinks);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEquAddModel() {
        Map model = new HashMap();

        // 设备类型
        List equipmentTypeList = equipmentTypeDao.findAll();
        model.put("equipmentTypeList", equipmentTypeList);

        List intLinks = intLinkDao.findAll();
        model.put("intLinks", intLinks);

        List extLinks = extLinkDao.findAll();
        model.put("extLinks", extLinks);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEquConfIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = equipmentDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEquDetailModel(Long id) {
        Map model = new HashMap();

        Equipment equipment = (Equipment) equipmentDao.getById(id);
        model.put("equipment", equipment);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getEquUpdateModel(Long id) {
        Map model = new HashMap();
        Equipment equipment = (Equipment) equipmentDao.getById(id);
        model.put("equipment", equipment);

        // 设备类型
        List equipmentTypeList = equipmentTypeDao.findAll();
        model.put("equipmentTypeList", equipmentTypeList);

        List intLinks = intLinkDao.findAll();
        model.put("intLinks", intLinks);

        List extLinks = extLinkDao.findAll();
        model.put("extLinks", extLinks);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtLinkAddModel() {
        Map model = new HashMap();
        List linkTypeList = extLinkTypeDao.findAll();
        model.put("linkTypeList", linkTypeList);

        List linkPropertyList = extLinkPropertyDao.findAll();
        model.put("linkPropertyList", linkPropertyList);

        List linkCorpList = terminalOutLinkDao.findAll();
        model.put("linkCorpList",linkCorpList);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtLinkConfIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = extLinkDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtLinkDetailModel(Long id) {
        Map model = new HashMap();
        ExtLink entry = (ExtLink) extLinkDao.getById(id);
        model.put("entry", entry);
        List linkCorpList = terminalOutLinkDao.findAll();
        model.put("linkCorpList",linkCorpList);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtLinkUpdateModel(Long id) {
        Map model = new HashMap();
        ExtLink entry = (ExtLink) extLinkDao.getById(id);
        model.put("entry", entry);

        List linkTypeList = extLinkTypeDao.findAll();
        model.put("linkTypeList", linkTypeList);

        List linkPropertyList = extLinkPropertyDao.findAll();
        model.put("linkPropertyList", linkPropertyList);

        List linkCorpList = terminalOutLinkDao.findAll();
        model.put("linkCorpList",linkCorpList);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getIntLinkAddModel() {
        Map model = new HashMap();

        List businessExchModels = businessExchModelDao.findAll();
        model.put("businessExchModels", businessExchModels);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getIntLinkConfIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = intLinkDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getIntLinkDetailModel(Long id) {
        Map model = new HashMap();
        IntLink entry = (IntLink) intLinkDao.getById(id);
        model.put("entry", entry);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getIntLinkUpdateModel(Long id) {
        Map model = new HashMap();

        IntLink entry = (IntLink) intLinkDao.getById(id);
        model.put("entry", entry);

        List businessExchModels = businessExchModelDao.findAll();
        model.put("businessExchModels", businessExchModels);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getMaAddModel() {
        Map model = new HashMap();
        List intLinks = intLinkDao.findAll();
        model.put("intLinks", intLinks);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getMaConfIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = monitorAgentDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getMaDetailModel(Long id) {
        Map model = new HashMap();
        MonitorAgent entry = (MonitorAgent) monitorAgentDao.getById(id);
        model.put("entry", entry);
        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getMaUpdateModel(Long id) {
        Map model = new HashMap();
        MonitorAgent entry = (MonitorAgent) monitorAgentDao.getById(id);
        model.put("entry", entry);

        List intLinks = intLinkDao.findAll();
        model.put("intLinks", intLinks);
        return model;
    }

    public void newBusinessRegister(BusinessRegister entry,
                                    List<FileItem> fileItems) {
        for (Iterator<FileItem> iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = iter.next();
            if (item != null && item.getSize() > 0) {
                String name = item.getFieldName();
                Blob blob = Hibernate.createBlob(item.get());
                if (name.equalsIgnoreCase("auth_material")) {
                    String _name = item.getName();
                    String[] sa = _name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    entry.setAuthMaterialFileName(fileName);
                    entry.setAuthMaterial(blob);
                } else if (name.equalsIgnoreCase("tp_graph")) {
                    String _name = item.getName();
                    String[] sa = _name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    entry.setTpGraphFileName(fileName);
                    entry.setTpGraph(blob);
                }
                item.delete();
            }
        }

        businessRegisterDao.create(entry);

    }

    @SuppressWarnings("rawtypes")
    public void newEquipment(Equipment entry,List fileItems) {
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("equSysConfig")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    entry.setEquSysConfig(filePath);
                }
                item.delete();
            }
        }
        equipmentDao.create(entry);

        String[][] params = new String[][] {
                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                { "Command", "configdevice" },
                { "DeviceID", entry.getId().toString() } };
        ServiceUtil.callService(params);
    }

    @SuppressWarnings("rawtypes")
    public void newExchangePlatform(ExchangePlatform entry, List fileItems) {

        // 附件保存
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("certificatePath")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    entry.setCertificatePath(filePath);
                }
                item.delete();
            }
        }

        exchangePlatformDao.create(entry);

        String[][] params = new String[][] {
                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                { "Command", "configplatform" },
                { "ip", entry.getPlatformIp() + ":" + entry.getPlatformPort() } };
        ServiceUtil.callService(params);
    }

    public void newExtLink(ExtLink entry) {
        extLinkDao.create(entry);
    }

    public void newIntLink(IntLink entry) {
        intLinkDao.create(entry);
    }

    @SuppressWarnings("rawtypes")
    public void newMonitorAgent(MonitorAgent entry, List fileItems) {
        // 附件保存
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("certificatePath")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    entry.setCertificatePath(filePath);
                }
                item.delete();
            }
        }
        monitorAgentDao.create(entry);

        String[][] params = new String[][] {
                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                { "Command", "configprobe" },
                { "ip", entry.getAgentIp() + ":" + entry.getAgentPort() } };
        ServiceUtil.callService(params);
    }

    public void setBusinessCodeDao(BusinessCodeDao businessCodeDao) {
        this.businessCodeDao = businessCodeDao;
    }

    public void setBusinessExchModelDao(
            BusinessExchModelDao businessExchModelDao) {
        this.businessExchModelDao = businessExchModelDao;
    }

    public void setBusinessRegisterDao(BusinessRegisterDao businessRegisterDao) {
        this.businessRegisterDao = businessRegisterDao;
    }

    public void setEquipmentDao(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    public void setEquipmentTypeDao(EquipmentTypeDao equipmentTypeDao) {
        this.equipmentTypeDao = equipmentTypeDao;
    }

    public void setExchangeDirectionDao(
            ExchangeDirectionDao exchangeDirectionDao) {
        this.exchangeDirectionDao = exchangeDirectionDao;
    }

    public void setExchangePlatformDao(ExchangePlatformDao exchangePlatformDao) {
        this.exchangePlatformDao = exchangePlatformDao;
    }

    public void setExtLinkDao(ExtLinkDao extLinkDao) {
        this.extLinkDao = extLinkDao;
    }

    public void setExtLinkPropertyDao(ExtLinkPropertyDao extLinkPropertyDao) {
        this.extLinkPropertyDao = extLinkPropertyDao;
    }

    public void setExtLinkTypeDao(ExtLinkTypeDao extLinkTypeDao) {
        this.extLinkTypeDao = extLinkTypeDao;
    }

    public void setIntLinkDao(IntLinkDao intLinkDao) {
        this.intLinkDao = intLinkDao;
    }

    public void setJbqkDao(JbqkDao jbqkDao) {
        this.jbqkDao = jbqkDao;
    }

    public void setMonitorAgentDao(MonitorAgentDao monitorAgentDao) {
        this.monitorAgentDao = monitorAgentDao;
    }

    public void setProtocolTypeDao(ProtocolTypeDao protocolTypeDao) {
        this.protocolTypeDao = protocolTypeDao;
    }

    public void setUseDepartTypeDao(UseDepartTypeDao useDepartTypeDao) {
        this.useDepartTypeDao = useDepartTypeDao;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void updateBusinessRegister(BusinessRegister entry, List fileItems) {
        // BusinessRegister old = (BusinessRegister) businessRegisterDao
        // .retrieveById(entry.getId());
        // old.setAdminEmail(entry.getAdminEmail());
        // old.setAdminOtherPhone(entry.getAdminOtherPhone());
        // old.setAdminPhone(entry.getAdminPhone());
        // old.setAuthDate(entry.getAuthDate());
        // old.setAuthSerial(entry.getAuthSerial());
        // old.setAuthDepart(entry.getAuthDepart());
        // old.setApprovedDepart(entry.getApprovedDepart());
        // old.setBusinessAdmin(entry.getBusinessAdmin());
        // old.setBusinessCode(entry.getBusinessCode());
        // old.setBusinessDepart(entry.getBusinessDepart());
        // old.setBusinessExchModel(entry.getBusinessExchModel());
        // old.setBusinessName(entry.getBusinessName());
        // old.setBusinessProtocol(entry.getBusinessProtocol());
        // old.setDayDataVolume(entry.getDayDataVolume());
        // old.setDestIpRange(entry.getDestIpRange());
        // old.setDestPortRange(entry.getDestPortRange());
        // old.setExchangeDirection(entry.getExchangeDirection());
        // old.setExchProtocol(entry.getExchProtocol());
        // old.setIsApproved(entry.getIsApproved());
        // old.setIsRealtime(entry.getIsRealtime());
        // old.setLinkName(entry.getLinkName());
        // old.setProtocolDesc(entry.getProtocolDesc());
        // old.setRegisterDate(entry.getRegisterDate());
        // old.setSourceIpRange(entry.getSourceIpRange());
        // old.setSourcePortRange(entry.getSourcePortRange());
        // old.setTerminalAmount(entry.getTerminalAmount());
        // old.setUseDepartAdminEmail(entry.getUseDepartAdminEmail());
        // old.setUseDepartAdminName(entry.getUseDepartAdminName());
        // old.setUseDepartAdminOhterPhone(entry.getUseDepartAdminOhterPhone());
        // old.setUseDepartAdminPhone(entry.getUseDepartAdminPhone());
        // old.setUseDepartType(entry.getUseDepartType());
        // old.setUseDepartAddress(entry.getUseDepartAddress());
        // old.setUserAmount(entry.getUserAmount());
        // old.setUseDepart(entry.getUseDepart());

        for (Iterator<FileItem> iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = iter.next();
            if (item != null && item.getSize() > 0) {
                String name = item.getFieldName();
                Blob blob = Hibernate.createBlob(item.get());

                if (name.equalsIgnoreCase("auth_material")) {
                    String _name = item.getName();
                    String[] sa = _name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    entry.setAuthMaterialFileName(fileName);
                    entry.setAuthMaterial(blob);
                } else if (name.equalsIgnoreCase("tp_graph")) {
                    String _name = item.getName();
                    String[] sa = _name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    entry.setTpGraphFileName(fileName);
                    entry.setTpGraph(blob);
                }

                item.delete();
            }
        }

        businessRegisterDao.update(entry);

    }

    @SuppressWarnings("rawtypes")
    public void updateEquipment(Equipment entry,List fileItems) {
        Equipment old = (Equipment) equipmentDao.getById(entry.getId());
//        EquipmentType equipmentType = (EquipmentType) equipmentTypeDao.getById(entry.getEquTypeCode());
        old.setBuyDay(entry.getBuyDay());
        old.setEquIconCode(entry.getEquIconCode());
        old.setEquInfo(entry.getEquInfo());
        old.setEquName(entry.getEquName());
        old.setEquPhone(entry.getEquPhone());
        old.setEquStation(entry.getEquStation());
        old.setEquTypeCode(entry.getEquTypeCode());
//		old.setEquType(equipmentType);
        old.setInrOrExt(entry.getInrOrExt());
        old.setIp(entry.getIp());
        old.setLinkName(entry.getLinkName());
        old.setMac(entry.getMac());
        old.setManufacturer(entry.getManufacturer());
        old.setModel(entry.getModel());
        old.setMonitorUsed(entry.getMonitorUsed());
        old.setNetStation(entry.getNetStation());
        old.setOtherIp(entry.getOtherIp());
        old.setOtherPhone(entry.getOtherPhone());
        old.setProvider(entry.getProvider());
        old.setSubnetMask(entry.getSubnetMask());
        old.setUnrepairDay(entry.getUnrepairDay());
        old.setIsKeyDevice(entry.getIsKeyDevice());
        old.setEquManagerDepart(entry.getEquManagerDepart());
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("equSysConfig")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    old.setEquSysConfig(filePath);
                }
                item.delete();
            }
        }
        equipmentDao.update(old);

        String[][] params = new String[][] {
                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                { "Command", "configdevice" },
                { "DeviceID", entry.getId().toString() } };
        ServiceUtil.callService(params);
    }

    @SuppressWarnings("rawtypes")
    public void updateExchangePlatform(ExchangePlatform entry, List fileItems) {
        ExchangePlatform older = (ExchangePlatform) exchangePlatformDao
                .getById(entry.getId());
        String olderIp = older.getPlatformIp() + ":" + older.getPlatformPort();
        older.setCertificatePath(entry.getCertificatePath());
        older.setCertificatePwd(entry.getCertificatePwd());
        older.setIsEncrypted(entry.getIsEncrypted());
        older.setLinkName(entry.getLinkName());
        older.setMemo(entry.getMemo());
        older.setPlatformIp(entry.getPlatformIp());
        older.setPlatformName(entry.getPlatformName());
        older.setPlatformPort(entry.getPlatformPort());

        // 附件保存
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("certificatePath")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    older.setCertificatePath(filePath);
                }
                item.delete();
            }
        }

        exchangePlatformDao.update(older);

        String newIp = entry.getPlatformIp() + ":" + entry.getPlatformPort();
        if (!olderIp.equalsIgnoreCase(newIp)) {
            String[][] params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "configplatform" },
                    {
                            "ip",
                            entry.getPlatformIp() + ":"
                                    + entry.getPlatformPort() } };
            ServiceUtil.callService(params);
        }

    }

    public void updateExtLink(ExtLink entry) {
        ExtLink older = (ExtLink) extLinkDao.getById(entry.getId());
        older.setLinkAmount(entry.getLinkAmount());
        older.setLinkBandWidth(entry.getLinkBandWidth());
        older.setLinkCorp(entry.getLinkCorp());
        older.setLinkName(entry.getLinkName());
        older.setLinkPropertyCode(entry.getLinkPropertyCode());
        older.setLinkSecurity(entry.getLinkSecurity());
        older.setLinkTypeCode(entry.getLinkTypeCode());
        older.setOtherSecurity(entry.getOtherSecurity());
        older.setIp(entry.getIp());
        older.setMask(entry.getMask());
        older.setGateway(entry.getGateway());

        extLinkDao.update(older);
    }

    public void updateIntLink(IntLink entry) {
        IntLink older = (IntLink) intLinkDao.getById(entry.getId());
        older.setExchangeMode(entry.getExchangeMode());
        older.setFwUsed(entry.getFwUsed());
        older.setGapUsed(entry.getGapUsed());
        older.setJrdx(entry.getJrdx());
        older.setLinkBandWidth(entry.getLinkBandWidth());
        older.setLinkName(entry.getLinkName());
        older.setOtherSecurity(entry.getOtherSecurity());
        older.setSecGatewayUsed(entry.getSecGatewayUsed());
        older.setVpnUsed(entry.getVpnUsed());

        intLinkDao.update(older);
    }

    @SuppressWarnings("rawtypes")
    public void updateJbqk(Jbqk entry, List fileItems) {
        Jbqk older = (Jbqk) jbqkDao.retrieveById(entry.getId());
        older.setAddress(entry.getAddress());
        older.setBmxy(entry.getBmxy());
        older.setFzrEmail(entry.getFzrEmail());
        older.setFzrName(entry.getFzrName());
        older.setFzrOhterPhone(entry.getFzrOhterPhone());
        older.setFzrPhone(entry.getFzrPhone());
        older.setJksysIp(entry.getJksysIp());
        older.setJksysMac(entry.getJksysMac());
        older.setJsDay(entry.getJsDay());
        older.setJsdw(entry.getJsdw());
        older.setMainComp(entry.getMainComp());
        older.setMaintainerEmail(entry.getMaintainerEmail());
        older.setMaintainerName(entry.getMaintainerName());
        older.setMaintainerOhterPhone(entry.getMaintainerOhterPhone());
        older.setMaintainerPhone(entry.getMaintainerPhone());
        older.setPlatformId(entry.getPlatformId());
        older.setPlatformName(entry.getPlatformName());
        older.setSpDay(entry.getSpDay());
        older.setSpdw(entry.getSpdw());
        older.setSpph(entry.getSpph());
        older.setZycjdw(entry.getZycjdw());
        older.setSystemClass(entry.getSystemClass());

        // 附件保存
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("spcl")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    older.setSpclFileName(filePath);
//					entry.setSpclFileName(filePath);
                } else if (item.getFieldName().equalsIgnoreCase(
                        "technologySolution")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    older.setTechnologySolutionFileName(filePath);
//					entry.setTechnologySolutionFileName(filePath);
                } else if (item.getFieldName().equalsIgnoreCase(
                        "confidentialAgreement")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    older.setConfidentialAgreementFileName(filePath);
//					entry.setConfidentialAgreementFileName(filePath);
                } else if (item.getFieldName().equalsIgnoreCase("platformTp")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    older.setPlatformTpFileName(filePath);
//					entry.setPlatformTpFileName(filePath);
                }
                item.delete();
            }
        }

        jbqkDao.update(older);
    }

    @SuppressWarnings("rawtypes")
    public void updateMonitorAgent(MonitorAgent entry, List fileItems) {
        MonitorAgent older = (MonitorAgent) monitorAgentDao.getById(entry
                .getId());
        String olderIpPort = older.getAgentIp() + ":" + older.getAgentPort();
        String newIpPort = entry.getAgentIp() + ":" + entry.getAgentPort();
        older.setAgentIp(entry.getAgentIp());
        older.setAgentName(entry.getAgentName());
        older.setAgentPort(entry.getAgentPort());
        older.setAgentStation(entry.getAgentStation());
        older.setCertificatePath(entry.getCertificatePath());
        older.setCertificatePwd(entry.getCertificatePwd());
        older.setIsEncrypted(entry.getIsEncrypted());
        older.setLinkName(entry.getLinkName());
        older.setMemo(entry.getMemo());

        // 附件保存
        for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
            FileItem item = (FileItem) iter.next();
            if (item.getSize() > 0) {
                if (item.getFieldName().equalsIgnoreCase("certificatePath")) {
                    String name = item.getName();
                    String[] sa = name.split("\\\\");
                    String fileName = sa[sa.length - 1];
                    // 保存到文件夹中
                    String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                    String filePath = uploadFileDir + fileName;
                    File file = new File(filePath);
                    try {
                        item.write(file);
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                    older.setCertificatePath(filePath);
                }
                item.delete();
            }
        }

        monitorAgentDao.update(older);

        if (!olderIpPort.equalsIgnoreCase(newIpPort)) {
            String[][] params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "configprobe" }, { "ip", newIpPort } };
            ServiceUtil.callService(params);
        }
    }

    public static void main(String args[]) {
        String name = "C:\\Documents and Settings\\collin\\soapui-errors.log";
        String[] arr = name.split("\\\\");
        System.out.println(arr[arr.length - 1]);
    }

    public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
        this.userOperLogDao = userOperLogDao;
    }

    public void deleteSbpz(String[] idsArr, Account account) {
        for (String id : idsArr) {
            Sbpz item = (Sbpz) sbpzDao.getById(Long.valueOf(id));
            UserOperLog entry = new UserOperLog();
            entry.setAuditInfo("业务"+item.getId()+" 删除成功");
            entry.setAuditModule("上报配置管理");
            entry.setLevel("INFO");
            entry.setUserName(account.getUserName());
            entry.setLogTime(DateUtils.getNow());
            userOperLogDao.create(entry);
        }

        sbpzDao.deleteWithDependent(idsArr);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map getSbpzDetailModel(Long id) {
        Map model = new HashMap();
        Sbpz entry = (Sbpz) sbpzDao.getById(id);
        model.put("entry", entry);
        return model;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map getSbpzIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = sbpzDao.listByPage(pageIndex, 15);
        model.put("ps", ps);
        return model;
    }

    public void newSbpz(Sbpz entry) {
        sbpzDao.create(entry);
    }

    public void updateSbpz(Sbpz entry) {
        Sbpz older = (Sbpz) sbpzDao.getById(entry.getId());
        older.setHour(entry.getHour());
        older.setMinute(entry.getMinute());
        older.setSecond(entry.getSecond());
        older.setIp(entry.getIp());
        older.setPort(entry.getPort());
        older.setUsername(entry.getUsername());
        older.setPassword(entry.getPassword());
        older.setLevel(entry.getLevel());

        sbpzDao.update(older);
    }
    public String showLinkInt() {
        List<IntLink> oc=  intLinkDao.findAll();
        String json="{success:true,total:"+oc.size()+",results:[";
        for (int i = 0;i<oc.size();i++){
            json+="{id:'"+oc.get(i).getId()+"',name:'"+oc.get(i).getLinkName()+"'},";
        }
        json+="]}";
        if(json.contains("},]}")) {
            return json.replace("},]}","}]}").trim();
        } else {
            return json.trim();
        }
    }
    public String showLinkExt() {
        List<ExtLink> oc=  extLinkDao.findAll();
        String json="{success:true,total:"+oc.size()+",results:[";
        for (int i = 0;i<oc.size();i++){
            json+="{id:'"+oc.get(i).getId()+"',name:'"+oc.get(i).getLinkName()+"'},";
        }
        json+="]}";
        if(json.contains("},]}")) {
            return json.replace("},]}","}]}").trim();
        } else {
            return json.trim();
        }
    }
    public boolean findByIp(String ip) throws Exception {
        String hql="from Equipment where ip='"+ip+"'";
        List<Equipment> oc=  equipmentDao.list(hql);
        if(oc!=null&&oc.size()!=0){
            return false;
        } else {
            return true;
        }
    }
    public void addBusinessRegister(BusinessRegister businessRegister){
        businessRegisterDao.create(businessRegister);
    }
    public void updateBusinessRegister(BusinessRegister businessRegister){
        businessRegisterDao.update(businessRegister);
    }
}
