#include "bignum.h"

int main(int argc, char** argv) 
{
    int total;
    int max;

    scanf("%d%d", &total, &max);
    
    long_number_t *cache[2][max+1];

    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < max+1; j++) {
            cache[i][j] = new_number();
        }
    }

    for (int i  = 0; i <= max; i++) {
        *(cache[0][i]->values) = 1;
    }
    
    int prev = 0;

    for ( int len = 1; len <= total; len++) {
        int cur = (prev + 1) % 2;

        for (int lemons = 0; lemons <= max; lemons++) {
            zero_number(cache[cur][lemons]);
        
            if (lemons < max) {
                add_long_number(cache[cur][lemons], cache[prev][lemons +1]);
            }

            add_long_number(cache[cur][lemons],  cache[prev][0]);
        }

        prev = (prev +1) % 2;
    }

    print_long_number(cache[prev][0]);
}

long_number_t *kbanacci(int n, int k) 
{
    
    long_number_t *cache = (long_number_t *) malloc(sizeof(long_number_t*)*($k+1));
    
    long_number_t *two = new_number;
    two->values[0] = 2;
    
    cache[0] = new_number();
    *(cache[0]->values)=2;

    for (int i = 1; i <= k ; i++) {
        *(cache + i) =  multiply_long_numbers((cache+i-1), two);
    }

    for (int i = 0; i < n; i++) {
        my $x = $cache[$k] - $cache[0] + $cache[$k];
        
        for (my $j = 1; $j <= $k; $j++) {
            $cache[$j - 1] = $cache[$j];
        }

        $cache[$k] = $x;
    }
    
    return \@cache;
}
