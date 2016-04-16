package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.TerminalAccessUrl;


public interface TerminalAccessUrlService {
	public String findPages(int pageIndex, int pageLength);
	
	public String create(TerminalAccessUrl entry);
	
	public String delete(long[] ids);
	
	public String update(TerminalAccessUrl entry);
	public TerminalAccessUrl findByIdTerminal(Long idTerminal);

}
