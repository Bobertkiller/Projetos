#include <stdio.h>
#include <limits.h>

int min(int a, int b) {
    return (a < b) ? a : b;
}

int parentizacao_otima_recursiva(int p[], int i, int j) {
    if (i == j) {
        return 0;
    }

    int min_operacoes = INT_MAX;

    for (int k = i; k < j; k++) {
        int operacoes = parentizacao_otima_recursiva(p, i, k) +
                        parentizacao_otima_recursiva(p, k + 1, j) +
                        p[i - 1] * p[k] * p[j];

        min_operacoes = min(min_operacoes, operacoes);
    }

    return min_operacoes;
}

int main() {
    int n = 4;
    int p[] = {200, 2, 30, 20, 5};

    int resultado = parentizacao_otima_recursiva(p, 1, n);

    printf("Número mínimo de operações: %d\n", resultado);

    return 0;
}
