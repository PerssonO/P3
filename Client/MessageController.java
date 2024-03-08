package Client;

import Model.Message;
import Model.User;
import View.ChatHandler;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MessageController {
    private ClientStarter starter;
    private OutputHandler outputHandler;
    private MainFrame mainFrame;

    public MessageController(ClientStarter starter) {
        this.starter = starter;
    }

    public void displayMessage(Message message) {
        mainFrame.transferMessage(message);
    }

    public void sendMessage(String text, ImageIcon image, User...recipients) {
        System.out.println(Arrays.toString(recipients));
        Message message = Message.Builder.newInstance()
                .setSender(starter.getUser())
                .setText(text)
                .setImage(image)
                .setRecipients(recipients)
                .build();

        System.out.println(message.getRecipients().size());
        outputHandler.sendMessage(message);
    }

    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
