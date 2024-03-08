package View;

import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatSouthPanel extends JPanel implements ActionListener, KeyListener {

    private JTextArea messageDraft;
    private JButton send;
    private JButton attach;
    private MainFrame mainFrame;
    private ImageIcon messageImage;
    private JLabel userImage = new JLabel();
    private User sender;


    public ChatSouthPanel(MainFrame mainFrame, User sender){
        this.mainFrame = mainFrame;
        this.sender = sender;
        setLayout(null);
        setBounds(0,300,500,250);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
//        userImage.setIcon(new ImageIcon(new ImageIcon("375px-Amerikanische_Pekingenten_2013_01b.jpg").getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        add(userImage);
        userImage.setIcon(new ImageIcon(mainFrame.getUserImage().getImage().getScaledInstance(100,100, Image.SCALE_REPLICATE)));
        userImage.setBounds(380,10,100,100);
        setUserImage();
        messageDraft = new JTextArea();
        messageDraft.setBackground(Color.WHITE);
        messageDraft.setBounds(5,10,370,140);
        add(messageDraft);
        send = new JButton("Send");
        send.setBounds(380,110,100,20);
        add(send);
        attach = new JButton("Attach");
        attach.setBounds(380,130,100,20);
        add(attach);
        messageDraft.addKeyListener(this);
        send.addActionListener(this);
        messageDraft.setLineWrap(true);
        attach.addActionListener(this);
    }

    public void setImage(String url){
        messageImage = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING));
    }

    public void setUserImage(){
        userImage.setIcon(mainFrame.getUserImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send){
            System.out.println("send");
            sendMessage();
            messageDraft.setText("");
            messageDraft.moveCaretPosition(0);
        }
        else if(e.getSource() == attach){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                setImage(fc.getSelectedFile().getAbsolutePath());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void sendMessage(){
        String messageText = messageDraft.getText();
        mainFrame.displayOwnMessage(messageText, messageImage, sender);
        mainFrame.sendMessage(messageText, messageImage, sender);
        messageImage = null;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("ENTER");
            sendMessage();
            messageDraft.setText("");
            e.consume();
            messageDraft.moveCaretPosition(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
