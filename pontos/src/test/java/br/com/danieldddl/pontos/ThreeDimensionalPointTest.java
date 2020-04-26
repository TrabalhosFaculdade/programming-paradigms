package br.com.danieldddl.pontos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeDimensionalPointTest {

    @Test
    void distanceThreeDimensionalPoints() {

        ThreeDimensionalPoint first = new ThreeDimensionalPoint(2,3,4);
        ThreeDimensionalPoint second = new ThreeDimensionalPoint(5,6,7);

        double expectedValue = Math.sqrt(
                        Math.pow(first.getX() - second.getX(), 2) +
                        Math.pow(first.getY() - second.getY(), 2) +
                        Math.pow(first.getZ() - second.getZ(), 2));

        assertEquals(expectedValue, first.distanceTo(second));

    }
}