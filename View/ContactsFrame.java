package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class ContactsFrame extends JFrame {

    JPanel contactsPanel;

    public ContactsFrame(MessageSender sender, MessageReceiver receiver){
        setSize(300,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel(sender, receiver);
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
    }
}
