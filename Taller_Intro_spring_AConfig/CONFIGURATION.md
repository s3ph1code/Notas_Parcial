# Configuracion por archivo (Java Config)

Este proyecto usa inyeccion de dependencias **solo** por archivo de configuracion Java. No hay XML ni anotaciones en las clases de servicio/repositorio.

## Archivo clave

- `src/main/java/com/icesi/taller_intro_spring/bootstrap/AppConfig.java`

Aqui se declaran todos los beans (repositorios y servicios) y sus dependencias.

## Como funciona el flujo

1. El servidor arranca la aplicacion web.
2. `AppContextListener` carga `AppConfig` con `AnnotationConfigApplicationContext`.
3. Spring crea los beans definidos en `AppConfig`.
4. `DataInitializer` carga datos iniciales usando los servicios.
5. Los servicios se guardan en `ServletContext` para que los servlets los reutilicen.

## Ejemplo de beans

```java
@Configuration
public class AppConfig {
    @Bean
    public ArtistRepository artistRepository() {
        return new ArtistRepository();
    }

    @Bean
    public TrackRepository trackRepository() {
        return new TrackRepository();
    }

    @Bean
    public ArtistService artistService(ArtistRepository artistRepository) {
        return new ArtistService(artistRepository);
    }

    @Bean
    public TrackService trackService(TrackRepository trackRepository, ArtistRepository artistRepository) {
        return new TrackService(trackRepository, artistRepository);
    }
}
```

## Como agregar un nuevo servicio

1. Crea la clase del repositorio (si aplica) y la clase del servicio con constructor.
2. Declara un metodo `@Bean` en `AppConfig` para cada dependencia.
3. Si necesitas usar el servicio en un servlet, obtenlo desde `ServletContext`.

Ejemplo de acceso desde un servlet:

```java
MyService myService = (MyService) getServletContext().getAttribute("myService");
```

## Reglas importantes

- No uses `@Component`, `@Service`, `@Repository` ni `@Autowired`.
- Toda la inyeccion debe estar en `AppConfig`.
- Si un constructor cambia, actualiza el metodo `@Bean` correspondiente.
