package Server;

import Model.ModelLog;
import Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class ConnectedClients {
    private ConcurrentHashMap<User, Connection> connectedList;

    public ConnectedClients() {
        this.connectedList = new ConcurrentHashMap<>();
    }

    public synchronized void put(User user, Connection connection) {
        if (!connectedList.containsKey(user)) {
            connectedList.put(user, connection);
        }
        notifyAll();
    }

    public boolean isUserConnected(User user) {

        return connectedList.containsKey(user);
    }

    public Connection getConnectionForUser(User user) {

        return connectedList.get(user);
    }

    public ArrayList<User> getListOfConnected() {
        ArrayList<User> listConnectedClients = new ArrayList<>();
        for (User user : connectedList.keySet()){
            listConnectedClients.add(user);
        }


        return listConnectedClients;
    }

    public ArrayList<Connection> getListOfAllConnection() {
        ArrayList<Connection> allC = new ArrayList<>();

        for (Connection connection : connectedList.values()){
            allC.add(connection);
        }


        return allC;
    }

    public void removeUser(User user){
        connectedList.remove(user);
        try{
            LogReaderWriter.getInstance().addToLogList(new ModelLog("User: " + user + " disconnected from the server."));
        }
        catch (IOException e){
            System.out.println("something went wrong in the removeUser method in ConnectedCClients");
        }

    }

}
