package Controller.Client;

import Entity.Message;
import Entity.User;
import Entity.MessageDisplayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles all Message-objects from the boundary-classes.
 */
public class MessageController {
    private ClientStarter starter;
    private OutputHandler outputHandler;
    private MessageDisplayer displayer;

    /**
     * Constructor for the class.
     * @param starter ClientStarter-object.
     */
    public MessageController(ClientStarter starter) {
        this.starter = starter;
    }

    /**
     * Unpacks a Message-object and sends the relevant data to the boundary-classes.
     * @param message Message-object.
     */
    public void displayMessage(Message message) {
        String sender = message.getSender().toString();
        ImageIcon senderImage = message.getSender().getImage();
        ImageIcon messageImage = message.getImage();
        ImageIcon mainUserImage = starter.getUser().getImage();
        String messageString = String.format("<html> <br></span> %s %s <br> %s </span><br></span></html>", sender, message.getTimeDelivered().toString().substring(0,17), message.getText());
        displayer.displayMessage(sender, senderImage, mainUserImage, messageString, messageImage);
    }

    /**
     * Receives data from boundary-classes, creates a Message-object and sends it to the outputhandler.
     * @param text Text content of message.
     * @param image Image of message.
     * @param recipients Recipients of message.
     */
    public void sendMessage(String text, ImageIcon image, String...recipients) {
        ArrayList<User> recipientsArray = new ArrayList<>();
        for (String user : recipients) {
            HashMap<String, ImageIcon> userImagePair = displayer.getUserImagePair();
            recipientsArray.add(new User(user, userImagePair.get(user)));
        }

        Message message = Message.Builder.newInstance()
                .setSender(starter.getUser())
                .setText(text)
                .setImage(image)
                .setRecipients(recipientsArray)
                .build();
        outputHandler.sendMessage(message);
    }

    /**
     * Sets an outputhandler.
     * @param outputHandler OutputHandler-object.
     */
    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    /**
     * Sets an implementation of MessageDisplayer.
     * @param displayer Implementation of MessageDisplayer.
     */
    public void setMessageDisplayer(MessageDisplayer displayer) {
        this.displayer = displayer;
    }
}
