package View;

import Client.MessageReceiver;
import Client.MessageSender;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GroupMessagePanel extends JPanel implements ActionListener, KeyListener {

    private JTextArea messageDraft;
    private JButton send;
    private JButton attach;
    private MessageSender sender;
    private MessageReceiver receiver;
    private JList recipients;
    private JButton addRecipientButton;
    private JButton removeRecipientButton;
    private ArrayList<String> recieverList;
    private ArrayList<User> availableRecipientList;
    private ArrayList<String> userListTEST;
    private JList userList;

//    public GroupMessagePanel(MessageSender sender, ArrayList<User> contacts, ArrayList<User> onlineUsers){
    public GroupMessagePanel(MessageSender sender){
//        mergeLists(contacts, onlineUsers);
        recieverList = new ArrayList<>();
        this.sender = sender;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        JLabel userImage = new JLabel();
        userImage.setIcon(new ImageIcon(new ImageIcon("375px-Amerikanische_Pekingenten_2013_01b.jpg").getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        add(userImage);
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
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userListTEST.add("Carmen");
        userList.setListData(userListTEST.toArray());
//        userList.setListData(availableRecipientList.toArray());

    }

    public void mergeLists(ArrayList<User> contacts, ArrayList<User> onlineUsers){
        availableRecipientList.addAll(contacts);
        availableRecipientList.addAll(onlineUsers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send){
            System.out.println("send");
            // Beteende för att skicka meddelanden här
        }
        else if(e.getSource() == attach){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
        }

        else if(e.getSource() == addRecipientButton && userList.getSelectedIndex()!= -1){
            int index = userList.getSelectedIndex();
            recieverList.add(userListTEST.get(index));
            userListTEST.remove(index);
            recipients.setListData(recieverList.toArray());
            userList.setListData(userListTEST.toArray());
        }
        else if(e.getSource() == removeRecipientButton && recipients.getSelectedIndex() != -1){
            int index = recipients.getSelectedIndex();
            userListTEST.add(recieverList.get(index));
            recieverList.remove(index);
            recipients.setListData(recieverList.toArray());
            userList.setListData(userListTEST.toArray());
        }
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
