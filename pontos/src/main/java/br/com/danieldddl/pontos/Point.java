package br.com.danieldddl.pontos;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int numberOfDimensions () {
        return 2; // to be overridden if necessary
    }

    public double distanceTo (Point point) {

        if (point.numberOfDimensions() != 2) {
            throw new UnsupportedOperationException(
                    "Attempt of comparing a point in a " +
                    "different dimension to a 2d point");
        }

        return Math.sqrt(
                Math.pow(getX() - point.getX(), 2) +
                Math.pow(getY() - point.getY(), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
