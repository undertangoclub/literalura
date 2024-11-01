package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String title;

    @JsonAlias("download_count")
    private Integer downloadCount;

    private List<Person> authors;

    // Para capturar el idioma principal del libro.
    @JsonAlias("languages")
    private List<String> languages;
    private String language;

    public String getLanguage() {
        if (languages != null && !languages.isEmpty()) {
            return languages.get(0); // Toma el primer idioma si está presente
        }
        return "unknown"; // Define "unknown" si no hay idioma
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    // Obtener el primer autor (si está disponible) de la lista de autores
    public Person getMainAuthor() {
        if (authors != null && !authors.isEmpty()) {
            return authors.get(0);
        }
        return null; // Devuelve null si no hay autores en la lista
    }

    // Otros getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", downloadCount=" + downloadCount +
                ", authors=" + authors +
                ", language='" + getLanguage() + '\'' +
                '}';
    }
}
