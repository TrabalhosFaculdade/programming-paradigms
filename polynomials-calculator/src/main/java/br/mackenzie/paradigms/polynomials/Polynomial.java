package br.mackenzie.paradigms.polynomials;

import java.util.*;

public class Polynomial {

    private Deque<Term> terms;

    public Polynomial(Integer degree, List<Double> coefficients) {

        if (coefficients.size() != degree + 1) { //+1 because of the constant at the end

            //we might have a different numbers of terms than the polynomial degree,
            //but these values must be equal during the creation of the polynomial
            throw new IllegalArgumentException(
                    "Attempt of creating a polynomial " +
                    "with a different numbers of terms than its degree");
        }

        initializeTermsFromCoefficients(degree, coefficients);
    }

    public Double value (int x) {

        Double result = 0D;

        for (Term term : terms) {
            result += term.value(x);
        }

        return result;
    }

    private void initializeTermsFromCoefficients(Integer degree, List<Double> coefficients) {

        terms = new LinkedList<>();

        //using exponent as index on the coefficients list
        for (int exponent = degree; exponent >= 0; exponent--) {

            Double coefficient = coefficients.get(exponent);
            if (coefficient == 0) continue; //just ignoring, the value of the term would be 0

            Term term = new Term(coefficient, exponent);
            append(term);
        }
    }

    private void append (Term term) {

        if (!isValidForCurrentPolynomial(term)) {

            throw new IllegalArgumentException(
                    "Attempt of adding term with a " +
                    "higher exponent than last term added.");
        }

        terms.push(term);
    }

    public void add (Polynomial polynomial) {

        Objects.requireNonNull(polynomial);

        //TODO create a copy of the polynomials before proceeding

        Deque<Term> resultingTerms = new LinkedList<>();

        while (!terms.isEmpty() && !polynomial.terms.isEmpty()) {

            Optional<Term> currentPolynomialTerm = Optional.ofNullable(terms.peekFirst());
            Optional<Term> otherPolynomialTerm = Optional.ofNullable(polynomial.terms.peekFirst());

            if (currentPolynomialTerm.isPresent() && otherPolynomialTerm.isPresent()) {

                Term current = currentPolynomialTerm.get();
                Term other = otherPolynomialTerm.get();

                if (current.getExponent().equals(other.getExponent())) {
                    //equals, creating new terms with sum and adding it to new queue
                    current.add(other);
                    resultingTerms.push(current);

                    terms.removeFirst();
                    polynomial.terms.removeFirst();

                } else if (current.getExponent() > other.getExponent()) {
                    //the current term is bigger, and there is not
                    //match to it on the other polynomial terms
                    //adding it plainly to new queue
                    resultingTerms.push(current);
                    terms.removeFirst();

                } else {
                    //the same thing as above if, but with the other polynomial term
                    resultingTerms.push(other);
                    polynomial.terms.removeFirst();
                }

            } else if (otherPolynomialTerm.isEmpty() && currentPolynomialTerm.isPresent()) {

                Term currentTerm = currentPolynomialTerm.get();

                resultingTerms.push(currentTerm);
                terms.removeFirst();

            } else if (otherPolynomialTerm.isPresent()) { //currentPolynomialTerm is always empty in here

                Term otherTerm = otherPolynomialTerm.get();

                resultingTerms.push(otherTerm);
                polynomial.terms.removeFirst();
            }
        }

        this.terms = resultingTerms;
    }

    private boolean isValidForCurrentPolynomial (Term term) {

        //validating for term degree when adding to polynomial
        //current term degree cannot be higher than last one added
        //for example: 2x^3 + x^2, attempting adding x^3 is not
        //valid for current expression

        //peek will return null if empty queue
        Optional<Term> possibleLastTerm = Optional.ofNullable(terms.peek());
        if (possibleLastTerm.isPresent())  {
            Term lastTerm = possibleLastTerm.get();
            return lastTerm.getExponent() > term.getExponent();
        }

        //queue is empty, hence, any coefficient value will be acceptable
        return true;
    }
}
