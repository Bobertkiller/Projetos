package  hash;

// arquivo: HashTable.java
public interface HashTable {
    String get(int key);
    String insert(int key, String value);
    boolean remove(int key);
}
