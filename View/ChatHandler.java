package View;

import Client.MessageController;
import Model.User;

import javax.swing.*;
import java.util.HashMap;

public class ChatHandler {
    private ChatFrame chatWindow;

    public ChatHandler(ChatFrame chatWindow) {
        this.chatWindow = chatWindow;
    }

    public void displayMessage(String text, ImageIcon image) {
        chatWindow.displayMessage(text, image);
    }
}
