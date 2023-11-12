//Matteo Domiciano Varnier - 32158238
//Felipe Mazzeo Barbosa - 32257023

package hash;

import java.util.LinkedList;

public class HashTableChaining implements HashTable {
    private int size;
    private LinkedList<Entry>[] table;

    private class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public HashTableChaining(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public String get(int key) {
        int index = hashFunction(key);
        LinkedList<Entry> list = table[index];

        for (Entry entry : list) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
        }

        return null; // Chave não encontrada
    }

    @Override
    public String insert(int key, String value) {
        int index = hashFunction(key);
        LinkedList<Entry> list = table[index];

        for (Entry entry : list) {
            if (entry.getKey() == key) {
                entry.setValue(value);
                return "valor da chave atualizado";
            }
        }

        list.add(new Entry(key, value));
        return "chave-valor inserido";
    }

    @Override
    public boolean remove(int key) {
        int index = hashFunction(key);
        LinkedList<Entry> list = table[index];

        for (Entry entry : list) {
            if (entry.getKey() == key) {
                list.remove(entry);
                return true;
            }
        }

        return false; // Chave não encontrada para remoção
    }

    // Método de função hash
    private int hashFunction(int key) {
        return key % size;
    }

    // Outros métodos, se necessário
}
