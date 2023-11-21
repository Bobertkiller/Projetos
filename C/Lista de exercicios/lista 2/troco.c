#include <stdio.h>
#include <math.h>

int main() {
    // Ler o valor de ponto flutuante
    double valor;
    printf("Digite o valor: ");
    scanf("%lf", &valor);

    // Converter o valor para centavos para evitar problemas com arredondamento de ponto flutuante
    int centavos = round(valor * 100);

    // Definir os valores das notas e moedas em centavos
    int notas[] = {10000, 5000, 2000, 1000, 500, 200};
    int moedas[] = {100, 50, 25, 10, 5, 1};

    printf("NOTAS:\n");
    for (int i = 0; i < 6; i++) {
        int quantidade = centavos / notas[i];
        centavos %= notas[i];
        printf("%d nota(s) de R$ %.2lf\n", quantidade, notas[i] / 100.0);
    }

    printf("MOEDAS:\n");
    for (int i = 0; i < 6; i++) {
        int quantidade = centavos / moedas[i];
        centavos %= moedas[i];
        printf("%d moeda(s) de R$ %.2lf\n", quantidade, moedas[i] / 100.0);
    }

    return 0;
}
