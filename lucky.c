#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bignum.h"

int main(int argc, char** argv) 
{
    int digits;
    int sum;

    scanf("%d%d", &digits, &sum);

    if (sum % 2 != 0) {
       printf("0\n");
       return 0;
    }

    sum = sum/2;

    long_number_t  *cache[digits+1][sum+1];

    for (int i = 0; i < digits+1; i++) {
        for (int j = 0; j < sum+1; j++) {
            cache[i][j] = new_number();
        }
    }

        
    *(cache[0][0]->values) = 1;

    for (int i = 1; i <= sum; i++) {
        *(cache[0][i]->values) = 0;
    }

    for (int i = 1; i <= digits; i++) {
        for (int j = 0; j <= sum; j++) {
            for (int k = j; k>=0 && k > j - 10; k--) {
                add_long_number(cache[i][j], cache[i-1][k]);
            }
        }
    }

    print_long_number(multiply_long_numbers(cache[digits][sum],cache[digits][sum]));
    puts("");

    return 0;
}

