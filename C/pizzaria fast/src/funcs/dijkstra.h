//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238
//Daniel Reis Raske TIA - 32219441

#ifndef DIJKSTRA_H
#define DIJKSTRA_H

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define MAX_VERTICES 100

typedef struct {
    int intersecao1;
    int intersecao2;
    int tempo_de_trajeto;
} Triple;

void dijkstra(int graph[MAX_VERTICES][MAX_VERTICES], int local, int vertices, int destino);
int tempominimo(int tempo[], int encontra[], int vertices);
void printarota(int saida[], int destino);

#endif
