package Boundary.GUI;

import javax.swing.*;

public class ChatMainPanel extends JPanel {

    private ChatSouthPanel chatSouthPanel;
    private ChatNorthPanel chatNorthPanel;


    public ChatMainPanel(BoundaryHandler boundaryHandler, String reciever, ImageIcon senderImage, ImageIcon mainUserImage) {
        setLayout(null);
        chatSouthPanel = new ChatSouthPanel(boundaryHandler, reciever, mainUserImage);
        add(chatSouthPanel);
        chatNorthPanel = new ChatNorthPanel(boundaryHandler, reciever, senderImage);
        add(chatNorthPanel);
    }

    public void displayMessage(String text, ImageIcon image) {
        chatNorthPanel.populateReceivedMessages(text, image);
    }

}
