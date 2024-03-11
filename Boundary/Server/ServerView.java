package Boundary.Server;
import Controller.Server.ServerViewController;
import Entity.ModelLog;
import Entity.LogReaderWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Klass som visar utskrift av servertrafik i terminalen efter att två datum har matats in av användare.
 * @author Jonatan Tempel
 */
public class ServerView {
    private Thread thread;
    private Boolean running = true;

    private ServerViewController svc;

    /**
     * Konstruktor som skapar en ny tråd och kör en run metod. Metoden körs så länge tråden lever
     * @param serverViewController
     */
    public ServerView(ServerViewController serverViewController){
        this.svc = serverViewController;
        this.thread = new Thread(() -> { do {
            Scanner myscanner = new Scanner(System.in);
            System.out.println("Enter a date you want to see the logs from: (dd/mm/yyyy hh:mm) Like: 01/01/2024 00:00 ");
            String input = myscanner.nextLine();
            Date date1 = svc.parseDate(input);
            System.out.println("Enter the day you want to see the logs to: (dd/mm/yyyy hh:mm) Like: 01/01/2024 00:00");
            String input2 = myscanner.nextLine();
            Date date2 = svc.parseDate(input2);
            try {
                svc.findLogs(date1, date2);
            } catch (IOException e) {
                System.err.println("Something went wrong with the findLogs-method");
            }

        }

        while(running);

        });
        thread.start();
    }

    /**
     * Metod för att skriva ut en string i terminalen.
     * @param text den string som ska skrivas ut i terminalen.
     */
    public void displayMessage(String text){
        System.out.println(text);
    }
}
