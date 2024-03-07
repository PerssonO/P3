package Server;

import Model.ContactUpdateObject;
import Model.Message;
import Model.Update;
import Model.User;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;


public class Connection {


    private InputHandler inputHandler;
    private Socket socket;

    private Buffer<Object> outputBuffer;

    private MainServerController msc;
    private OutputHandler outputHandler;

    private ConnectedClients connectedClients;

    private User client;

    public Connection(Socket socket, MainServerController msc, ConnectedClients connectedClients)  {
        this.msc = msc;
        this.connectedClients = connectedClients;
        this.socket = socket;

        this.inputHandler = new InputHandler(socket);
        inputHandler.start();
        this.outputBuffer = new Buffer<Object>();
        this.outputHandler = new OutputHandler(socket);
        outputHandler.start();
        System.out.println("Ny anslutning med klient");


    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessage(Message msg) {
        msg.setTimeDelivered(new Date());
        outputBuffer.put(msg);
    }

    public void sendUpdate(Update update) {
        outputBuffer.put(update);
    }
    public void sendContactList(ContactUpdateObject ob) {
        outputBuffer.put(ob);
    }


    private class OutputHandler extends Thread {
        private Socket socket;


        public OutputHandler(Socket socket) {

            this.socket = socket;

        }

        public void run() {

            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {
                System.out.println("Outputtråd skapad");
                while (!Thread.interrupted()) {
                    oos.writeObject(outputBuffer.get());
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
            System.out.println("Inputtråd skapad");
            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {


                while (!Thread.interrupted()) {
                    Object ob = ois.readObject();
                    if (ob instanceof Message){
                        msc.newIncomingMessage((Message) ob);
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










