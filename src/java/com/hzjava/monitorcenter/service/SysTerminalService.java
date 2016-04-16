package com.hzjava.monitorcenter.service;

import java.util.List;
import java.util.Map;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.SysTerminalInfo;
import com.hzjava.monitorcenter.domain.TerminalAddress;


public interface SysTerminalService {
	public Map getSysTerminalConfIndexModel(int pageIndex, int pageLength, String userName,
                                            String policeId, String userDepart, String userZone, String ifCancel);

	public void newSysTerminal(SysTerminalInfo entry);

	public void updateSysTerminal(SysTerminalInfo entry);

	public void deleteSysTerminal(String[] idsArr, Account account);

	public Map getSysTerminalAddModel();

	public Map getSysTerminalDetailModel(Long id);

	public Map getSysTerminalUpdateModel(Long id);

    public String updateTerminalAddress(Long id, String ip, int port)throws Exception;

    public TerminalAddress getTerminalAddress() throws Exception;

    public String saveMany(List<SysTerminalInfo> list, Account account) throws Exception;

    public void truncateSysTerminal() throws Exception;

}
