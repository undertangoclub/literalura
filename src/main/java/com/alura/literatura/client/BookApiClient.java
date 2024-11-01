package com.alura.literatura.client;

import com.alura.literatura.model.ApiResponse;
import com.alura.literatura.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BookApiClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Constructor
    public BookApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Seguir redirecciones automáticamente
                .build();
        this.objectMapper = new ObjectMapper();
    }

    // Método para realizar la solicitud a la API
    public List<Book> fetchBooks(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error en la solicitud a la API: Código de estado " + response.statusCode());
        }

        // Convertir JSON a objetos Book
        ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);
        return apiResponse.getResults();
    }
}
