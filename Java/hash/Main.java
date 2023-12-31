//Matteo Domiciano Varnier - 32158238
//Felipe Mazzeo Barbosa - 32257023

package hash;

public class Main {
    public static void main(String[] args) {
        // Criando e testando a HashTableChaining

        // Tamanho da tabela hash
        int tableSize = 10;

        // Criando uma instância da tabela hash com encadeamento
        HashTable hashTable = new HashTableChaining(tableSize);
        HashTable hashTable2 = new HashTableOA(tableSize);


        //Inserindo testes do HashTableChaining

        System.out.println("\n-----------Testando HashTableChaining-----------\n");

        // Inserindo valores na tabela hash
        System.out.println(hashTable.insert(10, "Hello"));
        System.out.println(hashTable.insert(20, "World"));
        System.out.println(hashTable.insert(30, "of"));
        System.out.println(hashTable.insert(40, "Hash"));
        System.out.println(hashTable.insert(50, "Tables"));

        // Testando busca por chaves existentes e inexistentes
        System.out.println("Valor associado à chave 20: " + hashTable.get(20));
        System.out.println("Valor associado à chave 25: " + hashTable.get(25));

        // Removendo chave e valor da tabela hash
        System.out.println("Remoção da chave 20: " + hashTable.remove(20));
        System.out.println("Chave 20 após remoção: " + hashTable.get(20));
        
        // Tentando inserir um valor em uma chave já existente
        System.out.println(hashTable.insert(10, "Hey"));
        // Tentando remover uma chave inexistente
        System.out.println("Remoção da chave 15: " + hashTable.remove(15));

        //Inserindo testes do HashTableOA

        System.out.println("\n-----------Testando HashTableOA-----------\n");

        System.out.println(hashTable2.insert(10, "Hello"));
        System.out.println(hashTable2.insert(20, "World"));
        System.out.println(hashTable2.insert(30, "of"));
        System.out.println(hashTable2.insert(40, "Hash"));
        System.out.println(hashTable2.insert(50, "Tables"));

        // Testando busca por chaves existentes e inexistentes
        System.out.println("Valor associado à chave 20: " + hashTable2.get(20));
        System.out.println("Valor associado à chave 25: " + hashTable2.get(25));

        // Removendo chave e valor da tabela hash
        System.out.println("Remoção da chave 20: " + hashTable2.remove(20));
        System.out.println("Chave 20 após remoção: " + hashTable2.get(20));
        
        // Tentando inserir um valor em uma chave já existente
        System.out.println(hashTable2.insert(10, "Hey"));
        // Tentando remover uma chave inexistente
        System.out.println("Remoção da chave 15: " + hashTable2.remove(15));
    }
}
