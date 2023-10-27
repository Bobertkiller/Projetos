import sympy as sp
import math

def bisseccao(f, intervalo, epsilon, max_iteracoes=1000):
    a, b = intervalo
    if f(a) * f(b) >= 0:
        raise ValueError("A função f deve ter sinais opostos em a e b.")
    
    for i in range(max_iteracoes):
        c = (a + b) / 2
        if f(c) == 0 or (b - a) / 2 < epsilon:
            return c  # Encontramos uma raiz exata ou atingimos a tolerância ε.
        
        if f(c) * f(a) < 0:
            b = c
        else:
            a = c
    
    return (a + b) / 2

# Solicitar entrada do usuário
funcao_str = input("Digite a função (use 'x' como variável, e inclua 'math.' para funções trigonométricas): ")
epsilon = float(input("Digite a tolerância (epsilon): "))

# Analisar a expressão da função
x = sp.symbols('x')

# Função para avaliar a função
def eval_funcao(x_val):
    x = x_val
    return eval(funcao_str, {"x": x, "math": math})

# Perguntar se os limites são intervalos
e_intervalo = input("Os limites para o intervalo são um intervalo? (sim ou não): ").strip().lower()
if e_intervalo == "sim":
    limite_inferior = float(input("Digite o limite inferior do intervalo (ex. 0.2): "))
    limite_superior = float(input("Digite o limite superior do intervalo (ex. 0.3): "))
    intervalo = [limite_inferior, limite_superior]
else:
    limite = float(input("Digite o limite (ex. 0.2): "))
    intervalo = [limite, limite]

# Calcular a raiz aproximada
raiz_aproximada = bisseccao(eval_funcao, intervalo, epsilon)
print("Aproximação da raiz:", raiz_aproximada)
