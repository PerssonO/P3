package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ContactsPanel extends JPanel implements ActionListener, MouseListener {

    private JList contactsList;
    private JList allUsersList;
    private JButton addUserToContacts;
    private JButton removeContact;
    private JButton startChat;
    private JButton groupMessage;
    private MainFrame mainFrame;


    private ArrayList<String> contacts = new ArrayList();

    public ContactsPanel(MainFrame){
        this.sender = sender;
        this.receiver = receiver;
        contacts.add("Carlos");
        contacts.add("Steven");
        contacts.add("Esteban");
        setBackground(new Color(207, 219, 239));
        setLayout(null);
        JLabel contactLabel = new JLabel("Contacts:");
        add(contactLabel);
        contactLabel.setBounds(5,5,100,13);
        contactsList = new JList();
        contactsList.setBounds(5,20,275,150);
        add(contactsList);
        addUserToContacts = new JButton("Add User");
        addUserToContacts.setBounds(5,370,90,30);
        add(addUserToContacts);
        addUserToContacts.addActionListener(this);
        removeContact = new JButton("Remove");
        removeContact.setBounds(97,370,90,30);
        add(removeContact);
        removeContact.addActionListener(this);
        startChat = new JButton("Chat");
        startChat.setBounds(190,370,90,30);
        add(startChat);
        startChat.addActionListener(this);
        contactsList.addMouseListener(this);
        contactsList.setListData(contacts.toArray());
        allUsersList = new JList();
        allUsersList.setBounds(5,190,275,150);
        add(allUsersList);
        JLabel allUsersLabel = new JLabel("Online:");
        add(allUsersLabel);
        allUsersLabel.setBounds(5,175,100,13);
        JLabel latestLogin = new JLabel("Latest login: ");
        add(latestLogin);
        latestLogin.setBounds(5,345,275,15);
        groupMessage = new JButton("Group message");
        add(groupMessage);
        groupMessage.setBounds(75,405,130,30);
        groupMessage.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addUserToContacts){
        }
        else if(e.getSource() == removeContact){
            contacts.remove(contactsList.getSelectedIndex());
            contactsList.setListData(contacts.toArray());
        }
        else if(e.getSource() == startChat){
            ChatFrame chatFrame = new ChatFrame(sender, receiver);
        }
        else if(e.getSource() == groupMessage){
            GroupMessageFrame groupMessageFrame = new GroupMessageFrame(sender);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()== 2){
            System.out.println(contacts.get(contactsList.getSelectedIndex()));
            ChatFrame chatFrame = new ChatFrame(sender, receiver);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
