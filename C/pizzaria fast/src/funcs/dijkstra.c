#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "dijkstra.h"

#define MAX_VERTICES 100

// Function to find the intersection with the minimum time
int findMinTime(int time[], int visited[], int vertices) {
    int min = INT_MAX, minIndex;
    for (int v = 1; v <= vertices; v++) {
        if (visited[v] == 0 && time[v] < min) {
            min = time[v];
            minIndex = v;
        }
    }
    return minIndex;
}

// Function to print the route from source to destination
void printRoute(int parent[], int destination) {
    if (parent[destination] == -1) {
        printf("%d ", destination);
        return;
    }

    printRoute(parent, parent[destination]);
    printf("%d ", destination);
}

// Dijkstra's algorithm to find the fastest route
void dijkstra(int graph[MAX_VERTICES][MAX_VERTICES], int source, int vertices, int destination) {
    int time[MAX_VERTICES];    // To store the time to reach each intersection
    int visited[MAX_VERTICES]; // To mark visited intersections
    int parent[MAX_VERTICES];  // To store the parent intersection for each intersection in the route

    // Initialization
    for (int v = 1; v <= vertices; v++) {
        time[v] = INT_MAX;
        visited[v] = 0;
        parent[v] = -1;
    }
    time[source] = 0;

    // Dijkstra's algorithm
    for (int count = 1; count < vertices; count++) {
        int u = findMinTime(time, visited, vertices);
        visited[u] = 1;

        for (int v = 1; v <= vertices; v++) {
            if (!visited[v] && graph[u][v] && time[u] != INT_MAX &&
                time[u] + graph[u][v] < time[v]) {
                time[v] = time[u] + graph[u][v];
                parent[v] = u;
            }
        }
    }

    // Print the route and time for the destination
    printf("Fastest route from %d to %d: ", source, destination);
    printRoute(parent, destination);
    printf("\nTime: %d minutes\n", time[destination]);
}
