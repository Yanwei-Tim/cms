package com.hzjava.monitorcenter.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JsonUtil {

	private static final String RESULT_OBJECT = "root";
	private JSONObject rootObject = new JSONObject();
	private JSONObject jsonObject = new JSONObject();

	public JsonUtil() {
		rootObject.put(RESULT_OBJECT, jsonObject);
	}

	public String toString() {
		return rootObject.toString();
	}

	public void addState(boolean state) {
		jsonObject.put("state", state);
	}

	public void addSuccess(boolean success) {
		jsonObject.put("success", success);
	}

	public void addResult(Map map) {
		JSONObject json = new JSONObject();
		json.putAll(map);
		jsonObject.put("result", json);
	}

	public static void main(String[] args) {
		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(true);
		Map map = new HashMap();
		map.put("title", "dfdfd");
		map.put("content", "dfdsfdsf");
		map.put("other", "othedsfdsfsdr2");
		jsonUtil.addResult(map);
		System.out.print(jsonUtil);

	}
}
