

package Arvore_mat;

import java.util.Stack;

public class ExpressaoAritmetica {

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static int precedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        return 0; // Operadores desconhecidos
    }

    private static BinaryTree buildExpressionTree(String[] expressao) {
        Stack<BinaryTree> stack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : expressao) {
            if (!token.isEmpty()) {
                if (!isOperator(token)) {
                    Node node = new Node(token);
                    BinaryTree tree = new BinaryTree(node);
                    stack.push(tree);
                } else {
                    while (!operatorStack.isEmpty() && precedence(token) <= precedence(operatorStack.peek())) {
                        String operator = operatorStack.pop();
                        BinaryTree rightTree = stack.pop();
                        BinaryTree leftTree = stack.pop();

                        Node operatorNode = new Node(operator);
                        operatorNode.setleft(leftTree.getroot());
                        operatorNode.setright(rightTree.getroot());

                        BinaryTree resultTree = new BinaryTree(operatorNode);
                        stack.push(resultTree);
                    }
                    operatorStack.push(token);
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            BinaryTree rightTree = stack.pop();
            BinaryTree leftTree = stack.pop();

            Node operatorNode = new Node(operator);
            operatorNode.setleft(leftTree.getroot());
            operatorNode.setright(rightTree.getroot());

            BinaryTree resultTree = new BinaryTree(operatorNode);
            stack.push(resultTree);
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expressão inválida: operandos sem operador correspondente.");
        }

        return stack.pop();
    }

    private static int evaluateExpressionTree(BinaryTree tree) {
        if (tree == null || tree.isEmpty()) {
            return 0;
        }

        Node root = tree.getroot();

        if (!isOperator(root.getdata())) {
            return Integer.parseInt(root.getdata());
        }

        int leftValue = evaluateExpressionTree(new BinaryTree(root.getleft()));
        int rightValue = evaluateExpressionTree(new BinaryTree(root.getright()));

        switch (root.getdata()) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue != 0) {
                    return leftValue / rightValue;
                } else {
                    throw new ArithmeticException("Divisão por zero.");
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String expressao = "1+2*3";
        String[] tokens = expressao.split("\\s+|(?=[()\\+\\-*/])|(?<=[()\\+\\-*/])");
        BinaryTree tree = buildExpressionTree(tokens);

        if (tree != null) {
            int resultado = evaluateExpressionTree(tree);
            System.out.println("Resultado da expressão: " + resultado);
        } else {
            System.out.println("Expressão inválida.");
        }
    }
}
