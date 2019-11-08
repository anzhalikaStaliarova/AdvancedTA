package com.epam.rp.com.epam.rp.utils;

import com.epam.rp.com.epam.rp.pages.BaseTestPage;
import org.apache.log4j.LogManager;

public class Logger {

    private static final org.apache.log4j.Logger LOG4J = LogManager.getLogger(BaseTestPage.class);
    private static Logger instance = null;

    private Logger() {
    }
    /**
     * Implementation of the Singleton pattern
     * @return Logger instance
     */
    public synchronized static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

     public void info(Object message) {
        LOG4J.info(message);
    }

}