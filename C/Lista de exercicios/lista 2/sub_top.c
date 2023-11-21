#include <stdio.h>
#include <string.h>

#define MAX 100

int max(int a, int b) {
    return (a > b) ? a : b;
}

int dp[MAX][MAX];

int scm_top_down(char X[], char Y[], int m, int n) {
    if (m == 0 || n == 0) {
        return 0;
    }

    if (dp[m][n] != -1) {
        return dp[m][n];
    }

    if (X[m - 1] == Y[n - 1]) {
        dp[m][n] = 1 + scm_top_down(X, Y, m - 1, n - 1);
    } else {
        dp[m][n] = max(scm_top_down(X, Y, m, n - 1), scm_top_down(X, Y, m - 1, n));
    }

    return dp[m][n];
}

int main() {
    char X[] = "ATCTGAT";
    char Y[] = "TGCATTA";
    int m = strlen(X);
    int n = strlen(Y);

    memset(dp, -1, sizeof(dp));

    int resultado = scm_top_down(X, Y, m, n);

    printf("Tamanho da maior subsequência comum: %d\n", resultado);

    return 0;
}
