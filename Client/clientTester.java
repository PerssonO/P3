package Client;

import Server.MockServer;

// -------- ONLY FOR TESTING -------- //
public class clientTester {
    public static void main(String[] args) {
        new MockServer().start();
        new ClientController();
    }
}