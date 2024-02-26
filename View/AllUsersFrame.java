package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class AllUsersFrame extends JFrame {

    private JPanel allUsersPanel;

    public AllUsersFrame(MessageSender sender, MessageReceiver receiver){
        setSize(300,450);
        setResizable(false);
        allUsersPanel = new AllUsersPanel(sender, receiver);
        add(allUsersPanel);
        setContentPane(allUsersPanel);
        setVisible(true);
    }
}
