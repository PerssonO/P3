package View;

import Model.ContactUpdateObject;
import Model.Message;
import Model.Update;

public interface Communicator {
    void transferMessage(Message message);
    void transferUpdate(Update update);
    void transferContactUpdateObject(ContactUpdateObject contactUpdateObject);
}
