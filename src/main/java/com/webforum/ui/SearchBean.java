package com.webforum.ui;


import com.webforum.model.bo.entity.User;
import com.webforum.model.bo.facade.UserHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 *
 */
@ManagedBean(name = "search", eager = true)
@RequestScoped
public class SearchBean implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private String input;
    private List<User> users;

    // Getters/setters ----------------------------------------------------------------------------
    public String getInput() { return input; }
    public void setInput(String input) { this.input = input; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    // Actions ------------------------------------------------------------------------------------
    /**
     * Search users by username by input through user handler
     */
    public void search() {
        this.users = UserHandler.searchByUsername(input);
    }
}
