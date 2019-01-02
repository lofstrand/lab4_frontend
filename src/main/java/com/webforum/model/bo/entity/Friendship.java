package com.webforum.model.bo.entity;

import java.io.Serializable;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Represents Friendships between Users
 */
public class Friendship implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private User requester;
    private User friend;
    private Boolean active;

    // Getters/setters ----------------------------------------------------------------------------
    public User getRequester() { return requester; }
    public void setRequester(User requester) { this.requester = requester; }

    public User getFriend() { return friend; }
    public void setFriend(User friend) { this.friend = friend; }

    public Boolean isActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    // Constructors -------------------------------------------------------------------------------
    public Friendship() {
    }

    public Friendship(User requester, User friend) {
        this.requester = requester;
        this.friend = friend;
        this.active = false;
    }
}
