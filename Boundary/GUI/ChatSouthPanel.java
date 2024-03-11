package Boundary.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Panel filled with gui elements used when sending a message
 */
public class ChatSouthPanel extends JPanel implements ActionListener, KeyListener {

    private JTextArea messageDraftJTextArea;
    private JButton sendButton;
    private JButton attachButton;
    private BoundaryHandler boundaryHandler;
    private ImageIcon messageImage;
    private JLabel userImage = new JLabel();
    private String user;
    private ImageIcon mainUserImage;
    private JLabel attachedFileLabel;
    private JButton clearAttachedFileButton;

    /**
     * Constructor for class, creates gui elements
     * @param boundaryHandler refrence to boundaryHandler
     * @param user name of sender
     * @param mainUserImage profile picture of sender
     */
    public ChatSouthPanel(BoundaryHandler boundaryHandler, String user, ImageIcon mainUserImage){
        this.boundaryHandler = boundaryHandler;
        this.user = user;
        this.mainUserImage = mainUserImage;
        setLayout(null);
        setBounds(0,300,500,250);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        add(userImage);
        userImage.setBounds(380,10,100,100);
        setUserImage();
        messageDraftJTextArea = new JTextArea();
        messageDraftJTextArea.setBackground(Color.WHITE);
        messageDraftJTextArea.setBounds(5,25,370,125);
        add(messageDraftJTextArea);
        sendButton = new JButton("Send");
        sendButton.setBounds(380,110,100,20);
        add(sendButton);
        attachButton = new JButton("Attach");
        attachButton.setBounds(380,130,100,20);
        add(attachButton);
        messageDraftJTextArea.addKeyListener(this);
        sendButton.addActionListener(this);
        messageDraftJTextArea.setLineWrap(true);
        attachButton.addActionListener(this);
        attachedFileLabel = new JLabel();
        attachedFileLabel.setText("Attached: ");
        add(attachedFileLabel);
        attachedFileLabel.setBounds(5,0,270,20);
        clearAttachedFileButton = new JButton("Clear");
        add(clearAttachedFileButton);
        clearAttachedFileButton.addActionListener(this);
        clearAttachedFileButton.setBounds(305,5,70,15);
    }

    /**
     * Method for setting the imageURL for images to be attached to a message
     * @param url path of the picture
     */
    public void setImage(String url){
        String substring = url.substring(url.length() - 4).toLowerCase();
        if(substring.equals(".jpg") || substring.equals(".png")){
        messageImage = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING));
        attachedFileLabel.setText(url);
        }
    }

    /**
     * method used for setting user profile picture in gui
     */
    public void setUserImage(){
        userImage.setIcon(new ImageIcon(mainUserImage.getImage().getScaledInstance(100,100, Image.SCALE_REPLICATE)));
    }

    /**
     * ActionListener interface implementation for JButtons in the panel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendButton){
            System.out.println("send");
            sendMessage();
            messageDraftJTextArea.setText("");
            messageDraftJTextArea.moveCaretPosition(0);
        }
        else if(e.getSource() == attachButton){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                setImage(fc.getSelectedFile().getAbsolutePath());
            }
        }
        else if(e.getSource() == clearAttachedFileButton){
            messageImage = null;
            attachedFileLabel.setText("Attached: ");
        }
    }

    /**
     * KeyListener interface method (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Method grabbing text from the textarea and calls for the boundaryHandler to send it
     * and display it in the messagelist in the northern panel.
     * also resets the attached file JLabel
     */
    public void sendMessage(){
        String messageText = messageDraftJTextArea.getText();
        boundaryHandler.displayOwnMessage(messageText, messageImage, user);
        boundaryHandler.sendMessage(messageText, messageImage, user);
        messageImage = null;
        attachedFileLabel.setText("Attached: ");
    }

    /**
     * KeyListener interface method, sends message and clears textarea when enter-key is pressed
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage();
            messageDraftJTextArea.setText("");
            e.consume();
            messageDraftJTextArea.moveCaretPosition(0);
        }
    }

    /**
     * Keylistener method (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
