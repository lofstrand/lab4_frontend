package com.webforum.ui;

import com.webforum.model.bo.entity.BlogPost;
import com.webforum.model.bo.entity.Role;
import com.webforum.model.bo.entity.User;
import com.webforum.model.bo.facade.BlogPostHandler;
import com.webforum.model.bo.facade.UserHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 *
 */
@ManagedBean(name = "member", eager = true)
@ViewScoped
public class MemberBean implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private String username;
    private String email;
    private Role role;
    private List<BlogPost> blogPosts;

    // Getters/setters ----------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<BlogPost> getBlogPosts() { return blogPosts; }
    public void setBlogPosts(List<BlogPost> blogPosts) { this.blogPosts = blogPosts; }

    // Actions ------------------------------------------------------------------------------------

    /**
     * Initiate the member bean by fetching user by identification in URI
     */
    public void init() {
        // Bad request with invalid identification sends a message to front end
        if (id == null) {
            MessageView.error("Warning", "Bad request. Please use a link from within the system.");
            return;
        }

        // Fetch user from database through user handler
        User user = UserHandler.getUser(id);

        // Check if user is null or invalid
        if (user == null || !user.getId().equals(id)) {
            MessageView.error("Warning", "Bad request. Unknown user.");
        } else {
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.role = user.getRole();
            this.blogPosts = BlogPostHandler.getBlogPostsByUserId(user.getId());
        }
    }
}
