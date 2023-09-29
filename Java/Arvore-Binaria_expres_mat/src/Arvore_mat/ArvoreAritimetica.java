

package Arvore_mat;

import java.util.Stack;

public class ArvoreAritimetica {
    public static BinaryTree criarArvore(String expressao) {
        // Remove espaços em branco da expressão
        expressao = expressao.replaceAll("\\s+", "");

        // Pilha para operadores
        Stack<Node> pilhaOperadores = new Stack<>();
        // Pilha para operandos
        Stack<Node> pilhaOperandos = new Stack<>();

        int index = 0;
        while (index < expressao.length()) {
            char caractere = expressao.charAt(index);

            if (Character.isDigit(caractere) || (caractere == '.' && index + 1 < expressao.length() && Character.isDigit(expressao.charAt(index + 1)))) {
                // Se for um dígito ou parte de um número decimal, encontre o número completo
                int startIndex = index;
                while (index < expressao.length() && (Character.isDigit(expressao.charAt(index)) || expressao.charAt(index) == '.')) {
                    index++;
                }
                String numero = expressao.substring(startIndex, index);
                float valor = Float.parseFloat(numero);
                Node operando = new Operando(valor);
                pilhaOperandos.push(operando);
            } else if ("+-*/".indexOf(caractere) != -1) {
                // Se for um operador, crie um nó operador e gerencie a pilha de operadores
                Node operador = new Operador(caractere);
                while (!pilhaOperadores.isEmpty() && getPrioridade(pilhaOperadores.peek().getdata().charAt(0)) >= prioridade(operador.getdata().charAt(0))) {
                    Node operadorTopo = pilhaOperadores.pop();
                    Node direita = pilhaOperandos.pop();
                    Node esquerda = pilhaOperandos.pop();
                    operadorTopo.setleft(esquerda);
                    operadorTopo.setright(direita);
                    pilhaOperandos.push(operadorTopo);
                }
                pilhaOperadores.push(operador);
                index++;
            } else if (caractere == '(') {
                // Se for um parêntese de abertura, empilhe na pilha de operadores
                pilhaOperadores.push(new Operador('('));
                index++;
            } else if (caractere == ')') {
                // Se for um parêntese de fechamento, desempilhe operadores até encontrar o parêntese de abertura correspondente
                while (!pilhaOperadores.isEmpty() && pilhaOperadores.peek().getdata().charAt(0) != '(') {
                    Node operadorTopo = pilhaOperadores.pop();
                    Node direita = pilhaOperandos.pop();
                    Node esquerda = pilhaOperandos.pop();
                    operadorTopo.setleft(esquerda);
                    operadorTopo.setright(direita);
                    pilhaOperandos.push(operadorTopo);
                }
                if (!pilhaOperadores.isEmpty() && pilhaOperadores.peek().getdata().charAt(0) == '(') {
                    pilhaOperadores.pop(); // Remova o parêntese de abertura correspondente
                } else {
                    return null; // Parênteses desbalanceados
                }
                index++;
            } else {
                return null; // Caractere inválido
            }
        }

        // Processar operadores restantes na pilha
        while (!pilhaOperadores.isEmpty()) {
            Node operadorTopo = pilhaOperadores.pop();
            Node direita = pilhaOperandos.pop();
            Node esquerda = pilhaOperandos.pop();
            operadorTopo.setleft(esquerda);
            operadorTopo.setright(direita);
            pilhaOperandos.push(operadorTopo);
        }

        // A pilha de operandos agora contém a árvore da expressão
        if (!pilhaOperandos.isEmpty()) {
            BinaryTree arvore = new BinaryTree(pilhaOperandos.pop());
            return arvore;
        } else {
            return null;
        }
    }
}
