package Controller.Client;

import Boundary.Client.Connection;
import Entity.ContactUpdateObject;
import Entity.Message;
import Entity.Update;

/**
 * Retrieves objects from the boundary to the server and sends the correct object-type to the correct controller.
 */
public class InputHandler {
    private MessageController messageController;
    private UpdateController updateController;
    private ContactController contactController;

    /**
     * Constructor for the class.
     * @param messageController MessageController-object.
     * @param updateController UpdateController-object.
     * @param contactController ContactController-object.
     */
    public InputHandler(MessageController messageController, UpdateController updateController, ContactController contactController) {
        this.messageController = messageController;
        this.updateController = updateController;
        this.contactController = contactController;
    }

    /**
     * Retrieves an input from the inputstream. Sends each object-type to the correct controller.
     * @param obj Object received from boundary.
     */
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

    /**
     * Sends a Message-object to the MessageController.
     * @param message Message-object.
     */
    private void relayMessage(Message message) {
        messageController.displayMessage(message);
    }

    /**
     * Sends an Update-object to the UpdateController.
     * @param update Update-object.
     */
    private void relayUpdate(Update update) {
        updateController.updateGUI(update);
    }

    /**
     * Sends a ContactUpdateObject to the ContactController.
     * @param contactUpdate ContactUpdateObject-object.
     */
    private void relayContactUpdateObject(ContactUpdateObject contactUpdate) {
        contactController.updateUserInfo(contactUpdate);
    }

}

