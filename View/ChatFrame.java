package View;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame {

    private ChatMainPanel chatMainPanel;
    public ChatFrame(){
        setResizable(false);
        setSize(500,500);
        setVisible(true);
        chatMainPanel = new ChatMainPanel();
        setContentPane(chatMainPanel);
    }
}
