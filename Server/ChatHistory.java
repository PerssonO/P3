package Server;

import Model.Message;
import Model.User;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ChatHistory {


    private ConcurrentHashMap<User, ConcurrentHashMap<User, ArrayList<Message>>> chatHistoryMain;


    public ChatHistory() {
        chatHistoryMain = new ConcurrentHashMap<>();
    }


    public synchronized void putFromNewMessage(User user, Message msg) {
        if (!chatHistoryMain.containsKey(user)) {
            ConcurrentHashMap<User, ArrayList<Message>> usersChatHistoryMap = new ConcurrentHashMap<>();
            chatHistoryMain.put(user, usersChatHistoryMap);
        }
        for (int i = 0; i < msg.getRecipients().size(); i++) {
            User userToAdd = msg.getRecipients().get(i);
            if (!chatHistoryMain.get(user).containsKey(userToAdd)) {
                chatHistoryMain.get(user).put(userToAdd, new ArrayList<>());
            }
            chatHistoryMain.get(user).get(userToAdd).add(msg);
        }

        notifyAll();
    }


//    public synchronized void putFromSendMessage(User user, Message msg){
//        if(!chatHistoryMain.containsKey(user)){
//            ConcurrentHashMap <User, ArrayList<Message>> usersChatHistoryMap = new ConcurrentHashMap<>();
//            chatHistoryMain.put(user, usersChatHistoryMap);
//        }
//
//            if (!chatHistoryMain.get(user).containsKey(msg.getSender())){
//                chatHistoryMain.get(user).put(msg.getSender(), new ArrayList<>() );
//            }
//            chatHistoryMain.get(user).get(msg.getSender()).add(msg);
//
//
//        notifyAll();
//    }


    public ConcurrentHashMap<User, ArrayList<Message>> getChatHistoryForUser(User user) {
        return chatHistoryMain.get(user);
    }
}
