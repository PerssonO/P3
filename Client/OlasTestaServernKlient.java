package Client;

import Model.Message;
import Model.Update;
import Model.User;

public class OlasTestaServernKlient {


    public static void main(String[] args){
     /*   //Testar update

        OlasTestConnection con1 = new OlasTestConnection();
        User user = new User();
        user.setUserName("Klient 1");
        con1.send(user);
        Update upp1 = (Update) con1.getMessage();
        System.out.println(upp1.toString());

        OlasTestConnection con2 = new OlasTestConnection();
        user.setUserName("Klient 2");
        con2.send(user);
        upp1 = (Update) con1.getMessage();
        System.out.println(upp1.toString());
        upp1 = (Update) con2.getMessage();
        System.out.println(upp1.toString());

        //Klient 2 skapar IO exeption på servern. Klient 1 ska få en uppdatering
       con2.send(upp1);
       upp1 = (Update) con1.getMessage();
        System.out.println(upp1.toString());*/










        /*
        OlasTestConnection con = new OlasTestConnection();
        User user = new User();
        user.setUserName("Ola");
        User user2 = new User();
        user2.setUserName("Kalle");
        con.send(user);
        Message msg = new Message();

        msg.addRecipients(user2);
        msg.setText("Från Ola till Kalle");
        con.send(msg);

        //Andra klient som är utloggad när meddelande skickas
        OlasTestConnection con2 = new OlasTestConnection();
        con2.send(user2);
        Message msgMottagit = (Message)con2.getMessage();
        System.out.println(msgMottagit.getText());

        // Tredje klient skickar till första två som är online
        User user3 = new User();
        user3.setUserName("Bosse");
        OlasTestConnection con3 = new OlasTestConnection();
        con3.send(user3);
        Message msg2 = new Message();
        msg2.setText("Meddelande till både user och user 1 som båda är online");
        msg2.addRecipients(user);
        msg2.addRecipients(user2);
        con3.send(msg2);

        Message msgMottagit2 = (Message)con.getMessage();
        System.out.println(msgMottagit2.getText());

        Message msgMottagit3 = (Message)con2.getMessage();
        System.out.println(msgMottagit3.getText());

        //Skickar till en som är online och en som är offline.
        User user4 = new User();
        user4.setUserName("Sven");

        Message msg3 = new Message();
        msg3.setText("Meddelande till en online och en offline");
        msg3.addRecipients(user4);
        msg3.addRecipients(user);
        con3.send(msg3);


       //Fyran går online
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        OlasTestConnection con4 = new OlasTestConnection();
        con4.send(user4);

        Message msgMottagit4 = (Message)con.getMessage();
       System.out.println(msgMottagit4.getText());

        Message msgMottagit5 = (Message)con4.getMessage();
        System.out.println(msgMottagit5.getText());
*/

    }
}
