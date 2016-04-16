package com.hzjava.monitorcenter.utils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.inetec.common.client.ECommonUtil;
import com.inetec.common.client.util.LogBean;
import com.inetec.common.client.util.XChange;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import cn.collin.commons.utils.DateUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {

	public static List<FileItem> populate(Object bean, List items, String encode)
			throws IllegalAccessException, InvocationTargetException,
			UnsupportedEncodingException {
		List<FileItem> fileItems = new ArrayList<FileItem>();
		for (Iterator iter = items.iterator(); iter.hasNext();) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				fileItems.add(item);
			} else {
				String fieldName = item.getFieldName();
				BeanUtils.setProperty(bean, fieldName, item.getString(encode));
			}
		}
		return fileItems;
	}

	public static Timestamp getFieldValueWithTimestamp(List items,
			String fieldName, String pattern) throws ParseException {
		for (Iterator iter = items.iterator(); iter.hasNext();) {
			FileItem item = (FileItem) iter.next();
			if (item == null)
				return null;
			if (item.isFormField()
					&& item.getFieldName().equalsIgnoreCase(fieldName)) {
				if (item.getString().trim().length() == 0)
					return null;
				return new Timestamp(DateUtils.parse(item.getString(), pattern)
						.getTime());
			}
		}
		return null;
	}

    /**
     * 上传文件
     * @param savePath          保存路径
     * @param uploadFile        上传文件
     * @param uploadFileFileName  上传文件文件名
     * @throws java.io.IOException
     */
    public static void upload(String savePath,File uploadFile,String uploadFileFileName) throws IOException {
        File dir = new File(savePath);
        if(!dir.exists()){
            dir.mkdir();
        }
        String newFile = dir+"/"+uploadFileFileName;
        copy(uploadFile, newFile);
    }

    /**
     *
     * @param from   被复制文件
     * @param to     保存后文件地址
     */
    public static void copy(File from,String to) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        bis = new BufferedInputStream(
                new FileInputStream(from));
        bos = new BufferedOutputStream(
                new FileOutputStream(
                        new File(to)));
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = bis.read(buf))!=-1){
            bos.write(buf,0,len);
        }
        bos.flush();
        bos.close();
        bis.close();
    }

    /**
     * 读取
     * @return
     */
    public static String readFileNames(String path) {
        String[] files = readFileName(path);
        String json = null;
        if(files.length==0){
            json = "{'success':true,'total':"+files.length+",rows:[,]}";
        }else{
            json = "{'success':true,'total':"+files.length+",rows:[";
            int count = 0;
            for (int i = 0; i<files.length; i++){
//                if(i==start&& count<limit){
//                    start ++;
//                    count ++;
                    json += "{'fileName':'"+files[i]+"'";;
                if(files[i].contains("cms.log")){
                  json +=",'Desc':'集中监控管理日志'";
                }
                if(files[i].contains("monitorservice.log")) {
                    json +=",'Desc':'集中监控后台日志'";
                }
                json+="},";
            }
            json += "]}";
            json=json.replace("},]}","}]}");
        }
        return json;
    }

    /**
     *
     * @param path  文件夹路径 rizhi
     * @return      文件夹中所有文件名
     */
    public static String[] readFileName(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
			if(files[i].getName().endsWith(".log")){
                String length = setLength(files[i].length());
			    String logName = files[i].getName()+"("+length+")";
				list.add(logName);
			}
		}
        return list.toArray(new String[list.size()]);
    }

    public static String readRemoteFileNames() throws XChange {
         String[] files = readRemoteLogFileName();
        String json = null;
        if(files.length==0){
            json = "{'success':true,'total':"+files.length+",rows:[,]}";
        }else{
            json = "{'success':true,'total':"+files.length+",rows:[";
            int count = 0;
            for (int i = 0; i<files.length; i++){
//                if(i==start&& count<limit){
//                    start ++;
//                    count ++;
                    json += "{'fileName':'"+files[i]+"'},";
//                }
            }
            json += "]}";
        }
        return json;
    }

    /**
	 * 读取外网服务器日志
	 */
	public static String[] readRemoteLogFileName() throws XChange {
		ECommonUtil ecu = new ECommonUtil();
		LogBean[] bean = ecu.getLogFiles();
		int total = bean.length;
		List<String> logs = new ArrayList<String>();
		for (int i = 0; i < total; i++) {
			String length = setLength(bean[i].getLogFileLength());
			String externalLog = bean[i].getLogFileName()+"("+length+")";
			logs.add(externalLog);
		}
		return logs.toArray(new String[logs.size()]);
	}

    /**
     * 计算long成*MB*Kb
     * @param l
     * @return
     */
    public static String setLength(long l) {
		String a = "0";
		if(l>0){
			if(l<512){
				a =l+"B";
			}else if(l >=512&&l <= 10485){
				a = new DecimalFormat("0.00").format((double)l/(1024));
				String[] b = a.split("\\.");
				if(b[1].equals("00")){
					a = b[0]+"KB";
				}else{
					a +="KB";
				}
			}else if(l > 10485){
				a = new DecimalFormat("0.00").format((double)l/(1024*1024));
				String[] b = a.split("\\.");
				if(b[1].equals("00")){
					a = b[0]+"MB";
				}else{
					a +="MB";
				}
			}
		}
		return a;
	}

    public static void downType(HttpServletResponse response,String name,String userBrowser) {
		response.reset();
		response.setBufferSize(5*1024*1024);
		response.addHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
		if(userBrowser.indexOf("Firefox")>0){
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
		}
		response.setContentType("application/octet-stream; charset=UTF-8");
	}

    /**
     *
     * @param from       被复制文件
     * @param response   传输响应 用于文件下载时
     */
    public static HttpServletResponse copy(File from,HttpServletResponse response){ //下载
//    	response.addHeader("Content-Length", ""+from);

        ServletOutputStream out =null;
        BufferedInputStream in = null;
        try {
            out = response.getOutputStream();
            in = new BufferedInputStream(new FileInputStream(from));
            byte[] content = new byte[1024*1024];
            int length;
            while ((length = in.read(content, 0, content.length)) != -1){
                out.write(content, 0, length);
                out.flush();
            }
            in.close();
            out.flush();
//            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
