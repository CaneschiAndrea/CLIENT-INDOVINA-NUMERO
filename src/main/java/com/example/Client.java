package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    Socket miosocket;
    Scanner input;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(String nomeServer, int portaServer) {
        System.out.println("CLIENT in esecuzione ...");
        try {
            input = new Scanner(System.in);
            miosocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Qualcosa è andato storto, chiusura client...");
            System.exit(1);
        }
        return miosocket;
    }

    public void comunica() {
        try {
            System.out.println("Benvenuto al gioco di indovinare il numero!");

            while (true) {
                stringaRicevutaDalServer = inDalServer.readLine();
                System.out.println(stringaRicevutaDalServer);

                if (stringaRicevutaDalServer.contains("Congratulazioni")) {
                    break;
                }

                System.out.print("Inserisci un numero: ");
                stringaUtente = input.next();
                outVersoServer.writeBytes(stringaUtente + '\n');
            }

            System.out.println("CLIENT: Termina il gioco e chiude la connessione");
            miosocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }
}