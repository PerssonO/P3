package Entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface meant to be implemented by boundary-classes handling currently connected users to the server.
 */
public interface UpdateDisplayer {
    /**
     * Display list of users.
     * @param newUser Latest connected user.
     * @param onlineUsers ArrayList of Strings.
     */
    void displayUpdate(String newUser, ArrayList<String> onlineUsers);
    /**
     * Returns a map username and the user image corresponding to that user.
     * @return HashMap of strings and ImageIcon
     */
    HashMap<String, ImageIcon> getUserImagePair();
}
