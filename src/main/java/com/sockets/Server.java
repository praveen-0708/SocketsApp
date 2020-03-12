package com.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 9999;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket;
        int numberOfClients = 0;
        while (true) {
            clientSocket = serverSocket.accept();
            Thread t = new Thread(new ServerThread(clientSocket, ++numberOfClients));
            t.start();
        }
    }
}


