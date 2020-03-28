package br.mackenzie.paradigms.polynomials;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    /**
     * Used when creating a copy of a Polynomial object
     */
    private Polynomial(Deque<Term> terms) {
        this.terms = terms;
    }

    public Double value(Double x) {

        Double result = 0D;

        for (Term term : terms) {
            result += term.value(x);
        }

        return result;
    }

    public void add(Polynomial polynomial) {

        Objects.requireNonNull(polynomial);

        //copy of polynomial on top of which we will operate because
        //we dont want to change our original argument
        Polynomial otherPolynomial = polynomial.copy();
        Deque<Term> resultingTerms = new LinkedList<>();

        while (!terms.isEmpty() && !otherPolynomial.terms.isEmpty()) {

            Optional<Term> currentPolynomialTerm = Optional.ofNullable(terms.peekLast());
            Optional<Term> otherPolynomialTerm = Optional.ofNullable(otherPolynomial.terms.peekLast());

            if (currentPolynomialTerm.isPresent() && otherPolynomialTerm.isPresent()) {

                Term current = currentPolynomialTerm.get();
                Term other = otherPolynomialTerm.get();

                if (current.getExponent().equals(other.getExponent())) {
                    //equals, creating new terms with sum and adding it to new queue
                    Term additionResult = current.add(other);
                    resultingTerms.addFirst(additionResult);

                    terms.removeLast();
                    otherPolynomial.terms.removeLast();

                } else if (current.getExponent() > other.getExponent()) {
                    //the current term is bigger, and there is not
                    //match to it on the other polynomial terms
                    //adding it plainly to new queue
                    resultingTerms.addFirst(current);
                    terms.removeLast();

                } else {
                    //the same thing as above if, but with the other polynomial term
                    resultingTerms.addFirst(other);
                    otherPolynomial.terms.removeLast();
                }

            } else if (!otherPolynomialTerm.isPresent() && currentPolynomialTerm.isPresent()) {

                Term currentTerm = currentPolynomialTerm.get();

                resultingTerms.addFirst(currentTerm);
                terms.removeLast();

            } else if (otherPolynomialTerm.isPresent()) { //currentPolynomialTerm is always empty in here

                Term otherTerm = otherPolynomialTerm.get();

                resultingTerms.addFirst(otherTerm);
                otherPolynomial.terms.removeLast();
            }
        }

        this.terms = resultingTerms;
    }

    public void multiplyBy(Polynomial polynomial) {

        Objects.requireNonNull(polynomial);

        Map<Integer, Term> multiplicationResult = new HashMap<>();

        for (Term currentTerm : terms) {

            for (Term otherTerm : polynomial.terms) {

                Term multiplicationResultTerm = currentTerm.multiply(otherTerm);

                Optional<Term> possibleTermWithExponent =
                        Optional.ofNullable(multiplicationResult.get(multiplicationResultTerm.getExponent()));

                if (possibleTermWithExponent.isPresent()) {

                    Term presentTermWithExponent = possibleTermWithExponent.get();
                    Term newResultingTerm = presentTermWithExponent.add(multiplicationResultTerm);

                    multiplicationResult.put(multiplicationResultTerm.getExponent(), newResultingTerm);

                } else {
                    multiplicationResult.put(multiplicationResultTerm.getExponent(), multiplicationResultTerm);
                }
            }
        }

        terms = new LinkedList<>(multiplicationResult.values());
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

    private void append(Term term) {

        if (!isValidForCurrentPolynomial(term)) {

            throw new IllegalArgumentException(
                    "Attempt of adding term with a " +
                            "higher exponent than last term added.");
        }

        terms.addFirst(term);
    }

    private boolean isValidForCurrentPolynomial(Term term) {

        //validating for term degree when adding to polynomial.
        //current term degree cannot be higher than last one added
        //for example: 2x^3 + x^2, attempting adding x^3 is not valid

        //peek will return null if empty queue
        Optional<Term> possibleLastTerm = Optional.ofNullable(terms.peek());
        if (possibleLastTerm.isPresent()) {
            Term lastTerm = possibleLastTerm.get();
            return lastTerm.getExponent() > term.getExponent();
        }

        //queue is empty, hence, any coefficient value will be acceptable
        return true;
    }

    private Polynomial copy() {

        Deque<Term> newTerms = new LinkedList<>();

        for (Term term : terms) {
            newTerms.add(term.copy());
        }

        return new Polynomial(newTerms);
    }

    @Override
    public String toString() {
        return terms.toString();
    }
}
