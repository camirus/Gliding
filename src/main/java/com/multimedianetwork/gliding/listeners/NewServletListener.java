/*
 * 
 * 
 */
package com.multimedianetwork.gliding.listeners;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author user
 */
@WebListener()
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        C3P0Registry.getNumPooledDataSources();

        @SuppressWarnings({"unchecked", "rawtypes"})
        Iterator<Set> it = C3P0Registry.getPooledDataSources().iterator();
        while (it.hasNext()) {
            try {
                PooledDataSource dataSource = (PooledDataSource) it.next();
                dataSource.hardReset();
                dataSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Now deregister JDBC drivers in this context's ClassLoader:
        // Get the webapp's ClassLoader
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                }
            } else {
                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
            }
        }

    }
}
