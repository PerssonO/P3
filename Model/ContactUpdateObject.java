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

    public ArrayList<String> listToString() {
        ArrayList<String> stringList = new ArrayList<>();
        for (User user : userArrayList) {
            stringList.add(user.toString());
        }
        return stringList;
    }

    @Override
    public String toString() {
        return "ContactUpdateObject " + user;
    }
}
