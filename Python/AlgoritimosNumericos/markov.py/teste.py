import numpy as np

# Definindo o espaço de estados
estados = [0, 1, 2, 3, 4, 5]  # Representando o número de miniaturas colecionadas

# Definindo a matriz de transição
# Cada linha representa o estado atual e cada coluna o próximo estado
# P[i][j] é a probabilidade de transição do estado i para o estado j
matriz_transicao = np.array([
    [1, 0, 0, 0, 0, 0],  # Probabilidade de ficar no estado 0
    [0.8, 0, 0.2, 0, 0, 0],  # Probabilidade de transição do estado 1 para os estados 0, 2
    [0, 0.6, 0, 0.4, 0, 0],  # Probabilidade de transição do estado 2 para os estados 1, 3
    [0, 0, 0.4, 0, 0.6, 0],  # Probabilidade de transição do estado 3 para os estados 2, 4
    [0, 0, 0, 0.2, 0, 0.8],  # Probabilidade de transição do estado 4 para o estado 3
    [0, 0, 0, 0, 0, 1]  # Probabilidade de ficar no estado 5
])

# Calculando as potências da matriz de transição
P2 = np.linalg.matrix_power(matriz_transicao, 2)
P5 = np.linalg.matrix_power(matriz_transicao, 5)
P10 = np.linalg.matrix_power(matriz_transicao, 10)
P20 = np.linalg.matrix_power(matriz_transicao, 20)

# Imprimindo as probabilidades
print("P2:")
print(P2)
print("\nP5:")
print(P5)
print("\nP10:")
print(P10)
print("\nP20:")
print(P20)

# Calculando a probabilidade P(X5=2, X10=3, X15=4, X20=5 | X0=0)
# Isso é equivalente a multiplicar as probabilidades de transição relevantes
probabilidade_condicional = matriz_transicao[0][2] * matriz_transicao[2][3] * matriz_transicao[3][4] * matriz_transicao[4][5]

print("\nProbabilidade condicional:")
print(probabilidade_condicional)
