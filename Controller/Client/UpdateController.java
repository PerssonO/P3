package Controller.Client;

import Entity.Update;
import Entity.User;
import Entity.UpdateDisplayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateController {
    private UpdateDisplayer displayer;

    public void updateGUI(Update update) {
        ArrayList<User> contactList = update.getUsers();
        for (User user : contactList) {
            HashMap<String, ImageIcon> userAndImage = displayer.getUserImagePair();
            userAndImage.put(user.toString(), user.getImage());
        }
        String newUser = "";
        if (update.getUser() != null) {
            newUser = update.getUser().toString();
        }
        ArrayList<String> onlineUsers = update.listToString();
        displayer.displayUpdate(newUser, onlineUsers);
    }

    public void setUpdateDisplayer(UpdateDisplayer displayer) {
        this.displayer = displayer;
    }

}
