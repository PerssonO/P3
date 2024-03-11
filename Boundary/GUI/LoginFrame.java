package Boundary.GUI;

import javax.swing.*;

/**
 * Frame used for LoginPanel
 */
public class LoginFrame extends JFrame {
    private LoginPanel loginPanel;

    /**
     * Constructor, created the window
     * @param boundaryHandler reference to boundaryHandler
     */
    public LoginFrame(BoundaryHandler boundaryHandler){
        setSize(200,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        loginPanel = new LoginPanel(this, boundaryHandler);
        add(loginPanel);
        setContentPane(loginPanel);
        setVisible(true);
    }
}
