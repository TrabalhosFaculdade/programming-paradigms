package br.com.danieldddl.pontos;

public class ThreeDimensionalPoint extends Point {

    private int z;

    public ThreeDimensionalPoint(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

   @Override
    public double distanceTo(Point point) {

        if (point.numberOfDimensions() != 3 || !(point instanceof ThreeDimensionalPoint)) {
            throw new IllegalArgumentException(
                    "Attempt of comparing two dimensional point " +
                    "to a three dimensional one");
        }

        ThreeDimensionalPoint other = (ThreeDimensionalPoint) point;

       return Math.sqrt(
               Math.pow(getX() - other.getX(), 2) +
               Math.pow(getY() - other.getY(), 2) +
               Math.pow(getZ() - other.getZ(), 2));
    }

    @Override
    public int numberOfDimensions() {
        return 3;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ThreeDimensionalPoint that = (ThreeDimensionalPoint) o;

        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + z;
        return result;
    }
}
