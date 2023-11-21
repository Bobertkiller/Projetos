#include <stdio.h>
#include <stdlib.h>

// Definição da estrutura do objeto
typedef struct {
    int peso;
    int valor;
    double razao; // Razão valor/peso
} Objeto;

// Função de comparação para qsort
int comparar(const void *a, const void *b) {
    const Objeto *objetoA = (const Objeto *)a;
    const Objeto *objetoB = (const Objeto *)b;
    return (objetoB->razao - objetoA->razao);
}

// Função para resolver o problema da mochila fracionária
double mochilaFracionaria(Objeto objetos[], int n, int capacidade) {
    // Calcular as razões valor/peso
    for (int i = 0; i < n; i++) {
        objetos[i].razao = (double)objetos[i].valor / objetos[i].peso;
    }

    // Ordenar os objetos com base na razão valor/peso (em ordem decrescente)
    qsort(objetos, n, sizeof(Objeto), comparar);

    double valorTotal = 0.0;
    int capacidadeRestante = capacidade;

    // Preencher a mochila com base na ordem ordenada
    for (int i = 0; i < n; i++) {
        if (objetos[i].peso <= capacidadeRestante) {
            // Se o objeto inteiro couber na mochila
            valorTotal += objetos[i].valor;
            capacidadeRestante -= objetos[i].peso;
        } else {
            // Se o objeto não couber inteiro, coloque a fração possível
            valorTotal += objetos[i].razao * capacidadeRestante;
            break; // Mochila está cheia
        }
    }

    return valorTotal;
}

int main() {
    // Exemplo de uso
    Objeto objetos[] = {
        {40, 840},
        {30, 600},
        {20, 400},
        {10, 100},
        {20, 30}
    };

    int n = sizeof(objetos) / sizeof(objetos[0]);
    int capacidade = 50;

    double valorMaximo = mochilaFracionaria(objetos, n, capacidade);

    printf("Valor maximo na mochila: %.2f\n", valorMaximo);

    return 0;
}
