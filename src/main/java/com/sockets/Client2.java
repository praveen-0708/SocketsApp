package com.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        String IP = "localhost";
        int port = 9999;
        Socket socket = new Socket(IP, port);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Scanner input = new Scanner(System.in);
        String message = "";
        while (true) {
            message = input.nextLine();
            printWriter.println(message);
            printWriter.flush();
            if (message.equals("exit")) {
                break;
            }
            System.out.println(bufferedReader.readLine());
        }
        printWriter.close();
        socket.close();
    }
}
