package Boundary.GUI;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * JFrame used for chat windows
 */
public class ChatFrame extends JFrame implements WindowListener {

    private ChatMainPanel chatMainPanel;
    private BoundaryHandler boundaryHandler;
    private String recipient;

    /**
     * Constructor
     * @param boundaryHandler reference to boundaryHandler
     * @param recipient name of the message recipient
     * @param userImage the recipient profile picture
     * @param mainUserImage user profile picture
     */
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

    /**
     * Mehtod for displaying strings and images in the panel
     * @param text text to be displayed
     * @param image imageIcon to be displayed
     */
    public void displayMessage(String text, ImageIcon image) {
        chatMainPanel.displayMessage(text, image);
    }

    /**
     * Method belonging to the windowlistener interface (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Method triggered when closing the frame, calls for the boundaryHandler to remove
     * the chathandler of the recipient
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {
        boundaryHandler.removeChatHandler(recipient);
    }
    /**
     * Method triggered when closing the frame, calls for the boundaryHandler to remove
     * the chathandler of the recipient
     * @param e the event to be processed
     */
    @Override
    public void windowClosed(WindowEvent e) {
        boundaryHandler.removeChatHandler(recipient);
    }

    /**
     * Method belonging to the windowlistener interface (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }
    /**
     * Method belonging to the windowlistener interface (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }
    /**
     * Method belonging to the windowlistener interface (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }
    /**
     * Method belonging to the windowlistener interface (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
