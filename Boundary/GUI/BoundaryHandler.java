package Boundary.GUI;

import Controller.Client.ClientStarter;
import Controller.Client.ContactController;
import Controller.Client.MessageController;
import Entity.ContactUpdateDisplayer;
import Entity.MessageDisplayer;
import Entity.UpdateDisplayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class distributing incoming data from controller-classes to Swing-components.
 */
public class BoundaryHandler implements MessageDisplayer, UpdateDisplayer, ContactUpdateDisplayer {
    private ClientStarter starter;
    private MessageController messageController;
    private ContactController contactController;
    private HashMap<String, ChatHandler> activeChats = new HashMap<>();
    private HashMap<String, ImageIcon> userAndImage = new HashMap<>();
     private ContactsFrame contactsFrame;

    /**
     * Constructor for the class.
     * @param starter ClientStarter-object.
     * @param messageController MessageController-object.
     * @param contactController ContactController-object.
     */
    public BoundaryHandler(ClientStarter starter, MessageController messageController, ContactController contactController) {
        new LoginFrame(this);
        this.starter = starter;
        this.messageController = messageController;
        this.contactController = contactController;
    }

    /**
     * Sends message-data to the correct Swing-component holding a conversation between two users.
     * @param sender Sender of message.
     * @param senderImage Userimage of the sender.
     * @param mainUserImage Userimage of the main user.
     * @param messageString Text content of message.
     * @param image Image to be sent as part of message.
     */
    @Override
    public void displayMessage(String sender, ImageIcon senderImage, ImageIcon mainUserImage, String messageString, ImageIcon image) {
        if (!activeChats.containsKey(sender)) {
            openNewChat(sender, new ChatFrame(this, sender, senderImage, mainUserImage));
        }
        userAndImage.put(sender, senderImage);
        ChatHandler chat = activeChats.get(sender);
        chat.displayMessage(messageString, image);
    }

    /**
     * Returns a hashmap of usernames and user images paired to the correct user.
     * @return HashMap
     */
    @Override
    public HashMap<String, ImageIcon> getUserImagePair() {
        return userAndImage;
    }

    /**
     * Sends information about currently connected users to the correct Swing-components.
     * @param newUser Latest connected user.
     * @param onlineUsers List of currently connected users.
     */
    @Override
    public void displayUpdate(String newUser, ArrayList<String> onlineUsers) {
        contactsFrame.setLatestLogin(newUser);
        contactsFrame.setAllUsersList(onlineUsers);
    }

    /**
     * Sends a list of the users contacts to the correct Swing-component.
     * @param contactUpdateObject ArrayList of Strings.
     */
    @Override
    public void displayContactUpdate(ArrayList<String> contactUpdateObject) {
        contactsFrame.setContactList(contactUpdateObject);
    }

    /**
     * Gets called when the user wants to log in. A username and image is sent to a controller-class for creation of a User-object.
     * @param userName Username as String.
     * @param userImage user image as ImageIcon.
     */
    public void newLogIn(String userName, ImageIcon userImage) {
        starter.createUser(userName, userImage);
    }

    /**
     * Gets called when a user sends a message. Relevant data is sent to a controller-class for creation of a Message-object.
     * @param text Text content of message.
     * @param image Image of message.
     * @param recipients Recipients of message.
     */
    public void sendMessage(String text, ImageIcon image, String...recipients) {
        messageController.sendMessage(text, image, recipients);
    }

    /**
     * Opens a new Swing-component of a conversation between two users.
     * @param user username as String.
     * @param chatFrame ChatFrame-object.
     */
    public void openNewChat(String user, ChatFrame chatFrame) {
        activeChats.put(user, new ChatHandler(chatFrame));
    }

    /**
     * Gets called on when the user closes a Swing-component of a conversation. Removes a username and ChatHandler-pair from the HashMap activeChats.
     * @param identifier Username used as key.
     */
    public void removeChatHandler(String identifier) {
        System.out.println(activeChats.remove(identifier));
    }

    /**
     * Sets a ContactFrame-object.
     * @param contactsFrame Swing-component.
     */
    public void setContactsFrame(ContactsFrame contactsFrame) {
        this.contactsFrame = contactsFrame;
    }

    /**
     * Gets the main user's image.
     * @return User image.
     */
    public ImageIcon getUserImage(){
        return starter.getUser().getImage();
    }

    /**
     * Gets a recipient user's image.
     * @param userName User image.
     * @return image as ImageIcon.
     */
    public ImageIcon getRecipientImage(String userName) {
        return userAndImage.get(userName);
    }

    /**
     * Gets the main user's username.
     * @return username as string.
     */
    public String getUser(){
        return starter.getUserName();
    }

    /**
     * Sends an ArrayList of contacts to the ContactController.
     * @param newContactList list of contacts.
     */
    public void sendUpdatedContactList(ArrayList<String> newContactList) {
        contactController.sendUpdatedContactList(newContactList);
    }

    /**
     * Displays a user's own message in a Swing-component. Gets called on when the user sends a message.
     * @param text Text content of message.
     * @param image Image of message.
     * @param user Username as string.
     */
    public void displayOwnMessage(String text, ImageIcon image, String user) {
        String userName = starter.getUserName();
        String messageString = String.format("<html> <br></span> %s %s <br> %s </span><br></span></html>", userName, new Date().toString().substring(0,17), text);
        ChatHandler chat = activeChats.get(user);
        chat.displayMessage(messageString, image);
    }

    /**
     * Checks if a Swing-component of a conversation between two users is open.
     * @param user Username used as key.
     * @return true if open, false if closed.
     */
    public boolean checkOpenChatFrame(String user){
        boolean chatOpen = false;
        if(activeChats.containsKey(user)){
            chatOpen = true;
        }
        return chatOpen;
    }
}
