package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import Model.User;

public class ContactsPanel extends JPanel implements ActionListener, MouseListener {

    private JList contactsList;
    private JList allUsersList;
    private JLabel latestLogin;
    private JButton addUserToContacts;
    private JButton removeContact;
    private JButton startChat;
    private JButton groupMessage;
    private MainFrame mainFrame;


    //    private ArrayList<String> contacts = new ArrayList();
    private ArrayList<User> contacts = new ArrayList();
    private ArrayList<User> allUsers = new ArrayList<>();

    public ContactsPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
//        contacts.add("Carlos");
//        contacts.add("Steven");
//        contacts.add("Esteban");
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
        latestLogin = new JLabel("Latest login: ");
        add(latestLogin);
        latestLogin.setBounds(5,345,275,15);
        groupMessage = new JButton("Group message");
        add(groupMessage);
        groupMessage.setBounds(75,405,130,30);
        groupMessage.addActionListener(this);



    }

    //    public void populateContactList(ArrayList<String> newContactList) {
//        contacts.clear();
//        contacts.addAll(newContactList);
//        contactsList.setListData(contacts.toArray());
//    }
    public void populateContactList(ArrayList<User> newContactList) {
        contacts.clear();
        contacts.addAll(newContactList);
        SwingUtilities.invokeLater(() -> contactsList.setListData(contacts.toArray()));
    }

    public void populateAllUserList(ArrayList<User> newUserList) {
        allUsers.clear();
        allUsers.addAll(newUserList);
        SwingUtilities.invokeLater(() -> allUsersList.setListData(allUsers.toArray()));
    }

    public void setLatestLogin(String user) {
        SwingUtilities.invokeLater(() -> latestLogin.setText(user));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addUserToContacts){
            User userToAdd = allUsers.get(allUsersList.getSelectedIndex());
            if (!contacts.contains(userToAdd)) {
                contacts.add(userToAdd);
                mainFrame.sendUpdatedContactList(contacts);
                contactsList.setListData(contacts.toArray());
            }
        }
        else if(e.getSource() == removeContact){
            contacts.remove(contactsList.getSelectedIndex());
            contactsList.setListData(contacts.toArray());
        }
        else if(e.getSource() == startChat){
//            String recipient = contacts.get(contactsList.getSelectedIndex());
            User recipient = allUsers.get(allUsersList.getSelectedIndex());
            mainFrame.openNewChat(recipient, new ChatFrame(mainFrame, recipient));
        }
        else if(e.getSource() == groupMessage){
            GroupMessageFrame groupMessageFrame = new GroupMessageFrame(contacts,allUsers,mainFrame);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()== 2){
            System.out.println(contacts.get(contactsList.getSelectedIndex()));
//            String recipient = contacts.get(contactsList.getSelectedIndex());
            User recipient = contacts.get(contactsList.getSelectedIndex());
            mainFrame.openNewChat(recipient, new ChatFrame(mainFrame, recipient));
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
