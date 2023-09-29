package Arvore_mat;

public class Operador extends Node{
    private char op; // Campo para armazenar o operador.

    public Operador(char op) {
        super(String.valueOf(op)); // Define o operador como a representação textual do nó.
        this.op = op;
    }

    public int getPrioridade() {
        // Defina a prioridade dos operadores com base em suas regras.
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0; // Valor padrão para operadores não reconhecidos.
        }
    }

    @Override
    public float visitar() {
        float resultado = Float.NaN;

         // Realiza a operação correspondente com base no operador.
         float esquerda = 0.0f; // Valor esquerdo da operação
         float direita = 0.0f;  // Valor direito da operação
 
         // Suponhamos que a árvore seja montada de forma que o nó atual tenha filhos esquerdo e direito
         if (getleft() != null && getright() != null) {
             esquerda = getleft().visitar();
             direita = getright().visitar();
 
             switch (op) {
                 case '+':
                     resultado = esquerda + direita;
                     break;
                 case '-':
                     resultado = esquerda - direita;
                     break;
                 case '*':
                     resultado = esquerda * direita;
                     break;
                 case '/':
                     if (direita != 0.0f) {
                         resultado = esquerda / direita;
                     } else {
                         // Divisão por zero, tratamento de erro.
                         resultado = Float.NaN;
                     }
                     break;
                 default:
                     // Operador não reconhecido, tratamento de erro.
                     resultado = Float.NaN;
                     break;
             }
         }
        // Retorna o operador quando visitado.
        return resultado;
    }
}
