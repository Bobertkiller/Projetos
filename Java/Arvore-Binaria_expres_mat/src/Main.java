import Arvore_mat.ArvoreAritimetica;
import Arvore_mat.BinaryTree;
public class Main {
    public static void main(String[] args) {
        String expressao = "3 + 4 * ( 2 - 1 ) / 5";

        BinaryTree arvore = ArvoreAritimetica.criarArvore(expressao);

        if (arvore != null) {
            System.out.println("Árvore criada com sucesso.");
            System.out.println("Percurso em ordem (in-order): ");
            arvore.inOrderTraversal();
            System.out.println("\nPercurso em pré-ordem (pre-order): ");
            arvore.preOrderTraversal();
            System.out.println("\nPercurso em pós-ordem (post-order): ");
            arvore.postOrderTraversal();
        } else {
            System.out.println("Erro na criação da árvore.");
        }
    }
}
