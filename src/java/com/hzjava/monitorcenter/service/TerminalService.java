package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hzjava.monitorcenter.domain.CardType;
import com.hzjava.monitorcenter.domain.SysTerminalStatus;
import com.hzjava.monitorcenter.domain.TenimalList;
import com.hzjava.monitorcenter.domain.TenimalOperationAudit;


public interface TerminalService {
	@SuppressWarnings("rawtypes")
	public Map getOperAuditModel(
            int pageIndex,
            int pageLength,
            String cardId,
            String cardType,
            String policeId,
            String operateTpye,
            String operater,
            Date startDate,
            Date endDate);
	@SuppressWarnings("rawtypes")
	public Map getAccessAuditModel(
            int pageIndex,
            int pageLength,
            String policeId,
            String policeName,
            String messageLevel,
            Date startDate,
            Date endDate, String busName, String userDepart, String userZone);
	
	@SuppressWarnings("rawtypes")
	public Map getAccessAuditReportModel(int pageIndex, int pageLength,
                                         String userDepart, Date reportDate);
	
	@SuppressWarnings("rawtypes")
	public Map getTenimalModel(int pageIndex, int pageLength, String cardType,
                               String state, String blockType, String RadioPolice, String police);
	@SuppressWarnings("rawtypes")
	/*
	 * date:2012-03-16 author:crj function:显示详细信息
	 */
	public Map loadById(Long id);
	/*
	 * date:2012-03-16 author:crj function:显示所有的安全卡类型信息
	 */
	public List<CardType> showCardType();
	/*
	 * date:2012-03-16 author:crj function:解除阻断操作和阻断操作
	 */
	public void releaseBlock(TenimalList tenimal);
	
	// date:2012-03-16 author:crj function:创建一条吊销证件记录
	public void revokeCert(TenimalOperationAudit t);
	
	// date:2012-03-20 author:qxp function:创建一条截图记录
	public void addCert(TenimalOperationAudit t);
	
	public Map getSysTerminalStatusReportModel(int pageIndex, int pageLength, Long idTerminal, Date reportDate);
	
	public Map getIdTerminalModel();
	
	public void addSysTerminalStatus(SysTerminalStatus sysTerminalStatus);
}
