package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objekt som skickas från servern som innehåller en User som är senast anslutna användare och En
 * ArrayList med User objekt som är alla uppkopplade användare
 */
public class Update implements Serializable {
    private User newUser;
    private ArrayList<User> onlineUsers;

    public Update(User newUser, ArrayList<User> onlineUsers) {
        this.newUser = newUser;
        this.onlineUsers = onlineUsers;
    }

    /**
     * Metoden Loopar igenom arrayn med Users och skapar en ArrayList Med strängar med namnen
     * på alla uppkopplade användare.
     * @return ArrayList String med användarnamn
     */
    public ArrayList<String> listToString() {
        ArrayList<String> stringList = new ArrayList<>();
        for (User user : onlineUsers) {
            stringList.add(user.toString());
        }
        return stringList;
    }

    /**
     * Metoden hämtar listan med uppkopplade användare
     * @return Retunerar ArrayList User med alla uppkopplade användare
     */
    public ArrayList<User> getUsers() {
        return onlineUsers;
    }

    /**
     * Metoden hämtar sen senast anslutna användaren
     * @return User objekt
     */
    public User getUser() {
        return newUser;
    }


    /**
     * Metoden retunerar en strängrepresentation av ArrayList med uppkopplade användare
     * @return String
     */
    public String toString(){

        return onlineUsers.toString();
    }
}
