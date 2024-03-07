package Client;

import Model.Message;
import Model.User;
import View.ChatHandler;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageController {
    private OutputHandler outputHandler;
    private MainFrame mainFrame;

    public void displayMessage(Message message) {
        mainFrame.transferMessage(message);
    }

    public void sendMessage(String text, ImageIcon image, User...recipients) {
        Message message = Message.Builder.newInstance()
                .setText(text)
                .setImage(image)
                .setRecipients(recipients)
                .build();

        outputHandler.sendMessage(message);
    }
}
