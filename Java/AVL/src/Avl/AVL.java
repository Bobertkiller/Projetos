//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

package Avl;

public class AVL extends BT {

    public AVL() {
        super();
    }

    // Rotação simples para a esquerda (LL)
    private Node rotateLeft(Node root) {
        Node newRoot = root.getright();
        root.setright(newRoot.getleft());
        newRoot.setleft(root);
        root.updateBalanceFactor();
        newRoot.updateBalanceFactor();
        return newRoot;
    }

    // Rotação simples para a direita (RR)
    private Node rotateRight(Node root) {
        Node newRoot = root.getleft();
        root.setleft(newRoot.getright());
        newRoot.setright(root);
        root.updateBalanceFactor();
        newRoot.updateBalanceFactor();
        return newRoot;
    }

    // Rotação esquerda-direita (LR)
    private Node rotateLeftRight(Node root) {
        root.setleft(rotateLeft(root.getleft()));
        return rotateRight(root);
    }

    // Rotação direita-esquerda (RL)
    private Node rotateRightLeft(Node root) {
        root.setright(rotateRight(root.getright()));
        return rotateLeft(root);
    }

    // Inserir um nó na árvore AVL (chamando o método da classe base)
    public void insert(int chave) {
        super.insert(chave);
        root = balance(root);
    }

    // Método auxiliar para reequilibrar a árvore após a inserção
    private Node balance(Node node) {
        if (node == null) {
            return node;
        }

        node.updateBalanceFactor();

        if (node.getBalanceFactor() > 1) {
            if (node.getleft().getBalanceFactor() >= 0) {
                return rotateRight(node); // Rotação RR
            } else {
                return rotateLeftRight(node); // Rotação LR
            }
        } else if (node.getBalanceFactor() < -1) {
            if (node.getright().getBalanceFactor() <= 0) {
                return rotateLeft(node); // Rotação LL
            } else {
                return rotateRightLeft(node); // Rotação RL
            }
        }

        return node;
    }

}
