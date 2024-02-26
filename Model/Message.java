package Model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Serializable {
    private User sender;
    private final ArrayList<User> recipients = new ArrayList<>();
    private String text;
    private ImageIcon image;
    private Date timeSent;
    private Date timeDelivered;

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void addRecipients(User user) {
        recipients.add(user);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public void setTimeDelivered(Date timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipients() {
        for (User recipient : recipients) {
            return recipient;
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public Date getTimeDelivered() {
        return timeDelivered;
    }
}
