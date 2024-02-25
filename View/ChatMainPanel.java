package View;

import javax.swing.*;
import java.awt.*;

public class ChatMainPanel extends JPanel {

    private ChatSouthPanel chatSouthPanel;
    private ChatNorthPanel chatNorthPanel;


    public ChatMainPanel(){
        setLayout(null);
        chatSouthPanel = new ChatSouthPanel();
        add(chatSouthPanel);
        chatNorthPanel = new ChatNorthPanel();
        add(chatNorthPanel);


    }
}
