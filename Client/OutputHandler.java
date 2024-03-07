package Client;

import Model.ContactUpdateObject;
import Model.Message;

public class OutputHandler {
    private final Connection connection;
    private final ClientStarter controller;

    public OutputHandler(Connection connection, ClientStarter controller) {
        this.connection = connection;
        this.controller = controller;
    }

    public void sendMessage(Message message) {
        connection.send(message);
    }

    public void sendContactUpdate(ContactUpdateObject contactUpdateObject) {
        connection.send(contactUpdateObject);
    }
}
