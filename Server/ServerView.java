package Server;

import Model.ModelLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerView {
    private Thread thread;
    private Boolean running = true;
    private ArrayList<ModelLog> loghistory = new ArrayList<>();

    /*
    Eftersom detta är på serversidan antar jag att användaren inte är helt inkompetent så felhantering är minimal.
     */
    public ServerView(){
        this.thread = new Thread(() -> { do {
            Scanner myscanner = new Scanner(System.in);
            System.out.println("Enter the day, month, date and time for when you want to see the logs: (Day Mon XX HH:MM) Like: Thu Mar 07 10:00 ");
            String input = myscanner.nextLine();
            try {
                input = input.substring(0, 1).toUpperCase() + input.substring(1, 4) + input.substring(4, 5).toUpperCase() + input.substring(5, input.length());
            }
            catch (Exception e){
                System.out.println("Wrong input, try again");
            }
            try {
                for (int i = 0; i < LogReaderWriter.getInstance().getLoglist().size(); i++) {
                    if (LogReaderWriter.getInstance().getLoglist().get(i).toString().subSequence(0, 16).toString().contains(input)) {
                        loghistory.add(LogReaderWriter.getInstance().getLoglist().get(i));
                    }
                }
            } catch (IOException e) {
                System.out.println("Something went wrong here");
            }
            if (!loghistory.isEmpty()){
                for (int i = 0; i< loghistory.size(); i++){
                    System.out.println(loghistory.get(i));
                }
                loghistory.clear();
            }
            else {
                System.out.println("Nothing to show for this date and time");
            }
        }
        while(running);



        });
        thread.start();
    }

}
