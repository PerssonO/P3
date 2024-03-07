package View;

import Client.InputHandler;
import Client.OutputHandler;

import javax.swing.*;

public class ChatMainPanel extends JPanel {

    private ChatSouthPanel chatSouthPanel;
    private ChatNorthPanel chatNorthPanel;


    public ChatMainPanel(MainFrame mainFrame){
        setLayout(null);
        chatSouthPanel = new ChatSouthPanel(mainFrame);
        add(chatSouthPanel);
        chatNorthPanel = new ChatNorthPanel(mainFrame);
        add(chatNorthPanel);
    }

    public void displayMessage(String text, ImageIcon image) {
        chatNorthPanel.populateReceivedMessages(text, image);
    }
}
