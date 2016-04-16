package com.hzjava.monitorcenter.service;

import java.util.List;

import com.hzjava.monitorcenter.dao.CodeTableDao;

public class CodeTableServiceImpl implements CodeTableService {
	CodeTableDao codeTableDao;
	
	
	
	public void setCodeTableDao(CodeTableDao codeTableDao) {
		this.codeTableDao = codeTableDao;
	}
	public List listAllCardType(){
		return codeTableDao.listAllCardType();
	}
	public List listAllTerminalType(){
		return codeTableDao.listAllTerminalType();
	}
	public List listAllTermianlOutlink(){
		return codeTableDao.listAllTermianlOutlink();
	}
	public List listAllTermianlOS(){
		return codeTableDao.listAllTermianlOS();
	}
	public List listAllTermianlBand(){
		return codeTableDao.listAllTermianlBand();
	}
}
