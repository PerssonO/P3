package Controller.Client;

import Boundary.Client.Connection;
import Entity.ContactUpdateObject;
import Entity.Message;
import Entity.Update;

public class InputHandler {
    private MessageController messageController;
    private UpdateController updateController;
    private ContactController contactController;

    public InputHandler(MessageController messageController, UpdateController updateController, ContactController contactController) {
        this.messageController = messageController;
        this.updateController = updateController;
        this.contactController = contactController;
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

