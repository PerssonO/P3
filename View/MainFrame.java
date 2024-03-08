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
        ChatHandler chat = activeChats.get(sender);
        chat.displayMessage(message.getText(), message.getImage());

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
        activeChats.remove(identifier);
    }

    public void setContactsFrame(ContactsFrame contactsFrame) {
        this.contactsFrame = contactsFrame;
    }

    public ImageIcon getUserImage(){
        return starter.getUser().getImage();
    }

    public void sendUpdatedContactList(ArrayList<User> newContactList) {
        contactController.sendUpdatedContactList(newContactList);
    }
}
