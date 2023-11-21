#include <stdio.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int mochila_recursiva(int capacidade, int pesos[], int valores[], int n) {
    // Caso base
    if (n == 0 || capacidade == 0) {
        return 0;
    }

    // Se o peso do item é maior que a capacidade, não pode ser incluído
    if (pesos[n - 1] > capacidade) {
        return mochila_recursiva(capacidade, pesos, valores, n - 1);
    }

    // Retornar o máximo entre incluir e não incluir o item
    return max(valores[n - 1] + mochila_recursiva(capacidade - pesos[n - 1], pesos, valores, n - 1),
                mochila_recursiva(capacidade, pesos, valores, n - 1));
}

int main() {
    int valores[] = {60, 100, 120};
    int pesos[] = {10, 20, 30};
    int capacidade = 50;
    int n = sizeof(valores) / sizeof(valores[0]);

    int resultado = mochila_recursiva(capacidade, pesos, valores, n);

    printf("Valor máximo da mochila: %d\n", resultado);

    return 0;
}
