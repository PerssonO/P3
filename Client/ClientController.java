package Client;

import Model.User;
import View.ChatFrame;
import View.LoginFrame;

import java.util.HashMap;

public class ClientController {
    private MessageSender messageSender;
    private MessageReceiver messageReceiver;
    private User user;
    private HashMap<String, User> contactList = new HashMap<>();

    public ClientController() {
        this.user = new User();
        Connection connection = new Connection();
        messageSender = new MessageSender(connection, this);
        messageReceiver = new MessageReceiver(connection, this);
        new LoginFrame(messageSender, messageReceiver);
    }

    public HashMap<String, User> getContacts() {
        return contactList;
    }

    public User getUser() {
        return this.user;
    }

}
