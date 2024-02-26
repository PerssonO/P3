package Server;

import Model.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// -------- ONLY FOR TESTING ------- //
public class MockServer extends Thread {
    private ServerSocket serverSocket;

    public MockServer() {
        try {
            serverSocket = new ServerSocket(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("Client connected");
                try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                     ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()))) {
                    Message message = (Message) ois.readObject();
                    message.setTimeDelivered(new Date());
                    oos.writeObject(message);
                    oos.flush();
                } catch (IOException e) {
                    System.err.println("I/O Exception at MockServer:" + e);
                } catch (ClassNotFoundException e) {
                    System.err.println("Could not cast to type at MockServer: " + e);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
