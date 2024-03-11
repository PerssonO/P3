package Boundary.Client;

import Controller.Client.ClientStarter;
import Controller.Client.InputHandler;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Boundary to server. Establishes a connection to the server.
 */
public class Connection {
    private ClientStarter starter;
    private InputHandler inputHandler;
    private Socket socket;
    private Output output;
    private Input input;

    /**
     * Constructor for the class. Creates a socket-object and binds it to the specified port.
     * @param starter ClientStarter-object.
     */
    public Connection(ClientStarter starter) {
        try {
            this.starter = starter;
            socket = new Socket("127.0.0.1", 10000);
            output = new Output();
            input = new Input();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Puts an object into the outgoing queue of messages to the server.
     * @param obj Object to be sent.
     */
    public void send(Object obj) {
        output.addObjectToQueue(obj);
    }

    /**
     * Sets an instance of InputHandler.
     * @param inputHandler
     */
    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * Starts the thread for the inner class Input.
     */
    public void startInput() {
        input.start();
    }

    /**
     * Starts the thread for the outer thread Output.
     */
    public void startOutput() {
        output.start();
    }

    /**
     * Inner class responsible for all output to the server. Runs on its own thread.
     */
    private class Output extends Thread {
        private final BlockingQueue<Object> outgoingObjects = new LinkedBlockingQueue<>();

        /**
         * While the thread is not interrupted, an object is retrieved from the queue and then written to the output stream.
         */
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

        /**
         * Adds an object to the queue of outgoing messages.
         * @param obj Object to be added to the queue.
         */
        private void addObjectToQueue(Object obj) {
            try {
                outgoingObjects.put(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inner class responsible for all input from the server. Runs its own thread.
     */
    private class Input extends Thread {
        /**
         * While th thread is not interrupted, an object is read from the inputstream. Every object gets handed to an instance of InputHandler.
         */
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





