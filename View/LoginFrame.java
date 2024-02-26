package View;

import Client.MessageReceiver;
import Client.MessageSender;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private LoginPanel loginPanel;

    public LoginFrame(MessageSender sender, MessageReceiver receiver){
        setSize(200,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        loginPanel = new LoginPanel(this, sender, receiver);
        add(loginPanel);
        setContentPane(loginPanel);
        setVisible(true);
    }
}
