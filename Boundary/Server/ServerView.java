package Boundary.Server;
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    Date date1;
    Date date2;

    /*
    Eftersom detta är på serversidan antar jag att användaren inte är helt inkompetent så felhantering är minimal.
     */
    public ServerView(){
        this.thread = new Thread(() -> { do {
            Scanner myscanner = new Scanner(System.in);
            System.out.println("Enter a date you want to see the logs from: (dd/mm/yyyy hh:mm) Like: 01/01/2024 00:00 ");
            String input = myscanner.nextLine();
            System.out.println("Enter the day you want to see the logs to: (dd/mm/yyyy hh:mm) Like: 01/01/2024 00:00");
            String input2 = myscanner.nextLine();
            try {
                date1 = dateFormat.parse(input);
                date2 = dateFormat.parse(input2);
                for (int i = 0; i < LogReaderWriter.getInstance().getLoglist().size(); i++) {
                    if (LogReaderWriter.getInstance().getLoglist().get(i).getDate().after(date1) && LogReaderWriter.getInstance().getLoglist().get(i).getDate().before(date2)) {
                        System.out.println(LogReaderWriter.getInstance().getLoglist().get(i));
                    }
                }

            } catch (Exception e) {
                System.out.println("Wrong input, try again");
            }
        }

        while(running);

        });
        thread.start();
    }
}
