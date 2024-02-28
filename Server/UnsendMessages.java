package Server;

import Model.Message;
import Model.User;

import java.util.ArrayList;

import java.util.concurrent.ConcurrentHashMap;

public class UnsendMessages {

    private ConcurrentHashMap<User, ArrayList<Message>> notSentMessages;


    public UnsendMessages(){
        this.notSentMessages = new ConcurrentHashMap<>();
    }


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

    public ArrayList<Message> getMessagesForUser(User user){

        return notSentMessages.remove(user);
    }

    public boolean userHasOldMessages(User user){

        return notSentMessages.containsKey(user);
    }


}
