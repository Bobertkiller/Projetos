#include <stdio.h>

// Função para encontrar o máximo entre dois inteiros
int max(int a, int b) {
    return (a > b) ? a : b;
}

// Função para encontrar o tamanho da maior subsequência crescente
int maior_subsequencia_crescente(int array[], int tamanho) {
    if (tamanho == 0) {
        return 0;
    }

    int dp[tamanho];
    int max_tamanho = 1;

    // Inicializa o array dp com 1, pois a subsequência com um único elemento sempre é crescente
    for (int i = 0; i < tamanho; i++) {
        dp[i] = 1;
    }

    // Calcula o tamanho da maior subsequência crescente
    for (int i = 1; i < tamanho; i++) {
        for (int j = 0; j < i; j++) {
            if (array[i] > array[j]) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        max_tamanho = max(max_tamanho, dp[i]);
    }

    return max_tamanho;
}

int main() {
    int array[] = {10, 22, 9, 33, 21, 50, 41, 60, 80};
    int tamanho = sizeof(array) / sizeof(array[0]);

    int resultado = maior_subsequencia_crescente(array, tamanho);

    printf("O tamanho da maior subsequência crescente é: %d\n", resultado);

    return 0;
}
