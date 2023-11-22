// Definição da classe ProgramaNetflix
class ProgramaNetflix {
    private String id;
    private String titulo;
    private String showType;
    private String descricao;
    private int releaseYear;
    private String ageCertification;
    private int runtime;
    private String generos;
    private String productionCountries;
    private int temporadas;
    private String imdbId;
    private double imdbScore;
    private int imdbVotes;
    private double tmdbPopularity;
    private double tmdbScore;

    // Construtor
    public ProgramaNetflix(String id, String titulo, String showType, String descricao,
                           int releaseYear, String ageCertification, int runtime, String generos,
                           String productionCountries, int temporadas, String imdbId, double imdbScore,
                           int imdbVotes, double tmdbPopularity, double tmdbScore) {
        this.id = id;
        this.titulo = titulo;
        this.showType = showType;
        this.descricao = descricao;
        this.releaseYear = releaseYear;
        this.ageCertification = ageCertification;
        this.runtime = runtime;
        this.generos = generos;
        this.productionCountries = productionCountries;
        this.temporadas = temporadas;
        this.imdbId = imdbId;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
        this.tmdbPopularity = tmdbPopularity;
        this.tmdbScore = tmdbScore;
    }

    // Getters e Setters (para cada atributo)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAgeCertification() {
        return ageCertification;
    }

    public void setAgeCertification(String ageCertification) {
        this.ageCertification = ageCertification;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public double getTmdbPopularity() {
        return tmdbPopularity;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public double getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(double tmdbScore) {
        this.tmdbScore = tmdbScore;
    }
}
