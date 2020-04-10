package br.com.danieldddl.pontos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void add() {

        Point point = PointMother.create();

        Container container = new Container(3);
        container.add(point);

        assertEquals(1, container.insertedPoints());
        assertEquals(point, container.get(0));
    }

    @Test
    void addLast() {

        Point point = PointMother.create();

        Container container = new Container(3);
        container.addLast(point);

        assertEquals(1, container.insertedPoints());
        assertEquals(point, container.get(2));
    }

    @Test
    void testMultipleInsertions () {

        Point first = PointMother.create();
        Point second = PointMother.create();

        Container container = new Container(3);
        container.add(first);
        container.add(second);

        assertEquals(2, container.insertedPoints());
        assertEquals(first, container.get(0));
        assertEquals(second, container.get(1));
    }

    @Test
    void addPosition() {

        Point point = PointMother.create();

        Container container = new Container(3);
        container.add(point, 1);

        assertEquals(1, container.insertedPoints());
        assertEquals(point, container.get(1));
    }


    @Test
    void testAddWithShift () {

        Point first = PointMother.create();
        Point second = PointMother.create();

        Container container = new Container(3);
        container.add(first);
        container.add(second, 0);

        assertEquals(2, container.insertedPoints());
        assertEquals(first, container.get(1));
        assertEquals(second, container.get(0));
    }

    @Test
    void testAddWithShiftAndPositionNotAvailable () {

        Point point = PointMother.create();

        Container container = new Container(1);
        container.add(point);

        try {
            container.add(point, 0);
            fail("Operation executed, but container was already full");
        } catch (IllegalStateException e) {
            assertTrue(true);
        }

    }

    @Test
    void positionOf() {

        Point point = PointMother.create();
        Point nonInserted = PointMother.create();

        Container container = new Container(3);
        container.add(point);

        assertEquals(0, container.positionOf(point));
        assertEquals(-1, container.positionOf(nonInserted));
    }


    @Test
    void remove() {

        Point point = PointMother.create();

        Container container = new Container(3);
        container.add(point); //we already know this changes the number of points

        Point removedPoint = container.remove(0);

        assertNull(container.get(0));
        assertEquals(0, container.insertedPoints());
        assertEquals(point, removedPoint);
    }
}