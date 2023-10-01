#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

bool encontrar_combinacao_pendrives(int L, int T, int S[]) {
  int soma_arquivos = 0;
  for (int i = 0; i < T; i++) {
    soma_arquivos += S[i];
  }

  for (int i = 0; i < T - 1; i++) {
    for (int j = i + 1; j < T; j++) {
      if (S[i] < S[j]) {
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
      }
    }
  }

  int pendriveA = 0;
  int pendriveB = 0;

  int arquivosPendriveA[T];
  int arquivosPendriveB[T];
  
  int indiceA = 0;
  int indiceB = 0;

  for (int i = 0; i < T; i++) {
    if (pendriveA <= pendriveB) {
      pendriveA += S[i];
      arquivosPendriveA[indiceA++] = S[i];
    } else {
      pendriveB += S[i];
      arquivosPendriveB[indiceB++] = S[i];
    }
  }

  printf("Tamanho total dos arquivos: %d GB\n", soma_arquivos);
  printf("Pendrive A (%d GB)\n", L / 2);
  for (int i = 0; i < indiceA; i++) {
    printf("%d GB\n", arquivosPendriveA[i]);
  }
  printf("Pendrive B (%d GB)\n", L / 2);
  for (int i = 0; i < indiceB; i++) {
    printf("%d GB\n", arquivosPendriveB[i]);
  }

  return true;
}

int main() {

  FILE *arquivo = fopen("pendrive.txt", "r");
  if (arquivo == NULL) {
    printf("Erro ao abrir o arquivo pendrive.txt\n");
    return 1;
  }

  int N; 
  fscanf(arquivo, "%d", &N);

  for (int i = 0; i < N; i++) {
    int L; 
    int T; 
    fscanf(arquivo, "%d %d", &L, &T);

    int S[T]; 
    int soma_arquivos = 0;

    for (int j = 0; j < T; j++) {
      fscanf(arquivo, "%d", &S[j]);
      soma_arquivos += S[j];
    }
  
    int tamanho_pendrive_A = L ;

    printf("\n%d GB\n", tamanho_pendrive_A);

    if (soma_arquivos > L) {      
      printf("Tamanho total dos arquivos: %d GB\n", soma_arquivos);
      printf("Impossível gravar todos os arquivos nos pendrives.\n");
    } else {
      if (!encontrar_combinacao_pendrives(tamanho_pendrive_A, T, S)) {
        printf("Tamanho total dos arquivos: %d GB\n", soma_arquivos);
        printf("Impossível gravar todos os arquivos nos pendrives.\n");
      }
    }
  }

  fclose(arquivo);

  return 0;
}

