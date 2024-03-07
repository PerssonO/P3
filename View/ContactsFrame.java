package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class ContactsFrame extends JFrame {

    private JPanel contactsPanel;
    private

    public ContactsFrame(MainFrame mainFrame){
        setSize(300,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel(sender, receiver);
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
    }
}
