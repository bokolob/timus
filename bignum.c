#include "bignum.h"

long_number_t *new_number() {
    long_number_t *number =(long_number_t*) malloc(sizeof(long_number_t));
    number->count = 2;
    number->values = (uint64_t *) malloc(sizeof(uint64_t) * number->count);
    zero_number(number); 

    return number;
}

void zero_number(long_number_t *number) {
    memset(number->values, 0, number->count*sizeof(uint64_t));
}

void add_digits (long_number_t *number) {
    number->count *= 2;

    uint64_t * old = number->values;
    number->values = (uint64_t *) malloc(number->count*sizeof(uint64_t));
    memset(number->values, 0, number->count*sizeof(uint64_t));
    memcpy(number->values, old, number->count*sizeof(uint64_t)/2);
    free(old);
}

void add_long_number(long_number_t *to, long_number_t *what) {
    int posA = 0;
    int carry = 0;

    while (posA < what->count  || carry) {
        
        carry = 0;

        if (posA < what->count) {

            if (to->count <= posA) {
                add_digits(to);
            }

            *(to->values + posA ) += *( what->values + posA  );
        }

        if (*( to->values + posA ) >= BASE) {
            if (to->count == posA+1 ) {
                add_digits(to);
            }

            *( to->values + posA + 1 ) += *( to->values + posA ) / BASE;
            *( to->values + posA ) = *( to->values + posA ) % BASE;
            carry = 1;
        }

        posA++;
    }
}

void print_long_number(long_number_t * ln) {
    
    int offt = ln->count - 1 ;
    while(ln->values[offt] == 0 && offt > 0) 
        offt--;

    printf("%llu", ln->values[offt]);

    int posA = offt -1;
    while (posA >= 0) {

        printf("%09llu", *(ln->values + posA));

        posA--;
    }

}

/*
                abcde
                efghk
            ---------
                k(abcde)
               h(abcde)
              ....
            e(abcde)
*/

long_number_t  *multiply_long_numbers(long_number_t *first, long_number_t *second) {

    long_number_t *tmp = new_number();

    for (int i = 0; i < second->count; i++) {
        for (int j = 0; j < first->count; j++) {

            if (tmp->count <= j + i) {
                add_digits(tmp);
            }

            *(tmp->values + j + i) += *(second->values + i) * *(first->values + j);

            //printf("%lu - %d %d\n", *(tmp->values + j + k), i, j);

            if (*(tmp->values + j + i) >= BASE) {
                if (tmp->count <= j + i +1 ) {
                    add_digits(tmp);
                }

                *(tmp->values + j + i + 1) += *(tmp->values + j + i) / BASE;
                *(tmp->values + j + i) = *(tmp->values + j + i) % BASE;
            }
        }
    }
   
    //XXX normalize tmp caaried digits

    return tmp;
}
