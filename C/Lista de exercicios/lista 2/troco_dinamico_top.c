#include <stdio.h>
#include <limits.h>

int min_moedas_recursivo(int troco, int moedas[], int n) {
    // Caso base
    if (troco == 0) {
        return 0;
    }

    int resultado = INT_MAX;

    // Tenta cada moeda
    for (int i = 0; i < n; i++) {
        if (moedas[i] <= troco) {
            int subproblema = min_moedas_recursivo(troco - moedas[i], moedas, n);
            if (subproblema != INT_MAX && subproblema + 1 < resultado) {
                resultado = subproblema + 1;
            }
        }
    }

    return resultado;
}

int main() {
    int moedas[] = {1, 3, 4};
    int n = sizeof(moedas) / sizeof(moedas[0]);
    int troco = 6;

    int resultado = min_moedas_recursivo(troco, moedas, n);

    printf("Quantidade mínima de moedas: %d\n", resultado);

    return 0;
}

