//complexidade O(n log n)
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int index;
    int size;
} File;

// Função de comparação para ordenação em ordem decrescente
int compare(const void *a, const void *b) {
    return ((File *)b)->size - ((File *)a)->size;
}

// Algoritmo guloso para seleção de arquivos
void selectFiles(File files[], int n, int capacity) {
    // Ordena os arquivos em ordem decrescente de tamanho
    qsort(files, n, sizeof(File), compare);

    // Inicializa o espaço restante no pendrive
    int remainingCapacity = capacity;

    // Inicializa o índice de arquivos selecionados
    int selectedFiles[n];
    int numSelected = 0;

    // Seleciona os arquivos enquanto houver espaço no pendrive
    for (int i = 0; i < n && files[i].size <= remainingCapacity; i++) {
        selectedFiles[numSelected++] = files[i].index;
        remainingCapacity -= files[i].size;
    }

    // Imprime os arquivos selecionados
    printf("Arquivos Selecionados: ");
    for (int i = 0; i < numSelected; i++) {
        printf("%d ", selectedFiles[i]);
    }
    printf("\n");
}

int main() {
    int n = 5;  // Número de arquivos
    int c = 10; // Capacidade do pendrive

    // Array de arquivos
    File files[] = {{1, 4}, {2, 5}, {3, 8}, {4, 3}, {5, 6}};

    // Chama a função para selecionar os arquivos
    selectFiles(files, n, c);

    return 0;
}
