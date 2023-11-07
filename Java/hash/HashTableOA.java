package hash;

// arquivo: HashTableOA.java
public class HashTableOA implements HashTable {
    private int size;
    private int[] keys;
    private String[] values;
    private boolean[] filled;

    public HashTableOA(int size) {
        this.size = size;
        this.keys = new int[size];
        this.values = new String[size];
        this.filled = new boolean[size];
    }

    @Override
    public String get(int key) {
        // Implementação para obter valor por chave usando endereçamento aberto
        return null; // Implemente a lógica para retornar o valor associado à chave
    }

    @Override
    public String insert(int key, String value) {
        // Implementação para inserir e retornar um dos valores indicados
        return "inseridos"; // Implemente a lógica correta de inserção
    }

    @Override
    public boolean remove(int key) {
        // Implementação para remover a chave e valor correspondente
        return true; // Implemente a lógica correta de remoção
    }

    // Outros métodos e lógica de hash para endereçamento aberto
}
