package Entity;
//Ta bort denna kommentar
import Entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Klassen håller en ConcurrentHashmap som sparar alla användares kontaktlistor i form av en arrayList med userobjekt.
 * User är nyckel i map
 * Inre klass läser och sparar map till hårddisk.
 */
public class ContactList implements Runnable {


    private ConcurrentHashMap<User, ArrayList<User>> contactList;
    boolean fileLoaded;


    public ContactList() {
        this.contactList = new ConcurrentHashMap<>();
        fileLoaded = false;
        new Thread(this).start();
    }

    /**
     * Metoden lägger till en uppdaterad kontaklista för en användare.
     * @param userKey Användare vars kontaktlistan ska sparas. Varje gång en ny lista läggs
     * till startas en ny tråd o filen sparas
     * @param array Användarens kontakter
     */
    public synchronized void put(User userKey, ArrayList<User> array) {
        contactList.put(userKey, array);

        new Thread(this).start();
        notifyAll();
    }

    /**
     * Hämtar en kontaktlista för en User.
     * @param user User vars kontaktlista som ska hämtas
     * @return ArrayList med User objekt
     */
    public ArrayList<User> getContactlist(User user) {
        if (!contactList.containsKey(user)){
            contactList.put(user, new ArrayList<User>());
        }

        return contactList.get(user);
    }

    /**
     * Klassens Run metod som kontrolerar om fil är laddad med hjälp av flagga. Är filen inte laddad så läses den in
     *. Är filen inläst så sparas filen
     */
    @Override
    public void run() {
        if (!fileLoaded) {
            loadFile();
        } else {
            saveFile();
        }
    }

    /**
     * Metod som sparar till fil
     */
    private void saveFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/savedContactList.dat"))) {
                oos.writeObject(contactList);
                oos.flush();

        } catch (IOException e){
            System.out.println("Kunde inte spara fil");
        }


    }

    /**
     * Metoden läser in filen
     */
    private void loadFile () {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/savedContactList.dat"))) {
            contactList = (ConcurrentHashMap<User, ArrayList<User>>) ois.readObject();
            fileLoaded = true;
            System.out.println(contactList);

        } catch (IOException e){
            if (e instanceof EOFException) {
                System.out.println("Kontaktlista: Ingen mer fil att läsa");
            } else {
                System.out.println("Kontaktlista: Kunde inte ladda fil IOException");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Kunde inte ladda fil ClassNotFoundException");
        }
    }
}



