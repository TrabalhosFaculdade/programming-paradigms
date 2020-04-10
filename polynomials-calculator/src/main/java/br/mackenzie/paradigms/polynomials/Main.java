package br.mackenzie.paradigms.polynomials;

import java.util.Scanner;

/**
 * Entrega de trabalho - Calculadora de polinômios
 *
 * Nós,
 *
 * Bárbara Este Fernandez
 *
 * Daniel Dias de Lima, e
 *
 * Luan Rocha Damato,
 *
 * declaramos que
 *
 * todas as respostas são fruto de nosso próprio trabalho,
 * não copiamos respostas de colegas externos à equipe,
 * não disponibilizamos nossas respostas para colegas externos à equipe e
 * não realizamos quaisquer outras atividades desonestas para nos beneficiar ou prejudicar outros.
 * */
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
