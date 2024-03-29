package Entity;

import Boundary.Server.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Klassen innehåller en ConcurrentHashMap som spara alla User deras connection. User är nyckel.
 */
public class ConnectedClients {
    private ConcurrentHashMap<User, Connection> connectedList;

    public ConnectedClients() {
        this.connectedList = new ConcurrentHashMap<>();
    }

    /**
     * Lägger till en user och dess connection. Metoden kontrolerar så att användaren inte redan finns i map.
     * @param user User som ska läggas till
     * @param connection Userns connection
     */
    public synchronized void put(User user, Connection connection) {
        if (!connectedList.containsKey(user)) {
            connectedList.put(user, connection);
        }
        notifyAll();
    }

    /**
     * Kontrolerar om användare finns i map.
     * @param user Användare som ska kontroleras
     * @return true eller false
     */
    public boolean isUserConnected(User user) {

        return connectedList.containsKey(user);
    }

    /**
     * Reunerar en Users Connection objekt
     * @param user Användaren var anslutning man vill nå
     * @return Connection objekt
     */
    public Connection getConnectionForUser(User user) {

        return connectedList.get(user);
    }

    /**
     * Metoden loppar igenom map och retunerar en ArrayList med alla Userobjekt i map.
     * @return ArrayList med alla user objekt
     */
    public ArrayList<User> getListOfConnected() {
        ArrayList<User> listConnectedClients = new ArrayList<>();
        for (User user : connectedList.keySet()){
            listConnectedClients.add(user);
        }


        return listConnectedClients;
    }
    /**
     * Metoden loppar igenom map och retunerar en ArrayList med alla Connectionobjekt i map.
     * @return ArrayList med alla connection objekt
     */
    public ArrayList<Connection> getListOfAllConnection() {
        ArrayList<Connection> allC = new ArrayList<>();

        for (Connection connection : connectedList.values()){
            allC.add(connection);
        }


        return allC;
    }

    /**
     * Tar bort User från map
     * @param user User som ska tas bort
     */
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
