package com.hzjava.monitorcenter.utils;

/**
 * Sting通用处理方法
 * 
 * @author xiangqi
 * 
 */
public class StringUtils {
	/**
	 * when str is null or "" return true
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isBlank(String str) {
		if (str == null || str.equals("") || str.length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * when str is null or "" return false
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isNotBlank(String str) {
		if (str == null || str.equals("") || str.length() == 0)
			return false;
		else
			return true;
	}
}
