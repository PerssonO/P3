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
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame implements Communicator {
    private ClientStarter starter;
    private MessageController messageController;
    private UpdateController updateController;
    private ContactController contactController;
    private HashMap<String, ChatHandler> activeChats = new HashMap<>();
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
        ArrayList<User> recipients = message.getRecipients();
        for (User recipient : recipients) {
            ChatHandler chat = activeChats.get(recipient.toString());
            chat.displayMessage(message.getText(), message.getImage());
        }
    }

    @Override
    public void transferUpdate(Update update) {
        contactsFrame.setContactList(update.listToString());
    }

    @Override
    public void transferContactUpdateObject(ContactUpdateObject contactUpdateObject) {
        //TODO
        // Uppdatera profilbild
        //Uppdatera kontaktlista
    }

    public void newLogIn(String userName, ImageIcon userImage) {
        starter.createUser(userName, userImage);
    }

    public void sendMessage(String text, ImageIcon image, User...recipients) {
        messageController.sendMessage(text, image, recipients);
    }

    public void openNewChat(String user, ChatFrame chatFrame) {
        activeChats.put(user, new ChatHandler(chatFrame));
    }

    public void removeChatHandler(String identifier) {
        activeChats.remove(identifier);
    }

    public void setContactsFrame(ContactsFrame contactsFrame) {
        this.contactsFrame = contactsFrame;
    }
}
