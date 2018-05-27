#include <stdio.h>
#include <string.h>

int query(int *tree, int id) {
    int result = 0;
    while (id > 0) {
        result += tree[id];
        /*
         * To query an index, we need all indices that were not included in
         * previous intervals of form [i - 2 ^ r + 1, i]. That is, we need
         * to find the next lowest number that does not have the lowest bit
         * in i set. (id & -id) represents the lowest bit in id; by
         * subtracting this we reach the next number we need to query.
         */
        id -= (id & -id);
    }

    return result;
}

void update(int *tree, int size, int id) {
    while (id <= size) {
        tree[id]++;
        /*
         * To update an index, we need to update all indices that subsume
         * the current range [i - 2 ^ r + 1, i]. That is, we need to find
         * the next highest number that has the lowest bit of i unset.
         */
        id += (id & -id);
    }
}

int main() {
        int total;
        scanf("%d", &total);
        
        int size = 32002;
        int tree[32002];
        int byLevels[total];

        memset(tree, 0, sizeof(tree));
        memset(byLevels, 0, sizeof(byLevels));

        int cp = total;
        
        while(cp-- > 0) {
            int x,y;
            scanf("%d%d", &x, &y);
            x++;

            byLevels[query(tree, x)]++;
            update(tree, size, x);
        }

        for (int i = 0; i < total; i++) {
            printf("%d\n", byLevels[i]);
        }

        return 0;
}
