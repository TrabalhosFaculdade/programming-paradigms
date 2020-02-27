#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <assert.h>

#include "fraction.h"

#define boolean_format "true" : "false"

void test_bool(bool expected, bool result)
{
    printf("Testing: %s == %s\n", 
        expected ? boolean_format, 
        result ? boolean_format);

    if (expected != result)
    {
        fprintf(stderr, "Expected: %d, got %d\n", expected, result);
        exit(EXIT_FAILURE);
    }
}

void test(fraction result, fraction expected)
{
    printf("Testing: %d/%d == %d/%d\n",
           result.numerator, result.denominator,
           expected.numerator, expected.denominator);

    if (expected.numerator != result.numerator || expected.denominator != result.denominator)
    {
        fprintf(stderr,
                "Expected: %d/%d, got %d/%d\n",
                expected.numerator, expected.denominator,
                result.numerator, result.denominator);

        exit(EXIT_FAILURE);
    }
}

void additions()
{
    //first
    fraction a = create(1, 2);
    fraction b = create(2, 3);
    fraction first_actual_result = addition(a, b);
    fraction first_expected_result = create(7, 6);
    test(first_actual_result, first_expected_result);

    //second
    fraction c = create(-2, 2);
    fraction d = create(1, 1);
    fraction second_actual_result = addition(c, d);
    fraction second_expected_result = create(0, 2);
    test(second_actual_result, second_expected_result);
}

void multiplications()
{
    //first
    fraction a = create(2, 3);
    fraction b = create(3, 2);
    fraction first_actual_result = multiplication(a, b);
    fraction first_expected_result = create(6, 6);
    test(first_actual_result, first_expected_result);

    //second
    fraction c = create(-2, -4);
    fraction d = create(1, 1);
    fraction second_actual_result = multiplication(c, d);
    fraction second_expected_result = create(-2, -4);
    test(second_actual_result, second_expected_result);
}

void divisions()
{

    //first
    fraction a = create(1, 2);
    fraction b = create(2, 3);
    fraction first_actual_result = division(a, b);
    fraction first_expected_result = create(3, 4);
    test(first_actual_result, first_expected_result);

    //second
    fraction c = create(4, 4);
    fraction d = create(2, 2);
    fraction second_actual_result = division(c, d);
    fraction second_expected_result = create(8, 8);
    test(second_actual_result, second_expected_result);
}

void equalities()
{
    //first
    fraction a = create(1, 2);
    fraction b = create(2, 4);
    bool first_actual_result = equals(a, b);
    test_bool(first_actual_result, true);

    //second
    fraction c = create(1, 2);
    fraction d = create(2, 3);
    bool second_actual_result = equals(c, d);
    test_bool(second_actual_result, false);
}

int main()
{
    additions();
    multiplications();
    divisions();
    equalities();

    printf("All functions successfully run\n");
    exit(EXIT_SUCCESS);
}