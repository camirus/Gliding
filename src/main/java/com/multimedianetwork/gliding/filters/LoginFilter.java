/*
 * 
 * 
 */
package com.multimedianetwork.gliding.filters;

import com.multimedianetwork.gliding.beans.model.UserManagerBean;
import com.multimedianetwork.gliding.utils.UAgentInfo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Camelia Rus
 */
public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //get the request page
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestPath = httpServletRequest.getRequestURI();

        System.out.println(requestPath);
        /*
         * omit login page
         */
        if (!(requestPath.contains("login.xhtml")
                || httpServletRequest.getServletPath().contains("/javax.faces.resource")
                || requestPath.contains("/membership_request")
                || requestPath.contains("/template/request"))) {
            boolean loggedIn = false;
            //getting the session object

            HttpSession session = (HttpSession) httpServletRequest.getSession();
            //check if there is a user logged in the session
            UserManagerBean userManagerBean = (UserManagerBean) session.getAttribute(UserManagerBean.BEAN_NAME);
            if (userManagerBean != null) {
                //check if the user is logged in
                loggedIn = userManagerBean.isLoggedIn();
            }

            UAgentInfo agent = new UAgentInfo(httpServletRequest.getHeader("User-Agent"), httpServletRequest.getHeader("Accept"));

            if (!loggedIn) {

                if (agent.isMobilePhone) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.sendRedirect(
                            httpServletRequest.getContextPath() + "/mobile/login.xhtml");
                    return;
                }

                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath() + "/login.xhtml");
                return;
            } else {
                if (!"ADMIN".equals(userManagerBean.getUser().getRole())) {
                    if (requestPath.contains("/admin")) {
                        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                        httpServletResponse.sendRedirect(
                                httpServletRequest.getContextPath() + "/member/index.xhtml");
                    }
                } else {
                    if (agent.isMobilePhone) {
                        if (!requestPath.contains("/mobile")) {
                            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                            httpServletResponse.sendRedirect(
                                    httpServletRequest.getContextPath() + "/mobile/index.xhtml");
                        }
                    }
                }

            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
