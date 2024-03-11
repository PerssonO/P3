package Controller.Client;

import Boundary.Client.Connection;
import Entity.ContactUpdateObject;
import Entity.Message;

/**
 * Handles all output from the boundary to the server.
 */
public class OutputHandler {
    private final Connection connection;

    /**
     * Constructor for the class.
     * @param connection Connection-object.
     */
    public OutputHandler(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sends a Message-object to the Connection-class.
     * @param message Message-object.
     */
    public void sendMessage(Message message) {
        connection.send(message);
    }

    /**
     * Sends a ContactUpdateObject-object to the Connection-class.
     * @param contactUpdateObject ContactUpdateObject-object.
     */
    public void sendContactUpdate(ContactUpdateObject contactUpdateObject) {
        connection.send(contactUpdateObject);
    }
}
