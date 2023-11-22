class BST {
    private Node root;

    // Construtor
    public BST() {
        this.root = null;
    }

    // Métodos de inserção e busca
    public void insert(ProgramaNetflix programa) {
        root = insertRec(root, programa);
    }

    private Node insertRec(Node root, ProgramaNetflix programa) {
        if (root == null) {
            root = new Node(programa);
            return root;
        }

        // Adicione a lógica de comparação para decidir em qual subárvore inserir
        if (programa.getId().compareTo(root.programa.getId()) < 0) {
            root.left = insertRec(root.left, programa);
        } else if (programa.getId().compareTo(root.programa.getId()) > 0) {
            root.right = insertRec(root.right, programa);
        }

        return root;
    }

    public ProgramaNetflix search(String id) {
        return searchRec(root, id);
    }

    private ProgramaNetflix searchRec(Node root, String id) {
        // Caso base: a árvore ou subárvore está vazia ou encontramos o nó com o ID correspondente
        if (root == null || root.programa.getId().equals(id)) {
            return (root != null) ? root.programa : null;
        }

        // Se o ID a ser pesquisado for menor que o ID do nó atual, busca na subárvore esquerda
        if (id.compareTo(root.programa.getId()) < 0) {
            return searchRec(root.left, id);
        }

        // Se o ID a ser pesquisado for maior que o ID do nó atual, busca na subárvore direita
        return searchRec(root.right, id);
    }

    // Outros métodos BST conforme necessário

    // Classe interna Node para representar os nós da árvore
    private static class Node {
        private ProgramaNetflix programa;
        private Node left, right;

        public Node(ProgramaNetflix programa) {
            this.programa = programa;
            this.left = this.right = null;
        }
    }
}
