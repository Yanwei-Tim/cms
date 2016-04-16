package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.dao.*;
import com.hzjava.monitorcenter.domain.*;
import org.apache.log4j.Logger;

import java.util.List;

public class OrgcodeServiceImpl implements OrgcodeService {

	private final static Logger logger = Logger.getLogger(OrgcodeServiceImpl.class);
	private OrgcodeDao orgcodeDao;
    private JbqkDao jbqkDao;
    private BusinessExchModelDao bemDao;
    private ExchangeDirectionDao exchangeDirectionDao;
    private ProtocolTypeDao protocolTypeDao;
    private UseDepartTypeDao useDepartTypeDao;

	@Override
	public List<Orgcode> findChildByParent(Long parentId) {
		return null;
	}

    public UseDepartTypeDao getUseDepartTypeDao() {
        return useDepartTypeDao;
    }

    public void setUseDepartTypeDao(UseDepartTypeDao useDepartTypeDao) {
        this.useDepartTypeDao = useDepartTypeDao;
    }

    public ProtocolTypeDao getProtocolTypeDao() {
        return protocolTypeDao;
    }

    public void setProtocolTypeDao(ProtocolTypeDao protocolTypeDao) {
        this.protocolTypeDao = protocolTypeDao;
    }

    public ExchangeDirectionDao getExchangeDirectionDao() {
        return exchangeDirectionDao;
    }

    public void setExchangeDirectionDao(ExchangeDirectionDao exchangeDirectionDao) {
        this.exchangeDirectionDao = exchangeDirectionDao;
    }

    public BusinessExchModelDao getBemDao() {
        return bemDao;
    }

    public void setBemDao(BusinessExchModelDao bemDao) {
        this.bemDao = bemDao;
    }

    public JbqkDao getJbqkDao() {
        return jbqkDao;
    }

    public void setJbqkDao(JbqkDao jbqkDao) {
        this.jbqkDao = jbqkDao;
    }

    public OrgcodeDao getOrgcodeDao() {
        return orgcodeDao;
    }

    public void setOrgcodeDao(OrgcodeDao orgcodeDao) {
        this.orgcodeDao = orgcodeDao;
    }

    public static Logger getLogger() {
		return logger;
	}

	@Override
	public String findParents() {
        List<Jbqk> list=jbqkDao.findAll();
        if(list.size()!=0){
            Jbqk jbqk= list.get(0);
            String code=jbqk.getPlatformId().substring(1,7);
            List<Orgcode> oc=  orgcodeDao.getAllParents(code);
            String json="{success:true,total:"+oc.size()+",results:[";
            for (int i = 0;i<oc.size();i++){
                json+="{id:'"+oc.get(i).getOrgcode().trim()+"',name:'"+oc.get(i).getOrgname().trim()+"'},";
            }
            json+="]}";
            return json.replace("},]}","}]}").trim();
        }else {
            return null;
        }

	}
    @Override
    public String showBusinessExchModel() {
        List<BusinessExchModel> oc=  bemDao.findAll();
        String json="{success:true,total:"+oc.size()+",results:[";
        for (int i = 0;i<oc.size();i++){
            json+="{id:'"+oc.get(i).getCode().trim()+"',name:'"+oc.get(i).getDescription().trim()+"'},";
        }
        json+="]}";
        if(json.contains("},]}")) {
            return json.replace("},]}","}]}").trim();
        } else {
            return json.trim();
        }
    }
    public String showExchangeDirection() {
        List<ExchangeDirection> oc=  exchangeDirectionDao.findAll();
        String json="{success:true,total:"+oc.size()+",results:[";
        for (int i = 0;i<oc.size();i++){
            json+="{id:'"+oc.get(i).getCode().trim()+"',name:'"+oc.get(i).getCodeDesc().trim()+"'},";
        }
        json+="]}";
        if(json.contains("},]}")) {
            return json.replace("},]}","}]}").trim();
        } else {
            return json.trim();
        }
    }
    public String showUseDepartType() {
        List<UseDepartType> oc=  useDepartTypeDao.findAll();
        String json="{success:true,total:"+oc.size()+",results:[";
        for (int i = 0;i<oc.size();i++){
            json+="{id:'"+oc.get(i).getDepartCode().trim()+"',name:'"+oc.get(i).getDepartTypeDesc().trim()+"'},";
        }
        json+="]}";
        if(json.contains("},]}")) {
            return json.replace("},]}","}]}").trim();
        } else {
            return json.trim();
        }
    }
    public String showProtocolType() {
        List<ProtocolType> oc=  protocolTypeDao.findAll();
        String json="{success:true,total:"+oc.size()+",results:[";
        for (int i = 0;i<oc.size();i++){
            json+="{id:'"+oc.get(i).getProtocolCode().trim()+"',name:'"+oc.get(i).getProtocolName().trim()+"'},";
        }
        json+="]}";
        if(json.contains("},]}")) {
            return json.replace("},]}","}]}").trim();
        } else {
            return json.trim();
        }
    }
}
