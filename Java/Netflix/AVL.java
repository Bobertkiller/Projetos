import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class AVL {
    private Node root;

    // Construtor
    public AVL() {
        this.root = null;
    }

    // Métodos de inserção e busca
    public void insert(ProgramaNetflix programa) {
        root = insertRec(root, programa);
    }

    private Node insertRec(Node root, ProgramaNetflix programa) {
        // Adicione a lógica de inserção AVL aqui
        if (root == null) {
            return new Node(programa);
        }

        if (programa.getId().compareTo(root.programa.getId()) < 0) {
            root.left = insertRec(root.left, programa);
        } else if (programa.getId().compareTo(root.programa.getId()) > 0) {
            root.right = insertRec(root.right, programa);
        } else {
            // Não permita elementos duplicados (se necessário, ajuste conforme sua lógica)
            return root;
        }

        // Atualize a altura do nó atual
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        // Verifique o fator de equilíbrio e faça as rotações necessárias
        int balance = getBalance(root);

        // Casos de rotação
        // Esquerda-Esquerda
        if (balance > 1 && programa.getId().compareTo(root.left.programa.getId()) < 0) {
            return rotateRight(root);
        }
        // Direita-Direita
        if (balance < -1 && programa.getId().compareTo(root.right.programa.getId()) > 0) {
            return rotateLeft(root);
        }
        // Esquerda-Direita
        if (balance > 1 && programa.getId().compareTo(root.left.programa.getId()) > 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        // Direita-Esquerda
        if (balance < -1 && programa.getId().compareTo(root.right.programa.getId()) < 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public ProgramaNetflix search(String id) {
        return searchRec(root, id);
    }

    private ProgramaNetflix searchRec(Node root, String id) {
        // Caso base: a árvore ou subárvore está vazia ou encontramos o nó com o ID correspondente
        if (root == null || root.programa.getId().equals(id)) {
            return (root != null) ? root.programa : null;
        }

        // Se o ID a ser pesquisado for menor que o ID do nó atual, busca na subárvore esquerda
        if (id.compareTo(root.programa.getId()) < 0) {
            return searchRec(root.left, id);
        }
        // Se o ID a ser pesquisado for maior que o ID do nó atual, busca na subárvore direita
        return searchRec(root.right, id);
    }

    // Métodos AVL adicionais conforme necessário

    // Outros métodos AVL conforme necessário

    // Métodos auxiliares para balanceamento AVL
    private int getHeight(Node node) {
        return (node != null) ? node.height : 0;
    }

    private int getBalance(Node node) {
        return (node != null) ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza alturas
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza alturas
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Classe interna Node para representar os nós da árvore AVL
    private static class Node {
        private ProgramaNetflix programa;
        private Node left, right;
        private int height;

        public Node(ProgramaNetflix programa) {
            this.programa = programa;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    public List<ProgramaNetflix> getTopTitles() {
    List<ProgramaNetflix> result = new ArrayList<>();
    getTopTitlesRec(root, result);
    // Sort the result list based on IMDb score in descending order
    result.sort(Comparator.comparing(ProgramaNetflix::getImdbScore).reversed());
    // Return the top 5 titles or fewer if the list is smaller
    return result.subList(0, Math.min(result.size(), 5));
}

private void getTopTitlesRec(Node root, List<ProgramaNetflix> result) {
    if (root == null) {
        return;
    }

    getTopTitlesRec(root.left, result);
    result.add(root.programa);
    getTopTitlesRec(root.right, result);
}

    public List<ProgramaNetflix> getTitlesReleasedBetweenYears(int startYear, int endYear) {
    List<ProgramaNetflix> result = new ArrayList<>();
    getTitlesReleasedBetweenYearsRec(root, startYear, endYear, result);
    return result;
}

    private void getTitlesReleasedBetweenYearsRec(Node root, int startYear, int endYear, List<ProgramaNetflix> result) {
    if (root == null) {
        return;
    }

    // Traverse left subtree
    getTitlesReleasedBetweenYearsRec(root.left, startYear, endYear, result);

    // Check if the current node's release year is within the specified range
    if (root.programa.getReleaseYear() >= startYear && root.programa.getReleaseYear() <= endYear) {
        result.add(root.programa);
    }

    // Traverse right subtree
    getTitlesReleasedBetweenYearsRec(root.right, startYear, endYear, result);
}


    public List<ProgramaNetflix> getTopTitlesWithCertificationAndGenre(String certification, String genre, int n) {
    List<ProgramaNetflix> topTitles = new ArrayList<>();
    getTopTitlesWithCertificationAndGenreRec(root, certification, genre, topTitles, n);
    return topTitles;
}

    private void getTopTitlesWithCertificationAndGenreRec(Node root, String certification, String genre,
                                                        List<ProgramaNetflix> result, int n) {
    if (root != null) {
        // Em ordem inversa para obter os maiores tmdb_score primeiro
        getTopTitlesWithCertificationAndGenreRec(root.right, certification, genre, result, n);

        ProgramaNetflix programa = root.programa;
        if (programa.getAgeCertification().equals(certification) && programa.getGeneros().contains(genre)) {
            result.add(programa);
        }

        if (result.size() == n) {
            return; // Já encontramos os top N
        }

        getTopTitlesWithCertificationAndGenreRec(root.left, certification, genre, result, n);
    }
}

    public List<ProgramaNetflix> getLowestTmdbScoreTitles(int n) {
    List<ProgramaNetflix> lowestTmdbScoreTitles = new ArrayList<>();
    getLowestTmdbScoreTitlesRec(root, lowestTmdbScoreTitles, n);
    return lowestTmdbScoreTitles;
}

    private void getLowestTmdbScoreTitlesRec(Node root, List<ProgramaNetflix> result, int n) {
    if (root != null) {
        // Em ordem para obter os menores tmdb_score primeiro
        getLowestTmdbScoreTitlesRec(root.left, result, n);

        ProgramaNetflix programa = root.programa;
        result.add(programa);

        if (result.size() == n) {
            return; // Já encontramos os N menores
        }

        getLowestTmdbScoreTitlesRec(root.right, result, n);
    }
}

private void displayTop10Movies() {
    List<ProgramaNetflix> topMovies = getTopMovies(10);
    System.out.println("Top 10 Movies:");

    if (topMovies.isEmpty()) {
        System.out.println("No top movies found.");
    } else {
        for (ProgramaNetflix movie : topMovies) {
            System.out.println("Titulo: " + movie.getTitulo() + " | Avaliação: " + movie.getImdbScore());
        }
    }
}

private List<ProgramaNetflix> getTopMovies(int n) {
    List<ProgramaNetflix> result = new ArrayList<>();
    getTopMoviesRec(root, result, n);

    // Print details of all titles for debugging
    System.out.println("Details of All Titles:");
    result.forEach(movie -> System.out.println("Title: " + movie.getTitulo() + " | Show Type: " + movie.getShowType() + " | IMDb Score: " + movie.getImdbScore()));

    // Filter out movies with invalid IMDb scores
    result = result.stream()
            .filter(movie -> {
                boolean isMovie = "MOVIE".equalsIgnoreCase(movie.getShowType());
                boolean hasValidScore = movie.getImdbScore() > 0;
                if (!isMovie || !hasValidScore) {
                    // Print details of excluded titles for debugging
                    System.out.println("Excluded Title: " + movie.getTitulo() + " | Show Type: " + movie.getShowType() + " | IMDb Score: " + movie.getImdbScore());
                }
                return isMovie && hasValidScore;
            })
            .collect(Collectors.toList());

    // Sort the result list based on IMDb score in descending order
    result.sort(Comparator.comparing(ProgramaNetflix::getImdbScore).reversed());

    // Return the top n titles or fewer if the list is smaller
    return result.subList(0, Math.min(result.size(), n));
}

private void getTopMoviesRec(Node root, List<ProgramaNetflix> result, int n) {
    if (root != null && result.size() < n) {
        // Reverse in-order traversal
        getTopMoviesRec(root.right, result, n);

        ProgramaNetflix content = root.programa;
        // Check if the content is a movie and has a valid IMDb score before adding it to the result list
        if ("MOVIE".equalsIgnoreCase(content.getShowType()) && content.getImdbScore() > 0) {
            result.add(content);
        }

        // Print IMDb score for debugging
        System.out.println("Title: " + content.getTitulo() + " | IMDb Score: " + content.getImdbScore());

        if (result.size() < n) {
            // If we haven't reached the required number of movies, continue searching
            getTopMoviesRec(root.left, result, n);
        }
    }
}

private void printPrograms(List<ProgramaNetflix> programas) {
    for (ProgramaNetflix programa : programas) {
        System.out.println("Título: " + programa.getTitulo() + " | Ano de Lançamento: " + programa.getReleaseYear() + " | Avaliação: " + programa.getImdbScore());  // Você pode querer substituir isso pelo formato desejado
    }
}


public void opcoes_analise() {
    Scanner scanner = new Scanner(System.in);
    int option;

    do {
        System.out.println("\nOpções de Análise:");
        System.out.println("1. Top 5 Títulos");
        System.out.println("2. Títulos Lançados Entre Anos X e Y");
        System.out.println("3. Top Títulos com Certificação e Gênero");
        System.out.println("4. Títulos com Menores Valores de Tmdb Score");
        System.out.println("5. Top 10 Filmes");
        System.out.println("6. Voltar para o Menu Principal");
        System.out.print("Escolha uma opção de análise: ");

        option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("");

        switch (option) {
            case 1:
                List<ProgramaNetflix> topTitles = getTopTitles();
                printPrograms(topTitles);
                //getTopTitles();
                break;
            case 2:
                List<ProgramaNetflix> titlesBetweenYears = getTitlesReleasedBetweenYears(2000, 2010);
                System.out.println("Títulos Lançados Entre 2000 e 2010:");
                printPrograms(titlesBetweenYears);
                break;
            case 3:
                List<ProgramaNetflix> topTitlesWithCertificationAndGenre = getTopTitlesWithCertificationAndGenre("TV-14", "Crime", 10);
                printPrograms(topTitlesWithCertificationAndGenre);
                break;
            case 4:
                getLowestTmdbScoreTitles(5);
                break;
            case 5:
                displayTop10Movies();
                break;
            case 6:
                System.out.println("Retornando ao Menu Principal.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

    } while (option != 6);
    scanner.close();
}

public int getHeight() {
    return getHeight(root);
}

    // Inside the AVL class
public List<String> getAllDataAsStringList() {
    List<String> result = new ArrayList<>();
    getAllDataAsStringListRec(root, result);
    return result;
}

private void getAllDataAsStringListRec(Node root, List<String> result) {
    if (root != null) {
        getAllDataAsStringListRec(root.left, result);
        result.add(root.programa.toString()); // Assuming you have overridden the toString() method in ProgramaNetflix class
        getAllDataAsStringListRec(root.right, result);
    }
}


}