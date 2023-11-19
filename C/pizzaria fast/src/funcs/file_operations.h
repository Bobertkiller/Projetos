#ifndef FILE_OPERATIONS_H
#define FILE_OPERATIONS_H

#include <stdio.h>
#include <stdlib.h>
#include "dijkstra.h"

void read_input_file(const char *file_path, int *delivery_intersection, int *total_intersections, Triple **triples, int *num_triples);

#endif /* FILE_OPERATIONS_H */
