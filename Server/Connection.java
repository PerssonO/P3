package Server;

import Model.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


public class Connection {


    private InputHandler inputHandler;
    private Socket socket;

    private Buffer<Object> outputBuffer;

    private MainServerController msc;
    private OutputHandler outputHandler;

    private ConnectedClients connectedClients;

    private User client;
    private ChatHistory chatHistory;

    public Connection(Socket socket, MainServerController msc, ConnectedClients connectedClients)  {
        this.msc = msc;
        this.connectedClients = connectedClients;
        this.socket = socket;

        this.inputHandler = new InputHandler(socket);
        inputHandler.start();
        this.outputBuffer = new Buffer<Object>();
        this.outputHandler = new OutputHandler(socket);
        outputHandler.start();

        //System.out.println("Ny anslutning med klient"); //Minimera utskrifter i terminal eftersom den används av ServerView


    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessage(Message msg){
        msg.setTimeDelivered(new Date());
       // msc.addToChatHistory(client, msg);

        try {
            LogReaderWriter.getInstance().addToLogList(new ModelLog("New outgoing message: " + msg.toString()));
        }
        catch (IOException e){
            System.out.println("fel i sendMessageMetoden");
        }
        finally {
            outputBuffer.put(msg);
        }
    }

    public void sendUpdate(Update update) {
        try {

            LogReaderWriter.getInstance().addToLogList(new ModelLog("New outgoing Update: " + update.toString()));
        }
        catch (IOException e){
            System.out.println("fel i sendMessageMetoden");
        }
        finally {
            outputBuffer.put(update);
        }


        outputBuffer.put(update);
    }

    public void sendChatHistory(ConcurrentHashMap <User, ArrayList<Message>> update) {
        outputBuffer.put(update);
    }
    public void sendContactList(ContactUpdateObject ob){
        try {

            LogReaderWriter.getInstance().addToLogList(new ModelLog("New outgoing ContactUpdateObject: " + ob.toString()));
        }
        catch (IOException e){
            System.out.println("fel i sendMessageMetoden");
        }
        finally {
            outputBuffer.put(ob);
        }


    }


    private class OutputHandler extends Thread {
        private Socket socket;


        public OutputHandler(Socket socket) {

            this.socket = socket;

        }

        public void run() {

            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {
               // System.out.println("Outputtråd skapad"); //Minimera utskrifter i terminal eftersom den används av ServerView
                while (!Thread.interrupted()) {
                    Object obj = outputBuffer.get();
                    System.out.println("object to send" + obj);

                    oos.writeObject(obj);
                    oos.flush();
                }
            } catch (IOException e) {
                System.out.println("FEL Run metoden i InputHandler IOException");
                System.out.println(e.getMessage());


            } catch (InterruptedException e) {
                System.out.println("FEL Run metoden i InputHandler InterruptedException");
                System.out.println(e.getMessage());
            }
            finally {
                connectedClients.removeUser(client);
                msc.updateAfterDisconnect();
                try{
                    this.socket.close();

                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                    System.out.println("Kunde ej stänga socket");

                }
            }


        }


    }


    private class InputHandler extends Thread {
        private Socket socket;

        public InputHandler(Socket socket) {

            this.socket = socket;
        }

        public void run() {
            //System.out.println("Inputtråd skapad"); //Minimera utskrifter i terminal eftersom den används av ServerView
            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


                while (!Thread.interrupted()) {
                    Object ob = ois.readObject();
                    if (ob instanceof Message){
                        msc.newIncomingMessage((Message) ob, client);
                    }
                    if (ob instanceof ContactUpdateObject){
                        msc.clientContactListUpdate((ContactUpdateObject) ob);
                    }
                    if (ob instanceof User){
                        client = (User)ob;
                        msc.newConnetction((User) ob, Connection.this);
                    }



                }
            } catch (IOException e) {
                System.out.println("FEL Run metoden i InputHandler IOException");
                System.out.println(e.getMessage());


            } catch (ClassNotFoundException e) {
                System.out.println("FEL Run metoden i InputHandler InterruptedException");
                System.out.println(e.getMessage());

            } finally {
                connectedClients.removeUser(client);
                msc.updateAfterDisconnect();
                try{
                    this.socket.close();
                }

                catch (IOException e){
                    System.out.println(e.getMessage());
                    System.out.println("Kunde inte stänga socket");
                }
            }

        }


    }
}










