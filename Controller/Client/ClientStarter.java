package Controller.Client;

import Boundary.Client.Connection;
import Entity.User;
import Boundary.GUI.BoundaryHandler;

import javax.swing.*;

/**
 * Instantiates objects the client is dependent on, such as controller-classes.
 */
public class ClientStarter {
    private User user;
    private Connection connection;

    /**
     * Constructor for the class. Instantiates all controller- and the connection-object.
     */
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

    /**
     * Starts the output and input thread.
     */
    private void initializeConnection() {
        connection.startOutput();
        connection.startInput();
    }

    /**
     * Creates a new User-object and connects to the server.
     * @param userName username encoded as String
     * @param userImage an image chosen by the user
     */
    public void createUser(String userName, ImageIcon userImage) {
        this.user = new User(userName, userImage);
        initializeConnection();
    }

    /**
     * Gets the User-object of the main user.
     * @return User-object.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Gets the main user's username.
     * @return Username encoded as String
     */
    public String getUserName() {
        return this.user.toString();
    }

    /**
     * Sets a User.
     * @param user User-object.
     */
    public void setUser(User user) {
        this.user = user;

    }

    /**
     * Returns the user's chosen image.
     * @return ImageIcon
     */
    public ImageIcon getImage(){
        return user.getImage();
    }

}
