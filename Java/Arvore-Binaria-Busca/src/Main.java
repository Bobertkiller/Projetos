//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

import Arvore_Binaria.Node;
import Arvore_Binaria.BST;


public class Main {

    public static void main(String[] args) {
        // Criando a árvore binária de busca
        BST bst = new BST();

        // Inserindo chaves na árvore
        bst.insert("C");
        bst.insert("A");
        bst.insert("E");
        bst.insert("B");
        bst.insert("D");

        // Tentando inserir uma chave duplicada
        bst.insert("C"); // Isso não deve adicionar outra "C"

        // Realizando buscas na árvore
        System.out.println("Resultados da busca:");
        searchAndPrint(bst, "C");
        searchAndPrint(bst, "A");
        searchAndPrint(bst, "E");
        searchAndPrint(bst, "B");
        searchAndPrint(bst, "D");
        searchAndPrint(bst, "X"); // Chave que não existe

        // Removendo alguns nós da árvore
        bst.remove("B");
        bst.remove("D");
        bst.remove("C");

        // Tentando remover um nó que não existe
        bst.remove("Y");

        // Imprimindo a árvore após remoções
        System.out.println("\nÁrvore após remoções:");
        bst.inOrderTraversal();
    }

    private static void searchAndPrint(BST bst, String data) {
        Node node = bst.search(data);
        if (node != null) {
            System.out.println("Chave encontrada: " + node.toString());
        } else {
            System.out.println("Chave não encontrada: " + data);
        }
    }
}
