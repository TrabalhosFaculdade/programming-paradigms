package br.mackenzie.paradigms.polynomials;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Client client = new Client();
        do {
            client.printMenu();
        } while(client.getMenuChoice() != 4);

        //TODO just testing, to be removed when client is integrated
        testAddition();
        testMultiplication();
        testCalculate();

    }

    private static void testAddition() {

        Polynomial first = new Polynomial(1, Arrays.asList(3D, 3D));
        Polynomial second = new Polynomial(1, Arrays.asList(-5D, 4D));
        System.out.println(String.format("(%s) + (%s)", first, second));

        first.add(second);
        System.out.println(String.format("Result = %s\n", first));
    }

    private static void testMultiplication() {

        Polynomial first = new Polynomial(1, Arrays.asList(2D, 3D));
        Polynomial second = new Polynomial(1, Arrays.asList(-4D, 4D));
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
