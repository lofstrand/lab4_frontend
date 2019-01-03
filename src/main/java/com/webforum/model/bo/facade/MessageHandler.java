package com.webforum.model.bo.facade;

import com.webforum.model.bo.dto.MessageDto;
import com.webforum.model.bo.entity.Message;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 */
public class MessageHandler {
    // Constants ----------------------------------------------------------------------------------
    public static final String REST_MESSAGES_URI    = "http://192.168.99.100:8083/api/messages";

    // Actions ------------------------------------------------------------------------------------
    /**
     * Creating a Message and saving it to the database
     *
     * @param messageDto the message dto
     * @return success / fail
     */
    public static Boolean create(MessageDto messageDto) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            URI uri = restTemplate.postForLocation(REST_MESSAGES_URI + "/", messageDto, MessageDto.class);
            return true;
        } catch(HttpClientErrorException.Conflict e) {
            return false;
        }
    }


    /**
     * Fetching Message Inbox by user identification
     *
     * @param user_id the user identification
     * @return the messages
     */
    public static List<Message> getMessageInboxUserId(Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        // Fetch users blog posts
        try {
            ResponseEntity<List<Message>> response = restTemplate.exchange(REST_MESSAGES_URI + "/user/" + user_id.toString() + "/inbox/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
            });
            List<Message> messages = (List<Message>) response.getBody();
            Collections.sort(messages);
            return messages;
        } catch (Exception e) {
            return new ArrayList<Message>();
        }

    }

    /**
     * Fetching Message Outbox by user identification
     *
     * @param user_id the user identification
     * @return the messages
     */
    public static List<Message> getMessageOutboxUserId(Long user_id) {
        RestTemplate restTemplate = new RestTemplate();
        // Fetch users blog posts
        try {
            ResponseEntity<List<Message>> response = restTemplate.exchange(REST_MESSAGES_URI + "/user/" + user_id.toString() + "/outbox/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
            });
            List<Message> messages = (List<Message>) response.getBody();
            Collections.sort(messages);
            return messages;
        } catch (Exception e) {
            return new ArrayList<Message>();
        }

    }
}
