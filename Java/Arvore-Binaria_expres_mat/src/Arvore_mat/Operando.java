package Arvore_mat;

public class Operando extends Node {
    private float num;

    public Operando(float num) {
        super(Float.toString(num)); // Converte o valor float para string
        this.num = num;
    }
    
    
    @Override
    public float visitar() {
        
        return num; // Retorna o valor do operando quando visitado.
    }

}
