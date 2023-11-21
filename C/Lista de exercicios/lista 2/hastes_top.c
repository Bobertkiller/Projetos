#include <stdio.h>
#include <limits.h>

#define MAX 100

int max(int a, int b) {
    return (a > b) ? a : b;
}

int dp[MAX];

int corte_hastes_top_down(int preco[], int n) {
    if (n <= 0) {
        return 0;
    }

    if (dp[n] != -1) {
        return dp[n];
    }

    int receita_maxima = INT_MIN;

    for (int i = 0; i < n; i++) {
        receita_maxima = max(receita_maxima, preco[i] + corte_hastes_top_down(preco, n - i - 1));
    }

    dp[n] = receita_maxima;
    return receita_maxima;
}

int main() {
    int preco[] = {1, 5, 8, 9, 10, 17, 17, 20};
    int tamanho = sizeof(preco) / sizeof(preco[0]);

    for (int i = 0; i < MAX; i++) {
        dp[i] = -1;
    }

    int resultado = corte_hastes_top_down(preco, tamanho);

    printf("Receita máxima: %d\n", resultado);

    return 0;
}
