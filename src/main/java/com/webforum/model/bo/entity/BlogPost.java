package com.webforum.model.bo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Represents a Blog post for Users
 */
public class BlogPost implements Serializable, Comparable<BlogPost> {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private User owner;
    private String content;
    private String graph_url;
    private Date creationDate;
    private Date updatedDate;

    // Getters/setters ----------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getGraph_url() { return graph_url; }
    public void setGraph_url(String graph_url) { this.graph_url = graph_url; }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }

    // Constructors -------------------------------------------------------------------------------
    public BlogPost() {
    }

    public BlogPost(User owner, String content) {
        this.owner = owner;
        this.content = content;
    }

    public BlogPost(User owner, String content, String graph_url) {
        this.owner = owner;
        this.content = content;
        this.graph_url = graph_url;
    }

    // Actions ------------------------------------------------------------------------------------

    /**
     * Comparator for the Blog post, sorting by creation date
     *
     * @param o the Blog post to compare
     * @return sorting value
     */
    @Override
    public int compareTo(BlogPost o) {
        return o.creationDate.compareTo(this.creationDate);
    }
}
