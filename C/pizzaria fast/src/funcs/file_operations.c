#include <stdio.h>
#include <stdlib.h>
#include "file_operations.h"

void read_input_file(const char *nome_arquivo, int *intersecao_entrega, int *intersecao_total, Triple **triples, int *num_triples) {
    FILE *file = fopen(nome_arquivo, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    // Le a intersecao da entreha
    fscanf(file, "%d", intersecao_entrega);

    // Le o total de intersecoes
    fscanf(file, "%d", intersecao_total);

    // Le as triples da intersecao e o tempo de trajeto
    *triples = NULL;
    *num_triples = 0;

    while (1) {
        Triple triple;
        int resultado = fscanf(file, "%d %d %d", &triple.intersecao1, &triple.intersecao2, &triple.tempo_de_trajeto);
        if (resultado == EOF || resultado < 3) {
            break;
        }

        (*num_triples)++;
        *triples = realloc(*triples, (*num_triples) * sizeof(Triple));

        if (*triples == NULL) {
            perror("Erro de alocacao de memoria");
            exit(EXIT_FAILURE);
        }

        (*triples)[(*num_triples) - 1] = triple;
    }

    fclose(file);
}
