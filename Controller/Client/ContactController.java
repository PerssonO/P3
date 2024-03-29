package Controller.Client;

import Entity.ContactUpdateObject;
import Entity.User;
import Entity.ContactUpdateDisplayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Controller class handling the lists of contacts.
 */
public class ContactController {
    private ClientStarter starter;
    private OutputHandler outputHandler;
    private ContactUpdateDisplayer displayer;

    /**
     * Constructor for the class.
     * @param starter ClientStarter-object.
     */
    public ContactController(ClientStarter starter) {
        this.starter = starter;
    }

    /**
     * Unpacks a ContactUpdateObject and sends the relevant data to a boundary-class.
     * @param contactUpdateObject Object containing contactlist.
     */
    public void updateUserInfo(ContactUpdateObject contactUpdateObject) {
        ArrayList<User> contactList = contactUpdateObject.getUserArrayList();
        for (User user : contactList) {
            HashMap<String, ImageIcon> userAndImage = displayer.getUserImagePair();
            userAndImage.put(user.toString(), user.getImage());
        }
        displayer.displayContactUpdate(contactUpdateObject.listToString());
    }

    /**
     * Gets information from a new boundary-class and creates a UpdateContactObject which is then sent to the outputstream.
     * @param newContactList ArrayList of Strings.
     */
    public void sendUpdatedContactList(ArrayList<String> newContactList) {
        ArrayList<User> contactList = new ArrayList<>();
        for (String user : newContactList) {
            HashMap<String, ImageIcon> userImagePair = displayer.getUserImagePair();
            contactList.add(new User(user, userImagePair.get(user)));
        }
        outputHandler.sendContactUpdate(new ContactUpdateObject(starter.getUser(), contactList));
    }

    /**
     * Sets the outputhandler.
     * @param outputHandler OutputHandler-object.
     */
    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    /**
     * Sets an implementation of ContactUpdateDisplayer.
     * @param displayer Implementation of ContactUpdateDisplayer.
     */
    public void setContactUpdateDisplayer(ContactUpdateDisplayer displayer) {
        this.displayer = displayer;
    }
}
