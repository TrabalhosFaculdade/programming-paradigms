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

    private static void testCalculate(){

        Polynomial first = new Polynomial(2,Arrays.asList(2D, 3D, 2D));
        Double result = first.value(3D);

        System.out.println(String.format("Result = %s\n", result));
    }


}
