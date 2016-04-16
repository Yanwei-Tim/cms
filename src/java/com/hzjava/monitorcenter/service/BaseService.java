package com.hzjava.monitorcenter.service;

import java.io.Serializable;

public interface BaseService {

	public void saveObject(Object obj);

	public void delete(Serializable[] ids);

	public void update(Object obj);
}
