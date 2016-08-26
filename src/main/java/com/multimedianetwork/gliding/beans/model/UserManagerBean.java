/*
 * 
 * 
 */
package com.multimedianetwork.gliding.beans.model;

import com.multimedianetwork.gliding.logging.LoginLogger;
import com.multimedianetwork.gliding.managers.UserManager;
import com.multimedianetwork.gliding.model.User;
import com.multimedianetwork.gliding.utils.PasswordService;
import com.multimedianetwork.webutil.FacesUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Camelia Rus
 */
@SessionScoped
@ManagedBean(name = "userManagerBean")
public class UserManagerBean implements Serializable {

    public static final String BEAN_NAME = "userManagerBean";
    private static final String LOGIN_ERROR_MESSAGE = "Utilizatorul nu a fost gasit!";
    public static final String CHANGE_PASSWORD_ERROR_MESSAGE = "Eroare schimbare parola!";
    public static final String CHANGE_PASSWORD_SUCCESS_MESSAGE = "Parola a fost schimbata";
    public static final String INCORRECT_OLD_PASSWORD_ERROR_MESSAGE = "Parola veche nu este corecta!";
    public static final String TO_CHANGE_PASSWORD = "schimbare-parola";
    public static final String TO_ADMIN_INDEX = "/admin/index?faces-redirect=true";
    public static final String TO_ADMIN_MOBILE_INDEX = "/mobile/index?faces-redirect=true";
    public static final String TO_MEMBER_INDEX = "/member/index?faces-redirect=true";
    public static final String UPDATE_USER_SUCCESS_MESSAGE = "Setarile au fost salvate";
    public static final String UPDATE_USER_ERROR_MESSAGE = "Eroare salvare setari";
    public static final String TO_LOGIN = "login?faces-redirect=true";

    private boolean mobile;

    private String username;
    private String password;
    private String oldPasword;
    private String newPasword;
    private String paswordConfirmation;
    private User user;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPasword() {
        return newPasword;
    }

    public void setNewPasword(String newPasword) {
        this.newPasword = newPasword;
    }

    public String getOldPasword() {
        return oldPasword;
    }

    public void setOldPasword(String oldPasword) {
        this.oldPasword = oldPasword;
    }

    public String getPaswordConfirmation() {
        return paswordConfirmation;
    }

    public void setPaswordConfirmation(String paswordConfirmation) {
        this.paswordConfirmation = paswordConfirmation;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        if (user != null) {
            return user.getRole();
        }
        return "";
    }

    public String login() {

        final boolean loginError;
        final String remoteIP = getRemoteIp();
        final String encryptedPassword = PasswordService.getInstance().encrypt(password);

        user = new UserManager().getByUsernameAndPassword(username, encryptedPassword);
        loginError = (user == null);
        /*
         * Log login info (handled by new thread)
         */
        new Thread() {
            @Override
            public void run() {
                LoginLogger.getInstance().log(username, encryptedPassword, remoteIP, loginError);
            }
        }.start();

        /*
         * Return an error message in case of login error, otherwise forward to index
         */
        if (loginError) {
            FacesUtil.addErrorMessage(LOGIN_ERROR_MESSAGE);
            return "";
        } else {
            if ("ADMIN".equals(user.getRole())) {
                if (mobile) {
                    return TO_ADMIN_MOBILE_INDEX;
                } else {
                    return TO_ADMIN_INDEX;
                }
            } else {
                return TO_MEMBER_INDEX;
            }
        }
    }

    public String changePassword() {

        if (isLoggedIn()) {

            String encryptedPassword = PasswordService.getInstance().encrypt(newPasword);

            if (!oldPasword.equals(password)) {
                FacesUtil.addErrorMessage(INCORRECT_OLD_PASSWORD_ERROR_MESSAGE);
                return TO_CHANGE_PASSWORD;
            }
            try {
                user.setPassword(encryptedPassword);
                new UserManager().update(user);
            } catch (Exception ex) {
                FacesUtil.addInfoMessage(CHANGE_PASSWORD_SUCCESS_MESSAGE);
            }

            FacesUtil.addInfoMessage(CHANGE_PASSWORD_SUCCESS_MESSAGE);
            password = newPasword;
            user.setPassword(password);
            return TO_CHANGE_PASSWORD;
        }
        return TO_LOGIN;

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public String getRemoteIp() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRemoteAddr().equals("127.0.0.1") ? request.getHeader("X-Forwarded-For") : request.getRemoteAddr();
    }

}
