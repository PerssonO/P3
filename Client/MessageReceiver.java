package Client;

import Model.Message;

public class MessageReceiver {
    private Connection connection;
    private ClientController controller;

    public MessageReceiver(Connection connection, ClientController controller) {
        this.connection = connection;
        this.controller = controller;
    }

    public Message getMessage() {
        return connection.getMessage();
    }
}

