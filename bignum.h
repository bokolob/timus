#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BASE (1000000000L)

typedef unsigned long long uint64_t;

typedef struct long_number {
    uint64_t *values;
    unsigned int count;
} long_number_t;

long_number_t  *multiply_long_numbers(long_number_t *first, long_number_t *second);
long_number_t *new_number();
void add_long_number(long_number_t *to, long_number_t *what);
void print_long_number(long_number_t * ln);
void zero_number(long_number_t *number);

