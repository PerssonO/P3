package Client;

import Model.Message;
import Model.User;

import javax.swing.*;

public class MessageSender {
    private final Connection connection;
    private final ClientController controller;

    public MessageSender(Connection connection, ClientController controller) {
        this.connection = connection;
        this.controller = controller;
    }

    // Skapar nya Message-objekt med data från GUI:t. Metoden anropas direkt av en GUI-klass, ex. ChatFrame?
    public void sendMessage(String textContent, ImageIcon image, String...recipients) {
        Message message = new Message();
        message.setSender(controller.getUser());
        for (String recipient : recipients) {
            message.addRecipients(controller.getContacts().get(recipient));
        }
        message.setText(textContent);
        message.setImage(image);

        connection.send(message);
    }
}
