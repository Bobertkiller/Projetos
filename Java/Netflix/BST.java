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
        return searchRec(root, id, 0);
    }

    private ProgramaNetflix searchRec(Node root, String id, int comparacoes) {
        // Caso base: a árvore ou subárvore está vazia ou encontramos o nó com o ID correspondente
        if (root == null || root.programa.getId().equals(id)) {
            comparacoes++;
            return (root != null) ? root.programa : null;
        }

        comparacoes++;
        // Se o ID a ser pesquisado for menor que o ID do nó atual, busca na subárvore esquerda
        if (id.compareTo(root.programa.getId()) < 0) {
            return searchRec(root.left, id, comparacoes);
        }

        // Se o ID a ser pesquisado for maior que o ID do nó atual, busca na subárvore direita
        return searchRec(root.right, id, comparacoes);
    }

    void exibirResultadoBusca(ProgramaNetflix programa, int comparacoes, long tempoExecucao) {
        if (programa != null) {
            System.out.println("Programa encontrado na BST:");
            System.out.println(programa);
        } else {
            System.out.println("Programa não encontrado na BST.");
        }

        System.out.println("Número de comparações na BST: " + comparacoes);
        System.out.println("Tempo de execução na BST: " + tempoExecucao + " nanosegundos");
    }

    // Na classe BST
    public boolean remove(String id) {
        if (root == null) {
            return false; // Árvore vazia, nada para remover
        }

        // Chama o método auxiliar para realizar a remoção recursiva
        root = removeRec(root, id);
        return true; // Remoção bem-sucedida
    }

    // Método auxiliar recursivo para remover um nó específico
    private Node removeRec(Node root, String id) {
        if (root == null) {
            return null; // Nó não encontrado
        }

        // Compara o ID para decidir em qual subárvore procurar
        int compareResult = id.compareTo(root.programa.getId());
        if (compareResult < 0) {
            root.left = removeRec(root.left, id);
        } else if (compareResult > 0) {
            root.right = removeRec(root.right, id);
        } else {
            // Caso 1: Nó folha ou caso 2: Nó com um filho
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Caso 3: Nó com dois filhos
            root.programa = findMin(root.right).programa;
            root.right = removeRec(root.right, root.programa.getId());
        }

        return root;
    }

    // Método auxiliar para encontrar o nó mínimo em uma subárvore
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Classe interna Node para representar os nós da árvore
    private static class Node {
        private ProgramaNetflix programa;
        private Node left, right;

        public Node(ProgramaNetflix programa) {
            this.programa = programa;
            this.left = this.right = null;
        }
    }

    public int getHeight() {
        return getHeight(root);
    }
    
    private int getHeight(Node node) {
        return (node != null) ? Math.max(getHeight(node.left), getHeight(node.right)) + 1 : 0;
    }
    
}
