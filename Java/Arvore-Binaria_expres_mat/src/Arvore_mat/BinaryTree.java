//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

package Arvore_mat;

public class BinaryTree{
    private Node root;


    public BinaryTree(){
        this.root = null;
    }

    public Node getroot(){
        return root;
    }

    public void setroot(Node root){
        this.root = root;
    }
     
    public boolean isEmpty(){
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
    
    

}


