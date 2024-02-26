package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class ChatMainPanel extends JPanel {

    private ChatSouthPanel chatSouthPanel;
    private ChatNorthPanel chatNorthPanel;


    public ChatMainPanel(MessageSender sender, MessageReceiver receiver){
        setLayout(null);
        chatSouthPanel = new ChatSouthPanel(sender, receiver);
        add(chatSouthPanel);
        chatNorthPanel = new ChatNorthPanel(sender, receiver);
        add(chatNorthPanel);
    }
}
