package View;



import Model.User;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class ContactsFrame extends JFrame implements WindowListener {

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
        addWindowListener(this);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println(contactsPanel.getContacts());
        mainFrame.sendUpdatedContactList(contactsPanel.getContacts());
        System.out.println("Sending updated contactlist");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println(contactsPanel.getContacts());
        mainFrame.sendUpdatedContactList(contactsPanel.getContacts());
        System.out.println("Sending updated contactlist");
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    /*public void setImage(ImageIcon image) {
        ((ContactsPanel) contactsPanel).
    }*/
}
