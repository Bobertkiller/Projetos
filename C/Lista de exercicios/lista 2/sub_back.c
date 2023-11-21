#include <stdio.h>
#include <string.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int scm_recursivo(char X[], char Y[], int m, int n) {
    if (m == 0 || n == 0) {
        return 0;
    }

    if (X[m - 1] == Y[n - 1]) {
        return 1 + scm_recursivo(X, Y, m - 1, n - 1);
    } else {
        return max(scm_recursivo(X, Y, m, n - 1), scm_recursivo(X, Y, m - 1, n));
    }
}

int main() {
    char X[] = "ATCTGAT";
    char Y[] = "TGCATTA";
    int m = strlen(X);
    int n = strlen(Y);

    int resultado = scm_recursivo(X, Y, m, n);

    printf("Tamanho da maior subsequência comum: %d\n", resultado);

    return 0;
}
