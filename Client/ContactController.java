package Client;

import Model.ContactUpdateObject;
import Model.User;
import View.MainFrame;

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

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }
}
