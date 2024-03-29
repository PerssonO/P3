package Controller.Client;

import Entity.Update;
import Entity.User;
import Entity.UpdateDisplayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class handling the lists of users currently connected to the server.
 */
public class UpdateController {
    private UpdateDisplayer displayer;

    /**
     * Unpacks an Update-object and sends relevant data to the boundary-classes.
     * @param update Update-object.
     */
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

    /**
     * Sets an implementation of UpdateDisplayer.
     * @param displayer Implementation of UpdateDisplayer.
     */
    public void setUpdateDisplayer(UpdateDisplayer displayer) {
        this.displayer = displayer;
    }

}
