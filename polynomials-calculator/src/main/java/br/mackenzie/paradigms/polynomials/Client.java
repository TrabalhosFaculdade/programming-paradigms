package br.mackenzie.paradigms.polynomials;

import java.util.*;

public class Client {
    private int menuChoice = 0;

    private boolean notValid() {
        int[] valids = new int[]{1, 2, 3, 4};

        for(int i = 0; i < valids.length; i++) {
            if(valids[i] == this.menuChoice) {
                return false;
            }
        }
        return true;
    }

    public void printMenu() {
        Scanner s = new Scanner(System.in);

        System.out.println("1- Calcular polinomio");
        System.out.println("2-  Soma de dois polinomios");
        System.out.println("3-  Multiplicacao de dois polinomios");
        System.out.println("4-  Sair do programa");

        do {
            System.out.println("Escolha uma opcao entre 1 a 4");
            if(s.hasNextInt()) this.menuChoice = s.nextInt();
            else this.menuChoice = 0;
        } while (notValid());

        s.close();
    }

    public void selectPolynomialFunction() {
        switch(this.menuChoice) {
            case 1:
                // Calcular polinômio
                break;
            case 2:
                // Soma de dois polinômios
                break;
            case 3:
                //Multiplicação de dois polinômios
                break;
            case 4:
            default:
                //Sair do programa
                break;
        }
    }

    public int getMenuChoice() {
        return this.menuChoice;
    }
}