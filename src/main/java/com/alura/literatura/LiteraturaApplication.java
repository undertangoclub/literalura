package com.alura.literatura;

import com.alura.literatura.client.BookApiClient;
import com.alura.literatura.model.Book;
import com.alura.literatura.model.Person;
import com.alura.literatura.model.Autor;
import com.alura.literatura.repository.AutorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	private final BookApiClient bookApiClient = new BookApiClient();
	private final String apiEndpoint = "https://gutendex.com/books?search=";

	// Listas para almacenar libros y autores consultados
	private final List<Book> librosConsultados = new ArrayList<>();
	private final List<Person> autoresConsultados = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean isRunning = true;

		while (isRunning) {
			System.out.println("Bienvenido al sistema de consulta de libros de Gutendex. Elige una opción:");
			System.out.println("1. Buscar libros por autor o título");
			System.out.println("2. Listar todos los libros consultados");
			System.out.println("3. Listar libros por idioma");
			System.out.println("4. Listar autores de los libros consultados");
			System.out.println("5. Listar autores vivos en un año específico");
			System.out.println("6. Mostrar estadísticas de libros por idioma");
			System.out.println("7. Salir");

			System.out.print("Seleccione una opción: ");
			int userOption = scanner.nextInt();
			scanner.nextLine(); // Consumir el salto de línea

			switch (userOption) {
				case 1:
					buscarLibros(scanner);
					break;
				case 2:
					listarLibrosConsultados();
					break;
				case 3:
					listarLibrosPorIdioma(scanner);
					break;
				case 4:
					listarAutores();
					break;
				case 5:
					listarAutoresVivosEnAnio(scanner);
					break;
				case 6:
					mostrarEstadisticasPorIdioma(scanner);
					break;
				case 7:
					System.out.println("Saliendo del sistema. ¡Hasta luego!");
					isRunning = false;
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
			}
		}

		scanner.close();
	}

	private void buscarLibros(Scanner scanner) {
		System.out.print("Ingrese el nombre del autor o título para buscar: ");
		String query = scanner.nextLine();

		try {
			// Codificar la consulta para asegurar que se manejen bien los espacios y caracteres especiales
			String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

			// Enviar la solicitud a la API con la búsqueda codificada del usuario
			List<Book> books = bookApiClient.fetchBooks(apiEndpoint + encodedQuery);

			// Verificar si se encontraron libros y quedarse solo con el primero
			if (books.isEmpty()) {
				System.out.println("No se encontraron resultados para la búsqueda: " + query);
			} else {
				// Obtener el primer libro de los resultados y mostrar la información
				Book firstBook = books.get(0);
				System.out.println("Título: " + firstBook.getTitle());
				System.out.println("Autores: " + firstBook.getAuthors());
				System.out.println("Idioma: " + firstBook.getLanguage());
				System.out.println("Número de descargas: " + firstBook.getDownloadCount());
				System.out.println("---");

				// Guardar el primer libro consultado
				librosConsultados.add(firstBook);
				Person mainAuthor = firstBook.getMainAuthor();
				if (mainAuthor != null && !autoresConsultados.contains(mainAuthor)) {
					autoresConsultados.add(mainAuthor);
				}
			}
		} catch (IOException | InterruptedException e) {
			System.out.println("Error al realizar la búsqueda: " + e.getMessage());
		}
	}

	private void listarLibrosConsultados() {
		if (librosConsultados.isEmpty()) {
			System.out.println("No hay libros consultados.");
		} else {
			librosConsultados.forEach(book -> {
				System.out.println("Título: " + book.getTitle());
				System.out.println("Autores: " + book.getAuthors());
				System.out.println("Idioma: " + book.getLanguage());
				System.out.println("Número de descargas: " + book.getDownloadCount());
				System.out.println("---");
			});
		}
	}

	private void listarLibrosPorIdioma(Scanner scanner) {
		System.out.print("Ingrese el idioma para listar los libros (ejemplo: en, fi): ");
		String idioma = scanner.nextLine();

		List<Book> librosFiltrados = librosConsultados.stream()
				.filter(libro -> idioma.equals(libro.getLanguage())) // Solo compara si el idioma no es nulo
				.toList();

		if (librosFiltrados.isEmpty()) {
			System.out.println("No se encontraron libros en el idioma: " + idioma);
		} else {
			librosFiltrados.forEach(libro -> {
				System.out.println("Título: " + libro.getTitle());
				System.out.println("Autores: " + libro.getAuthors());
				System.out.println("Idioma: " + libro.getLanguage());
				System.out.println("Número de descargas: " + libro.getDownloadCount());
				System.out.println("---");
			});
		}
	}

	private void listarAutores() {
		if (autoresConsultados.isEmpty()) {
			System.out.println("No hay autores consultados.");
		} else {
			autoresConsultados.forEach(author -> {
				System.out.println("Nombre: " + author.getName());
				System.out.println("Año de nacimiento: " + author.getBirthYear());
				System.out.println("Año de muerte: " + author.getDeathYear());
				System.out.println("---");
			});
		}
	}

	private void listarAutoresVivosEnAnio(Scanner scanner) {
		System.out.print("Ingrese el año para listar los autores que estaban vivos en ese momento: ");
		int anio = scanner.nextInt();

		// Usar el repositorio para encontrar autores vivos en el año especificado
		List<Autor> autoresVivos = autorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(anio, anio);

		if (autoresVivos.isEmpty()) {
			System.out.println("No se encontraron autores vivos en el año especificado.");
		} else {
			autoresVivos.forEach(autor -> {
				System.out.println("Nombre: " + autor.getNombre());
				System.out.println("Año de nacimiento: " + autor.getBirthYear());
				System.out.println("Año de muerte: " + (autor.getDeathYear() != null ? autor.getDeathYear() : "Aún vivo"));
				System.out.println("---");
			});
		}
	}

	private void mostrarEstadisticasPorIdioma(Scanner scanner) {
		System.out.print("Ingrese el idioma para el que desea ver la cantidad de libros (ejemplo: en, es): ");
		String idioma = scanner.nextLine();

		// Usar el repositorio para contar los libros por idioma
		long cantidadLibros = libroRepository.countByIdioma(idioma);
		System.out.println("Cantidad de libros en el idioma '" + idioma + "': " + cantidadLibros);
	}
}
