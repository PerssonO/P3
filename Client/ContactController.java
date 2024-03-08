package Client;

import Model.ContactUpdateObject;
import Model.User;
import View.MainFrame;

import java.util.ArrayList;

public class ContactController {
    private ClientStarter starter;
    private OutputHandler outputHandler;
    private MainFrame mainFrame;

    public ContactController(ClientStarter starter) {
        this.starter = starter;
    }

    public void updateUser(User user) {
        starter.setUser(user);
    }

    public void updateUserInfo(ContactUpdateObject contactUpdateObject) {
        updateUser(contactUpdateObject.getUser());
        mainFrame.transferContactUpdateObject(contactUpdateObject);
    }

    public void sendUpdatedContactList(ArrayList<User> newContactList) {
        System.out.println("Contactcontroller: " + newContactList);
        outputHandler.sendContactUpdate(new ContactUpdateObject(starter.getUser(), newContactList));
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }
}
