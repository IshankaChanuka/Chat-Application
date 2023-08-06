import controller.ClientController;
import controller.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();


    public static void main(String[] args) {

        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(3000);
            while(true) {
                System.out.println("Waiting for clients...");
                socket = serverSocket.accept();
                System.out.println("Connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}