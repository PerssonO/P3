package Model;

import javax.swing.*;
import java.io.Serializable;

public class User implements Serializable {
    private String userName = "default";
    private ImageIcon image;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof User)
            return userName.equals(((User)obj).getUserName());
        return false;
    }

    @Override
    public String toString() {
        return userName;
    }
}
