package View;

import Client.ClientStarter;
import Client.ContactController;
import Client.MessageController;
import Client.UpdateController;
import Model.ContactUpdateObject;
import Model.Message;
import Model.Update;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainFrame implements Communicator {
    private ClientStarter starter;
    private MessageController messageController;
    private UpdateController updateController;
    private ContactController contactController;
    private HashMap<User, ChatHandler> activeChats = new HashMap<>();
    private ContactsFrame contactsFrame;

    public MainFrame(ClientStarter starter, MessageController messageController, UpdateController updateController, ContactController contactController) {
        new LoginFrame(this);
        this.starter = starter;
        this.messageController = messageController;
        this.updateController = updateController;
        this.contactController = contactController;
    }

    @Override
    public void transferMessage(Message message) {
        User sender = message.getSender();
        if (!activeChats.containsKey(sender)) {
            openNewChat(sender, new ChatFrame(this, sender));
        }
        String messageString = String.format("<html> <br></span> %s %s <br> %s </span><br></span></html>", message.getSender(), message.getTimeDelivered().toString().substring(0,17), message.getText());
        ChatHandler chat = activeChats.get(sender);
        chat.displayMessage(messageString, message.getImage());
    }

    @Override
    public void transferUpdate(Update update) {
        if (update.getUser() != null){
        contactsFrame.setLatestLogin(update.getUser().toString());
        }
        contactsFrame.setAllUsersList(update.getUsers());
    }

    @Override
    public void transferContactUpdateObject(ContactUpdateObject contactUpdateObject) {
//        contactsFrame.setContactList(contactUpdateObject.listToString()); STRÄNG
        contactsFrame.setContactList(contactUpdateObject.getUserArrayList());
    }

    public void newLogIn(String userName, ImageIcon userImage) {
        starter.createUser(userName, userImage);
    }

    public void sendMessage(String text, ImageIcon image, User...recipients) {
        messageController.sendMessage(text, image, recipients);
    }

    public void openNewChat(User user, ChatFrame chatFrame) {
        activeChats.put(user, new ChatHandler(chatFrame));
    }

    //    public void removeChatHandler(String identifier) {
//        activeChats.remove(identifier);
//    }
    public void removeChatHandler(User identifier) {
        System.out.println("Inne i removeChatHandler");
        System.out.println(activeChats.remove(identifier));
    }

    public void setContactsFrame(ContactsFrame contactsFrame) {
        this.contactsFrame = contactsFrame;
    }

    public ImageIcon getUserImage(){
        return starter.getUser().getImage();
    }

    public User getUser(){
        return starter.getUser();
    }

    public void sendUpdatedContactList(ArrayList<User> newContactList) {
        contactController.sendUpdatedContactList(newContactList);
    }

    public void displayOwnMessage(String text, ImageIcon image, User sender) {
        String messageString = String.format("<html> <br></span> %s %s <br> %s </span><br></span></html>", starter.getUser().toString(), new Date().toString().substring(0,17), text);
        ChatHandler chat = activeChats.get(sender);
        chat.displayMessage(messageString, image);
    }
}
