package Avl;

public class BT {
    private Node root;

    public BT() {
        this.root = null;
    }

    public Node getroot() {
        return root;
    }

    public void setroot(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getDegree() {
        if (root == null) {
            return 0;
        }
        return root.getDegree();
    }

    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return root.getHeight();
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getleft());
            System.out.print(node.getdata() + " ");
            inOrderTraversal(node.getright());
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.getdata() + " ");
            preOrderTraversal(node.getleft());
            preOrderTraversal(node.getright());
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.getleft());
            postOrderTraversal(node.getright());
            System.out.print(node.getdata() + " ");
        }
    }

    // Novo método público para obter o fator de balanceamento da raiz
    public int getBalanceFactor() {
        return (root != null) ? root.getBalanceFactor() : 0;
    }

    public void insert(int chave) {
        root = insert(root, null, chave);
    }
    
    private Node insert(Node node, Node parent, int chave) {
        if (node == null) {
            Node novoNo = new Node(chave);
            novoNo.setparent(parent); // Configurar o nó pai
            return novoNo;
        }
    
        if (chave < node.getdata()) {
            node.setleft(insert(node.getleft(), node, chave));
        } else if (chave > node.getdata()) {
            node.setright(insert(node.getright(), node, chave));
        }
    
        node.updateBalanceFactor();
    
        return node;
    }
}
