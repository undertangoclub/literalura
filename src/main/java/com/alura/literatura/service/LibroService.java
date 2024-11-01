package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro guardarLibroConAutor(Libro libro) {
        // Primero verifica si el autor ya existe en la base de datos
        Autor autor = libro.getAutor();
        Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());

        if (autorExistente.isPresent()) {
            // Si el autor ya existe, lo obtenemos del Optional
            libro.setAutor(autorExistente.get());
        } else {
            // Si el autor no existe, lo guardamos
            autor = autorRepository.save(autor);
            libro.setAutor(autor);
        }

        // Luego guarda el libro con el autor relacionado
        return libroRepository.save(libro);
    }
}
