package View;

import Model.User;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatNorthPanel extends JPanel {

    private JLabel recipientImage;
    private JList<Object> receivedMessages;
    private MainFrame mainFrame;
    private List<Object> testMessages = new ArrayList<>();

    public ChatNorthPanel(MainFrame mainFrame, User recipient){
        this.mainFrame = mainFrame;
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
    public void populateReceivedMessages(String text, ImageIcon image){
        testMessages.add(text);
        testMessages.add(image);
        receivedMessages.setListData(testMessages.toArray());
    }
}
