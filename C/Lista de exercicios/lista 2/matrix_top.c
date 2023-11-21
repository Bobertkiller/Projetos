#include <stdio.h>
#include <limits.h>

#define MAX 100

int min(int a, int b) {
    return (a < b) ? a : b;
}

int dp[MAX][MAX];

int parentizacao_otima_top_down(int p[], int i, int j) {
    if (i == j) {
        return 0;
    }

    if (dp[i][j] != -1) {
        return dp[i][j];
    }

    int min_operacoes = INT_MAX;

    for (int k = i; k < j; k++) {
        int operacoes = parentizacao_otima_top_down(p, i, k) +
                        parentizacao_otima_top_down(p, k + 1, j) +
                        p[i - 1] * p[k] * p[j];

        min_operacoes = min(min_operacoes, operacoes);
    }

    dp[i][j] = min_operacoes;
    return min_operacoes;
}

int main() {
    int n = 4;
    int p[] = {200, 2, 30, 20, 5};

    for (int i = 0; i < MAX; i++) {
        for (int j = 0; j < MAX; j++) {
            dp[i][j] = -1;
        }
    }

    int resultado = parentizacao_otima_top_down(p, 1, n);

    printf("Número mínimo de operações: %d\n", resultado);

    return 0;
}
