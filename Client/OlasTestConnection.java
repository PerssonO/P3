package Client;

import Model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OlasTestConnection {
    private Socket socket;
    private Writer writer;
    private Reader reader;

    public OlasTestConnection() {
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

    public void send(Object object) {
        writer.addMessage(object);
    }

    public Object getMessage() {
        return reader.getMessage();
    }


    private class Writer extends Thread {
        private final BlockingQueue<Object> outgoingMessages = new LinkedBlockingQueue<>(); // Motsvarande den Buffer-klass vi använt i P1 och P2.

        @Override
        public void run() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))){
                while (!Thread.interrupted()) {
                    Object object = outgoingMessages.take();

                    oos.writeObject(object);
                    oos.flush();
                }
            } catch (IOException e) {
                System.err.println("I/O exception at Writer: " + e);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e);
            }
        }

        public void addMessage(Object object) {
            try {
                outgoingMessages.put(object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Reader extends Thread {
        private final BlockingQueue<Object> incomingMessages = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
                while (!Thread.interrupted()) {
                    Object ob = ois.readObject();
                    incomingMessages.put(ob);
                }
            } catch (IOException e) {
                System.err.println("I/O exception at Reader: " + e);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e);
            } catch (ClassNotFoundException e) {
                System.err.println("Could not cast to type: " + e);
            }
        }

        private Object getMessage() {
            try {
                return incomingMessages.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}





