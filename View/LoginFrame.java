package View;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private LoginPanel loginPanel;

    public LoginFrame(){
        setSize(200,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        loginPanel = new LoginPanel(this);
        add(loginPanel);
        setContentPane(loginPanel);
        setVisible(true);
    }
}
