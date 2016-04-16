package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.Equipment;

import java.util.Map;

/**
 * 运行监控
 * 
 * @author collin.code@gmail.com
 * 
 */
public interface RunMonitorService {

	Map getBizIndexModel(int pageIndex);

    Map getVisMonitor(int pageIndex) ;

	Map getEquIndexModel(int pageIndex);

	Map getEquDetailModel(Long id);

	Map getBizStatModel(String businessName);

	Map getTerminalIndexModel(int pageIndex, int limit);

    boolean isEquExist(String ip) throws Exception;

    String findName();
    public Equipment findEquipmentById(Long id) ;
}
