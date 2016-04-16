package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.dao.*;
import com.hzjava.monitorcenter.domain.*;
import com.hzjava.monitorcenter.utils.ServiceResponse;
import com.hzjava.monitorcenter.utils.ServiceUtil;
import com.hzjava.monitorcenter.web.SiteContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class JlsbServiceImpl implements JlsbService {
	private final static Logger logger = Logger
			.getLogger(JlsbServiceImpl.class);
	private SysruntimeDao sysruntimeDao;
	private SysabnormalDao sysabnormalDao;
	private SysstatusDao sysstatusDao;
	private SysbizstatusDao sysbizstatusDao;
	private LowerSysruntimeDao lowerSysruntimeDao;
	private LowerSysabnormalDao lowerSysabnormalDao;
	private LowerSysstatusDao lowerSysstatusDao;
	private LowerSysbizstatusDao lowerSysbizstatusDao;
	private ProvinceExchangePlatformDao provinceExchangePlatformDao;
	private ParentExchangePlatformDao parentExchangePlatformDao;
	private UserOperLogDao userOperLogDao;
	private IntLinkDao intLinkDao;
	private SysabnormalEventCodeDao sysabnormalEventCodeDao;
	private SysabnormalObjectTypeDao sysabnormalObjectTypeDao;
    private SysDelServiceDao sysDelServiceDao;
    private SysQueryServiceDao sysQueryServiceDao;

    public void setSysDelServiceDao(SysDelServiceDao sysDelServiceDao) {
        this.sysDelServiceDao = sysDelServiceDao;
    }

    public void setSysQueryServiceDao(SysQueryServiceDao sysQueryServiceDao) {
        this.sysQueryServiceDao = sysQueryServiceDao;
    }

    public void setSysabnormalEventCodeDao(
			SysabnormalEventCodeDao sysabnormalEventCodeDao) {
		this.sysabnormalEventCodeDao = sysabnormalEventCodeDao;
	}

	public void setSysabnormalObjectTypeDao(
			SysabnormalObjectTypeDao sysabnormalObjectTypeDao) {
		this.sysabnormalObjectTypeDao = sysabnormalObjectTypeDao;
	}

	public void setIntLinkDao(IntLinkDao intLinkDao) {
		this.intLinkDao = intLinkDao;
	}

	public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
		this.userOperLogDao = userOperLogDao;
	}

	public void setProvinceExchangePlatformDao(
			ProvinceExchangePlatformDao provinceExchangePlatformDao) {
		this.provinceExchangePlatformDao = provinceExchangePlatformDao;
	}

	public void setParentExchangePlatformDao(
			ParentExchangePlatformDao parentExchangePlatformDao) {
		this.parentExchangePlatformDao = parentExchangePlatformDao;
	}

	public void setSysruntimeDao(SysruntimeDao sysruntimeDao) {
		this.sysruntimeDao = sysruntimeDao;
	}

	public void setSysabnormalDao(SysabnormalDao sysabnormalDao) {
		this.sysabnormalDao = sysabnormalDao;
	}

	public void setSysstatusDao(SysstatusDao sysstatusDao) {
		this.sysstatusDao = sysstatusDao;
	}

	public void setSysbizstatusDao(SysbizstatusDao sysbizstatusDao) {
		this.sysbizstatusDao = sysbizstatusDao;
	}

	public void setLowerSysruntimeDao(LowerSysruntimeDao lowerSysruntimeDao) {
		this.lowerSysruntimeDao = lowerSysruntimeDao;
	}

	public void setLowerSysabnormalDao(LowerSysabnormalDao lowerSysabnormalDao) {
		this.lowerSysabnormalDao = lowerSysabnormalDao;
	}

	public void setLowerSysstatusDao(LowerSysstatusDao lowerSysstatusDao) {
		this.lowerSysstatusDao = lowerSysstatusDao;
	}

	public void setLowerSysbizstatusDao(
			LowerSysbizstatusDao lowerSysbizstatusDao) {
		this.lowerSysbizstatusDao = lowerSysbizstatusDao;
	}

	public PageResult listLowerSysabnormalByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String idSystem) {
		return lowerSysabnormalDao.findPage(pageIndex, pageLength, startDate,
				endDate, idSystem);
	}

	public PageResult listLowerSysbizstatusByPage(int pageIndex,
			int pageLength, Date startDate, Date endDate, String idSystem) {
		return lowerSysbizstatusDao.findPage(pageIndex, pageLength, startDate,
				endDate, idSystem);
	}

	public PageResult listLowerSysruntimeByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String idSystem) {
		return lowerSysruntimeDao.findPage(pageIndex, pageLength, startDate,
				endDate, idSystem);
	}

	public PageResult listLowerSysstatusByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String idSystem) {
		return lowerSysstatusDao.findPage(pageIndex, pageLength, startDate,
				endDate, idSystem);
	}

	public PageResult listSysruntimeByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate) {
		return sysruntimeDao
				.findPage(pageIndex, pageLength, startDate, endDate);
	}

	public PageResult listSysabnormalByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate,String eventCode,String objectCode) {
		return sysabnormalDao.findPage(pageIndex, pageLength, startDate,
				endDate,eventCode,objectCode);
	}

	public PageResult listSysbizstatusByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate) {
		return sysbizstatusDao.findPage(pageIndex, pageLength, startDate,
				endDate);
	}

	public PageResult listSysstatusByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate) {
		return sysstatusDao.findPage(pageIndex, pageLength, startDate, endDate);
	}

	public Map getParentEpConfIndexModel(int pageIndex, int pageLength) {
		Map model = new HashMap();
		PageResult ps = parentExchangePlatformDao.listByPage(pageIndex,
				pageLength);
		model.put("ps", ps);
		return model;
	}

	public Map getProvinceEpConfIndexModel(int pageIndex, int pageLength) {
		Map model = new HashMap();
		PageResult ps = provinceExchangePlatformDao.listByPage(pageIndex,
				pageLength);
		model.put("ps", ps);
		return model;
	}

	public void newParentExchangePlatform(ParentExchangePlatform entry,
			List fileItems) {

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
		parentExchangePlatformDao.create(entry);

		// String[][] params = new String[][] {
		// { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
		// { "Command", "configplatform" },
		// { "ip", entry.getPlatformIp() + ":" + entry.getPlatformPort() } };
		// ServiceUtil.callService(params);
	}

	public void newProvinceExchangePlatform(ProvinceExchangePlatform entry,
			List fileItems) {

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

		provinceExchangePlatformDao.create(entry);

		// String[][] params = new String[][] {
		// { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
		// { "Command", "configplatform" },
		// { "ip", entry.getPlatformIp() + ":" + entry.getPlatformPort() } };
		// ServiceUtil.callService(params);
	}

	public void updateParentExchangePlatform(ParentExchangePlatform entry,
			List fileItems) {
		ParentExchangePlatform older = (ParentExchangePlatform) parentExchangePlatformDao
				.getById(entry.getId());
		String olderIp = older.getPlatformIp() + ":" + older.getPlatformPort();
		// older.setCertificatePath(entry.getCertificatePath());
		older.setCertificatePwd(entry.getCertificatePwd());
		older.setIsEncrypted(entry.getIsEncrypted());
		older.setLinkName(entry.getLinkName());
		older.setMemo(entry.getMemo());
		older.setPlatformIp(entry.getPlatformIp());
		older.setPlatformName(entry.getPlatformName());
		older.setPlatformMac(entry.getPlatformMac());
		older.setPlatformPort(entry.getPlatformPort());
		older.setEnablereport(entry.isEnablereport());
		older.setHour(entry.getHour());
		older.setMinute(entry.getMinute());
		older.setSecond(entry.getSecond());
		older.setTimeType(entry.getTimeType());
        older.setType(entry.getType());
        older.setName(entry.getName());
        older.setPass(entry.getPass());

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

		parentExchangePlatformDao.update(older);

		// String newIp = entry.getPlatformIp() + ":" + entry.getPlatformPort();
		// if (!olderIp.equalsIgnoreCase(newIp)) {
		// String[][] params = new String[][] {
		// { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
		// { "Command", "configplatform" },
		// {
		// "ip",
		// entry.getPlatformIp() + ":"
		// + entry.getPlatformPort() } };
		// ServiceUtil.callService(params);
		// }

	}

	public void updateProvinceExchangePlatform(ProvinceExchangePlatform entry,
			List fileItems) {
		ProvinceExchangePlatform older = (ProvinceExchangePlatform) provinceExchangePlatformDao
				.getById(entry.getId());
		String olderIp = older.getPlatformIp() + ":" + older.getPlatformPort();
		// older.setCertificatePath(entry.getCertificatePath());
		older.setCertificatePwd(entry.getCertificatePwd());
		older.setIsEncrypted(entry.getIsEncrypted());
		older.setLinkName(entry.getLinkName());
		older.setMemo(entry.getMemo());
		older.setPlatformIp(entry.getPlatformIp());
		older.setPlatformName(entry.getPlatformName());
		older.setPlatformPort(entry.getPlatformPort());
		older.setEnablereport(entry.isEnablereport());
		older.setHour(entry.getHour());
		older.setMinute(entry.getMinute());
		older.setSecond(entry.getSecond());

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

		provinceExchangePlatformDao.update(older);

		// String newIp = entry.getPlatformIp() + ":" + entry.getPlatformPort();
		// if (!olderIp.equalsIgnoreCase(newIp)) {
		// String[][] params = new String[][] {
		// { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
		// { "Command", "configplatform" },
		// {
		// "ip",
		// entry.getPlatformIp() + ":"
		// + entry.getPlatformPort() } };
		// ServiceUtil.callService(params);
		// }

	}

	public void deleteParentExchangePlatform(String[] idsArr, Account account) {
		for (String id : idsArr) {
			ParentExchangePlatform item = (ParentExchangePlatform) parentExchangePlatformDao
					.getById(Long.valueOf(id));
			UserOperLog entry = new UserOperLog();
			entry.setAuditInfo("上级交换平台 " + item.getPlatformName() + " 删除成功");
			entry.setAuditModule("上级交换平台配置");
			entry.setLevel("INFO");
			entry.setUserName(account.getUserName());
			entry.setLogTime(DateUtils.getNow());
			userOperLogDao.create(entry);
			try{
                File file = new File(item.getCertificatePath());
                file.delete();
            } catch (Exception e){
                logger.error("文件没找到错误");
            }

		}
		parentExchangePlatformDao.deleteWithDependent(idsArr);

	}

	public void deleteProvinceExchangePlatform(String[] idsArr, Account account) {
		for (String id : idsArr) {
			ProvinceExchangePlatform item = (ProvinceExchangePlatform) provinceExchangePlatformDao
					.getById(Long.valueOf(id));
			UserOperLog entry = new UserOperLog();
			entry.setAuditInfo("下级交换平台 " + item.getPlatformName() + " 删除成功");
			entry.setAuditModule("下级交换平台配置");
			entry.setLevel("INFO");
			entry.setUserName(account.getUserName());
			entry.setLogTime(DateUtils.getNow());
			userOperLogDao.create(entry);
		}
		provinceExchangePlatformDao.deleteWithDependent(idsArr);

	}

	public Map getParentEpAddModel() {
		Map model = new HashMap();
		List intLinks = intLinkDao.findAll();
		model.put("intLinks", intLinks);
		return model;
	}

	public Map getParentEpDetailModel(Long id) {
		Map model = new HashMap();
		ParentExchangePlatform entry = (ParentExchangePlatform) parentExchangePlatformDao
				.getById(id);
		model.put("entry", entry);
		return model;
	}

	public Map getParentEpUpdateModel(Long id) {
		Map model = new HashMap();
		ParentExchangePlatform entry = (ParentExchangePlatform) parentExchangePlatformDao
				.getById(id);
		model.put("entry", entry);

		List intLinks = intLinkDao.findAll();
		model.put("intLinks", intLinks);
		return model;
	}

	public Map getProvinceEpAddModel() {
		Map model = new HashMap();
		List intLinks = intLinkDao.findAll();
		model.put("intLinks", intLinks);
		return model;
	}

	public Map getProvinceEpDetailModel(Long id) {
		Map model = new HashMap();
		ProvinceExchangePlatform entry = (ProvinceExchangePlatform) provinceExchangePlatformDao
				.getById(id);
		model.put("entry", entry);
		return model;
	}

	public Map getProvinceEpUpdateModel(Long id) {
		Map model = new HashMap();
		ProvinceExchangePlatform entry = (ProvinceExchangePlatform) provinceExchangePlatformDao
				.getById(id);
		model.put("entry", entry);

		List intLinks = intLinkDao.findAll();
		model.put("intLinks", intLinks);
		return model;
	}
	
	@Override
	public String findEventAll() {
		String json = "{success:true,rows:[";
		List<SysabnormalEventCode> list = sysabnormalEventCodeDao.findAll();
		int i = 1;
		for (SysabnormalEventCode sec : list) {
			if(i == list.size()){
				json += "{'code':'"+sec.getCode()+"','name':'"+sec.getName()+"'}";
			}else{
				json += "{'code':'"+sec.getCode()+"','name':'"+sec.getName()+"'},";
			}
			i++;
		}
		json += "]}";
		return json;
	}
	
	@Override
	public String findObjectAll() {
		String json = "{success:true,rows:[";
		List<SysabnormalObjectType> list = sysabnormalObjectTypeDao.findAll();
		int i = 1;
		for (SysabnormalObjectType sec : list) {
			if(i == list.size()){
				json += "{'code':'"+sec.getCode()+"','name':'"+sec.getName()+"'}";
			}else{
				json += "{'code':'"+sec.getCode()+"','name':'"+sec.getName()+"'},";
			}
			i++;
		}
		json += "]}";
		return json;
	}

    @Override
    public String findParentExchangePlatformKeyValue() {
        List<ParentExchangePlatform> list = parentExchangePlatformDao.findAll();
        String json = "{success:true,rows:[";
        int i = 1;
        for(ParentExchangePlatform pep : list){
            if(i == list.size()){
                json += "{'id':'"+pep.getId()+"','name':'"+pep.getPlatformName()+"'}";
            }else{
                json += "{'id':'"+pep.getId()+"','name':'"+pep.getPlatformName()+"'},";
            }
            i++;
        }
        json += "]}";
        return json;  //To change body of implemented methods use File | Settings | File Templates.

    }

    public String selectSysQueryService(String objectName,int start, int limit) throws Exception{
        PageResult ps = sysQueryServiceDao.listByPage(objectName,start/limit+1,limit);
        int total = ps.getAllResultsAmount();
        List<SysQueryService> list = ps.getResults();
        String json = "{success:true,total:"+total+",rows:[";
        for(SysQueryService s : list){
            json += "{id:'"+s.getId()+"',objectName:'"+s.getObjectName()+
                    "',ids:'"+s.getIds()+"',idSystem:'"+s.getIdSystem()+"'},";
        }
        json += "]}";
        json = trim(json);
        return json;
    }

    private String trim(String str){
        if(str.indexOf(",]")>-1){
            String[] ary = str.split(",]");
            if(ary.length > 1 ){
                str = ary[0]+"]"+ary[1];
            }
        }
        return str;
    }

    public String monitorSysQueryService(String objectName,String platformId) throws Exception{
        String msg = null;
        try{
            String[][] params = new String[][] {
						{ "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
						{ "Command", "sysqueryservice" },
						{ "platformid", platformId },
						{ "objectname", objectName }
            };
            ServiceResponse response = ServiceUtil.callService(params);
            if (response != null && response.getData() != null) {
                logger.info("已上报项信息:"+response.getData());
                msg = "success";
//                json="{success:true,total:1,rows:[{id:'',objectName:'',ids:'',idSystem:''}]}";
            } else {
                msg = "failure";
            }
        } catch (Exception e) {
            logger.error("从接口获取查询已上报项信息出错", e);
            msg = "failure";
        }
        return msg;
    }

    public String deleteSysQueryService(long[] idArray, String platformId) throws Exception{
        for (int i=0;i<idArray.length;i++){
            SysQueryService sysQueryService = (SysQueryService) sysQueryServiceDao.getById(idArray[i]);
            sysQueryServiceDao.delete(idArray[i]);
            SysDelService sysDelService = new SysDelService();
            sysDelService.setIds(sysQueryService.getIds());
            sysDelService.setIdSystem(sysQueryService.getIdSystem());
            sysDelService.setObjectName(sysQueryService.getObjectName());
            sysDelServiceDao.create(sysDelService);
        }
        String msg = null;
        try{
            String[][] params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "sysdelservice" },
                    { "platformid", platformId }
            };
            ServiceResponse response = ServiceUtil.callService(params);
            if (response != null && response.getData() != null) {
                logger.info("删除[已上报项信息]上报:"+response.getData());
                msg = "删除成功,点击[确定]返回列表";
            } else {
                msg = "删除上报服务出错";
            }
        } catch (Exception e) {
            logger.error("删除[已上报项信息]上报出错", e);
            msg = "删除[已上报项信息]上报出错";
        }
        return msg;
    }

}
