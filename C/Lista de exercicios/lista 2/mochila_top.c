#include <stdio.h>

#define MAX 100

int max(int a, int b) {
    return (a > b) ? a : b;
}

int dp[MAX][MAX];

int mochila_top_down(int capacidade, int pesos[], int valores[], int n) {
    // Caso base
    if (n == 0 || capacidade == 0) {
        return 0;
    }

    // Se o valor já foi calculado, retorná-lo
    if (dp[n][capacidade] != -1) {
        return dp[n][capacidade];
    }

    // Se o peso do item é maior que a capacidade, não pode ser incluído
    if (pesos[n - 1] > capacidade) {
        return mochila_top_down(capacidade, pesos, valores, n - 1);
    }

    // Retornar o máximo entre incluir e não incluir o item
    dp[n][capacidade] = max(valores[n - 1] + mochila_top_down(capacidade - pesos[n - 1], pesos, valores, n - 1),
                            mochila_top_down(capacidade, pesos, valores, n - 1));

    return dp[n][capacidade];
}

int main() {
    int valores[] = {60, 100, 120};
    int pesos[] = {10, 20, 30};
    int capacidade = 50;
    int n = sizeof(valores) / sizeof(valores[0]);

    for (int i = 0; i < MAX; i++) {
        for (int j = 0; j < MAX; j++) {
            dp[i][j] = -1;
        }
    }

    int resultado = mochila_top_down(capacidade, pesos, valores, n);

    printf("Valor máximo da mochila: %d\n", resultado);

    return 0;
}
