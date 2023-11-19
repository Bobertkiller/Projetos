#ifndef DIJKSTRA_H
#define DIJKSTRA_H

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define MAX_VERTICES 100

typedef struct {
    int intersection1;
    int intersection2;
    int travel_time;
} Triple;

void dijkstra(int graph[MAX_VERTICES][MAX_VERTICES], int source, int vertices, int destination);
int findMinTime(int time[], int visited[], int vertices);
void printRoute(int parent[], int destination);

#endif /* DIJKSTRA_H */
