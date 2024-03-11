package Entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface meant to be implemented by boundary-classes handling information about a users contacts.
 */
public interface ContactUpdateDisplayer {
    /**
     * Display list of contacts.
     * @param contactUpdateObject ArrayList of Strings.
     */
    void displayContactUpdate(ArrayList<String> contactUpdateObject);

    /**
     * Returns a map username and the user image corresponding to that user.
     * @return HashMap of strings and ImageIcon
     */
    HashMap<String, ImageIcon> getUserImagePair();
}
