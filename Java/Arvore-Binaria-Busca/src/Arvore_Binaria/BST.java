//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

package Arvore_Binaria;

public class BST extends BinaryTree{

    private Node root;

    public BST() {
        super(null);
    }

    public Node search(String data) {
        return search(root, data);
    }

    private Node search(Node node, String data) {
        if (node == null || node.getdata().equals(data)) {
            return node;
        }

        if (data.compareTo(node.getdata()) < 0) {
            return search(node.getleft(), data);
        } else {
            return search(node.getright(), data);
        }
    }

    public void insert(String data) {
        root = insert(root, data);
    }

    private Node insert(Node node, String data) {
        if (node == null) {
            return new Node(data);
        }

        if (data.compareTo(node.getdata()) < 0) {
            node.setleft(insert(node.getleft(), data));
        } else if (data.compareTo(node.getdata()) > 0) {
            node.setright(insert(node.getright(), data));
        }

        return node;
    }

    public void remove(String data) {
        root = remove(root, data);
    }

    private Node remove(Node node, String data) {
        if (node == null) {
            return node;
        }

        if (data.compareTo(node.getdata()) < 0) {
            node.setleft(remove(node.getleft(), data));
        } else if (data.compareTo(node.getdata()) > 0) {
            node.setright(remove(node.getright(), data));
        } else {
            if (node.getleft() == null) {
                return node.getright();
            } else if (node.getright() == null) {
                return node.getleft();
            }

            node.setdata(findMin(node.getright()).getdata());
            node.setright(remove(node.getright(), node.getdata()));
        }

        return node;
    }

    public Node findMin() {
        return findMin(root);
    }

    private Node findMin(Node node) {
        while (node.getleft() != null) {
            node = node.getleft();
        }
        return node;
    }

    public Node findMax() {
        return findMax(root);
    }

    private Node findMax(Node node) {
        while (node.getright() != null) {
            node = node.getright();
        }
        return node;
    }

    public Node findPredecessor(String data) {
        Node target = search(data);
        if (target == null) {
            return null;
        }
        return findPredecessor(root, target);
    }

    private Node findPredecessor(Node current, Node target) {
        if (current == null) {
            return null;
        }

        if (target.getleft() != null) {
            return findMax(target.getleft());
        }

        Node predecessor = null;
        while (current != null) {
            if (target.getdata().compareTo(current.getdata()) > 0) {
                predecessor = current;
                current = current.getright();
            } else if (target.getdata().compareTo(current.getdata()) < 0) {
                current = current.getleft();
            } else {
                break;
            }
        }

        return predecessor;
    }

    public Node findSuccessor(String data) {
        Node target = search(data);
        if (target == null) {
            return null;
        }
        return findSuccessor(root, target);
    }

    private Node findSuccessor(Node current, Node target) {
        if (current == null) {
            return null;
        }

        if (target.getright() != null) {
            return findMin(target.getright());
        }

        Node successor = null;
        while (current != null) {
            if (target.getdata().compareTo(current.getdata()) < 0) {
                successor = current;
                current = current.getleft();
            } else if (target.getdata().compareTo(current.getdata()) > 0) {
                current = current.getright();
            } else {
                break;
            }
        }

        return successor;
    }

    public void clear() {
         root = null;
    }

}
