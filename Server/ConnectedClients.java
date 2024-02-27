package Server;

import Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectedClients {
    private HashMap<User, Connection> connectedList;

    public ConnectedClients() {
        this.connectedList = new HashMap<>();
    }

    public void put(User user, Connection connection) {
        if (!connectedList.containsKey(user)) {
            connectedList.put(user, connection);
        }

    }

    public boolean isUserConnected(User user){

        return connectedList.containsKey(user);
    }

    public Connection getConnectionForUser(User user){

        return connectedList.get(user);
    }

    public ArrayList<User> getListOfConnected(){
        ArrayList<User> listConnectedClients = new ArrayList<>();
        //Loopa igenom och fyll listan
        return listConnectedClients;
    }

    public void removeUser(User user){
        //implementering

    }

}
