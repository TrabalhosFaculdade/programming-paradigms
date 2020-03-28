package br.mackenzie.paradigms.polynomials;

import java.util.*;

public class Client {
    private int menuChoice;

    private boolean verifyMenuChoice() {
        boolean found = false;
        int[] choicesArray = new int[]{1, 2, 3, 4};

        for(int i = 0; i < choicesArray.length; i++) {
            if(choicesArray[i] == this.menuChoice) {
                found = true;
            }
        }
        return found;
    }

    public void printMenu() {
        Scanner s = new Scanner(System.in);

        System.out.println("1- Calcular polinomio");
        System.out.println("2-  Soma de dois polinomios");
        System.out.println("3-  Multiplicacao de dois polinomios");
        System.out.println("4-  Sair do programa");

        do {
            this.menuChoice = s.nextInt();
        } while (verifyMenuChoice());

        s.close();
    }

    public int getMenuChoice() {
        return this.menuChoice;
    }
}