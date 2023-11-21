#include <stdio.h>

int minParadas(int distancias[], int n, int capacidade) {
    int paradas = 0;
    int atual = 0;

    while (atual < n - 1) {
        int proximo = atual;

        // Enquanto puder chegar ao próximo posto
        while (proximo < n - 1 && distancias[proximo + 1] - distancias[atual] <= capacidade) {
            proximo++;
        }

        // Se o próximo posto não é o último, abastecemos
        if (proximo != atual) {
            paradas++;
        }

        // Atualizamos a posição atual para o próximo posto alcançado
        atual = proximo;
    }

    return paradas;
}

int main() {
    int distancias[] = {150, 200, 250, 300, 400}; // Distâncias dos postos
    int n = sizeof(distancias) / sizeof(distancias[0]);
    int capacidade = 200; // Capacidade máxima do tanque

    int resultado = minParadas(distancias, n, capacidade);

    if (resultado == 0) {
        printf("Viagem impossivel.\n");
    } else {
        printf("Menor numero de paradas necessarias: %d\n", resultado);
    }

    return 0;
}

