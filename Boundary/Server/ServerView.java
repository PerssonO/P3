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

public class ServerView {
    private Thread thread;
    private Boolean running = true;

    private ServerViewController svc;

    /*
    Eftersom detta är på serversidan antar jag att användaren inte är helt inkompetent så felhantering är minimal.
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

    public void displayMessage(String text){
        System.out.println(text);
    }
}
