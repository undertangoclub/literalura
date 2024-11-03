# Literatura API - Proyecto de Consulta de Libros Gutendex

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-yellow)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-orange)

## 📚 Descripción del Proyecto

Bienvenido al proyecto **Literatura API**, una aplicación de consola desarrollada en Java para la consulta de libros y autores de la API de Gutendex, la cual permite a los usuarios buscar información sobre libros, listar libros por idioma y visualizar detalles sobre autores, incluyendo los que estaban vivos en un año determinado. Este proyecto es parte de los desafíos propuestos por Alura Latam en el programa ONE.

## 🛠️ Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Spring Boot 3.3.5**: Framework para la construcción de la aplicación web.
- **PostgreSQL**: Base de datos para almacenar la información de libros y autores.
- **Maven**: Gestor de dependencias y build para el proyecto.
- **Hibernate**: ORM (Mapeo objeto-relacional) para la gestión de las entidades.
- **Gutendex API**: API de consulta de libros públicos de dominio gratuito.

## 🛡️ Funcionalidades del Proyecto

- `Buscar libros por autor o título`: Permite al usuario buscar información de un libro específico.
- `Listar libros consultados`: Muestra todos los libros que han sido consultados durante la sesión.
- `Listar libros por idioma`: Filtra los libros previamente consultados por el idioma seleccionado.
- `Listar autores`: Muestra todos los autores de los libros consultados.
- `Listar autores vivos en un año específico`: Muestra los autores que estaban vivos en el año ingresado.
- `Mostrar estadísticas de libros por idioma`: Despliega la cantidad de libros por idioma según la base de datos almacenada.

## 💻 Acceso al Proyecto

Puedes descargar el proyecto clónandolo desde el repositorio de GitHub:

```bash
$ git clone https://github.com/undertangoclub/literalura.git
```

## 🛠️ Abre y ejecuta el proyecto

Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio.
2. Configura tu base de datos PostgreSQL y asegura la conexión utilizando las credenciales adecuadas en el archivo `application.properties`.
3. Construye el proyecto con Maven:
   ```bash
   $ mvn clean install
   ```
4. Ejecuta la aplicación:
   ```bash
   $ mvn spring-boot:run
   ```

## 👨‍💻 Personas Desarrolladoras del Proyecto

| [<img src="https://avatars.githubusercontent.com/u/75939161?s=400&u=a5276f62490bfed25b35f40410b8014b20b59364&v=4" width=115><br><sub>Pablo Guillermo Cieslik</sub>](https://github.com/undertangoclub) |
| :---: |

## ✉ Contacto

- Email: pablocieslik@gmail.com.com
- GitHub: [Undertangoclub](https://github.com/undertangoclub)

## 📜 Licencia

Este proyecto está bajo la licencia MIT. Puedes consultar los términos en el archivo `LICENSE` del repositorio.

## 📈 Estado del Proyecto

<h4 align="center">✅ Proyecto Completado ✅</h4>

## 🎥 Video del Proyecto

Puedes ver el video explicativo del proyecto en el siguiente enlace:

[![Video de Presentación](https://img.shields.io/badge/YouTube-Watch-red)](https://youtu.be/k30J8RuB-xQ)

## 🛡️ Referencias

- [Gutendex API Documentation](https://gutendex.com/docs/)
- [Guía para escribir un README increíble en GitHub](https://alura.com/blog/como-escribir-un-readme-increible)

