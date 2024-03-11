package Entity;

import javax.swing.*;
import java.util.HashMap;

/**
 * Interface meant to be implemented by boundary-classes handling messages.
 */
public interface MessageDisplayer {
    /**
     * Display message-data.
     * @param user Username.
     * @param userImage User image.
     * @param mainUserImage Main user image.
     * @param messageString Text content of message.
     * @param image Image of message.
     */
    void displayMessage(String user, ImageIcon userImage, ImageIcon mainUserImage, String messageString, ImageIcon image);

    /**
     * Returns a map username and the user image corresponding to that user.
     * @return HashMap of strings and ImageIcon
     */
    HashMap<String, ImageIcon> getUserImagePair();
}
