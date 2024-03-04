package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    public GroupMessagePanel(MessageSender sender){
        recieverList = new ArrayList<>();
        this.sender = sender;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        JLabel userImage = new JLabel();
        userImage.setIcon(new ImageIcon(new ImageIcon("375px-Amerikanische_Pekingenten_2013_01b.jpg").getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        add(userImage);
        userImage.setBounds(380,70,100,100);
        messageDraft = new JTextArea();
        messageDraft.setBackground(Color.WHITE);
        messageDraft.setBounds(5,70,370,140);
        add(messageDraft);
        send = new JButton("Send");
        send.setBounds(380,170,100,20);
        add(send);
        attach = new JButton("Attach");
        attach.setBounds(380,190,100,20);
        add(attach);
        messageDraft.addKeyListener(this);
        send.addActionListener(this);
        messageDraft.setLineWrap(true);
        attach.addActionListener(this);
        recipients = new JList<>();
        recipients.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        recipients.setVisibleRowCount(1);
        add(recipients);
        removeRecipientButton = new JButton("Remove");
        addRecipientButton = new JButton("add");
        add(removeRecipientButton);
        add(addRecipientButton);
        removeRecipientButton.setBounds(380,20,100,22);
        addRecipientButton.setBounds(380,42,100,22);
        removeRecipientButton.addActionListener(this);
        addRecipientButton.addActionListener(this);
        JLabel recipientsLabel = new JLabel("Recipients:");
        add(recipientsLabel);
        recipientsLabel.setBounds(5,5,100,15);
        JScrollPane listScroller = new JScrollPane(recipients);
        add(listScroller);
        listScroller.setBounds(5,20,370,44);
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
        else if(e.getSource() == addRecipientButton){
            recieverList.add("Carlos ");
            recipients.setListData(recieverList.toArray());
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
