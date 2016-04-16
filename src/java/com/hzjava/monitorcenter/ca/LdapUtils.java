package com.hzjava.monitorcenter.ca;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: hhm
 * Date: 12-8-13
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class LdapUtils {
    private static final Logger log = Logger.getLogger(LdapUtils.class);
    private String caip ="";
    private String ldapport="";
    private String ldapbasedn="";
    private String ldappwd;
    private static Properties prop= new Properties();
    /**
     *
     * 初始化方法
     * @return
     */
    public  Hashtable<String, String>   init() throws IOException {
        prop.load(LdapUtils.class.getResourceAsStream("/config.properties"));
        caip = prop.getProperty("caip");
        ldapport = prop.getProperty("ldapport");
        ldapbasedn = prop.getProperty("ldapbasedn");
        ldappwd = prop.getProperty("ldappwd");

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://"+caip+":"+ldapport);
        env.put(Context.AUTHORITATIVE, "simple");
        env.put(Context.SECURITY_PRINCIPAL,ldapbasedn);
        env.put(Context.SECURITY_CREDENTIALS, ldappwd);
        env.put("com.sun.jndi.ldap.connect.pool", "true");
        return env;
    }
    /**
     * 得到LDAP连接
     * @return 目录对象
     */
    public DirContext getLdapDirContext() throws IOException {
        Hashtable<String, String> env  =  init();
        DirContext ctx =null;
        // 参数为空
        if (env == null) {
//            log.info("请配置ldap连接参数!!!!");
        } else {
            try {
                ctx = new InitialDirContext(env);
//                log.info("ldap 连接开启！！");
            } catch (NamingException e) {
//                log.info("创建ldap连接不成功！");
                e.printStackTrace();
            }
        }
        return ctx;
    }

    /**
     * 关闭LDAP连接
     *
     * @param dirContext DirContext
     */
    public static void closeLdap(DirContext dirContext) {
        // 关闭LDAP连接
        try {
            if (dirContext != null)
                dirContext.close();
//                log.info("ldap连接成功关闭！");
        }
        catch (Exception ex) {
//            log.error("not close DirContext!");
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param attrs
     * @param attributeName     属性名称
     * @return
     * @throws javax.naming.NamingException
     */
    public static String getAttrValue(Attributes attrs, String attributeName) {
        String att = new String() ;
        Attribute attribute = attrs.get(attributeName);
        try{
            for (NamingEnumeration all = attribute.getAll(); all.hasMoreElements();) {
                Object o = all.nextElement();
                att = o.toString();
            }
        }catch (Exception e){
//            log.info(e.getMessage());
            return null;
        }
        return  att;
    }
}
