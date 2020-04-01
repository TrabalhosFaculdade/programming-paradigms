package br.mackenzie.paradigms.polynomials;

import java.util.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Client client = new Client();
        do {
            client.printMenu(s);
        } while(client.getMenuChoice() != 4);

        //TODO just testing, to be removed when client is integrated
        testAddition();
        testMultiplication();
        s.close();
    }

    private static void testAddition() {

        Polynomial first = new Polynomial(1, Arrays.asList(1D, 1D));
        Polynomial second = new Polynomial(1, Arrays.asList(1D, 1D));
        System.out.println(String.format("(%s) + (%s)", first, second));

        first.add(second);
        System.out.println(String.format("Result = %s\n", first));
    }

    private static void testMultiplication() {

        Polynomial first = new Polynomial(1, Arrays.asList(1D, 1D));
        Polynomial second = new Polynomial(1, Arrays.asList(1D, 1D));
        System.out.println(String.format("(%s) * (%s)", first, second));

        first.multiplyBy(second);
        System.out.println(String.format("Result = %s\n", first));
    }


}
