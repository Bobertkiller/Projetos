//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

package Avl;

public class Node {
    private int data;
    private Node parent;
    private Node left;
    private Node right;
    private int balanceFactor; // Novo atributo para o fator de balanceamento

    public Node(int data){
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.balanceFactor = 0; // Inicialmente, o fator de balanceamento é 0
    }

    public int getdata(){
        return data;
    }

    public void setdata(int data){
        this.data = data;
    }

    public Node getparent(){
        return parent;
    }

    public void setparent(Node parent){
        this.parent = parent;
    }

    public Node getleft(){
        return left;
    }

    public void setleft(Node left){
        this.left = left;
    }

    public Node getright(){
        return right;
    }

    public void setright(Node right){
        this.right = right;
    }

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    public int getDegree() {
        int degree = 0;
        if (left != null) degree++;
        if (right != null) degree++;
        return degree;
    }

    public int getLevel() {
        int level = 0;
        Node current = this;
        while (current.parent != null) {
            level++;
            current = current.parent;
        }
        return level;
    }

    public int getHeight() {
        int leftHeight = 0;
        if (left != null) {
            leftHeight = left.getHeight() + 1;
        }

        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.getHeight() + 1;
        }

        return Math.max(leftHeight, rightHeight);
    }

    // Novo método público para obter o fator de balanceamento
    public int getBalanceFactor() {
        return balanceFactor;
    }

    // Novo método privado para atualizar o fator de balanceamento
    void updateBalanceFactor() {
        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;
        balanceFactor = leftHeight - rightHeight;
    }

    @Override
    public String toString(){
        return "Data: " + data + ", Parent: " + (parent != null ? parent.getdata() : "null")
               + ", Esquerda: " + (left != null ? left.getdata() : "null" )
               + ", Direita: " + (right != null ? right.getdata() : "null" )
               + ", Raiz? " + isRoot()
               + ", Folha? " + isLeaf()
               + ", Grau: " + getDegree()
               + ", Nivel: " + getLevel()
               + ", Altura: " + getHeight()
               + ", Balance Factor: " + balanceFactor; // Inclua o fator de balanceamento
    }
}
