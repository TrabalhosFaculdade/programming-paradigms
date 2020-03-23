package br.mackenzie.paradigms.polynomials;

import java.util.Objects;

public class Term {

    private Double coefficient;
    private Integer exponent;

    public Term(Double coefficient, Integer exponent) {

        this.coefficient = Objects.requireNonNull(coefficient);
        this.exponent = Objects.requireNonNull(exponent);

        if (exponent < 0) {
            throw new IllegalArgumentException("Terms cannot have a negative exponent");
        }
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public Integer getExponent() {
        return exponent;
    }

    public Double value (Integer variableValue) {
        return coefficient * Math.pow(variableValue, exponent);
    }

    public void add (Term other) {

        if (other.getExponent().equals(this.getExponent())) {
            throw new IllegalArgumentException("Attempt of adding two terms with different exponents");
        }

        this.coefficient += other.getCoefficient();
    }

    public Term copy() {
        return new Term(coefficient, exponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Objects.equals(coefficient, term.coefficient) &&
                Objects.equals(exponent, term.exponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficient, exponent);
    }
}
