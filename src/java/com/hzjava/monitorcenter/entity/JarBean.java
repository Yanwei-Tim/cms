package com.hzjava.monitorcenter.entity;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-21
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class JarBean {
    private String warName;
    private String jarName;
    private String jarVersion;

    public String getWarName() {
        return warName;
    }

    public void setWarName(String warName) {
        this.warName = warName;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getJarVersion() {
        return jarVersion;
    }

    public void setJarVersion(String jarVersion) {
        this.jarVersion = jarVersion;
    }
}
