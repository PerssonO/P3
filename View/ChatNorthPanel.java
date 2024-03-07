package View;

import Client.MessageReceiver;
import Client.MessageSender;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatNorthPanel extends JPanel {

    private JLabel recipientImage;
    private JList<Object> receivedMessages;
    private MessageSender sender;
    private MessageReceiver receiver;
    private List<Object> testMessages = new ArrayList<>();

    public ChatNorthPanel(MessageSender sender, MessageReceiver receiver){
        this.sender = sender;
        this.receiver = receiver;
        setLayout(null);
        setBounds(0,0,500,300);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        recipientImage = new JLabel();
        recipientImage.setBounds(380,5,100,100);
        add(recipientImage);
        recipientImage.setIcon(new ImageIcon(new ImageIcon("default.jpg").getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
        receivedMessages = new JList();
        receivedMessages.setBounds(5,5,360,240);
        add(receivedMessages);
        receivedMessages.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(receivedMessages);
        scrollPane.setBounds(5,5,360,240);
        add(scrollPane);
        receivedMessages.addSelectionInterval(-1,-1);
        ImageIcon imgTest = (new ImageIcon(new ImageIcon("default.jpg").getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
        testMessages.add(imgTest);
        testMessages.add(imgTest);
        testMessages.add(imgTest);
        testMessages.add(imgTest);
        testMessages.add(imgTest);
        testMessages.add(imgTest);
        testMessages.add("text");
        receivedMessages.setListData(testMessages.toArray());
        receivedMessages.repaint();


    }
}
