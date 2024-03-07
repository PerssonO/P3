package View;



import Model.User;

import javax.swing.*;
import java.util.ArrayList;

public class ContactsFrame extends JFrame {

    private ContactsPanel contactsPanel;
    private MainFrame mainFrame;

    public ContactsFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setSize(300,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel(mainFrame);
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
    }

    //    public void setContactList(ArrayList<String> newContactList) {
//        ((ContactsPanel) contactsPanel).populateContactList(newContactList);
//    }
    public void setContactList(ArrayList<User> newContactList) {
        contactsPanel.populateContactList(newContactList);
    }

    public void setAllUsersList(ArrayList<User> newUserList) {
        contactsPanel.populateAllUserList(newUserList);
    }

    public void setLatestLogin(String user) {
        contactsPanel.setLatestLogin(user);
    }

    /*public void setImage(ImageIcon image) {
        ((ContactsPanel) contactsPanel).
    }*/
}
