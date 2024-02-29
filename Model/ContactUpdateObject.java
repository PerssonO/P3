package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ContactUpdateObject implements Serializable {

    User user;
    ArrayList<User> userArrayList;

    public ContactUpdateObject(User user, ArrayList<User> array){
        this.user = user;
        this.userArrayList = array;
    }
    public User getUser() {
        return user;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }




}
