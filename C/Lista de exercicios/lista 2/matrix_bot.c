#include <stdio.h>
#include <limits.h>

#define MAX 100

int min(int a, int b) {
    return (a < b) ? a : b;
}

int parentizacao_otima_bottom_up(int p[], int n) {
    int dp[MAX][MAX];

    for (int i = 1; i <= n; i++) {
        dp[i][i] = 0;
    }

    for (int L = 2; L <= n; L++) {
        for (int i = 1; i <= n - L + 1; i++) {
            int j = i + L - 1;
            dp[i][j] = INT_MAX;

            for (int k = i; k < j; k++) {
                int operacoes = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                dp[i][j] = min(dp[i][j], operacoes);
            }
        }
    }

    return dp[1][n];
}

int main() {
    int n = 4;
    int p[] = {200, 2, 30, 20, 5};

    int resultado = parentizacao_otima_bottom_up(p, n);

    printf("Número mínimo de operações: %d\n", resultado);

    return 0;
}
