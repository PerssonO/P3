package Server;

import Model.Message;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UnsendMessages {

    private HashMap<User, ArrayList<Message>> notSentMessages;


    public UnsendMessages(){
        this.notSentMessages = new HashMap<>();
    }


    public void put(User user, Message msg){
        if(!notSentMessages.containsKey(user)){
            ArrayList<Message> array = new ArrayList<>();
            array.add(msg);
            notSentMessages.put(user, array);
        }
        else {
           notSentMessages.get(user).add(msg);

        }

    }

    public ArrayList<Message> getMessagesForUser(User user){
        //Behövs kanske felkontroll om det inte finns några meddelanden
        //Eller en metod till som kollar om user finns i hashMap först
        return notSentMessages.remove(user);
    }

    public boolean userHasOldMessages(User user){

        return notSentMessages.containsKey(user);
    }


}
