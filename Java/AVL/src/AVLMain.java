//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

import Avl.AVL;
import Avl.Node;

public class AVLMain {
    public static void main(String[] args) {
        AVL avlTree = new AVL();

        // Teste (a)
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);

        System.out.println("Dados dos nós após inserção (a):");
        printNodeData(avlTree.getroot());

        // Teste (b)
        avlTree = new AVL();
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(1);

        System.out.println("\nDados dos nós após inserção (b):");
        printNodeData(avlTree.getroot());

        // Teste (c)
        avlTree = new AVL();
        avlTree.insert(3);
        avlTree.insert(1);
        avlTree.insert(2);

        System.out.println("\nDados dos nós após inserção (c):");
        printNodeData(avlTree.getroot());

        // Teste (d)
        avlTree = new AVL();
        avlTree.insert(1);
        avlTree.insert(3);
        avlTree.insert(2);

        System.out.println("\nDados dos nós após inserção (d):");
        printNodeData(avlTree.getroot());

        // Teste (e)
        avlTree = new AVL();
        avlTree.insert(5);
        avlTree.insert(4);
        avlTree.insert(3);
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(6);
        avlTree.insert(7);
        avlTree.insert(9);
        avlTree.insert(8);

        System.out.println("\nDados dos nós após inserção (e):");
        printNodeData(avlTree.getroot());

        // Teste (f)
        avlTree.remove(4);

        System.out.println("\nDados dos nós após a remoção do nó 4 (f):");
        printNodeData(avlTree.getroot());

        // Teste (g)
        avlTree.remove(5);

        System.out.println("\nDados dos nós após a remoção do nó 5 (g):");
        printNodeData(avlTree.getroot());

        // Teste (h)
        avlTree.remove(1);

        System.out.println("\nDados dos nós após a remoção do nó 1 (h):");
        printNodeData(avlTree.getroot());
    }

    private static void printNodeData(Node node) {
        if (node != null) {
            System.out.println("Chave: " + node.getdata() +
                               " \nNó Pai: " + (node.getparent() != null ? node.getparent().getdata() : "null") +
                               " / Nó Filho Esquerdo: " + (node.getleft() != null ? node.getleft().getdata() : "null") +
                               " / Nó Filho Direito: " + (node.getright() != null ? node.getright().getdata() : "null") +
                               " / Fator de Balanceamento: " + node.getBalanceFactor() +
                               " / Altura: " + node.getHeight());

            printNodeData(node.getleft());
            printNodeData(node.getright());
        }
    }
}

