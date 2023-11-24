import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
                    avlTree.opcoes_analise();
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

    private static String[] splitCSVLine(String line) {
    List<String> fields = new ArrayList<>();
    boolean aspas = false;
    StringBuilder atual = new StringBuilder();

    for (char c : line.toCharArray()) {
        if (c == '"') {
            aspas = !aspas;
        } else if (c == ',' && !aspas) {
            fields.add(atual.toString().trim());
            atual.setLength(0);  // limpa o campo atual
        } else {
            atual.append(c);
        }
    }

    // adiciona o campo final
    fields.add(atual.toString().trim());

    return fields.toArray(new String[0]);
    }


    private static void lerDadosDeArquivo(BST bstTree, AVL avlTree) {
        String fileName = "assets/titles.csv";

        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Dividir a linha em campos usando a vírgula como delimitador
                String[] fields = splitCSVLine(line);

                boolean taVazio = false;
                for (String field : fields) {
                    if (field.isEmpty()) {
                        taVazio = true;
                        break;
                    }
                }

                // Verificar se há pelo menos 15 campos (os atributos mencionados)
                if (taVazio) {
                    // Lidar com linhas que não têm informações suficientes (por exemplo, registros incompletos)
                    System.out.println("Ignorando linha incompleta: " + line);
                } else if(fields.length == 15) {
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
                            Float.parseFloat(fields[9]),  // temporadas
                            fields[10],  // imdbId
                            Float.parseFloat(fields[11]),  // imdbScore
                            Float.parseFloat(fields[12]),  // imdbVotes
                            Float.parseFloat(fields[13]),  // tmdbPopularity
                            Float.parseFloat(fields[14])  // tmdbScore
                    );

                    // Inserir o objeto nas árvores BST e AVL
                    bstTree.insert(programa);
                    avlTree.insert(programa);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

}
