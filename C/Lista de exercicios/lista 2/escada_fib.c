#include <stdio.h>

// Função para calcular o número de maneiras de subir as escadas
int subir_escadas(int n) {
    if (n <= 1) {
        return 1;
    }

    int maneiras[n + 1];
    maneiras[0] = 1;
    maneiras[1] = 1;

    for (int i = 2; i <= n; i++) {
        maneiras[i] = maneiras[i - 1] + maneiras[i - 2];
    }

    return maneiras[n];
}

int main() {
    int n;

    // Leitura do valor de n
    printf("Digite o número de degraus (1 <= n <= 45): ");
    scanf("%d", &n);

    // Verifica se o valor de n está dentro da faixa permitida
    if (n < 1 || n > 45) {
        printf("Restrição: 1 <= n <= 45\n");
        return 1;
    }

    // Calcula o número de maneiras de subir as escadas
    int resultado = subir_escadas(n);

    // Exibe o resultado
    printf("Existem %d maneiras distintas de subir até o topo.\n", resultado);

    return 0;
}
