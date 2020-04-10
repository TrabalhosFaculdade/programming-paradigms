package br.mackenzie.paradigms.polynomials;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Client client = new Client();
        do {
            client.printMenu(s);
        } while(client.getMenuChoice() != 4);

        s.close();
    }
}
