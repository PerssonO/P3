package Client;

import Model.User;
import View.ContactsPanel;
import View.MainFrame;

import javax.swing.*;

public class ClientStarter {
    private User user;
    private Connection connection;

    public ClientStarter() {
        MessageController messageController = new MessageController(this);
        UpdateController updateController = new UpdateController();
        ContactController contactController = new ContactController(this);
        MainFrame mainFrame = new MainFrame(this, messageController, updateController, contactController);
        messageController.setMainFrame(mainFrame);
        updateController.setMainFrame(mainFrame);
        contactController.setMainFrame(mainFrame);

        connection = new Connection(this);
        OutputHandler outputHandler = new OutputHandler(connection, this);
        InputHandler inputHandler = new InputHandler(connection, this, messageController, updateController, contactController);
        connection.setInputHandler(inputHandler);
        updateController.setOutputHandler(outputHandler);
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

    public void setUser(User user) {
        this.user = user;

    }

    public ImageIcon getImage(){
        return user.getImage();
    }

}
