import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bstTree = new BST();
        AVL avlTree = new AVL();

        int option;
        do {
            System.out.println("Menu:");
            System.out.println("1. Ler dados de arquivo");
            System.out.println("2. Análises na AVL");
            System.out.println("3. Inserir Programa");
            System.out.println("4. Buscar Programa");
            System.out.println("5. Remover Programa");
            System.out.println("6. Exibir a Altura das Árvores");
            System.out.println("7. Salvar dados em arquivo");
            System.out.println("8. Encerrar a Aplicação");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (option) {
                case 1:
                    lerDadosDeArquivo(bstTree, avlTree);
                    break;
                case 2:
                    // Implemente lógica para análises na AVL
                    break;
                case 3:
                    // Implemente lógica para inserir programa
                    break;
                case 4:
                    // Implemente lógica para buscar programa
                    break;
                case 5:
                    // Implemente lógica para remover programa
                    break;
                case 6:
                    // Implemente lógica para exibir altura das árvores
                    break;
                case 7:
                    // Implemente lógica para salvar dados em arquivo
                    break;
                case 8:
                    System.out.println("Encerrando a aplicação.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 8);
    }

    private static void lerDadosDeArquivo(BST bstTree, AVL avlTree) {
        String fileName = "/home/matteo/Documentos/Projetos/Java/Netflix/titles.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir a linha em campos usando a vírgula como delimitador
                String[] fields = line.split(",");

                // Verificar se há pelo menos 15 campos (os atributos mencionados)
                if (fields.length >= 15) {
                    // Criar um objeto ProgramaNetflix com base nos campos
                    ProgramaNetflix programa = new ProgramaNetflix(
                            fields[0],  // id
                            fields[1],  // título
                            fields[2],  // showType
                            fields[3],  // descrição
                            Integer.parseInt(fields[4]),  // releaseYear
                            fields[5],  // ageCertification
                            Integer.parseInt(fields[6]),  // runtime
                            fields[7],  // generos
                            fields[8],  // productionCountries
                            Integer.parseInt(fields[9]),  // temporadas
                            fields[10],  // imdbId
                            Double.parseDouble(fields[11]),  // imdbScore
                            Integer.parseInt(fields[12]),  // imdbVotes
                            Double.parseDouble(fields[13]),  // tmdbPopularity
                            Double.parseDouble(fields[14])  // tmdbScore
                    );

                    // Inserir o objeto nas árvores BST e AVL
                    bstTree.insert(programa);
                    avlTree.insert(programa);
                } else {
                    // Lidar com linhas que não têm informações suficientes (por exemplo, registros incompletos)
                    System.out.println("Ignorando linha incompleta: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
