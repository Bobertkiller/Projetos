class AVL {
    private Node root;

    // Construtor
    public AVL() {
        this.root = null;
    }

    // Métodos de inserção e busca
    public void insert(ProgramaNetflix programa) {
        root = insertRec(root, programa);
    }

    private Node insertRec(Node root, ProgramaNetflix programa) {
        // Adicione a lógica de inserção AVL aqui
        if (root == null) {
            return new Node(programa);
        }

        if (programa.getId().compareTo(root.programa.getId()) < 0) {
            root.left = insertRec(root.left, programa);
        } else if (programa.getId().compareTo(root.programa.getId()) > 0) {
            root.right = insertRec(root.right, programa);
        } else {
            // Não permita elementos duplicados (se necessário, ajuste conforme sua lógica)
            return root;
        }

        // Atualize a altura do nó atual
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        // Verifique o fator de equilíbrio e faça as rotações necessárias
        int balance = getBalance(root);

        // Casos de rotação
        // Esquerda-Esquerda
        if (balance > 1 && programa.getId().compareTo(root.left.programa.getId()) < 0) {
            return rotateRight(root);
        }
        // Direita-Direita
        if (balance < -1 && programa.getId().compareTo(root.right.programa.getId()) > 0) {
            return rotateLeft(root);
        }
        // Esquerda-Direita
        if (balance > 1 && programa.getId().compareTo(root.left.programa.getId()) > 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        // Direita-Esquerda
        if (balance < -1 && programa.getId().compareTo(root.right.programa.getId()) < 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
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

    // Métodos AVL adicionais conforme necessário

    // Outros métodos AVL conforme necessário

    // Métodos auxiliares para balanceamento AVL
    private int getHeight(Node node) {
        return (node != null) ? node.height : 0;
    }

    private int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza alturas
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza alturas
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Classe interna Node para representar os nós da árvore AVL
    private static class Node {
        private ProgramaNetflix programa;
        private Node left, right;
        private int height;

        public Node(ProgramaNetflix programa) {
            this.programa = programa;
            this.left = this.right = null;
            this.height = 1;
        }
    }
}