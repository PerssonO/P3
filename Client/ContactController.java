package Client;

import Model.ContactUpdateObject;
import View.MainFrame;

public class ContactController {
    private OutputHandler outputHandler;
    private MainFrame mainFrame;

    public void updateUserInfo(ContactUpdateObject contactUpdateObject) {
        mainFrame.transferContactUpdateObject(contactUpdateObject);
    }
}
