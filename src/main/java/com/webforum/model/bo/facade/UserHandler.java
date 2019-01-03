package com.webforum.model.bo.facade;

import com.webforum.model.bo.entity.BlogPost;
import com.webforum.model.bo.entity.Message;
import com.webforum.model.bo.entity.User;
import com.webforum.util.SessionUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 */
public class UserHandler {
    // Constants ----------------------------------------------------------------------------------
    public static final String REST_USERS_URI       = "http://192.168.99.100:8082/api/users";

    // Actions ------------------------------------------------------------------------------------

    /**
     * Validator for user authentication
     *
     * @param username the username
     * @param password the password
     * @return success / fail
     */
    public static boolean validate(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        // Controlling username and password if they are valid
        if(!username.matches("[A-Za-z0-9_]+") || username == null || password == null) {
            return false;
        }

        // Creating a user with the username and password to send for authentication
        User userLogin = new User(
                username,
                password
        );

        // Authenticate username and password towards database
        try {
            URI uri = restTemplate.postForLocation(REST_USERS_URI + "/login", userLogin, User.class);
            if(uri != null) {
                User user = restTemplate.getForObject(uri, User.class);

                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("user_id", user.getId());
            }

            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    /**
     * Fetch user from database
     *
     * @param id the user identification
     * @return the user
     */
    public static User getUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_USERS_URI + "/" + id, User.class);

            // Fetch blog posts
            List<BlogPost> blogPosts = BlogPostHandler.getBlogPostsByUserId(id);
            user.setBlogPosts(blogPosts);

            // Fetch inbox and outbox
            List<Message> inbox = MessageHandler.getMessageInboxUserId(id);
            List<Message> outbox = MessageHandler.getMessageOutboxUserId(id);
            user.setInbox(inbox);
            user.setOutbox(outbox);

            return user;
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Get all users from database
     *
     * @return List of users
     */
    public static List<User> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> response = restTemplate.exchange(REST_USERS_URI + "/", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() { });
        List<User> users = (List<User>) response.getBody();

        return users;
    }

    /**
     * Register username and password as a new User
     *
     * @param username the username
     * @param password the password
     * @return success / fail
     */
    public static Boolean register(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        User userData = new User(username, password);

        // Validate username and password
        if(!username.matches("[A-Za-z0-9_]+") || username == null || password == null) {
            return false;
        }

        // Send registration to database
        try {
            URI uri = restTemplate.postForLocation(REST_USERS_URI + "/", userData, User.class);
            return true;
        } catch(HttpClientErrorException.Conflict e) {
            return false;
        }
    }

    /**
     * Search username by input
     *
     * @param input the search input
     * @return list of users
     */
    public static List<User> searchByUsername(String input) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> response = restTemplate.exchange(REST_USERS_URI + "/search/" + input, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() { });
        List<User> users = (List<User>) response.getBody();

        return users;
    }
}
