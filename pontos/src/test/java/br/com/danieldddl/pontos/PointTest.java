package br.com.danieldddl.pontos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    public void testDistanceTwoDimensions () {

        Point first = new Point (4,3);
        Point second = new Point(3,-2);

        double expectedResult =
                Math.sqrt(Math.pow(first.getX() - second.getX(), 2) +
                Math.pow(first.getY() - second.getY(), 2));

        assertEquals(expectedResult, first.distanceTo(second));
    }

}