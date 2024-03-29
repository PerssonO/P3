package Controller.Server;

import Boundary.Server.ServerView;
import Entity.ModelLog;
import Entity.LogReaderWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klass som sköter logiken av inmatad data fån serverview. Parsar om inmatad data till datum och använder datum för att loppa igenom och
 * säga åt serverview att skriva ut trafiken.
 */

public class ServerViewController {
    private ServerView sv;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    /**
     * konstruktor som skapar ett nytt ServerViewController objekt.
     */
    public ServerViewController(){
        this.sv = new ServerView(this);
    }

    /**
     * Metod som tar en string och parsar om det till ett datum.
     * @param input Den string som ska parsas om till ett Date-objekt.
     * @return Returnerar det datum som det blivit parsat till.
     */
    public Date parseDate(String input) {
        try {
           Date date = dateFormat.parse(input);
           return date;
        } catch (ParseException e) {
            System.err.println("Could not parse to date");
        }
        return null;
    }

    /**
     * Metod som loopar igenom ArrayListan av servertrafik och säger åt serverview att skriva ut den trafik som ligger mellan tidpunkterna.
     * @param date1 Datum för den starttid du vill se servertrafik för.
     * @param date2 Datum för den sluttid du vill se servertrafik för.
     * @throws IOException
     */
    public void findLogs(Date date1, Date date2) throws IOException {
        for (int i = 0; i < LogReaderWriter.getInstance().getLoglist().size(); i++){
            if (LogReaderWriter.getInstance().getLoglist().get(i).getDate().after(date1) && LogReaderWriter.getInstance().getLoglist().get(i).getDate().before(date2)){
                sv.displayMessage(LogReaderWriter.getInstance().getLoglist().get(i).toString());
            }
        }
    }
}



