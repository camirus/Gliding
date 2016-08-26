/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2009 Multimedia Network. All rights reserved.
 *

 */
package com.multimedianetwork.webutil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class for JavaServer Faces.
 *
 */
public class FacesUtil {

    private static final int DEFAULT_BUFFER_SIZE = 10240;

    /**
     * Get servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param beanName the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        Object o = context.getELContext().getELResolver().getValue(context.getELContext(), null, beanName);
        return o;
    }

    /**
     * Get parameter value from request scope.
     *
     * @param name the name of the parameter
     * @return the parameter value
     */
    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Add information message.
     *
     * @param msg the information message
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Add information message to a sepcific client.
     *
     * @param clientId the client id
     * @param msg the information message
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     * Add error message.
     *
     * @param msg the error message
     */
    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }

    /**
     * Add error message to a sepcific client.
     *
     * @param clientId the client id
     * @param msg the error message
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     * Add warning message.
     *
     * @param msg the warning message
     */
    public static void addWarningMessage(String msg) {
        addWarningMessage(null, msg);
    }

    /**
     * Add warning message to a sepcific client.
     *
     * @param clientId the client id
     * @param msg the warning message
     */
    public static void addWarningMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
    }

    /**
     * Get remote User
     *
     * @return remote user name
     *
     */
    public static String getUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    /**
     * Returns true if user is in role role, false otherwise
     *
     * @return true if user is in role role, false otherwise
     *
     */
    public static boolean isUserInRole(String role) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }

    /**
     * Get remote IP
     *
     * @return remote IP
     *
     */
    public static String getRemoteIp() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
    }

    /**
     * Sets the value of the bean identified by name to object on the session map
     *
     * @param name
     * @param object
     */
    public static void putSessionMapObject(String name, Object object) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, object);
    }

     public static void writePDFToOutput(String dir, String filename, 
             boolean attach, String downloadedFileName) throws Exception {
         
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();



        String path = request.getRealPath(dir);

        File file = new File(path, filename);
        BufferedInputStream input = null;
        ServletOutputStream output = null;

        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

            // Init servlet response.
            response.reset();
            if (attach) {
                response.setHeader("Content-Type", "x-download");
            } else {
                response.setHeader("Content-Type", "application/pdf");
            }

            //response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setContentLength((int) file.length());
            if (attach) {
                response.setHeader("Content-Disposition", "attachment; filename="+downloadedFileName);
            } else {
                response.setHeader("Content-Disposition", "inline; filename="+downloadedFileName);
            }
            output = response.getOutputStream();

            // Write file contents to response.
            int readBytes = 0;
            byte[] readBs = new byte[DEFAULT_BUFFER_SIZE];
            while ((readBytes = input.read(readBs)) != -1) {
                output.write(readBs, 0, readBs.length);
                output.flush();
            }

            // Finalize task.

        } catch (Exception ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Gently close streams.
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
            response.flushBuffer();
        }

        // Inform JSF that it doesn't need to handle response.
        // This is very important, otherwise you will get the following exception in the logs:
        // java.lang.IllegalStateException: Cannot forward after response has been committed.
        facesContext.responseComplete();
    }

    public static void forwardPdfToServlet(String filename) {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            request.setAttribute("filename", filename);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faces/main/PdfShowerServlet");
            ctx.responseComplete();
            dispatcher.forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
}
