package View;

import Client.InputHandler;
import Client.OutputHandler;

import javax.swing.*;
import java.util.ArrayList;

public class ContactsFrame extends JFrame {

    JPanel contactsPanel;

    public ContactsFrame(MainFrame mainFrame){
        setSize(300,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel(mainFrame);
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
    }

    public void setContactList(ArrayList<String> contacts) {
        ((ContactsPanel)contactsPanel).setContactList(contacts);
    }

}
