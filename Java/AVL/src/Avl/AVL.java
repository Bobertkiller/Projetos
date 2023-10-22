//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

package Avl;

public class AVL extends BT {

  public AVL() {
    super();
  }

  private Node rotateLeft(Node root) {
    Node newRoot = root.getright();
    root.setright(newRoot.getleft());
    newRoot.setleft(root);
    root.updateBalanceFactor();
    newRoot.updateBalanceFactor();
    return newRoot;
  }

  private Node rotateRight(Node root) {
    Node newRoot = root.getleft();
    root.setleft(newRoot.getright());
    newRoot.setright(root);
    root.updateBalanceFactor();
    newRoot.updateBalanceFactor();
    return newRoot;
  }

  private Node rotateLeftRight(Node root) {
    root.setleft(rotateLeft(root.getleft()));
    return rotateRight(root);
  }

  private Node rotateRightLeft(Node root) {
    root.setright(rotateRight(root.getright()));
    return rotateLeft(root);
  }

  public void insert(int chave) {
    super.insert(chave);
    root = balance(root);
  }

  private Node balance(Node node) {
    if (node == null) {
      return node;
    }

    node.updateBalanceFactor();

    if (node.getBalanceFactor() > 1) {
      if (node.getleft().getBalanceFactor() >= 0) {
        return rotateRight(node);
      } else {
        return rotateLeftRight(node);
      }
    } else if (node.getBalanceFactor() < -1) {
      if (node.getright().getBalanceFactor() <= 0) {
        return rotateLeft(node);
      } else {
        return rotateRightLeft(node);
      }
    }

    return node;
  }

  public void remove(int chave) {
    root = remove(root, chave);
  }

  private Node remove(Node node, int chave) {
    if (node == null) {
      return node;
    }

    if (chave < node.getdata()) {
      node.setleft(remove(node.getleft(), chave));
    } else if (chave > node.getdata()) {
      node.setright(remove(node.getright(), chave));
    } else {
      // Nó a ser removido encontrado

      if (node.getleft() == null || node.getright() == null) {
        Node temp = (node.getleft() != null) ? node.getleft() : node.getright();

        if (temp == null) {
          // Nó é uma folha ou tem um único filho
          temp = node;
          node = null;
        } else {
          // Nó tem um único filho
          node = temp;
        }
      } else {
        // Nó tem dois filhos
        Node temp = findMin(node.getright()); // Encontre o nó mínimo na subárvore direita
        node.setdata(temp.getdata()); // Copie o valor do nó mínimo
        node.setright(remove(node.getright(), temp.getdata())); // Remova o nó mínimo
      }
    }

    if (node == null) {
      return node;
    }

    node.updateBalanceFactor();

    // Balanceie a árvore após a remoção
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

  private Node findMin(Node node) {
    while (node.getleft() != null) {
      node = node.getleft();
    }
    return node;
  }

}

