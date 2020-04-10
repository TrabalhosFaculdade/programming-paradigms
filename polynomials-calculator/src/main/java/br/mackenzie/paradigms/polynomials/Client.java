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

    public void printMenu(Scanner s) {
        System.out.println("1 - Calcular polinomio");
        System.out.println("2 -  Soma de dois polinomios");
        System.out.println("3 -  Multiplicacao de dois polinomios");
        System.out.println("4 -  Sair do programa");

        do {
            System.out.println("Escolha uma opcao entre 1 a 4");
            if(s.hasNextInt()) this.menuChoice = s.nextInt();
            else this.menuChoice = 0;
        } while (notValid());

        selectPolynomialFunction();
    }

    public void selectPolynomialFunction() {
        switch(this.menuChoice) {
            case 1:
                // Calcular polinômio
                Polynomial p = mount();
                Scanner s = new Scanner(System.in);
                System.out.println("Value of 'x': ");

                String value = s.nextLine();
                Double x;
                try {
                    x = Double.parseDouble(value);
                }
                catch (NumberFormatException e)
                {
                    x = 0D;
                }

                Double result = p.value(x);

                System.out.println(String.format("Result = %s\n", result));
                break;
            case 2:
                // Soma de dois polinômios
                Polynomial first = mount();
                Polynomial second = mount();
                System.out.println(String.format("(%s) + (%s)", first, second));

                first.add(second);
                System.out.println(String.format("Result = %s\n", first));
                break;
            case 3:
                //Multiplicação de dois polinômios
                Polynomial First = mount();
                Polynomial Second = mount();
                System.out.println(String.format("(%s) * (%s)", First, Second));

                First.multiplyBy(Second);
                System.out.println(String.format("Result = %s\n", First));
                break;
            case 4:
            default:
                //Sair do programa
                break;
        }
    }

    private Polynomial mount() {

        Scanner s = new Scanner(System.in);
        System.out.println("Grau do polinomio");

        String grau = s.nextLine();
        int degree;
        try {
            degree = Integer.parseInt(grau);
        }
        catch (NumberFormatException e)
        {
            degree = 0;
        }

        List<Double> aux = new ArrayList<Double>();
        for (int i = 0; i<degree+1; i++){
            Scanner c = new Scanner(System.in);
            System.out.println("Coeficiente para o grau " + i);
            String co = s.nextLine();
            Double Coeficient;
            try {
                Coeficient = Double.parseDouble(co);
            }
            catch (NumberFormatException e)
            {
                Coeficient = 0D;
            }
            aux.add(Coeficient);

        }
        Double[] Coeficients = new Double[ aux.size() ];
        aux.toArray( Coeficients );
        Polynomial poly1 = new Polynomial(degree, Arrays.asList(Coeficients));
        System.out.println(String.format("Polynomi = %s\n", poly1));

        return poly1;
    }

    public int getMenuChoice() {
        return this.menuChoice;
    }
}