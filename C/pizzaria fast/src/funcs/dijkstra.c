#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "dijkstra.h"

#define MAX_VERTICES 100

// Funcao para achar a intersecao com o menor tempo
int tempominimo(int tempo[], int encontra[], int vertices) {
    int min = INT_MAX, minIndex;
    for (int v = 1; v <= vertices; v++) {
        if (encontra[v] == 0 && tempo[v] < min) {
            min = tempo[v];
            minIndex = v;
        }
    }
    return minIndex;
}

// Funcao que printa a rota desde que a saida para o destino
void printarota(int saida[], int destino) {
    if (saida[destino] == -1) {
        printf("%d ", destino);
        return;
    }

    printarota(saida, saida[destino]);
    printf("%d ", destino);
}

// Algoritimo Dijkstra para encontrar a rota mais rapida
void dijkstra(int graph[MAX_VERTICES][MAX_VERTICES], int local, int vertices, int destino) {
    int tempo[MAX_VERTICES];    // To store the tempo to reach each intersection
    int encontra[MAX_VERTICES]; // To mark encontra intersections
    int saida[MAX_VERTICES];  // To store the saida intersection for each intersection in the route

    // Inicializa
    for (int v = 1; v <= vertices; v++) {
        tempo[v] = INT_MAX;
        encontra[v] = 0;
        saida[v] = -1;
    }
    tempo[local] = 0;

    // Algoritmo Dijkstra
    for (int count = 1; count < vertices; count++) {
        int u = tempominimo(tempo, encontra, vertices);
        encontra[u] = 1;

        for (int v = 1; v <= vertices; v++) {
            if (!encontra[v] && graph[u][v] && tempo[u] != INT_MAX &&
                tempo[u] + graph[u][v] < tempo[v]) {
                tempo[v] = tempo[u] + graph[u][v];
                saida[v] = u;
            }
        }
    }

    // Printa a rota e o tempo ate o destino
    printf("Rota mais rapida de  %d ate %d: ", local, destino);
    printarota(saida, destino);
    printf("\ntempo: %d minuto\n", tempo[destino]);
}
