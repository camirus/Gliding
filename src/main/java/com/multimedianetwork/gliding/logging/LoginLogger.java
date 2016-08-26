/*
 * 
 * 
 */
package com.multimedianetwork.gliding.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles logging of login data like username, password, date, ip
 *
 * @author Camelia Rus
 */
public class LoginLogger {

    public static String LOGIN_FILENAME = "/var/log/gliding/login.txt";
    public static String ERROR_LOGIN_FILENAME = "/var/log/gliding/loginerror.txt";
    
      public static LoginLogger instance = new LoginLogger();

    public static String getLOGIN_FILENAME() {
        return LOGIN_FILENAME;
    }

    public static void setLOGIN_FILENAME(String LOGIN_FILENAME) {
        LoginLogger.LOGIN_FILENAME = LOGIN_FILENAME;
    }

    public static String getERROR_LOGIN_FILENAME() {
        return ERROR_LOGIN_FILENAME;
    }

    public static void setERROR_LOGIN_FILENAME(String ERROR_LOGIN_FILENAME) {
        LoginLogger.ERROR_LOGIN_FILENAME = ERROR_LOGIN_FILENAME;
    }

    private LoginLogger() {
    }

    public static LoginLogger getInstance() {
        return instance;
    }

    public synchronized void log(String username, String password, String ip, boolean error) {

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date now = new Date();
        BufferedWriter bw = null;

        try {
            boolean append = true;

            bw = new BufferedWriter(
                    new FileWriter(
                    new File(error ? ERROR_LOGIN_FILENAME : LOGIN_FILENAME), append));
            bw.write((error ? "EROARE" : "") + "LOGIN " + df.format(now)
                    + " username: " + username + " password: " + password + " ip: " + ip);
            bw.newLine();

        } catch (IOException ex) {
            Logger.getLogger(LoginLogger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
