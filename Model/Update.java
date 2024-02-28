package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Update implements Serializable {
    private User newUser;

    private ArrayList<User> onlineUsers;

    public Update(User newUser, ArrayList<User> onlineUsers) {
        this.newUser = newUser;
        this.onlineUsers = onlineUsers;
    }


    //tostring för testning ok att ta bort/ändra
    public String toString(){

        return onlineUsers.toString();
    }
}
