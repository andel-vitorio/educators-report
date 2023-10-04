package app.backend.entities;

import java.time.LocalDate;

/**
 * Classe que representa um artigo (paper).
 */
public class Paper {
    private String title; // Título do artigo.
    private String authors; // Autores do artigo.
    private LocalDate publicationDate; // Data de publicação do artigo.
    private String keywords; // Palavras-chave do artigo.
    private String description; // Descrição do artigo.
    private String category; // Categoria do artigo.
    private String url; // URL do artigo.
    private int id; // Identificador único para o artigo.

    /**
     * Define o identificador único para o artigo.
     *
     * @param id O identificador único.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o identificador único para o artigo.
     *
     * @return O identificador único.
     */
    public int getId() {
        return id;
    }

    /**
     * Define os autores do artigo.
     *
     * @param authors Os autores do artigo.
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * Define a categoria do artigo.
     *
     * @param category A categoria do artigo.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Define a descrição do artigo.
     *
     * @param description A descrição do artigo.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Define as palavras-chave do artigo.
     *
     * @param keywords As palavras-chave do artigo.
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Define a data de publicação do artigo.
     *
     * @param publicationDate A data de publicação do artigo.
     */
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Define o título do artigo.
     *
     * @param title O título do artigo.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Define a URL do artigo.
     *
     * @param url A URL do artigo.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtém os autores do artigo.
     *
     * @return Os autores do artigo.
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Obtém a categoria do artigo.
     *
     * @return A categoria do artigo.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a descrição do artigo.
     *
     * @return A descrição do artigo.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtém as palavras-chave do artigo.
     *
     * @return As palavras-chave do artigo.
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Obtém a data de publicação do artigo.
     *
     * @return A data de publicação do artigo.
     */
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    /**
     * Obtém o título do artigo.
     *
     * @return O título do artigo.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtém a URL do artigo.
     *
     * @return A URL do artigo.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Retorna uma representação em string do objeto Paper.
     *
     * @return Uma representação em string do objeto.
     */
    @Override
    public String toString() {
        return "Paper{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publicationDate=" + publicationDate +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
