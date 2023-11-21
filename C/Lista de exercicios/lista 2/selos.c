#include <stdio.h>

void calcularSelos(int taxa) {
    int selosDeTres = 0;
    int selosDeCinco = 0;

    // Verifique se a taxa é menor que 8
    if (taxa < 8) {
        printf("Não é possível atingir a taxa exata com selos de 3 e 5 centavos.\n");
        return;
    }

    // Use selos de 5 centavos até atingir um valor menor que 8
    while (taxa >= 5) {
        taxa -= 5;
        selosDeCinco++;
    }

    // Use selos de 3 centavos para completar a taxa
    while (taxa > 0) {
        taxa -= 3;
        selosDeTres++;
    }

    // Exiba os resultados
    printf("Número mínimo de selos de 3 centavos: %d\n", selosDeTres);
    printf("Número mínimo de selos de 5 centavos: %d\n", selosDeCinco);
}

int main() {
    int taxa;

    // Obter a taxa do usuário
    printf("Digite a taxa (em centavos): ");
    scanf("%d", &taxa);

    // Calcular e exibir os selos necessários
    calcularSelos(taxa);

    return 0;
}
