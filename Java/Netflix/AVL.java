import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    if (root.programa.getReleaseYear() >= startYear && root.programa.getReleaseYear() <= endYear) {
        getTitlesReleasedBetweenYearsRec(root.left, startYear, endYear, result);
        result.add(root.programa);
        getTitlesReleasedBetweenYearsRec(root.right, startYear, endYear, result);
    } else if (root.programa.getReleaseYear() < startYear) {
        getTitlesReleasedBetweenYearsRec(root.right, startYear, endYear, result);
    } else {
        getTitlesReleasedBetweenYearsRec(root.left, startYear, endYear, result);
    }
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
    List<ProgramaNetflix> topMovies = getTopMovies(10); // You can adjust the number of movies
    System.out.println("Top 10 Movies:");
    for (ProgramaNetflix movie : topMovies) {
        System.out.println("Movie: " + movie.getTitulo() + ", IMDb Score: " + movie.getImdbScore());
    }
}

    private List<ProgramaNetflix> getTopMovies(int n) {
    List<ProgramaNetflix> topMovies = new ArrayList<>();
    getTopMoviesRec(root, topMovies, n);
    return topMovies;
}

    private void getTopMoviesRec(Node root, List<ProgramaNetflix> result, int n) {
    if (root != null) {
        // In-order to get movies with the highest IMDb scores first
        getTopMoviesRec(root.left, result, n);

        ProgramaNetflix movie = root.programa;
        result.add(movie);

        if (result.size() == n) {
            return; // Already found the top N movies
        }

        getTopMoviesRec(root.right, result, n);
    }
}

private void printPrograms(List<ProgramaNetflix> programas) {
    for (ProgramaNetflix programa : programas) {
        System.out.println(programa);  // Você pode querer substituir isso pelo formato desejado
    }
}


public void opcoes_analise() {
    Scanner scanner = new Scanner(System.in);
    int option;

    do {
        System.out.println("Opções de Análise:");
        System.out.println("1. Top 5 Títulos");
        System.out.println("2. Títulos Lançados Entre Anos X e Y");
        System.out.println("3. Top Títulos com Certificação e Gênero");
        System.out.println("4. Títulos com Menores Valores de Tmdb Score");
        System.out.println("5. Top 10 Filmes");
        System.out.println("6. Voltar para o Menu Principal");
        System.out.print("Escolha uma opção de análise: ");

        option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (option) {
            case 1:
                List<ProgramaNetflix> topTitles = getTopTitles();
                printPrograms(topTitles);
                //getTopTitles();
                break;
            case 2:
                getTitlesReleasedBetweenYears(2000,2010);
                break;
            case 3:
                getTopTitlesWithCertificationAndGenre("PG", "Comedy", 10);
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

}