package Model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Message implements Serializable {
    private User sender;
    private ArrayList<User> recipients = new ArrayList<>();
    private String text;
    private ImageIcon image;
    private Date timeSent;
    private Date timeDelivered;

    public Message(Builder builder) {
        this.sender = builder.sender;
        this.text = builder.text;
        this.image = builder.image;
        this.recipients = builder.recipients;
    }

    public static class Builder {
        private User sender;
        private final ArrayList<User> recipients = new ArrayList<>();
        private String text;
        private ImageIcon image;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setSender(User user) {
            sender = user;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setImage(ImageIcon image) {
            this.image = image;
            return this;
        }

        public Builder setRecipients(User...recipients) {
            this.recipients.addAll(Arrays.asList(recipients));
            return this;
        }
        public Message build() {
            return new Message(this);
        }

    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public void setTimeDelivered(Date timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    public ArrayList<User> getRecipients() {
        return recipients;
    }

    public String getText() {
        return text;
    }

    public ImageIcon getImage() {
        return this.image;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public Date getTimeDelivered() {
        return timeDelivered;
    }
}
