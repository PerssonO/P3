package Client;

import Model.User;
import View.MainFrame;

import javax.swing.*;

public class ClientStarter {
    private User user;
    private MessageController messageController;
    private UpdateController updateController;
    private ContactController contactController;
    private MainFrame mainFrame;

    public ClientStarter() {
        messageController = new MessageController(this);
        updateController = new UpdateController();
        contactController = new ContactController(this);

        mainFrame = new MainFrame(this, messageController, updateController, contactController);

        messageController.setMainFrame(mainFrame);
        updateController.setMainFrame(mainFrame);
        contactController.setMainFrame(mainFrame);
    }


    private void initializeConnection() {
        Connection connection = new Connection(this);
        OutputHandler outputHandler = new OutputHandler(connection, this);
        InputHandler inputHandler = new InputHandler(connection, this, messageController, updateController, contactController);
        connection.setInputHandler(inputHandler);
        updateController.setOutputHandler(outputHandler);
        contactController.setOutputHandler(outputHandler);
        messageController.setOutputHandler(outputHandler);
    }

    public void createUser(String userName, ImageIcon userImage) {

        this.user = new User(userName, userImage);
//        try {
//            while(user != null) {
//                wait();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
