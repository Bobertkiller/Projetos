//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

import Arvore_Binaria.Node;
import Arvore_Binaria.BST;

public class Main2 {

    public static void main(String[] args) {
        // Criando a árvore binária de busca
        BST bst = new BST();

        // Inserindo chaves na árvore
        bst.insert("C");
        bst.insert("A");
        bst.insert("E");
        bst.insert("B");
        bst.insert("D");

        //Tentando inserir uma chave duplicada
        bst.insert("C");

        // Exibindo o conteúdo da árvore com percurso em ordem
        System.out.println("Conteúdo da árvore com percurso em ordem:");
        System.out.println("\nResultados da busca:");
        searchAndPrint(bst, "C");
        searchAndPrint(bst, "A");
        searchAndPrint(bst, "E");
        searchAndPrint(bst, "B");
        searchAndPrint(bst, "D");
        searchAndPrint(bst, "X"); // Chave que não existe


        // Encontrando o mínimo e o máximo na árvore
        Node minNode = bst.findMin();
        Node maxNode = bst.findMax();
        System.out.println("\nChave Mínima: " + minNode.getdata());
        System.out.println("Chave Máxima: " + maxNode.getdata());

        // Encontrando predecessores e sucessores
        String keyToFind = "A";
        Node predecessor = bst.findPredecessor(keyToFind);
        Node successor = bst.findSuccessor(keyToFind);
        System.out.println("\nPredecessor de " + keyToFind + ": " + (predecessor != null ? predecessor.getdata() : "Não encontrado"));
        System.out.println("Sucessor de " + keyToFind + ": " + (successor != null ? successor.getdata() : "Não encontrado"));

        // Removendo alguns nós da árvore
        bst.remove("B");
        bst.remove("D");
        bst.remove("C");
        bst.remove("Y");

        // Exibindo o conteúdo atualizado da árvore com percurso em ordem
        System.out.println("\nÁrvore após remoções de B, C e D");
        searchAndPrint(bst, "C");
        searchAndPrint(bst, "A");
        searchAndPrint(bst, "E");
        searchAndPrint(bst, "B");
        searchAndPrint(bst, "D");
        searchAndPrint(bst, "Y"); //Chave nao existente

        // Limpando a árvore
        bst.clear();
        System.out.println("\nÁrvore após limpeza:");
        searchAndPrint(bst, "C");
        searchAndPrint(bst, "A");
        searchAndPrint(bst, "E");
        searchAndPrint(bst, "B");
        searchAndPrint(bst, "D");
                
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