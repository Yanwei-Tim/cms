package com.hzjava.monitorcenter.web;

/**
 * 站点上下文对象
 * 
 * @author collin.code@gmail.com
 * 
 */
public class SiteContext {
	/**
	 * web context根目录所在的真实文件路径
	 */
	public String contextRealPath = null;
	public String uploadFileDir = null;
	public String serviceUrl = null;
	private static SiteContext instance;
	static {
		instance = new SiteContext();
	}

	public static SiteContext getInstance() {
		return instance;
	}
}