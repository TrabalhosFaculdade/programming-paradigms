#include <stdlib.h>
#include <stdbool.h>

#include "fraction.h"

fraction create(int numerator, int denominator)
{
    if (denominator == 0)
    {
        exit(EXIT_FAILURE);
    }

    fraction result = {
        .numerator = numerator,
        .denominator = denominator};

    return result;
}

fraction addition(fraction a, fraction b)
{
    //(a/b)+(c/d)=((a.d+c.b)/b.d)

    int resulting_numerator = a.numerator * b.denominator + b.numerator * a.denominator;
    int resulting_denominator = a.denominator * b.denominator;

    return create(resulting_numerator, resulting_denominator);
}

fraction multiplication(fraction a, fraction b)
{
    //(a/b)*(c/d)=((a*c)/(b*d))
    int resulting_numerator = a.numerator * b.numerator;
    int resulting_denominator = a.denominator * b.denominator;

    return create(resulting_numerator, resulting_denominator);
}

fraction division(fraction a, fraction b)
{
    //(a/b)/(c/d)=(a/b)*(d/c)=(a*d)/(b*c)
    fraction inverted_b = create(b.denominator, b.numerator);
    return multiplication(a, inverted_b);
}

bool equals(fraction a, fraction b)
{
    //(a/b)==(c/d) if a*d==b*c
    return a.numerator * b.denominator == b.numerator * a.denominator;
}
