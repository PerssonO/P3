package Server;
//Ta bort denna kommentar
import Model.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


public class MainServerController  {
    private final int port = 10000;
    private NewClientConnection newClientConnection;

    private ConnectedClients connectedClients;

    private UnsendMessages unsendMessages;

    private ContactList contactList;
    private ChatHistory chatHistory;

    public MainServerController() {
        this.connectedClients = new ConnectedClients();
        this.unsendMessages = new UnsendMessages();
        this.newClientConnection = new NewClientConnection(10000);
        this.contactList = new ContactList();
        this.chatHistory = new ChatHistory();

        try{
            LogReaderWriter.getInstance();
        }
        catch (IOException e){
            System.out.println("Something went wrong when getting the instance for LogReaderWriter in MSC constructor.");
        }
        new ServerView();
        newClientConnection.start();
        //System.out.println("ServerController startad"); //Minimera utskrifter i terminal eftersom den används av ServerView
    }

//    public synchronized void addToChatHistory(User user, Message msg){
//        chatHistory.putFromSendMessage(user, msg);
//    }


    public synchronized void newIncomingMessage(Message msg, User user) throws IOException {
        System.out.println("new message on serverside");
        System.out.println(msg.getRecipients().size());
        msg.setTimeSent(new Date());
//        chatHistory.putFromNewMessage(user, msg);
        LogReaderWriter.getInstance().addToLogList(new ModelLog("New message received by server"));
        for (int  i = 0; i < msg.getRecipients().size(); i++){
            System.out.println("online user?");
            User userToSend;
            userToSend = msg.getRecipients().get(i);
            if (connectedClients.isUserConnected(userToSend)){
                connectedClients.getConnectionForUser(userToSend).sendMessage(msg);
                System.out.println("försök att skicka till online user");
            }
            else {
                unsendMessages.put(userToSend, msg);
                System.out.println("put into unsendmessages");
            }

        }
        notifyAll();
    }



    public void checkForOldMessages(User user) {
        if (unsendMessages.userHasOldMessages(user)) {
            System.out.println(Thread.currentThread());
            ArrayList<Message> toSend = unsendMessages.getMessagesForUser(user);
            for (int i = 0; i < toSend.size(); i++) {
                connectedClients.getConnectionForUser(user).sendMessage(toSend.get(i));
            }
        }


    }

    public synchronized void notifyOfNewOnlineUser(User user) {
        Update update = new Update(user, connectedClients.getListOfConnected());
        ArrayList<Connection> recivers = connectedClients.getListOfAllConnection();
        for (Connection connection : recivers){
            connection.sendUpdate(update);

        }
        notifyAll();

    }

    public synchronized void updateAfterDisconnect(){
        Update update = new Update(null, connectedClients.getListOfConnected());
        ArrayList<Connection> recivers = connectedClients.getListOfAllConnection();
        for (Connection connection : recivers){
            connection.sendUpdate(update);

        }

        notifyAll();
    }
    public void newConnetction(User user, Connection connection) throws IOException {
        LogReaderWriter.getInstance().addToLogList(new ModelLog("User: " + user.getUserName() + " connected to the server"));
        if (connectedClients.isUserConnected(user)){
            try {
                connectedClients.getConnectionForUser(user).getSocket().close();
                connectedClients.removeUser(user);
                connection.getSocket().close();
                updateAfterDisconnect();
            }
            catch (IOException e){
                System.out.println("IOException, newConnection(), MainServerController  ");
            }

        }
        connectedClients.put(user, connection);
        //connectedClients.getConnectionForUser(user).sendChatHistory(chatHistory.getChatHistoryForUser(user));
        //connection.sendContactList(new ContactUpdateObject(user, contactList.getContactlist(user)));
        // ----------------- TEST -------------------//
       /*
        if (contactList.getContactlist(user) == null) {
            ArrayList<User> testList = new ArrayList<>();
            testList.add(new User("Carlos", null));
            testList.add(new User("Pablo", null));
            contactList.put(user, testList);
        }
        */

        connection.sendContactList(new ContactUpdateObject(user, contactList.getContactlist(user)));
        // ------------------ TEST ------------------ //
        checkForOldMessages(user);
        notifyOfNewOnlineUser(user);
    }



    public void clientContactListUpdate(ContactUpdateObject ob) throws IOException {
        LogReaderWriter.getInstance().addToLogList(new ModelLog("New ClientContactListUpdate received by server"));
        contactList.put(ob.getUser(),ob.getUserArrayList());
    }


    private class NewClientConnection extends Thread {
        private ServerSocket serverSocket;

        public NewClientConnection(int port){
            try {
                this.serverSocket = new ServerSocket(port);


            } catch (IOException e) {
                System.out.println("IOException i NewClientConnection konstruktor");
                System.out.println(e.getMessage());

            }

        }

       @Override
       public void run() {

           System.out.println("Tråd som lyssnar efter nya anslutningar startad");
           while (!Thread.interrupted()) {
               try {
                   Socket socket = serverSocket.accept();
                   new Connection(socket, MainServerController.this, connectedClients);


               } catch (IOException e) {
                   System.out.println("IOException i NewClientConnection run metod");
                   System.out.println(e.getMessage());

               }


           }

       }





   }
}
