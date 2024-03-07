package View;

import Client.InputHandler;
import Client.OutputHandler;
import Model.User;

import javax.swing.*;

public class ChatMainPanel extends JPanel {

    private ChatSouthPanel chatSouthPanel;
    private ChatNorthPanel chatNorthPanel;


    public ChatMainPanel(MainFrame mainFrame, User reciever) {
        setLayout(null);
        chatSouthPanel = new ChatSouthPanel(mainFrame, reciever);
        add(chatSouthPanel);
        chatNorthPanel = new ChatNorthPanel(mainFrame, reciever);
        add(chatNorthPanel);
    }

    public void displayMessage(String text, ImageIcon image) {
        chatNorthPanel.populateReceivedMessages(text, image);
    }
}
