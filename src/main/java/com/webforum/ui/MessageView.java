package com.webforum.ui;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Helper class to publish FacesMessage to front end
 */
@ManagedBean
public class MessageView {

    // Actions ------------------------------------------------------------------------------------
    /**
     * Creating Faces Messages to font end
     *
     * @param severityInfo the severity info
     * @param summary the summary
     * @param details the details
     */
    private static void createFacesMessage(FacesMessage.Severity severityInfo, String summary, String details) {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        severityInfo,
                        summary,
                        details
                )
        );
    }

    /**
     * Creating info Faces message
     *
     * @param summary the summary
     * @param detail the details
     */
    public static void info(String summary, String detail) {
        createFacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    /**
     * Creating warning Faces message
     *
     * @param summary the summary
     * @param detail the details
     */
    public static void warn(String summary, String detail) {
        createFacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }

    /**
     * Creating error Faces message
     *
     * @param summary the summary
     * @param detail the details
     */
    public static void error(String summary, String detail) {
        createFacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    /**
     * Creating fatal Faces message
     *
     * @param summary the summary
     * @param detail the details
     */
    public static void fatal(String summary, String detail) {
        createFacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
    }
}