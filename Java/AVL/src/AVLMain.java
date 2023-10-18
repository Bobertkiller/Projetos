import Avl.AVL;
import Avl.Node;

public class AVLMain {
    public static void main(String[] args) {
        // Cria a árvore AVL
        AVL avlTree = new AVL();

        // Insere nós com as chaves 1, 2 e 3
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);

        // Exibe os dados atualizados dos nós após a inserção
        System.out.println("Dados dos nós após inserção:");
        printNodeData(avlTree.getroot());

        // O balanceamento ocorre automaticamente durante a inserção
        // Portanto, não é necessário chamar explicitamente o balanceamento.

        System.out.println("\nDados dos nós após o balanceamento:");
        printNodeData(avlTree.getroot());
    }

    // Método para exibir os dados dos nós
    private static void printNodeData(Node node) {
        if (node != null) {
            System.out.println("Chave: " + node.getdata());
            System.out.println("Nó Pai: " + (node.getparent() != null ? node.getparent().getdata() : "null"));
            System.out.println("Nó Filho Esquerdo: " + (node.getleft() != null ? node.getleft().getdata() : "null"));
            System.out.println("Nó Filho Direito: " + (node.getright() != null ? node.getright().getdata() : "null"));
            System.out.println("Fator de Balanceamento: " + node.getBalanceFactor());
            System.out.println("Altura: " + node.getHeight());
            System.out.println("--------");

            printNodeData(node.getleft());
            printNodeData(node.getright());
        }
    }
}
