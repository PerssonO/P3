package Client;

import Model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Connection {
    private Socket socket;
    private Writer writer;
    private Reader reader;

    public Connection() {
        try {
            socket = new Socket("127.0.0.1", 10000);
            writer = new Writer();
            reader = new Reader();
            writer.start();
            reader.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Message message) {
        writer.addMessage(message);
    }

    public Message getMessage() {
        return reader.getMessage();
    }


    private class Writer extends Thread {
        private final BlockingQueue<Message> outgoingMessages = new LinkedBlockingQueue<>(); // Motsvarande den Buffer-klass vi använt i P1 och P2.

        @Override
        public void run() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))){

                while (!Thread.interrupted()) {
                    Message message = outgoingMessages.take();
                    message.setTimeSent(new Date());
                    oos.writeObject(message);
                    oos.flush();
                }
            } catch (IOException e) {
                System.err.println("I/O exception at Writer: " + e);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e);
            }
        }

        public void addMessage(Message message) {
            try {
                outgoingMessages.put(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Reader extends Thread {
        private final BlockingQueue<Message> incomingMessages = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
                while (!Thread.interrupted()) {
                    incomingMessages.put((Message)ois.readObject());
                }
            } catch (IOException e) {
                System.err.println("I/O exception at Reader: " + e);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e);
            } catch (ClassNotFoundException e) {
                System.err.println("Could not cast to type: " + e);
            }
        }

        private Message getMessage() {
            try {
                return incomingMessages.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}





