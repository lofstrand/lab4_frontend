package com.webforum.ui;

import com.webforum.model.bo.dto.BlogPostDto;
import com.webforum.model.bo.facade.BlogPostHandler;
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
@ManagedBean(name = "blogpost", eager = true)
@RequestScoped
public class BlogPostBean implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    @ManagedProperty(value="#{user}")
    private UserBean userBean;
    private String content;

    // Getters/setters ----------------------------------------------------------------------------
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public UserBean getUserBean() { return userBean; }
    public void setUserBean(UserBean userBean) { this.userBean = userBean; }

    // Actions ------------------------------------------------------------------------------------

    /**
     * Save the blog post
     */
    public void save() {
        // Create blog post with entered data
        BlogPostDto blogPostDto = new BlogPostDto(SessionUtils.getUserId(), content);

        // Post the blog post to database through blog post handler
        Boolean success = BlogPostHandler.create(blogPostDto);

        // Create success / fail message to front end and refresh user bean
        if(success) {
            MessageView.info("Success", "The post is successfully created.");

            // Clear content
            setContent("");

            // Update user bean with new blog post data.
            userBean.refreshBlogPosts();
        } else {
            MessageView.warn("Warning", "Something went wrong while creating the post.");
        }
    }
}
