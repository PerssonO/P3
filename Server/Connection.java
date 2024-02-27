package Server;

import Model.Message;
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

    public void sendUpdate(Object object) {
        outputBuffer.put(object);
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
                System.exit(1);

            } catch (InterruptedException e) {

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
                System.exit(1);

            } catch (ClassNotFoundException e) {
                System.out.println("FEL Run metoden i InputHandler InterruptedException");
                System.out.println(e.getMessage());
                System.exit(1);
            }

        }


    }
}










