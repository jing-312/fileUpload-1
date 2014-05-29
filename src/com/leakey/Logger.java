package com.leakey;

import org.apache.log4j.PropertyConfigurator;
/**
 * 日志类
 */
public class Logger {
    private static org.apache.log4j.Logger logger = null;

    public static org.apache.log4j.Logger loadLogger() {
        if (logger == null) {
            logger = org.apache.log4j.Logger.getLogger(Logger.class);
            PropertyConfigurator.configure("config/log4j.properties");
        }
        return logger;
    }
}

