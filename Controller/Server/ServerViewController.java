package Controller.Server;

import Boundary.Server.ServerView;
import Entity.ModelLog;
import Entity.LogReaderWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerViewController {
    private ServerView sv;

    public ServerViewController(){
        this.sv = new ServerView(this);
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public Date parseDate(String input) {
        try {
           Date date = dateFormat.parse(input);
           return date;
        } catch (ParseException e) {
            System.err.println("Could not parse to date");
        }
        return null;
    }

    public void findLogs(Date date1, Date date2) throws IOException {
        for (int i = 0; i < LogReaderWriter.getInstance().getLoglist().size(); i++){
            if (LogReaderWriter.getInstance().getLoglist().get(i).getDate().after(date1) && LogReaderWriter.getInstance().getLoglist().get(i).getDate().before(date2)){
                sv.displayMessage(LogReaderWriter.getInstance().getLoglist().get(i).toString());
            }
        }
    }
}



