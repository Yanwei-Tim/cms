package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.utils.MonitorServiceUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-26
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class MonitorServiceTimer implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(MonitorServiceTimer.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("进入MonitorServiceTimer");
        Timer timer1 = new Timer();
        timer1.schedule(new MonitorServiceUtil(),1000,1000*600);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
