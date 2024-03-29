package Entity;
//Ta bort denna kommentar
import Entity.Message;
import Entity.User;

import java.util.ArrayList;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Klasssen sparar ner alla meddelanden som ska levereras till icke anslutna användare. Message objekten sparas i
 * en ArrayList i en ConcurrentHashMap med User som nyckel. Spara till fil är inte implementerat. Stängs servern av försvinner
 * alla meddelanden.
 */

public class UnsendMessages {

    private ConcurrentHashMap<User, ArrayList<Message>> notSentMessages;


    public UnsendMessages(){
        this.notSentMessages = new ConcurrentHashMap<>();
    }

    /**
     * Metoden lägger till ett Message i en ArrayList som tillhör användaren
     * @param user offline användaren som ska erhålla meddelandet
     * @param msg Meddelandet som ska levereras till offline användaren
     */
    public synchronized void put(User user, Message msg){
        if(!notSentMessages.containsKey(user)){
            ArrayList<Message> array = new ArrayList<>();
            array.add(msg);
            notSentMessages.put(user, array);
        }
        else {
           notSentMessages.get(user).add(msg);

        }
        notifyAll();
    }

    /**
     *
     * @param user Den användare vars meddelande som ska hämtas
     * @return ArrayList med MessageObjekt
     */
    public ArrayList<Message> getMessagesForUser(User user){

        return notSentMessages.remove(user);
    }

    /**
     * Metoden kontrollerar om en användare har meddelanden sparade.
     * @param user Den användare som ska kontrolleras
     * @return  true/false
     */
    public boolean userHasOldMessages(User user){



        return notSentMessages.containsKey(user);
    }


}
