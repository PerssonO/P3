package Client;

import Model.ContactUpdateObject;
import Model.Message;
import Model.Update;

import javax.swing.*;

public class InputHandler {
    private MessageController messageController;
    private UpdateController updateController;
    private ContactController contactController;
    private Connection connection;
    private ClientStarter controller;

    public InputHandler(Connection connection, ClientStarter controller, MessageController messageController, UpdateController updateController, ContactController contactController) {
        this.messageController = messageController;
        this.updateController = updateController;
        this.contactController = contactController;
        this.connection = connection;
        this.controller = controller;
    }

    public void retrieveInput(Object obj) {
        if (obj == null) {
            throw new RuntimeException("Null object from inputstream");
        }
        if (obj instanceof Message) {
            relayMessage((Message) obj);
        }
        if (obj instanceof Update) {
            relayUpdate((Update) obj);
        }
        if (obj instanceof ContactUpdateObject) {
            relayContactUpdateObject((ContactUpdateObject) obj);
        }
    }

    private void relayMessage(Message message) {
        messageController.displayMessage(message);
    }
    private void relayUpdate(Update update) {
        updateController.updateGUI(update);
    }
    private void relayContactUpdateObject(ContactUpdateObject contactUpdate) {
        contactController.updateUserInfo(contactUpdate);
    }

}

