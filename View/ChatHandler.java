package View;

import Client.MessageController;
import Model.User;

import javax.swing.*;
import java.util.HashMap;

public class ChatHandler {
    private ChatFrame chatWindow;

    public ChatHandler(ChatFrame chatWindow, String userName) {
        this.chatWindow = chatWindow;
        setUserName(userName);
    }

    public void displayMessage(String text, ImageIcon image) {
        chatWindow.displayMessage(text, image);
    }

    public void setUserName(String userName) {
        chatWindow.setTitle(userName);
    }
}
