#ifndef FILE_OPERATIONS_H
#define FILE_OPERATIONS_H

#include <stdio.h>
#include <stdlib.h>
#include "dijkstra.h"

void read_input_file(const char *nome_arquivo, int *intersecao_entrega, int *intersecao_total, Triple **triples, int *num_triples);

#endif 
