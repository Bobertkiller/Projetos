#include <stdio.h>
#include <stdlib.h>

// Definição da estrutura de uma atividade
typedef struct {
    int inicio;
    int fim;
} Atividade;

// Função de comparação para a ordenação das atividades pelo tempo de término
int comparar(const void *a, const void *b) {
    return ((Atividade *)a)->fim - ((Atividade *)b)->fim;
}

// Algoritmo guloso para seleção de atividades
void seleciona_atividades_guloso(Atividade atividades[], int n) {
    // Ordena as atividades pelo tempo de término
    qsort(atividades, n, sizeof(Atividade), comparar);

    // A primeira atividade sempre é escolhida
    printf("Atividade selecionada: 0 -> Início: %d, Fim: %d\n", atividades[0].inicio, atividades[0].fim);

    // Inicializa o índice da última atividade selecionada
    int lastSelected = 0;

    // Percorre as atividades restantes
    for (int i = 1; i < n; i++) {
        // Se a atividade atual começa após o término da última selecionada, ela é escolhida
        if (atividades[i].inicio >= atividades[lastSelected].fim) {
            printf("Atividade selecionada: %d -> Início: %d, Fim: %d\n", i, atividades[i].inicio, atividades[i].fim);
            lastSelected = i;
        }
    }
}

int main() {
    // Exemplo de uso
    Atividade atividades[] = {
        {1, 4},
        {3, 5},
        {0, 6},
        {5, 7},
        {3, 9},
        {5, 9},
        {6, 10},
        {8, 11},
        {8, 12},
        {2, 14},
        {12, 16}
    };

    int n = sizeof(atividades) / sizeof(atividades[0]);

    printf("Atividades disponíveis:\n");
    for (int i = 0; i < n; i++) {
        printf("Atividade %d -> Início: %d, Fim: %d\n", i, atividades[i].inicio, atividades[i].fim);
    }

    printf("\nSeleção de atividades baseada em abordagem gulosa:\n");
    seleciona_atividades_guloso(atividades, n);

    return 0;
}
