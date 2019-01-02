package com.webforum.ui;

import com.webforum.model.bo.entity.User;
import com.webforum.model.bo.facade.UserHandler;
import com.webforum.util.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 *
 */
@ManagedBean(name = "auth", eager = true)
@RequestScoped
public class AuthBean implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    @ManagedProperty(value="#{user}")
    private UserBean userBean;
    private String username;
    private String password;

    // Getters/setters ----------------------------------------------------------------------------
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserBean getUserBean() { return userBean; }
    public void setUserBean(UserBean userBean) { this.userBean = userBean; }

    // Actions ------------------------------------------------------------------------------------

    /**
     * Validating username and password
     *
     * @return the page to show
     */
    public String validateAuth() {
        // Check validation with user handler
        boolean valid = UserHandler.validate(username, password);

        // Redirect if invalid authentication
        if(!valid) {
            MessageView.error("Warning", "Incorrect username and/or password.");
            return "login";
        }

        // Get user id that logged on and update the user bean with that User
        Long user_id = SessionUtils.getUserId();
        User user = UserHandler.getUser(user_id);

        if(user != null)
            userBean.updateUserBean(user);

        // Redirect to index
        return "index";
    }

    /**
     * Registration for user
     *
     * @return redirect page
     */
    public String register() {
        // Register user through user handler
        boolean valid = UserHandler.register(username, password);

        // Redirect if registration failed.
        if(!valid) {
            MessageView.error("Warning", "The username is already taken, please try again.");
            return "register";
        }

        // Return success message and redirect to login page
        MessageView.info("Success", "Successfully registered as " + username + ".");
        return "login";
    }

    /**
     * Logout user and destroy session
     *
     * @return redirect page
     */
    public String logout() {
        // Destroy session
        SessionUtils.destroySession();

        // Redirect page
        return "login";
    }
}
