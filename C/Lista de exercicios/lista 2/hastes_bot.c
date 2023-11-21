#include <stdio.h>
#include <limits.h>

#define MAX 100

int max(int a, int b) {
    return (a > b) ? a : b;
}

int corte_hastes_bottom_up(int preco[], int n) {
    int dp[MAX];

    dp[0] = 0;

    for (int i = 1; i <= n; i++) {
        int receita_maxima = INT_MIN;

        for (int j = 0; j < i; j++) {
            receita_maxima = max(receita_maxima, preco[j] + dp[i - j - 1]);
        }

        dp[i] = receita_maxima;
    }

    return dp[n];
}

int main() {
    int preco[] = {1, 5, 8, 9, 10, 17, 17, 20};
    int tamanho = sizeof(preco) / sizeof(preco[0]);

    int resultado = corte_hastes_bottom_up(preco, tamanho);

    printf("Receita máxima: %d\n", resultado);

    return 0;
}
