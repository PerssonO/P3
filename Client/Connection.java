package Client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Connection {
    private ClientStarter starter;
    private InputHandler inputHandler;
    private Socket socket;
    private Output output;
    private Input input;

    public Connection(ClientStarter starter) {
        try {
            this.starter = starter;
            socket = new Socket("10.2.2.3", 10000);
            output = new Output();
            input = new Input();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object obj) {
        output.addObjectToQueue(obj);
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void startInput() {
        input.start();
    }

    public void startOutput() {
        output.start();
    }


    private class Output extends Thread {
        private final BlockingQueue<Object> outgoingObjects = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))){
                oos.writeObject(starter.getUser());
                oos.flush();

                while (!Thread.interrupted()) {
                    oos.writeObject(outgoingObjects.take());
                    oos.flush();
                }
            } catch (IOException e) {
                System.err.println("I/O exception at Output: " + e);
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e);
            }
        }

        private void addObjectToQueue(Object obj) {
            try {
                outgoingObjects.put(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Input extends Thread {

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {

                while (!Thread.interrupted()) {
                    Object obj = ois.readObject();
                    if (obj != null) {
                        inputHandler.retrieveInput(obj);
                    }
                }
            } catch (IOException e) {
                System.err.println("I/O exception at Input: " + e);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Could not cast to type: " + e);
            }
        }
    }

}





