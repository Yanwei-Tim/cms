package com.hzjava.monitorcenter.web.action.system;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.entity.FileForm;
import com.hzjava.monitorcenter.entity.JarBean;
import com.hzjava.monitorcenter.entity.NetInfo;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.utils.StringTrim;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.SiteContext;
import com.hzjava.monitorcenter.web.WebUtil;
import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-21
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
public class UpgradeVersionAction extends DispatchActionSupport {

    private static final Logger logger = Logger.getLogger(ConfigManagerAction.class);
    private static final String path = System.getProperty("monitor.home")+"/tomcat/webapps";
	private LogService logService;
    private String upgradeTime;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
    /**
     * 获取 服务软件包名列表
     * */
    public ActionForward selectWar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = "{success:true,total:0,rows:[]}";
        try {
            int start = ServletRequestUtils.getIntParameter(request, "start");
            int limit = ServletRequestUtils.getIntParameter(request, "limit");

            json = selectWars(path,start,limit);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "版本升级", "用户获取服务软件包名列表成功 ");
        } catch (Exception e) {
            logger.error("版本升级", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "版本升级", "用户获取服务软件包名列表不成功 ");
        }
        json = new StringTrim().trim(json);

        WebUtil.sendHtml(request, response, json);
		return null;
	}

    /**
     *
     * @param path     war包在tomcat下的路劲
     * @param start    分页开始页
     * @param limit    分页大小
     * @return
     * @throws Exception
     */
    private String selectWars(String path, int start, int limit) throws Exception {
        File webapps = new File(path);
        File[] wars = webapps.listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                if (s.endsWith(".war")) {
                    return true;
                }
                return false;
            }
        });
        int total = wars.length;
        String json = "{'success':true,'total':"+total+",'rows':[";
        if(total==0){
        	json+=",";
        }if(total > 0){
        	int index = 0;
            for (int i = 0;i<total;i ++) {
            	if(i == start && i < limit){
            		start ++;
            		index ++;
                    File f = wars[i];
                    long modifiedTime = f.lastModified();
                    Date date=new Date(modifiedTime);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String upgradeTime=sdf.format(date);
                    ZipFile file = new ZipFile(f);
                    ZipEntry entry = file.getEntry("META-INF/version.properties");
                    String warName = f.getName().substring(0,f.getName().lastIndexOf('.'));
                    boolean isExistOld = isExistOld(webapps,warName);
                    String version = null;
                    String buildDate = null;
                    String newVersion = null;
                    if(entry!=null){
                        InputStream in = file.getInputStream(entry);                       //"/META-INF/version.properties"
                        Properties config = new Properties();
                        config.load(in);
                        version = config.getProperty("version");
                        buildDate = config.getProperty("builddate");
                        in.close();
                    }else {
                        version = "低版本,没有版本说明";
                        buildDate = "低版本,没有版本说明";
                    }
                    newVersion = getNewVersion(path,f.getName());
                    file.close();
                    json += "{warName:'"+warName+"',upgradeTime:'"+upgradeTime+
                            "',warVersion:'"+version+"',buildDate:'"+buildDate+
                            "',newVersion:'"+newVersion+"',flag:"+isExistOld+"},";
            	}
            }
         }
        json += "]}";
        return json;
    }

    private String getNewVersion(String path, String name) throws IOException {
        File f = new File(path + "/" + name + "_tmp");
        String version = null;
        if(f.exists()){
            ZipFile file = new ZipFile(f);
            ZipEntry entry = file.getEntry("META-INF/version.properties");
            if(entry!=null){
                InputStream in = file.getInputStream(entry);                       //"/META-INF/version.properties"
                Properties config = new Properties();
                config.load(in);
                version = config.getProperty("version");
                in.close();
            }else {
                version = "低版本,没有版本说明";
            }
        }else {
            version = "没有上传升级版本";
        }
        return version;
    }

    private boolean isExistOld(File webapps, String warName) {
        File[] warOlds = webapps.listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                if (s.endsWith(".war_old")) {
                    return true;
                }
                return false;
            }
        });
        if(warOlds.length>0){
            for (int i = 0;i<warOlds.length;i++){
                if(warOlds[i].getName().equals(warName+".war_old")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取 自有Jar软件包列表名
     * */
    public ActionForward selectJar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = "{success:true,total:0,rows:[]}";
        try {
            int start = ServletRequestUtils.getIntParameter(request, "start");
            int limit = ServletRequestUtils.getIntParameter(request, "limit");
            json = selectJars(path, start, limit);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "版本升级", "用户获取自有Jar软件包列表名成功 ");
        } catch (Exception e) {
            logger.error("版本升级", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "版本升级", "用户获取自有Jar软件包名列表不成功 ");
        }
        json = new StringTrim().trim(json);

        WebUtil.sendHtml(request, response, json);
		return null;
	}

    private String selectJars(String path, int start, int limit) throws IOException {
        File webapps = new File(path);
        File[] wars = webapps.listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                if (s.endsWith(".war")) {
                    return true;
                }
                return false;
            }
        });
        List<JarBean> jarBeans = new ArrayList<JarBean>();
        for (int i = 0;i<wars.length;i ++) {
            File f = wars[i];
            ZipFile file = new ZipFile(f);
            ZipEntry entry = file.getEntry("META-INF/version.properties");
            String jar = null;
            if(entry!=null){
                InputStream in = file.getInputStream(entry);                       //"/META-INF/version.properties"
                Properties config = new Properties();
                config.load(in);
                jar = config.getProperty("jar");
                in.close();
                jarBeans = toJarBeanList(wars[i].getName(),jar,jarBeans);
            }
            file.close();
        }
        int total = jarBeans.size();
        String json = "{'success':true,'total':"+total+",'rows':[";
        if(total==0){
        	json+=",";
        }if(total > 0){
        	int index = 0;
            for (int i = 0;i<total;i ++) {
            	if(i == start && i < limit){
            		start ++;
            		index ++;
                    json += "{jarName:'" + jarBeans.get(i).getJarName() +
                            "',warName:'" + jarBeans.get(i).getWarName() +
                            "',jarVersion:'" + jarBeans.get(i).getJarVersion() + "'},";
                }
            }
        }
        json +="]}";
        return json;
    }

    private List<JarBean> toJarBeanList(String warName, String jar, List<JarBean> jarBeans) {
        String[] jars = jar.split(",");
        for (int i = 0;i<jars.length;i ++){
            JarBean jarBean = new JarBean();
            jarBean.setWarName(warName);
            jarBean.setJarName(jars[i].split("-")[0]);
            jarBean.setJarVersion(jars[i].split("-")[1]);
            jarBeans.add(jarBean);
        }
        return jarBeans;
    }

    /**
     * 上传文件*.war
     */
    public ActionForward uploadWar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String msg = null;
        try {
            FileForm entry = (FileForm) form;
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 50);
            upload.setHeaderEncoding("UTF-8");
            List items = upload.parseRequest(request);
            List fileItems = FileUtils.populate(entry, items, "UTF-8");
            for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
                FileItem item = (FileItem) iter.next();
                if (item.getSize() > 0) {
                    String n = item.getFieldName();
                    if (item.getFieldName().equalsIgnoreCase("formFile")) {
                        String name = item.getName();
                        String[] sa = name.split("\\\\");
                        String fileName = sa[sa.length - 1];
                        if(fileName.endsWith(".war")){
                            // 保存到文件夹中改成*.war_tmp
                            String uploadFileDir = path;
                            String filePath = uploadFileDir +"/"+ fileName+"_tmp";
                            File file = new File(filePath);

                            try {
                                item.write(file);
                            } catch (Exception e) {
                                logger.error("升级上传文件", e);
                            }
                        } else {
                            msg = "上传的文件不是[*.war]文件";
                        }
                    }
                    item.delete();
                }
            }
            if(msg==null){
                logService.newLog("INFO", SessionUtils.getAccount(request)
                        .getUserName(), "版本升级", "用户上传需要更新的.WAR文件成功 ");
                msg = "上传成功";
            }
        } catch (Exception e) {
            logger.error("版本升级", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "版本升级", "用户上传需要更新的.WAR文件不成功 ");
            msg = "上传失败"+e.getMessage();
        }
        String json = "{success:true,msg:'"+msg+"'}";

        WebUtil.sendHtml(request, response, json);
		return null;
	}

    /**
     * 升级
     */
    public ActionForward upgrade(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String msg = null;
        try {
            File dir = new File(path);
            boolean isOk = checkTime(dir); //true,可以升级
            if(isOk){
                File[] files = dir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        if (s.endsWith(".war_tmp")) {
                            return true;
                        }
                        return false;
                    }
                });
                if(files.length>0){
                    renameWarFiles(path,files);
                    renameWarTmpFiles(files);
                    if(msg==null){
                        logService.newLog("INFO", SessionUtils.getAccount(request)
                                .getUserName(), "版本升级", "用户升级成功 ");
                        upgrade("cms");
                        msg = "升级成功,几秒后重启!";
                    }
                }else {
                    logService.newLog("INFO", SessionUtils.getAccount(request)
                            .getUserName(), "版本升级", "用户升级失败,没有需要升级的服务");
                    msg = "没有需要升级的服务";
                }
            } else {
                logService.newLog("WARN",  SessionUtils.getAccount(request).getUserName(), "版本升级","用户升级失败,升级过于频繁:10分中内多次升级");
                msg = "升级过于频繁,请于"+upgradeTime+"后升级!";
            }
        } catch (Exception e) {
            logger.error("版本升级", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "版本升级", "用户升级不成功 ");
            msg = "升级失败"+e.getMessage();
        }
        String json = "{success:true,msg:'"+msg+"'}";

        WebUtil.sendHtml(request, response, json);
		return null;
	}

    private boolean checkTime(File dir) {
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                if(s.endsWith(".war")){
                    return true;
                }
                return false;
            }
        });
        long[] times = new long[files.length];
        for (int i=0;i<files.length;i++ ){
            long time = files[i].lastModified();
            times[i] = System.currentTimeMillis() - time;
        }
        Arrays.sort(times);
        long time = times[0];
        if(time < 10 * 60 * 1000){
            long m = time/1000/60;
            long s = time/1000 - m*60;
            upgradeTime = ( 10 - m ) +"分"+ ( 60 - s ) + "秒";
            return false;
        }
        return true;
    }

    /**
     * 改*.war成为*.war_old;
     * @param path
     */
    private void renameWarFiles(String path,File[] files) {
        for(int i=0;i<files.length;i++){
            File tmpFile = files[i];
            File newFile = new File(path+"/" + tmpFile.getName().split("_tmp")[0] + "_old");
            // 改变*.war 成为*.war_old
            if(newFile.exists()){
                newFile.delete();
            }
            File oldFile = new File(path+"/" + tmpFile.getName().split("_tmp")[0]);
            oldFile.renameTo(newFile);
        }
    }

    /**
     * 改*.war_tmp为*.war
     * @param files  *.war_tmp
     */
    private void renameWarTmpFiles(File[] files) {
        for(int i=0;i<files.length;i++){
            File tmpFile = files[i];
            File newFile = new File(path+"/"+tmpFile.getName().split("_tmp")[0]);//*.war
            // 改变*.war_tmp 成为*.war
            if(newFile.exists()){
                newFile.delete();
            }
            tmpFile.renameTo(newFile);
        }
    }

    private void upgrade(String service) {
        Proc proc;
        OSInfo osinfo = OSInfo.getOSInfo();
        if (osinfo.isWin()) {
            proc = new Proc();
            proc.exec("nircmd service upgrade "+service);
        }
        if (osinfo.isLinux()) {
            proc = new Proc();
            proc.exec("service "+service+" upgrade");
        }
    }

    /**
     * 版本回退
     */
    public ActionForward backup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String msg = null;
        try {
            String warName = ServletRequestUtils.getStringParameter(request,"warName");
            File oldFile = new File(path+"/"+warName+"_old");
            File tmpFile = new File(path+"/"+warName+"_tmp");
            if(tmpFile.exists()){
                tmpFile.delete();
            }
            oldFile.renameTo(tmpFile);
            if(msg==null){
                logService.newLog("INFO", SessionUtils.getAccount(request)
                        .getUserName(), "版本升级", "用户回退上一个版本成功 ");
                msg = "回退上一个版本成功";
            }
        } catch (Exception e) {
            logger.error("版本升级", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "版本升级", "用户回退上一个版本不成功 ");
            msg = "回退上一个版本失败"+e.getMessage();
        }
        String json = "{success:true,msg:'"+msg+"'}";

        WebUtil.sendHtml(request, response, json);
		return null;
	}

}
