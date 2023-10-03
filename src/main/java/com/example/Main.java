package com.example;

public class Main {
    public static void main(String[] args) {
        String ip = "localhost";
        int porta = 3000;
        Client client = new Client();
        client.connetti(ip, porta);
        client.comunica();
    }
}