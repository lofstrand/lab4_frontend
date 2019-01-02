package com.webforum.ui;

import com.webforum.model.bo.dto.MessageDto;
import com.webforum.model.bo.dto.MessageReceiverDto;
import com.webforum.model.bo.entity.User;
import com.webforum.model.bo.facade.MessageHandler;
import com.webforum.model.bo.facade.UserHandler;
import com.webforum.util.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 *
 */
@ManagedBean(name = "messageBean", eager = true)
@RequestScoped
public class MessageBean implements Serializable {
    // Constants ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------------------------------------------------------
    @ManagedProperty(value="#{user}")
    private UserBean userBean;
    private String receiver;
    private String subject;
    private String content;
    private ArrayList<MessageReceiverDto> receivers;

    // Getters/setters ----------------------------------------------------------------------------
    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public UserBean getUserBean() { return userBean; }
    public void setUserBean(UserBean userBean) { this.userBean = userBean; }

    /**
     *
     * @return
     */
    public ArrayList<MessageReceiverDto> getReceivers() {
        ArrayList<MessageReceiverDto> receivers = new ArrayList<MessageReceiverDto>();

        List<User> users = UserHandler.getAllUsers();
        for (User user: users) {
            if(user.getId() != SessionUtils.getUserId()) {
                MessageReceiverDto receiverDto = new MessageReceiverDto(user.getId(), user.getUsername());
                receivers.add(receiverDto);
            }
        }
        return receivers;
    }
    public void setReceivers(ArrayList<MessageReceiverDto> receivers) { this.receivers = receivers; }

    // Actions ------------------------------------------------------------------------------------
    /**
     * Send message
     */
    public void send() {
        // Create the message with entered data
        MessageDto messageDto = new MessageDto(SessionUtils.getUserId(), Long.valueOf(receiver), subject, content);

        // Post the message to the database through message handler
        Boolean success = MessageHandler.create(messageDto);

        // Publish message to front end and clear data.
        if(success) {
            MessageView.info("Success", "The message was successfully sent.");

            // Refresh user bean with the new message data
            userBean.refreshInbox();
            userBean.refreshOutbox();
            setContent("");
            setSubject("");
            setReceiver(null);
        } else {
            MessageView.warn("Warning", "Something went wrong while sending the message.");
        }
    }
}
