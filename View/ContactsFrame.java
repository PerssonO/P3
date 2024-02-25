package View;

import javax.swing.*;

public class ContactsFrame extends JFrame {

    JPanel contactsPanel;

    public ContactsFrame(){
        setSize(300,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel();
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
    }
}
