package View;

import Model.User;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatNorthPanel extends JPanel {

    private JLabel recipientImage;
    private JLabel recipientName;
    private JList<Object> receivedMessagesJlist;
    private MainFrame mainFrame;
    private List<Object> messages = new ArrayList<>();

    public ChatNorthPanel(MainFrame mainFrame, User recipient){
        this.mainFrame = mainFrame;
        setLayout(null);
        setBounds(0,0,500,300);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        recipientImage = new JLabel();
        recipientImage.setBounds(380,5,100,100);
        add(recipientImage);
        recipientName = new JLabel();
        recipientName.setBounds(380,110,100,20);
        recipientName.setText(recipient.getUserName());
        add(recipientName);
        recipientImage.setIcon(new ImageIcon(recipient.getImage().getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
        receivedMessagesJlist = new JList();
        receivedMessagesJlist.setBounds(5,5,360,240);
        add(receivedMessagesJlist);
        receivedMessagesJlist.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(receivedMessagesJlist);
        scrollPane.setBounds(5,5,360,240);
        add(scrollPane);
        receivedMessagesJlist.addSelectionInterval(-1,-1);
        receivedMessagesJlist.setListData(messages.toArray());
        receivedMessagesJlist.repaint();
    }
    public void populateReceivedMessages(String text, ImageIcon image){
        messages.add(text);
        messages.add(image);
        SwingUtilities.invokeLater(() -> receivedMessagesJlist.setListData(messages.toArray()));
        revalidate();
        repaint();
    }
}
