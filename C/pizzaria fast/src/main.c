#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define MAX_VERTICES 100

typedef struct {
    int intersection1;
    int intersection2;
    int travel_time;
} Triple;

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

void read_input_file(const char *file_path, int *delivery_intersection, int *total_intersections, Triple **triples, int *num_triples) {
    FILE *file = fopen(file_path, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    // Read delivery intersection
    fscanf(file, "%d", delivery_intersection);

    // Read total number of intersections
    fscanf(file, "%d", total_intersections);

    // Read triples of intersections and travel time
    *triples = NULL;
    *num_triples = 0;
    while (1) {
        Triple triple;
        int result = fscanf(file, "%d %d %d", &triple.intersection1, &triple.intersection2, &triple.travel_time);
        if (result == EOF || result < 3) {
            break;
        }
        (*num_triples)++;
        *triples = realloc(*triples, (*num_triples) * sizeof(Triple));
        if (*triples == NULL) {
            perror("Error allocating memory");
            exit(EXIT_FAILURE);
        }
        (*triples)[(*num_triples) - 1] = triple;
    }

    fclose(file);
}


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
    int destination = 3;

    // Run Dijkstra's algorithm
    dijkstra(graph, source, total_intersections, destination);

    // Free allocated memory
    free(triples);

    return 0;
}