package Boundary.GUI;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChatFrame extends JFrame implements WindowListener {

    private ChatMainPanel chatMainPanel;
    private BoundaryHandler boundaryHandler;
    private String recipient;

    public ChatFrame(BoundaryHandler boundaryHandler, String recipient, ImageIcon userImage, ImageIcon mainUserImage){
        setTitle(recipient);
        addWindowListener(this);
        this.boundaryHandler = boundaryHandler;
        this.recipient = recipient;
        setResizable(false);
        setSize(500,500);
        setVisible(true);
        chatMainPanel = new ChatMainPanel(boundaryHandler, recipient, userImage, mainUserImage);
        setContentPane(chatMainPanel);
    }

    public void displayMessage(String text, ImageIcon image) {
        chatMainPanel.displayMessage(text, image);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        boundaryHandler.removeChatHandler(recipient);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        boundaryHandler.removeChatHandler(recipient);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
