//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238
//Daniel Reis Raske TIA - 32219441

#include <stdio.h>
#include <stdlib.h>
#include "funcs/dijkstra.h"
#include "funcs/file_operations.h"

#define MAX_VERTICES 100

int main() {
    int intersecao_entrega, intersecao_total;
    Triple *triples;
    int num_triples;

    
    read_input_file("assets/input.txt", &intersecao_entrega, &intersecao_total, &triples, &num_triples);

    int graph[MAX_VERTICES][MAX_VERTICES] = {0};
    for (int i = 0; i < num_triples; i++) {
        graph[triples[i].intersecao1][triples[i].intersecao2] = triples[i].tempo_de_trajeto;
    }

    int local = 1;
   
    dijkstra(graph, local, intersecao_total, intersecao_entrega);

    free(triples);

    return 0;
}
