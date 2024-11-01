package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Método derivado para encontrar un autor por su nombre
    Optional<Autor> findByNombre(String nombre);

    // Método derivado para encontrar autores que estaban vivos en un año determinado
    List<Autor> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int birthYear, int deathYear);

}