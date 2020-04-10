package br.com.danieldddl.pontos;

import static java.util.Objects.requireNonNull;

public class Container {

    private static final int NON_AVAILABLE_POSITION = -1;

    private Point [] points;
    private int size;

    public Container (int size) {
        points = new Point[size];
        this.size = size;
    }

    public void add (Point point) {

        requireNonNull(point);

        for (int i = 0; i < size; i++) {
            if (points[i] == null) {
                points[i] = point;
                return;
            }
        }

        throw new IllegalStateException("Attempt of adding point to an already full container");
    }

    public void addLast (Point point) {

        requireNonNull(point);

        for (int i = size - 1; i >= 0; i--) {
            if (points[i] == null) {
                points[i] = point;
                return;
            }
        }

        throw new IllegalStateException("Attempt of adding point to an already full container");
    }

    public void add (Point point, int position) {

        requireNonNull(point);
        requireValidPosition(position);

        if (points[position] == null) {
            points[position] = point;
            return;
        }

        //position not available, checking for shift possibility
        int availablePosition = positionAvailableToTheRight(position);

        if (availablePosition == NON_AVAILABLE_POSITION) {
            //shift no possible
            throw new IllegalStateException("Attempt of adding point to an already full container");
        }

        shiftPointsToTheRight(position, availablePosition);
        points[position] = point;
    }

    public int positionOf (Point point) {

        requireNonNull(point);

        for (int i = 0; i < size; i++) {
            if (point.equals(points[i])) {
                return i;
            }
        }

        return NON_AVAILABLE_POSITION;
    }

    public Point remove (int position) {

        requireValidPosition(position);

        if (points[position] != null) {

            Point removed = points[position];
            points[position] = null;

            return removed;
        }

        return null;
    }

    public Point get (int position) {
        requireValidPosition(position);
        return points[position];
    }

    public int insertedPoints () {

        int count = 0;
        for (int i = 0; i < size; i++) {
            if (points[i] != null) count++;
        }

        return count;
    }

    /**
     * Return the index non occupied by a point;
     * NON_AVAILABLE_POSITION if all positions to the right are already occupied
     *
     * Assuming position passed as parameter already validated
     * */
    private int positionAvailableToTheRight(int position) {

        for (int i = position + 1; i < size; i++) {
            if (points[i] == null) {
                return i;
            }
        }

        return NON_AVAILABLE_POSITION;
    }

    /**
     * Assuming point in position "from" is the occupied position we want to make available
     * and "to" position the available one we want to get occupied
     * */
    private void shiftPointsToTheRight (int from, int to) {

        for (int i = to; i > from; i--) {
            points[i] = points[i - 1];
        }

        points[from] = null; //position freed
    }

    private void requireValidPosition (int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Operation attempt on invalid position");
        }
    }

}
