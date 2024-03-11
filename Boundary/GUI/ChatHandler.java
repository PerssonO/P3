package Boundary.GUI;

import Boundary.GUI.ChatFrame;

import javax.swing.*;

/**
 * Holds a reference to a chatFrame and displays messages on it
 */
public class ChatHandler {
    private ChatFrame chatWindow;

    /**
     * Constructor
     * @param chatWindow ChatFrame that is to be used for the chat
     */
    public ChatHandler(ChatFrame chatWindow) {
        this.chatWindow = chatWindow;
    }

    /**
     * Method for displaying messages in the frame
     * @param text text to be displayed
     * @param image image to be displayed
     */
    public void displayMessage(String text, ImageIcon image) {
        chatWindow.displayMessage(text, image);
    }
}
