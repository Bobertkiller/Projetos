#include <stdio.h>
#include <stdlib.h>
#include "file_operations.h"

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
