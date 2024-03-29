package Entity;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class containing data of a message to be sent and received by users.
 */
public class Message implements Serializable {
    private User sender;
    private ArrayList<User> recipients = new ArrayList<>();
    private String text;
    private ImageIcon image;
    private Date timeSent;
    private Date timeDelivered;

    /**
     * Private constructor for the class.
     * @param builder Builder-object for constructing an instance.
     */
    private Message(Builder builder) {
        this.sender = builder.sender;
        this.text = builder.text;
        this.image = builder.image;
        this.recipients = builder.recipients;
    }

    /**
     * Builder class. Constructs the object by the use of its public methods.
     */
    public static class Builder {
        private User sender;
        private final ArrayList<User> recipients = new ArrayList<>();
        private String text;
        private ImageIcon image;

        /**
         * Gets an instance of the builder.
         * @return A builder.
         */
        public static Builder newInstance() {
            return new Builder();
        }

        /**
         * Sets the sender of the message.
         * @param user User-object.
         * @return The builder.
         */
        public Builder setSender(User user) {
            sender = user;
            return this;
        }

        /**
         * Sets the text content of the message.
         * @param text Text content as string.
         * @return The builder.
         */
        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the image of the message.
         * @param image Image as ImageIcon.
         * @return The builder.
         */
        public Builder setImage(ImageIcon image) {
            this.image = image;
            return this;
        }

        /**
         * Sets all receiving users.
         * @param recipients ArrayList of User-objects.
         * @return The builder.
         */
        public Builder setRecipients(ArrayList<User> recipients) {
            this.recipients.addAll(recipients);
            return this;
        }

        /**
         * Constructs a new message.
         * @return
         */
        public Message build() {
            return new Message(this);
        }

    }

    /**
     * Returns the sender.
     * @return User-object.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Sets the time of when the message was sent.
     * @param timeSent Date.
     */
    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    /**
     * Sets the time of when the message was delivered.
     * @param timeDelivered Date.
     */
    public void setTimeDelivered(Date timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    /**
     * Gets the list of recipients.
     * @return ArrayList of User-objects.
     */
    public ArrayList<User> getRecipients() {
        return recipients;
    }

    /**
     * Gets the text content.
     * @return String.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the image.
     * @return imageicon
     */
    public ImageIcon getImage() {
        return this.image;
    }

    /**
     * Returns the time of when the message was sent.
     * @return Date.
     */
    public Date getTimeSent() {
        return timeSent;
    }

    /**
     * Returns the time of when the message was delivered.
     * @return Date.
     */
    public Date getTimeDelivered() {
        return timeDelivered;
    }
}
