#include <stdio.h>

#define MAX 100

int max(int a, int b) {
    return (a > b) ? a : b;
}

int mochila_bottom_up(int capacidade, int pesos[], int valores[], int n) {
    int dp[MAX][MAX];

    for (int i = 0; i <= n; i++) {
        for (int w = 0; w <= capacidade; w++) {
            // Caso base
            if (i == 0 || w == 0) {
                dp[i][w] = 0;
            } else if (pesos[i - 1] <= w) {
                // Se o peso do item é menor ou igual à capacidade, escolhe-se o máximo entre incluir e não incluir o item
                dp[i][w] = max(valores[i - 1] + dp[i - 1][w - pesos[i - 1]], dp[i - 1][w]);
            } else {
                // Se o peso do item é maior que a capacidade, não pode ser incluído
                dp[i][w] = dp[i - 1][w];
            }
        }
    }

    return dp[n][capacidade];
}

int main() {
    int valores[] = {60, 100, 120};
    int pesos[] = {10, 20, 30};
    int capacidade = 50;
    int n = sizeof(valores) / sizeof(valores[0]);

    int resultado = mochila_bottom_up(capacidade, pesos, valores, n);

    printf("Valor máximo da mochila: %d\n", resultado);

    return 0;
}
