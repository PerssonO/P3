package Controller.Client;

import Boundary.Client.Connection;
import Entity.User;
import Boundary.GUI.BoundaryHandler;

import javax.swing.*;

public class ClientStarter {
    private User user;
    private Connection connection;

    public ClientStarter() {
        MessageController messageController = new MessageController(this);
        UpdateController updateController = new UpdateController();
        ContactController contactController = new ContactController(this);
        BoundaryHandler boundaryHandler = new BoundaryHandler(this, messageController, contactController);
        messageController.setMessageDisplayer(boundaryHandler);
        updateController.setUpdateDisplayer(boundaryHandler);
        contactController.setContactUpdateDisplayer(boundaryHandler);

        connection = new Connection(this);
        OutputHandler outputHandler = new OutputHandler(connection);
        InputHandler inputHandler = new InputHandler(messageController, updateController, contactController);
        connection.setInputHandler(inputHandler);
        contactController.setOutputHandler(outputHandler);
        messageController.setOutputHandler(outputHandler);
    }


    private void initializeConnection() {
        connection.startOutput();
        connection.startInput();
    }

    public void createUser(String userName, ImageIcon userImage) {
        this.user = new User(userName, userImage);
        initializeConnection();
    }

    public User getUser() {
        return this.user;
    }

    public String getUserName() {
        return this.user.toString();
    }

    public void setUser(User user) {
        this.user = user;

    }

    public ImageIcon getImage(){
        return user.getImage();
    }

}
