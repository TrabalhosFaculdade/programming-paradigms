package br.mackenzie.paradigms.polynomials;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class Main {

    private static final NumberFormat SIGNED_FORMAT = new DecimalFormat("+#;-#");
    private static final NumberFormat UNSIGNED_FORMAT = new DecimalFormat("#");

    public static void main(String[] args) {

        Polynomial first = new Polynomial(1, Arrays.asList(2d, 2d));
        Polynomial second = new Polynomial(3, Arrays.asList(-1d, -1d, 0d, 1d));

        first.add(second);

        Client client = new Client();
        do {
            client.printMenu();
        } while(client.getMenuChoice() != 4);

    }

}
