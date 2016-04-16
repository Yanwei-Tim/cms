package com.hzjava.monitorcenter.domain;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-20
 * Time: 上午9:49
 * To change this template use File | Settings | File Templates.
 * 终端加固地址
 */
public class TerminalAddress {

    private long id;

    private String ip;

    private int port;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
