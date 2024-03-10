package Controller.Client;

import Boundary.Client.Connection;
import Entity.ContactUpdateObject;
import Entity.Message;

public class OutputHandler {
    private final Connection connection;

    public OutputHandler(Connection connection) {
        this.connection = connection;
    }

    public void sendMessage(Message message) {
        connection.send(message);
    }

    public void sendContactUpdate(ContactUpdateObject contactUpdateObject) {
        connection.send(contactUpdateObject);
    }
}
