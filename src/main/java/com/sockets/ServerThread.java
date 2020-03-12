package com.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket socket;
    private int clientNumber;

    ServerThread(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        try {
            System.out.println("connected-" + socket);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            String clientMessage = "";
            while (true) {
                clientMessage = bufferedReader.readLine();
                if (clientMessage.equals("exit")) {
                    System.out.println("Client-" + clientNumber + ":Connection Closed");
                    break;
                }
                System.out.println("Client-" + clientNumber + ":" + clientMessage);
                String serverMessage = input.nextLine();
                printWriter.println("Server:" + serverMessage);
                printWriter.flush();
            }
            printWriter.close();
            bufferedReader.close();
            inputStreamReader.close();
            socket.close();
        } catch (Exception e) {
            throw new RuntimeException("error in client-" + socket);
        }
    }
}
