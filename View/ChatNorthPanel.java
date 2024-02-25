package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChatNorthPanel extends JPanel {

    private JLabel recipientImage;
    private JList receivedMessages;

    public ChatNorthPanel(){
        setLayout(null);
        setBounds(0,0,500,300);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        recipientImage = new JLabel();
        recipientImage.setBounds(380,5,100,100);
        add(recipientImage);
        recipientImage.setIcon(new ImageIcon(new ImageIcon("images/default.jpg").getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
        receivedMessages = new JList();
        receivedMessages.setBounds(5,5,370,240);
        add(receivedMessages);
        receivedMessages.setBackground(Color.white);

    }
}
