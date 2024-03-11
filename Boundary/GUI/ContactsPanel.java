package Boundary.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * JPanel containing GUI elements for the main window
 */
public class ContactsPanel extends JPanel implements ActionListener, MouseListener, FocusListener {

    private JList contactsList;
    private JList allUsersList;
    private JLabel latestLogin;
    private JButton addUserToContactsJButton;
    private JButton removeContactJButton;
    private JButton startChatJButton;
    private JButton groupMessageJButton;
    private BoundaryHandler boundaryHandler;
    private int choice;


    private ArrayList<String> contacts = new ArrayList();
    private ArrayList<String> allUsers = new ArrayList<>();

    /**
     * Constructor for class, creates GUI elements
     * @param boundaryHandler reference to BoundaryHanldler
     */
    public ContactsPanel(BoundaryHandler boundaryHandler){

        this.boundaryHandler = boundaryHandler;
        setBackground(new Color(207, 219, 239));
        setLayout(null);
        JLabel contactLabel = new JLabel("Contacts:");
        add(contactLabel);
        contactLabel.setBounds(5,5,100,13);
        contactsList = new JList();
        contactsList.setBounds(5,20,275,150);
        JScrollPane contactListScrollPane = new JScrollPane(contactsList);
        contactListScrollPane.setBounds(5,20,275,150);
        add(contactListScrollPane);
        addUserToContactsJButton = new JButton("Add User");
        addUserToContactsJButton.setBounds(5,370,90,30);
        add(addUserToContactsJButton);
        addUserToContactsJButton.addActionListener(this);
        removeContactJButton = new JButton("Remove");
        removeContactJButton.setBounds(97,370,90,30);
        add(removeContactJButton);
        removeContactJButton.addActionListener(this);
        startChatJButton = new JButton("Chat");
        startChatJButton.setBounds(190,370,90,30);
        add(startChatJButton);
        startChatJButton.addActionListener(this);
        contactsList.addMouseListener(this);
        contactsList.setListData(contacts.toArray());
        allUsersList = new JList();
        allUsersList.setBounds(5,190,275,150);
        JScrollPane userListScrollPane = new JScrollPane(allUsersList);
        add(userListScrollPane);
        userListScrollPane.setBounds(5,190,275,150);
        allUsersList.addMouseListener(this);
        JLabel allUsersLabel = new JLabel("Online:");
        add(allUsersLabel);
        allUsersLabel.setBounds(5,175,100,13);
        latestLogin = new JLabel("Latest login: ");
        add(latestLogin);
        latestLogin.setBounds(5,345,275,15);
        groupMessageJButton = new JButton("Group message");
        add(groupMessageJButton);
        groupMessageJButton.setBounds(75,405,130,30);
        groupMessageJButton.addActionListener(this);
        contactsList.addFocusListener(this);
        contactsList.clearSelection();
        allUsersList.addFocusListener(this);
        allUsersList.clearSelection();
    }

    /**
     * Method for replacing the old list of contacts with an updated one.
     * Calls for invokeLater to update the listdata
     * @param newContactList
     */
    public void populateContactList(ArrayList<String> newContactList) {
        contacts.clear();
        contacts.addAll(newContactList);
        SwingUtilities.invokeLater(() -> contactsList.setListData(contacts.toArray()));
    }

    /**
     * Method for replacing the old list of online users with an updated one.
     * Calls for invokeLater to update the listData
     * @param newUserList
     */
    public void populateAllUserList(ArrayList<String> newUserList) {
        allUsers.clear();
        allUsers.addAll(newUserList);
        SwingUtilities.invokeLater(() -> allUsersList.setListData(allUsers.toArray()));
    }

    /**
     * Method for displaying the name of last user to connect to server
     * @param user name of the latest user
     */
    public void setLatestLogin(String user) {
        SwingUtilities.invokeLater(() -> latestLogin.setText(user));
    }

    /**
     * Getter for list of contacts
     * @return String arrayList containing names of the contacts
     */
    public ArrayList<String> getContacts() {
        return contacts;
    }

    /**
     * ActionListener interface implementation, controls events triggered by pressing buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addUserToContactsJButton && allUsersList.getSelectedIndex() != -1){
            String userToAdd = allUsers.get(allUsersList.getSelectedIndex());
            if (!contacts.contains(userToAdd) && !userToAdd.equals(boundaryHandler.getUser())) {
                contacts.add(userToAdd);
                System.out.println(contacts);
                contactsList.setListData(contacts.toArray());
            }
        }
        else if(e.getSource() == removeContactJButton && contactsList.getSelectedIndex() != -1){
            contacts.remove(contactsList.getSelectedIndex());
            contactsList.setListData(contacts.toArray());
            System.out.println(contacts);
        }
        else if(e.getSource() == startChatJButton){
            if (choice == 1 && contactsList.getSelectedIndex() != -1) {
                System.out.println("1");
                String recipient = contacts.get(contactsList.getSelectedIndex());
                openChatWindow(recipient);
            }
            if (choice == 2 && allUsersList.getSelectedIndex() != -1) {
                System.out.println("2");
                String recipient = allUsers.get(allUsersList.getSelectedIndex());
                openChatWindow(recipient);
            }
        }
        else if(e.getSource() == groupMessageJButton){
            GroupMessageFrame groupMessageFrame = new GroupMessageFrame(contacts,allUsers, boundaryHandler);
        }
    }

    /**
     * MouseListener interface implementation, events triggered by double-clicking certain objects
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()== 2){
            if (choice == 1 && contactsList.getSelectedIndex() != -1){
                String recipient = contacts.get(contactsList.getSelectedIndex());
                openChatWindow(recipient);
            }
            else if(choice == 2){
                String recipient = allUsers.get(allUsersList.getSelectedIndex());
                openChatWindow(recipient);
            }
        }
    }

    /**
     * Method for opening a new chatframe, calls for boundaryHandler to check if one is already open.
     * @param user name of the recipient
     */
    public void openChatWindow(String user){
        if(!boundaryHandler.checkOpenChatFrame(user)){
            boundaryHandler.openNewChat(user, new ChatFrame(boundaryHandler, user, boundaryHandler.getRecipientImage(user), boundaryHandler.getUserImage()));
        }
    }

    /**
     * MouseListener interface(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * MouseListener interface(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * MouseListener interface(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * MouseListener interface(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * FocusListener interface implementation, controls focus and changes the choice
     * variable depending on which frame was clicked
     * @param e the event to be processed
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == contactsList) {
            choice = 1;
            allUsersList.clearSelection();
        }
        if (e.getSource() == allUsersList) {
            choice = 2;
            contactsList.clearSelection();
        }
    }

    /**
     * FocusListener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void focusLost(FocusEvent e) {

    }
}
