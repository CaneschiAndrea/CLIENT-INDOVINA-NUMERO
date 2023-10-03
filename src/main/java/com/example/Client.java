package com.example;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    public void startClient() {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Connessione effettuata al server.");

            BufferedReader inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outVersoServer = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String messaggioDalServer = inDalServer.readLine();
                System.out.println("Server -> " + messaggioDalServer);

                if (messaggioDalServer.equals("4")) {
                    break;
                }

                int numeroUtente = scanner.nextInt();
                outVersoServer.writeBytes(numeroUtente + "\n");
            }

            System.out.println("Chiusura connessione.");
            socket.close();
        } catch (IOException e) {
            System.err.println("Errore durante l'esecuzione del client: " + e.getMessage());
        }
    }
}