package Arvore_mat;

public class operando extends Node {
    private float num;

    public operando(String data, float num){
        super(data);
        this.num = num;
    }

    public float visita_op() {
        return Float.NaN;
    }
    
    @Override
    public float visitar() {
        
        return num; // Retorna o valor do operando quando visitado.
    }

}
