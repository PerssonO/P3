package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class ContactsFrame extends JFrame {

    private JPanel contactsPanel;
    private MainFrame mainFrame;

    public ContactsFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setSize(300,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel(mainFrame);
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
    }
}
