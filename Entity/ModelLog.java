package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Klass som används för att skapa objekt när servern tar emot / skickar trafik som sparas i en arraylist.
 */
public class ModelLog implements Serializable {
    private Date date;
    private String str;

    /**
     * Konstruktor som skapar objekt av klassen. Skapar ett nytt datum-objekt och lägger till en string.
     * @param str Den string som objektet ska innehålla.
     */
    public ModelLog(String str){
        this.date = new Date();
        this.str = str;
    }

    /**
     * Metod för att göra om objektet till en string.
     * @return en String innehållande datum och text.
     */
    @Override
    public String toString() {
        return date + " " + str;
    }

    /**
     * metod som används för att få datum-objektet från ett MOdelLog objekt.
     * @return ett datum-objekt.
     */
    public Date getDate() {
        return date;
    }
}
