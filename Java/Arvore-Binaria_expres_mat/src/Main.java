import java.util.Scanner;

import Arvore_mat.ArvoreAritimetica;
import Arvore_mat.BinaryTree;
import misc.Valida;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expressao = null;
        BinaryTree arvore = null;

        while (true) {
            System.out.println("\n\nMenu de Opções:");
            System.out.println("1. Entrada da expressão aritmética na notação infixa.");
            System.out.println("2. Criação da árvore binária de expressão aritmética.");
            System.out.println("3. Exibição da árvore binária de expressão aritmética.");
            System.out.println("4. Cálculo da expressão.");
            System.out.println("5. Fim do programa.");
            System.out.print("Escolha: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch(escolha){
                case 1:
                    System.out.println("Insira a expressão a seguir: ");
                    expressao = scanner.nextLine();

                    if (Valida.valida(expressao)){

                        System.out.println("A Expressão é valida ✓");  
                    }else{
                        System.out.println("A Expressão é invalida ✗");
                        expressao = null;
                        System.out.println("Favor Insira outra Espressão.");
                    }
                    break;
                case 2:
                    if(expressao != null){
                        arvore = ArvoreAritimetica.criarArvore(expressao);
                        System.out.println("Árvore criada com sucesso.");
                    }else{
                        System.out.println("Insira uma Expressão valida antes de tentar cirar uma arvore");
                    }
                    break;
                case 3:
                    if(expressao != null){
                        if (arvore != null) {
                            System.out.println("Percurso em ordem (in-order): ");
                            arvore.inOrderTraversal();
                            System.out.println("\nPercurso em pré-ordem (pre-order): ");
                            arvore.preOrderTraversal();
                            System.out.println("\nPercurso em pós-ordem (post-order): ");
                            arvore.postOrderTraversal();
                        } else {
                            System.out.println("Erro na criação da árvore Ou árvore Inexistente.");
                        }
                    }else{
                        System.out.println("Insira uma Expressão valida antes de tentar criar a arvore, para depois exibi-la");
                    }
                    break;
                case 4:
                    if(expressao != null){
                        if (arvore != null) {
                            float resp = ArvoreAritimetica.calcularExpressao(arvore);
                            System.out.println("\nResultado da expressão: " + resp);
                        }else{
                            System.out.println("Arvore Inexistente.");
                        }
                    }else{
                        System.out.println("Insira uma Expressão valida para criar a arvore, antes de tentar resolve-la");
                    }
                    break;
                case 5:
                    System.out.println("Fim do Programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Deu ruim, Insira um numero valido");

            }
        }
    }
}
