package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GroupMessagePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    private JTextArea messageDraftTextArea;
    private JButton sendButton;
    private JButton attachButton;
    private MainFrame mainFrame;
    private JList recipientsJList;
    private JButton addRecipientButton;
    private JButton removeRecipientButton;
    private ArrayList<User> recieverList;
    private ArrayList<User> availableRecipientList;
    private JList userJList;
    private JLabel userImage = new JLabel();
    private ImageIcon messageImage;
    private JLabel attachedFileLabel;

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
        messageDraftTextArea = new JTextArea();
        messageDraftTextArea.setBackground(Color.WHITE);
        messageDraftTextArea.setBounds(5,280,370,140);
        add(messageDraftTextArea);
        sendButton = new JButton("Send");
        sendButton.setBounds(380,370,100,20);
        add(sendButton);
        attachButton = new JButton("Attach");
        attachButton.setBounds(380,390,100,20);
        add(attachButton);
        messageDraftTextArea.addKeyListener(this);
        sendButton.addActionListener(this);
        messageDraftTextArea.setLineWrap(true);
        attachButton.addActionListener(this);
        recipientsJList = new JList<>();
        add(recipientsJList);
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
        JScrollPane listScroller = new JScrollPane(recipientsJList);
        add(listScroller);
        listScroller.setBounds(5,220,370,44);
        JLabel onlineUsersLabel = new JLabel("Available recipients:");
        onlineUsersLabel.setBounds(5,5,150,15);
        add(onlineUsersLabel);
        userJList = new JList();
        JScrollPane availableRecipientsListScrollPane = new JScrollPane(userJList);
        availableRecipientsListScrollPane.setBounds(5,20,460,180);
        add(availableRecipientsListScrollPane);
        userJList.setListData(availableRecipientList.toArray());
        userJList.addMouseListener(this);
        recipientsJList.addMouseListener(this);
        attachedFileLabel = new JLabel();
        attachedFileLabel.setText("Attached: ");
        attachedFileLabel.setBounds(5,265,370,15);
        add(attachedFileLabel);
    }

    public void mergeLists(ArrayList<User> contacts, ArrayList<User> onlineUsers){
        availableRecipientList.addAll(contacts);
        for(User user : onlineUsers){
            if (!availableRecipientList.contains(user)){
                availableRecipientList.add(user);
            }
        }
    }

    public void setUserImage(){
        userImage.setIcon(new ImageIcon(mainFrame.getUserImage().getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
    }

    public void setImage(String url){
        messageImage = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendButton){
            sendMessage();
            messageDraftTextArea.setText("");
            messageDraftTextArea.moveCaretPosition(0);
        }
        else if(e.getSource() == attachButton){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                setImage(fc.getSelectedFile().getAbsolutePath());
                attachedFileLabel.setText("Attached: "+fc.getSelectedFile().getAbsolutePath().substring(0,50));
            }
        }

        else if(e.getSource() == addRecipientButton && userJList.getSelectedIndex()!= -1){
            int index = userJList.getSelectedIndex();
            recieverList.add(availableRecipientList.get(index));
            availableRecipientList.remove(index);
            recipientsJList.setListData(recieverList.toArray());
            userJList.setListData(availableRecipientList.toArray());
        }
        else if(e.getSource() == removeRecipientButton && recipientsJList.getSelectedIndex() != -1){
            int index = recipientsJList.getSelectedIndex();
            availableRecipientList.add(recieverList.get(index));
            recieverList.remove(index);
            recipientsJList.setListData(recieverList.toArray());
            userJList.setListData(availableRecipientList.toArray());
        }
    }

    public void sendMessage(){
        String messageText = messageDraftTextArea.getText();
        mainFrame.sendMessage(messageText, messageImage, recieverList.toArray(new User[0]));
        messageImage = null;
        attachedFileLabel.setText("Attached image: ");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage();
            messageDraftTextArea.setText("");
            e.consume();
            messageDraftTextArea.moveCaretPosition(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            if (e.getSource() == recipientsJList) {
                int index = recipientsJList.getSelectedIndex();
                availableRecipientList.add(recieverList.get(index));
                recieverList.remove(index);
                recipientsJList.setListData(recieverList.toArray());
                userJList.setListData(availableRecipientList.toArray());
            }
            else if(e.getSource() == userJList){
                int index = userJList.getSelectedIndex();
                recieverList.add(availableRecipientList.get(index));
                availableRecipientList.remove(index);
                recipientsJList.setListData(recieverList.toArray());
                userJList.setListData(availableRecipientList.toArray());

            }
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
