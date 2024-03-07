package Client;

import Model.User;
import View.MainFrame;

import javax.swing.*;
import java.util.HashMap;

public class ClientStarter {
    private User user;

    public ClientStarter() {
        new MainFrame(this, new MessageController(), new UpdateController(), new ContactController());
    }

    private void initializeConnection() {
        Connection connection = new Connection(this);
        new OutputHandler(connection, this);
        new InputHandler(connection, this);
    }

    public void createUser(String userName, ImageIcon userImage) {
        this.user = new User(userName, userImage);
        initializeConnection();
    }

    public User getUser() {
        return this.user;
    }

}
