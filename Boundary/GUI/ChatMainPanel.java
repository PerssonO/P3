package Boundary.GUI;

import javax.swing.*;

/**
 * Main panel holding subpanels for the chat window
 */
public class ChatMainPanel extends JPanel {

    private ChatSouthPanel chatSouthPanel;
    private ChatNorthPanel chatNorthPanel;

    /**
     * Constructor for ChatMainPanel
     * @param boundaryHandler reference to boundary handler
     * @param reciever name of the recipient
     * @param senderImage the recipient profile picture
     * @param mainUserImage profile picture of the user
     */
    public ChatMainPanel(BoundaryHandler boundaryHandler, String reciever, ImageIcon senderImage, ImageIcon mainUserImage) {
        setLayout(null);
        chatSouthPanel = new ChatSouthPanel(boundaryHandler, reciever, mainUserImage);
        add(chatSouthPanel);
        chatNorthPanel = new ChatNorthPanel(boundaryHandler, reciever, senderImage);
        add(chatNorthPanel);
    }

    /**
     * Method for displaying recieved messages in the northern panel
     * @param text text to be displayed
     * @param image image to be displayed
     */
    public void displayMessage(String text, ImageIcon image) {
        chatNorthPanel.populateReceivedMessages(text, image);
    }

}
