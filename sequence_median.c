#include <stdio.h>
#include <stdlib.h>

typedef struct Heap
{
    unsigned int *data;
    int maxElement;
    int size;
} Heap;

void heapify(Heap *heap, int index); 
void print_heap(Heap *heap);
void swap(Heap *heap, int a, int b);

void init_heap(Heap *heap, int size) 
{
    heap->maxElement = 0;
    heap->data = malloc(size * sizeof(unsigned int));
    heap->size = size;
}

unsigned int peek_max(Heap *heap) 
{
    return heap->data[0];
}

unsigned int get_max(Heap *heap) 
{
    unsigned int value = heap->data[0];

    heap->data[0] = heap->data[heap->maxElement - 1];
    heap->maxElement--;

    int index = 0;
    while (index < heap->maxElement) {
        int left_child = index * 2 + 1;
        int right_child = index * 2 + 2;
        
        int max_el = right_child;

        if (right_child > heap->maxElement || heap->data[left_child] > heap->data[right_child])
            max_el = left_child;
        
        if (max_el >= heap->maxElement || heap->data[max_el] < heap->data[index])
            break;

        swap(heap, index, max_el);
        index = max_el;
    }
    
    return value;
}

void print_heap(Heap *heap)
{
    for (int i = 0; i < heap->maxElement; i++) {
        printf("%u ", heap->data[i]);
    }

    puts("\n");
}

void add_to_heap(Heap *heap, unsigned int el)
{
    if (heap->maxElement == heap->size) {
        perror("Heap overflow!");
        exit(1);
    }

    heap->data[heap->maxElement] = el;
    heapify(heap, heap->maxElement);
    heap->maxElement++;
}

void swap(Heap *heap, int a, int b) {
    int tmp = heap->data[a];
    heap->data[a] = heap->data[b];
    heap->data[b] = tmp;
}

void heapify(Heap *heap, int index) 
{
    while (index != 0) {
        int root = (index - 1) / 2;

        if (heap->data[root] < heap->data[index]) {
            swap(heap, root, index);
            index = root;
        }
        else {
            break;
        }
    }
}

int main() 
{
    int n;
    scanf("%d", &n);

    Heap heap;
    init_heap(&heap, n/2 + 1);
    
    for (int i = 0; i < n; i++) {
        unsigned int v;
        scanf("%u", &v);
        
        if (heap.maxElement < heap.size) {
            add_to_heap(&heap, v);
//            printf("Add: %u\n", v);
        }
        else {
            if (peek_max(&heap) > v) {
                get_max(&heap);
                add_to_heap(&heap, v);
//                printf("Push: %u\n", v);
            }
            else {
//                 printf("Skip: %u\n", v);
            }
        }
    }

    if (n % 2 == 1) {
        printf("%u\n", get_max(&heap) );
    }
    else {
        unsigned long long sum = get_max(&heap) + get_max(&heap);
        //printf("sum: %llu\n", sum );

        printf("%.1f\n",((double)sum)/2);
    }
}


