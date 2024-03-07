package View;

import Client.InputHandler;
import Client.OutputHandler;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private LoginPanel loginPanel;

    public LoginFrame(MainFrame mainFrame){
        setSize(200,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        loginPanel = new LoginPanel(this, mainFrame);
        add(loginPanel);
        setContentPane(loginPanel);
        setVisible(true);
    }
}
