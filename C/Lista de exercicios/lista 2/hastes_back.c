#include <stdio.h>
#include <limits.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int corte_hastes_recursivo(int preco[], int n) {
    if (n <= 0) {
        return 0;
    }

    int receita_maxima = INT_MIN;

    for (int i = 0; i < n; i++) {
        receita_maxima = max(receita_maxima, preco[i] + corte_hastes_recursivo(preco, n - i - 1));
    }

    return receita_maxima;
}

int main() {
    int preco[] = {1, 5, 8, 9, 10, 17, 17, 20};
    int tamanho = sizeof(preco) / sizeof(preco[0]);

    int resultado = corte_hastes_recursivo(preco, tamanho);

    printf("Receita máxima: %d\n", resultado);

    return 0;
}
