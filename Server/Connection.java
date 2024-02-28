package Server;

import Model.Message;
import Model.Update;
import Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public void sendMessage(Message msg) {
        outputBuffer.put(msg);
    }

    public void sendUpdate(Update update) {
        outputBuffer.put(update);
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

                User user = (User) ois.readObject();
                System.out.println("Tagit emot ett user objekt");
                System.out.println(user.getUserName());
                client = user;
                if (connectedClients.isUserConnected(user)){
                    connectedClients.getConnectionForUser(user).socket.close();
                    connectedClients.removeUser(user);
                    msc.updateAfterDisconnect();
                    this.socket.close();
                }
                connectedClients.put(user, Connection.this);
                msc.checkForOldMessages(user);
                msc.notifyOfNewOnlineUser(user);

                while (!Thread.interrupted()) {
                    Message msg = (Message) ois.readObject();
                    msc.newIncomingMessage(msg);
                }
            } catch (IOException e) {
                System.out.println("FEL Run metoden i InputHandler IOException");
                System.out.println(e.getMessage());


            } catch (ClassNotFoundException e) {
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
                    System.out.println("Kunde inte stänga socket");
                }
            }

        }


    }
}










