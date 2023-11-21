#include <stdio.h>
#include <limits.h>

int min_moedas_bottom_up(int troco, int moedas[], int n) {
    int dp[troco + 1];

    dp[0] = 0;

    for (int i = 1; i <= troco; i++) {
        dp[i] = INT_MAX;
    }

    for (int i = 1; i <= troco; i++) {
        for (int j = 0; j < n; j++) {
            if (moedas[j] <= i) {
                int subproblema = dp[i - moedas[j]];
                if (subproblema != INT_MAX && subproblema + 1 < dp[i]) {
                    dp[i] = subproblema + 1;
                }
            }
        }
    }

    return dp[troco];
}

int main() {
    int moedas[] = {1, 3, 4};
    int n = sizeof(moedas) / sizeof(moedas[0]);
    int troco = 6;

    int resultado = min_moedas_bottom_up(troco, moedas, n);

    printf("Quantidade mínima de moedas: %d\n", resultado);

    return 0;
}
