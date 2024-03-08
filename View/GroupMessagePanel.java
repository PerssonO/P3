package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GroupMessagePanel extends JPanel implements ActionListener, KeyListener {

    private JTextArea messageDraft;
    private JButton send;
    private JButton attach;
    private MainFrame mainFrame;
    private JList recipients;
    private JButton addRecipientButton;
    private JButton removeRecipientButton;
    private ArrayList<User> recieverList;
    //    private ArrayList<User> availableRecipientList;
    private ArrayList<User> availableRecipientList;
    private ArrayList<String> userListTEST;
    private JList userList;
    private JLabel userImage = new JLabel();
    private ImageIcon messageImage;

    //    public GroupMessagePanel(ArrayList<String> allUsers, ArrayList<String> contacts, MainFrame mainFrame){
    public GroupMessagePanel(ArrayList<User> allUsers, ArrayList<User> contacts, MainFrame mainFrame){
        messageImage = null;
        availableRecipientList = new ArrayList<>();
        mergeLists(allUsers,contacts);
        recieverList = new ArrayList<>();
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        add(userImage);
        setUserImage();
        userImage.setBounds(380,270,100,100);
        messageDraft = new JTextArea();
        messageDraft.setBackground(Color.WHITE);
        messageDraft.setBounds(5,270,370,140);
        add(messageDraft);
        send = new JButton("Send");
        send.setBounds(380,370,100,20);
        add(send);
        attach = new JButton("Attach");
        attach.setBounds(380,390,100,20);
        add(attach);
        messageDraft.addKeyListener(this);
        send.addActionListener(this);
        messageDraft.setLineWrap(true);
        attach.addActionListener(this);
        recipients = new JList<>();
//        recipients.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        recipients.setVisibleRowCount(1);
        User user1 = new User("Esteban", null);
        User user2 = new User("Rodrigo", null);
        User user3 = new User("Miguel", null);
        User user4 = new User("Franco", null);
        User user5 = new User("Carlos", null);
        availableRecipientList.add(user1);
        availableRecipientList.add(user2);
        availableRecipientList.add(user3);
        availableRecipientList.add(user4);
        availableRecipientList.add(user5);
        add(recipients);
        removeRecipientButton = new JButton("Remove");
        addRecipientButton = new JButton("add");
        add(removeRecipientButton);
        add(addRecipientButton);
        removeRecipientButton.setBounds(380,220,100,22);
        addRecipientButton.setBounds(380,242,100,22);
        removeRecipientButton.addActionListener(this);
        addRecipientButton.addActionListener(this);
        JLabel recipientsLabel = new JLabel("Recipients:");
        add(recipientsLabel);
        recipientsLabel.setBounds(5,200,100,15);
        JScrollPane listScroller = new JScrollPane(recipients);
        add(listScroller);
        listScroller.setBounds(5,220,370,44);
        JLabel onlineUsersLabel = new JLabel("Available recipients:");
        onlineUsersLabel.setBounds(5,5,150,15);
        add(onlineUsersLabel);
        userList = new JList();
        JScrollPane availableRecipiendsListScrollPane = new JScrollPane(userList);
        availableRecipiendsListScrollPane.setBounds(5,20,460,180);
        add(availableRecipiendsListScrollPane);

        userListTEST = new ArrayList<>();
        userListTEST.add("Miguel");
        userListTEST.add("Carmen");
//        userList.setListData(userListTEST.toArray());
        userList.setListData(availableRecipientList.toArray());

    }

    //    public void mergeLists(ArrayList<String> contacts, ArrayList<String> onlineUsers){
//        availableRecipientList.addAll(contacts);
//        availableRecipientList.addAll(onlineUsers);
//    }
    public void mergeLists(ArrayList<User> contacts, ArrayList<User> onlineUsers){
        availableRecipientList.addAll(contacts);
        availableRecipientList.addAll(onlineUsers);
    }

    public void setUserImage(){
        userImage.setIcon(mainFrame.getUserImage());
    }

    public void setImage(String url){
        messageImage = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send){
            System.out.println("send");
            messageDraft.setText("");
            messageDraft.moveCaretPosition(0);
            sendMessage();
        }
        else if(e.getSource() == attach){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                setImage(fc.getSelectedFile().getAbsolutePath());
            }
        }

        else if(e.getSource() == addRecipientButton && userList.getSelectedIndex()!= -1){
            int index = userList.getSelectedIndex();
            recieverList.add(availableRecipientList.get(index));
            availableRecipientList.remove(index);
            recipients.setListData(recieverList.toArray());
            userList.setListData(availableRecipientList.toArray());
        }
        else if(e.getSource() == removeRecipientButton && recipients.getSelectedIndex() != -1){
            int index = recipients.getSelectedIndex();
            availableRecipientList.add(recieverList.get(index));
            recieverList.remove(index);
            recipients.setListData(recieverList.toArray());
            userList.setListData(availableRecipientList.toArray());
        }
    }

    public void sendMessage(){
        String messageText = messageDraft.getText();
        mainFrame.sendMessage(messageText, messageImage, recieverList.toArray(new User[0]));
        messageImage = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("ENTER");
            System.out.println(messageDraft.getText());
            messageDraft.setText("");
            e.consume();
            messageDraft.moveCaretPosition(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
