package com.webforum.model.bo.entity;

import java.io.Serializable;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Represents Messages between Users
 */
public class Message implements Serializable, Comparable<Message> {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private User sender;
    private User receiver;
    private String subject;
    private String content;
    private Boolean read;
    private Boolean deleted;
    private String viewSender;
    private String viewReceiver;

    // Getters/setters ----------------------------------------------------------------------------
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getSender() { return sender; }
    public void setSender(User fromUser) { this.sender = fromUser; }

    public User getReceiver() {return receiver; }
    public void setReceiver(User toUser) { this.receiver = toUser; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Boolean isDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public Boolean isRead() { return read; }
    public void setRead(Boolean read) { this.read = read; }

    public String getViewSender() { return viewSender; }
    public void setViewSender(String viewSender) { this.viewSender = viewSender; }

    public String getViewReceiver() { return viewReceiver; }
    public void setViewReceiver(String viewReceiver) { this.viewReceiver = viewReceiver; }

    // Constructors -------------------------------------------------------------------------------
    public Message() { }

    public Message(User sender, User receiver, String subject, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.read = false;
        this.deleted = false;
        this.viewSender = sender.getUsername();
        this.viewReceiver = receiver.getUsername();
    }

    // Actions ------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender.getUsername() +
                ", receiver=" + receiver.getUsername() +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", deleted=" + deleted +
                ", read=" + read +
                '}';
    }

    /**
     * Comparator for messages, sorting by id
     *
     * @param o the Message to compare
     * @return sorting value
     */
    @Override
    public int compareTo(Message o) {
        return o.id.compareTo(id);
    }
}
