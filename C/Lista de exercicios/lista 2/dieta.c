#include <stdio.h>
#include <stdlib.h>

// Definição da estrutura do alimento
typedef struct {
    int calorias;
    int proteinas;
} Alimento;

// Função para calcular a dieta
void calcularDieta(Alimento alimentos[], int n, int capacidadeCalorias) {
    // Criação de uma matriz para armazenar os resultados dos subproblemas
    int **tabela = (int **)malloc((n + 1) * sizeof(int *));
    for (int i = 0; i <= n; i++) {
        tabela[i] = (int *)malloc((capacidadeCalorias + 1) * sizeof(int));
    }

    // Preenchimento da tabela com os resultados dos subproblemas
    for (int i = 0; i <= n; i++) {
        for (int w = 0; w <= capacidadeCalorias; w++) {
            if (i == 0 || w == 0) {
                tabela[i][w] = 0;
            } else if (alimentos[i - 1].calorias <= w) {
                tabela[i][w] = (alimentos[i - 1].proteinas +
                                tabela[i - 1][w - alimentos[i - 1].calorias] >
                                tabela[i - 1][w])
                                ? alimentos[i - 1].proteinas +
                                tabela[i - 1][w - alimentos[i - 1].calorias]
                                : tabela[i - 1][w];
            } else {
                tabela[i][w] = tabela[i - 1][w];
            }
        }
    }

    // Resultado final
    int resultado = tabela[n][capacidadeCalorias];

    // Encontrar os alimentos incluídos na dieta
    int w = capacidadeCalorias;
    printf("Alimentos na dieta:\n");
    for (int i = n; i > 0 && resultado > 0; i--) {
        if (resultado != tabela[i - 1][w]) {
            printf("Alimento %d: Calorias = %d, Proteínas = %d\n", i,
                    alimentos[i - 1].calorias, alimentos[i - 1].proteinas);
            resultado -= alimentos[i - 1].proteinas;
            w -= alimentos[i - 1].calorias;
        }
    }

    // Liberar a memória alocada para a tabela
    for (int i = 0; i <= n; i++) {
        free(tabela[i]);
    }
    free(tabela);
}

int main() {
    // Exemplo de uso
    int capacidadeCalorias = 50;
    Alimento alimentos[] = {
        {10, 20},
        {20, 30},
        {30, 40}
    };

    int n = sizeof(alimentos) / sizeof(alimentos[0]);

    calcularDieta(alimentos, n, capacidadeCalorias);

    return 0;
}
