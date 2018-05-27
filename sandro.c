#include <stdio.h>
#include <string.h>
#include <ctype.h>

int cost(char a, char b) {
    if (a == b) {
        return 0;
    }
    else if (tolower(a) == tolower(b)) {
        return 5;
    }
    else {
        if (islower(a) != islower(b)) {
            return 10;
        }
        else {
            return 5;
        }
    }
}

int main() { 
    char input[201];
    char sandro[] = {'S','a','n','d','r','o'};

    scanf("%s", input);
    
    int dp[6][200];
    
    memset(dp,0,6*200);

    int len = 0;

    for (int j = 0; j < 200; j++) {
        if (input[j] == 0) {
            break;
        }

        len++;
        
        dp[0][j] = cost(input[j], 'S');
    }

    for (int i = 1; i < 6; i++) {
        for (int j = i; j < len; j++) {
            dp[i][j]=dp[i-1][j-1] + cost(input[j], sandro[i]);
        }
    }

    int min = 65535;

    for (int j = 5; j < len; j++) {
        if(dp[5][j] < min) 
            min = dp[5][j];
    }
    
    printf("%d\n", min);
}


