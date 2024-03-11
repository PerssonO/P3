package Entity;

import javax.swing.*;
import java.io.Serializable;

/**
 * Class containing information about a user.
 */
public class User implements Serializable {
    private String userName = "default";
    private ImageIcon image;

    /**
     * Constructor for the class.
     * @param userName Username as string.
     * @param userImage Image chosen by user.
     */
    public User(String userName, ImageIcon userImage) {
        this.userName = userName;
        this.image = userImage;
    }

    /**
     * Return user name.
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Return user image.
     * @return ImageIcon
     */
    public ImageIcon getImage(){
        return image;
    }

    /**
     * Sets a user name.
     * @param userName String.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns a hashcode for the string.
     * @return hashcode as integer.
     */
    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    /**
     * Compares an object to the specified object.
     * @param obj Object to be compared.
     * @return True if the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof User)
            return userName.equals(((User)obj).getUserName());
        return false;
    }

    /**
     * Return the username.
     * @return String
     */
    @Override
    public String toString() {
        return userName;
    }
}
