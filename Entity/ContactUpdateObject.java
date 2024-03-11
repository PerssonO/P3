package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objekt som skickas mellan klient och server för att uppdatera användares kontaktlista.
 * Består av en User (den vars lista det gäller) och en arrayList med User objekt som är kontaktlistan.
 */
public class ContactUpdateObject implements Serializable {

    User user;
    ArrayList<User> userArrayList;

    public ContactUpdateObject(User user, ArrayList<User> array){
        this.user = user;
        this.userArrayList = array;
    }

    /**
     * Hämtar User
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Hämtar en Users kontaktlista
     * @return ArrayList med User objekt
     */
    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    /**
     * Metoden retunerar en ArayList med Strängar som är namnen på alla användare i kontaktlistan
     * @return ArrayList String
     */
    public ArrayList<String> listToString() {
        ArrayList<String> stringList = new ArrayList<>();
        for (User user : userArrayList) {
            stringList.add(user.toString());
        }
        return stringList;
    }

    /**
     *
     * @return Users namn som Sträng
     */
    @Override
    public String toString() {
        return "ContactUpdateObject " + user;
    }
}
