package com.webforum.ui;


import com.webforum.model.bo.entity.*;
import com.webforum.model.bo.facade.BlogPostHandler;
import com.webforum.model.bo.facade.MessageHandler;
import com.webforum.model.bo.facade.UserHandler;
import com.webforum.util.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 *
 */
@ManagedBean(name = "user", eager = true)
@SessionScoped
public class UserBean implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Boolean blocked;
    private Collection<Message> inbox = new ArrayList<Message>();
    private Collection<Message> outbox = new ArrayList<Message>();
    private Collection<BlogPost> blogPosts = new ArrayList<BlogPost>();
    private Collection<Friendship> friendRequests = new ArrayList<Friendship>();
    private Collection<Friendship> friends = new ArrayList<Friendship>();

    // Getters/setters ----------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Boolean getBlocked() { return blocked; }
    public void setBlocked(Boolean blocked) { this.blocked = blocked; }

    public Collection<Message> getInbox() { return inbox; }
    public void setInbox(Set<Message> inbox) { this.inbox = inbox; }

    public Collection<Message> getOutbox() { return outbox; }
    public void setOutbox(Collection<Message> outbox) { this.outbox = outbox; }

    public Collection<BlogPost> getBlogPosts() { return blogPosts; }
    public void setBlogPosts(Collection<BlogPost> blogPosts) { this.blogPosts = blogPosts; }

    public Collection<Friendship> getFriendRequests() { return friendRequests; }
    public void setFriendRequests(Collection<Friendship> friendRequests) { this.friendRequests = friendRequests; }

    public Collection<Friendship> getFriends() { return friends; }
    public void setFriends(Collection<Friendship> friends) { this.friends = friends; }

    // Constructors -------------------------------------------------------------------------------
    public UserBean() {
        this.username = "";
    }

    // Actions ------------------------------------------------------------------------------------
    /**
     *
     * @param id
     * @return
     */
    public Message getViewMessage (Long id) {

        for(Message msg: inbox){
            if(id.equals(msg.getId()))
                return msg;
        }

        for(Message msg : outbox) {
            if(id.equals(msg.getId())) {
                return msg;
            }
        }

        return null;
    }

    /**
     * Updating user bean with user data
     * @param user
     */
    public void updateUserBean(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.blocked = user.isBlocked();
        this.inbox = user.getInbox();
        this.outbox = user.getOutbox();
        this.blogPosts = user.getBlogPosts();
        this.friendRequests = user.getFriendRequests();
        this.friends = user.getFriends();
    }

    /**
     * Refreshing user by re-downloading the user again through the user handler
     */
    public void refreshUser() {
        User user = UserHandler.getUser(SessionUtils.getUserId());
        updateUserBean(user);
    }

    public void refreshBlogPosts() {
        this.blogPosts = BlogPostHandler.getBlogPostsByUserId(SessionUtils.getUserId());
    }

    public void refreshInbox() {
        this.inbox = MessageHandler.getMessageInboxUserId(SessionUtils.getUserId());
    }

    public void refreshOutbox() {
        this.outbox = MessageHandler.getMessageOutboxUserId(SessionUtils.getUserId());
    }
}

