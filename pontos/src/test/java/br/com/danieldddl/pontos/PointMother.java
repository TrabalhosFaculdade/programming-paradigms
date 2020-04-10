package br.com.danieldddl.pontos;

import java.util.Random;

/**
 * https://martinfowler.com/bliki/ObjectMother.html
 * */
public class PointMother {

    private static Random random = new Random();

    public static Point create () {
        return new Point(random.nextInt(), random.nextInt());
    }

}
