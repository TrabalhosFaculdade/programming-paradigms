#ifndef FRACTION_H
#define FRACTION_H

typedef struct
{
    int numerator;
    int denominator;
} fraction;

fraction create(int numerator, int denominator);
fraction addition(fraction a, fraction b);
fraction multiplication(fraction a, fraction b);
fraction division(fraction a, fraction b);
bool equals(fraction a, fraction b);

#endif