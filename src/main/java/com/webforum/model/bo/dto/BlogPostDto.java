package com.webforum.model.bo.dto;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 * Data transfer object for BlogPosts
 */
public class BlogPostDto {
    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private Long owner;
    private String content;

    // Getters/setters ----------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOwner() { return owner; }
    public void setOwner(Long owner) { this.owner = owner; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    // Constructors -------------------------------------------------------------------------------
    public BlogPostDto() {
    }

    public BlogPostDto(Long owner, String content) {
        this.owner = owner;
        this.content = content;
    }
}
