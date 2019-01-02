package com.webforum.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Session utility for user login
 *
 */
public class SessionUtils {

    // Actions ------------------------------------------------------------------------------------
    /**
     * Get current HttpSession
     * @return the http session
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    /**
     * Get Htpp servlet request
     * @return
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    /**
     * Get logged on users username from session
     *
     * @return the username
     */
    public static String getUsername() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    /**
     * Get logged on users identification from session
     * @return
     */
    public static Long getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (Long) session.getAttribute("user_id");
        else
            return null;
    }

    /**
     * Destroy the session and clear attributes
     */
    public static void destroySession() {
        HttpSession session = getSession();
        session.setAttribute("username", null);
        session.setAttribute("user_id", null);
        session.invalidate();
    }
}
