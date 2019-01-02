package com.webforum.model.bo.facade;

import com.webforum.model.bo.dto.BlogPostDto;
import com.webforum.model.bo.entity.BlogPost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 */
public class BlogPostHandler {
    // Constants ----------------------------------------------------------------------------------
    public static final String REST_BLOGPOSTS_URI   = "http://192.168.99.100:8081/rest/api/blogposts";

    // Actions ------------------------------------------------------------------------------------
    /**
     * Creating a Blog post and saving it to the database
     *
     * @param blogPostDto the blog post dto
     * @return success / fail
     */
    public static Boolean create(BlogPostDto blogPostDto) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            URI uri = restTemplate.postForLocation(REST_BLOGPOSTS_URI + "/", blogPostDto, BlogPostDto.class);
            return true;
        } catch(HttpClientErrorException.Conflict e) {
            return false;
        }
    }

    /**
     * Fetching Blog posts by user identification
     *
     * @param user_id the user identification
     * @return the blog posts
     */
    public static List<BlogPost> getBlogPostsByUserId(Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        // Fetch users blog posts
        try {
            ResponseEntity<List<BlogPost>> response = restTemplate.exchange(REST_BLOGPOSTS_URI + "/user/" + user_id.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<BlogPost>>() {
            });
            List<BlogPost> blogPosts = (List<BlogPost>) response.getBody();
            Collections.sort(blogPosts);
            return blogPosts;
        } catch (Exception e) {
            return new ArrayList<BlogPost>();
        }

    }
}
