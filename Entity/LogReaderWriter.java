package Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
Singelton klass som sköter skrivandet av servertrafik till en dat-fil. Första gången man kallar på getinstance() metoden
läser klassen in vad som sår i filen. Annars kommer en fil att skapas när man skriver till de första gången.
 */

public class LogReaderWriter {
    private static LogReaderWriter instance = null;

    private ArrayList<ModelLog> loglist = new ArrayList<ModelLog>();

    private Timer timer;

    /**
    Privat konstruktor som läser från fil om filen finns.
    Sätter också upp en timer som skriver vad som finns i arraylistan varje minut så det sparas automatiskt.
     */
    private LogReaderWriter() {
        try {
            readFromFile("files/ServerTraffic.dat");
        } catch (Exception e) {
            System.out.println("There is no logfile to read. One will be written at some point during the servers runtime");
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new writeonSchedule(), 5000, 60000);

    }
/**
Synkroniserad metod så det inte blir problem om flera trådar försöker använda den samtidigt.
 */
    public synchronized void addToLogList(ModelLog log) {
        loglist.add(log);
    }

/**
Manuell skriva till fil metod om det skulle behövas.
 */
    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/ServerTraffic.dat"))) {
            oos.writeObject(loglist);
        } catch (Exception e) {
        }
    }

/**
metod som läser från fil.
 */
    private void readFromFile(String filename) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            try {
                loglist = (ArrayList<ModelLog>) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
    Metod som returnerar instansen av klassen. Om ingen instans finns ännu så skapas ett bytt objekt av klassen.
     */
    public static LogReaderWriter getInstance() throws IOException {
        if (instance == null) {
            instance = new LogReaderWriter();
        }
        return instance;
    }
/**
Metod som returnerar den arraylista som innehåller trafiken från servern
 */
    public synchronized ArrayList<ModelLog> getLoglist() {
        return loglist;
    }

    /**
    inre klass som ärver från timertask. Har bara en run metod som skriver till fil.
     */
    private class writeonSchedule extends TimerTask {

        @Override
        public void run() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/ServerTraffic.dat"))) {
                oos.writeObject(loglist);
            } catch (Exception e) {
            }
        }
    }
}

