package com.webforum.model.bo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Represents Users in the community
 */
public class User implements Serializable{
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Boolean blocked;
    private List<Message> inbox = new ArrayList<Message>();
    private Collection<Message> outbox = new ArrayList<Message>();
    private List<BlogPost> blogPosts = new ArrayList<BlogPost>();
    private Collection<Friendship> friendRequests = new ArrayList<Friendship>();
    private Collection<Friendship> friends = new ArrayList<Friendship>();

    // Getters/setters ----------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id= id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Boolean isBlocked() { return blocked; }
    public void setBlocked(Boolean blocked) { this.blocked = blocked; }

    public List<Message> getInbox() { return inbox; }
    public void setInbox(List<Message> inbox) { this.inbox = inbox; }

    public Collection<Message> getOutbox() { return outbox; }
    public void setOutbox(Collection<Message> outbox) { this.outbox = outbox; }

    public List<BlogPost> getBlogPosts() { return blogPosts; }
    public void setBlogPosts(List<BlogPost> blogPosts) { this.blogPosts = blogPosts; }

    public Collection<Friendship> getFriendRequests() {
        return friendRequests;
    }
    public void setFriendRequests(Collection<Friendship> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public Collection<Friendship> getFriends() {
        return friends;
    }
    public void setFriends(Collection<Friendship> friends) {
        this.friends = friends;
    }

    // Constructors -------------------------------------------------------------------------------
    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.blocked = false;
        this.role = Role.MEMBER;
    }

    public User(String username, String password, String email) {
        this(username, password);
        this.email = email;
    }

    public User(String username, String password, String email, Role role) {
        this(username, password, email);
        this.role = role;
    }

    // Actions ------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}