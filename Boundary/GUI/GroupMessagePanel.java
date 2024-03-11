package Boundary.GUI;

import Boundary.GUI.BoundaryHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * JPanel containing gui elements used for sending group messages
 */
public class GroupMessagePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    private JTextArea messageDraftTextArea;
    private JButton sendButton;
    private JButton attachButton;
    private BoundaryHandler boundaryHandler;
    private JList recipientsJList;
    private JButton addRecipientButton;
    private JButton removeRecipientButton;
    private ArrayList<String> recieverList;
    private ArrayList<String> availableRecipientList;
    private JList userJList;
    private JLabel userImage = new JLabel();
    private ImageIcon messageImage;
    private JLabel attachedFileLabel;
    private JButton clearAttachedFileButton;

    /**
     * Constructor, creates GUI components to be used
     * @param allUsers list of online users
     * @param contacts list of user contacts
     * @param boundaryHandler reference to BoundaryHandler
     */
    public GroupMessagePanel(ArrayList<String> allUsers, ArrayList<String> contacts, BoundaryHandler boundaryHandler){
        messageImage = null;
        availableRecipientList = new ArrayList<>();
        mergeLists(allUsers,contacts);
        recieverList = new ArrayList<>();
        this.boundaryHandler = boundaryHandler;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        add(userImage);
        setUserImage();
        userImage.setBounds(380,270,100,100);
        messageDraftTextArea = new JTextArea();
        messageDraftTextArea.setBackground(Color.WHITE);
        messageDraftTextArea.setBounds(5,285,370,125);
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
        attachedFileLabel.setBounds(5,265,320,15);
        add(attachedFileLabel);
        clearAttachedFileButton = new JButton("Clear");
        add(clearAttachedFileButton);
        clearAttachedFileButton.setBounds(305,265,70,15);
        clearAttachedFileButton.addActionListener(this);
    }

    /**
     * Method for merging the two lists of users to one
     * @param contacts list of contacts
     * @param onlineUsers every user online
     */
    public void mergeLists(ArrayList<String> contacts, ArrayList<String> onlineUsers){
        availableRecipientList.addAll(contacts);
        for(String user : onlineUsers){
            if (!availableRecipientList.contains(user)){
                availableRecipientList.add(user);
            }
        }
    }

    /**
     * Displays the profile picture of user
     */
    public void setUserImage(){
        userImage.setIcon(new ImageIcon(boundaryHandler.getUserImage().getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
    }

    /**
     * Sets image to be attached and updates gui with name of attached file
     * @param url filepath
     * @param fileName name of the file
     */
    public void setImage(String url, String fileName){
        String substring = url.substring(url.length() - 4).toLowerCase();
        if(substring.equals(".jpg") || substring.equals(".png")){
            messageImage = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING));
            attachedFileLabel.setText(fileName);
        }
    }

    /**
     * ActionListener implementation, sets behavior of buttons
     * @param e the event to be processed
     */
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
                setImage(fc.getSelectedFile().getAbsolutePath(), fc.getSelectedFile().getName());
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

        else if(e.getSource() == clearAttachedFileButton){
            messageImage = null;
            attachedFileLabel.setText("Attached: ");
        }
    }

    /**
     * Method grabbing text from draft, attached image, list of recipients
     * and calls for boundaryHandler to send it
     */
    public void sendMessage(){
        if(!recieverList.isEmpty()){
        String messageText = messageDraftTextArea.getText();
        boundaryHandler.sendMessage(messageText, messageImage, recieverList.toArray(new String[0]));
        messageImage = null;
        attachedFileLabel.setText("Attached: ");
        }
    }

    /**
     * Keylistener method (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * KeyListener method, sends message when user presses enter-key
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage();
            messageDraftTextArea.setText("");
            e.consume();
            messageDraftTextArea.moveCaretPosition(0);
        }
    }

    /**
     * Keylistener method (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * MouseListener method, controls behaviour when double-clicks occur
     * @param e the event to be processed
     */
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

    /**
     * MouseListener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * MouseListener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * MouseListener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * MouseListener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
