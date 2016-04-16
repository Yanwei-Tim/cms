package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.SysTerminalInfo;

import java.util.List;

public interface SysTerminalInfoDao extends BaseDao {
	public PageResult findPage(int pageIndex, int pageLength, String userName,
                               String policeId, String userDepart, String userZone, String ifCancel);

    void saveMany(List<SysTerminalInfo> list) throws Exception;

    void truncate()throws Exception;
}
