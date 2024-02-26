package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class ChatFrame extends JFrame {

    private ChatMainPanel chatMainPanel;
    private MessageSender sender;
    private MessageReceiver receiver;

    public ChatFrame(MessageSender sender, MessageReceiver receiver){
        this.sender = sender;
        this.receiver = receiver;
        setResizable(false);
        setSize(500,500);
        setVisible(true);
        chatMainPanel = new ChatMainPanel(sender, receiver);
        setContentPane(chatMainPanel);
    }

    public JPanel getChatMainPanel() {
        return chatMainPanel;
    }
}
