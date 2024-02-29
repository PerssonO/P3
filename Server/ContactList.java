package Server;

import Model.*;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ContactList implements Runnable {


    private ConcurrentHashMap<User, ArrayList<User>> contactList;
    boolean fileLoaded;


    public ContactList() {
        this.contactList = new ConcurrentHashMap<>();
        fileLoaded = false;
        new Thread(this).start();
    }


    public synchronized void put(User userKey, ArrayList<User> array) {
        contactList.put(userKey, array);

        new Thread(this).start();
        notifyAll();
    }


    public ArrayList<User> getContactlist(User user) {

        return contactList.get(user);
    }


    @Override
    public void run() {
        if (!fileLoaded) {
            loadFile();
        } else {
            saveFile();
        }


    }

    private void saveFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedContactList"))) {
                oos.writeObject(contactList);
                oos.flush();

        } catch (IOException e){
            System.out.println("Kunde inte spara fil");
        }


    }

    private void loadFile () {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedContactList"))) {
            contactList = (ConcurrentHashMap<User, ArrayList<User>>) ois.readObject();
            fileLoaded = true;


        } catch (IOException e){
            System.out.println("Kunde inte ladda fil IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("Kunde inte ladda fil ClassNotFoundException");
        }
    }
}



