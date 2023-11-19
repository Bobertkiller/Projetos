#include <stdio.h>
#include <stdlib.h>
#include "funcs/dijkstra.h"
#include "funcs/file_operations.h"

#define MAX_VERTICES 100

int main() {
    int delivery_intersection, total_intersections;
    Triple *triples;
    int num_triples;

    // Read input file
    read_input_file("assets/input.txt", &delivery_intersection, &total_intersections, &triples, &num_triples);

    // Create and initialize the graph
    int graph[MAX_VERTICES][MAX_VERTICES] = {0};
    for (int i = 0; i < num_triples; i++) {
        graph[triples[i].intersection1][triples[i].intersection2] = triples[i].travel_time;
    }

    // Set source and destination
    int source = 1;
   

    // Run Dijkstra's algorithm
    dijkstra(graph, source, total_intersections, delivery_intersection);

    // Free allocated memory
    free(triples);

    return 0;
}
