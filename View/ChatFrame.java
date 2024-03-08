package View;

import Client.InputHandler;
import Client.OutputHandler;
import Model.User;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChatFrame extends JFrame implements WindowListener {

    private ChatMainPanel chatMainPanel;
    private MainFrame mainFrame;
    private User recipient;
//    private String recipient;

    //    public ChatFrame(MainFrame mainFrame, String recipient){
    public ChatFrame(MainFrame mainFrame, User recipient){
        setTitle(recipient.toString());
        addWindowListener(this);
        this.mainFrame = mainFrame;
        this.recipient = recipient;
        setResizable(false);
        setSize(500,500);
        setVisible(true);
        chatMainPanel = new ChatMainPanel(mainFrame, recipient);
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
        mainFrame.removeChatHandler(recipient);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        mainFrame.removeChatHandler(recipient);
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
