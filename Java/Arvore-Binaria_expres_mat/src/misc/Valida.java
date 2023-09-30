//Felipe Mazzeo Barbosa TIA- 32257023
//Matteo Domiciano Varnier TIA - 32158238

package misc;

import java.util.Stack;

public class Valida {
    public static boolean valida(String expressao) {
        // Remove espaços em branco
        expressao = expressao.replaceAll("\\s+", "");

        // Pilha para verificar parênteses
        Stack<Character> pilha = new Stack<>();
        
        // Variável para acompanhar o tipo do último caractere
        char ultimoCaractere = '\0';

        for (char caractere : expressao.toCharArray()) {
            if (caractere == '(') {
                pilha.push('(');
                ultimoCaractere = '(';
            } else if (caractere == ')') {
                if (pilha.isEmpty() || pilha.pop() != '(') {
                    return false;
                }
                ultimoCaractere = ')';
            } else if (Character.isDigit(caractere) || (caractere == '.' && ultimoCaractere != '.')) {
                // Se for dígito ou ponto decimal (apenas um ponto é permitido), continue
                ultimoCaractere = caractere;
            } else if ("+-*/".indexOf(caractere) != -1) {
                // Se for um operador válido, continue
                ultimoCaractere = caractere;
            } else {
                return false; // Caractere inválido
            }
        }

        return pilha.isEmpty() && ultimoCaractere != '('; // A pilha deve estar vazia e a expressão não deve terminar com '('
    }

}
