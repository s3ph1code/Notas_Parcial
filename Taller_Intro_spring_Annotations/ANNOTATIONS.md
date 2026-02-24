# Annotations usadas en la version con Spring Annotations

Este documento describe las anotaciones usadas en esta version y cuando usarlas.

## @Configuration
- **Que hace**: Marca una clase como fuente de definicion de beans.
- **Cuando usarla**: Cuando quieras reemplazar XML con configuracion Java.
- **Ejemplo aqui**: `com.icesi.taller_intro_spring.config.AppConfig`.

## @ComponentScan
- **Que hace**: Habilita el escaneo de componentes para encontrar clases anotadas.
- **Cuando usarla**: Cuando quieres registrar automaticamente `@Service`, `@Repository`, etc.
- **Ejemplo aqui**: `@ComponentScan("com.icesi.taller_intro_spring")` en `AppConfig`.

## @Service
- **Que hace**: Marca una clase como servicio de negocio.
- **Cuando usarla**: En clases que contienen logica de negocio y orquestan repositorios.
- **Ejemplo aqui**: `ArtistService`, `TrackService`.

## @Repository
- **Que hace**: Marca una clase como repositorio de datos.
- **Cuando usarla**: En clases que gestionan acceso a datos (listas, BD, etc.).
- **Ejemplo aqui**: `ArtistRepository`, `TrackRepository`.

## @Autowired
- **Que hace**: Indica a Spring que inyecte la dependencia automaticamente.
- **Cuando usarla**: En constructores (recomendado) o campos que requieren un bean.
- **Ejemplo aqui**: Constructores de `ArtistService` y `TrackService`.

## @Component
- **Que hace**: Marca una clase como componente generico administrado por Spring.
- **Cuando usarla**: Cuando no aplica `@Service`, `@Repository` o `@Controller`.
- **Ejemplo**: Utilidades, validadores, mapeadores.

## @Bean
- **Que hace**: Declara un bean dentro de una clase `@Configuration`.
- **Cuando usarla**: Para registrar clases de terceros o cuando necesitas controlar la creacion.
- **Ejemplo**: `@Bean public ObjectMapper objectMapper() { ... }`.

## @Primary
- **Que hace**: Define el bean por defecto cuando hay multiples candidatos.
- **Cuando usarla**: Cuando existen varias implementaciones del mismo tipo.

## @Qualifier
- **Que hace**: Selecciona un bean especifico por nombre.
- **Cuando usarla**: En inyeccion con multiples beans del mismo tipo.
- **Ejemplo**: `@Qualifier("artistRepository")`.

## @Scope
- **Que hace**: Define el scope del bean (`singleton`, `prototype`, `request`, `session`, etc.).
- **Cuando usarla**: Cuando el ciclo de vida no debe ser singleton.
- **Nota**: `request` y `session` se usan con Spring MVC.

## @Lazy
- **Que hace**: Retrasa la creacion del bean hasta que se necesite.
- **Cuando usarla**: Para mejorar tiempos de arranque o evitar dependencias pesadas.

## @DependsOn
- **Que hace**: Fuerza el orden de inicializacion de beans.
- **Cuando usarla**: Cuando un bean requiere que otro ya exista.

## @Value
- **Que hace**: Inyecta valores desde propiedades o literales.
- **Cuando usarla**: Para configuracion externa con `application.properties` o similares.
- **Ejemplo**: `@Value("${app.name}")`.

## @PropertySource
- **Que hace**: Carga un archivo de propiedades en el contexto.
- **Cuando usarla**: Si necesitas leer propiedades personalizadas.
- **Ejemplo**: `@PropertySource("classpath:app.properties")`.

## @Profile
- **Que hace**: Activa beans segun un perfil (`dev`, `prod`, etc.).
- **Cuando usarla**: Para separar configuraciones por ambiente.

## @Import
- **Que hace**: Importa otra configuracion `@Configuration`.
- **Cuando usarla**: Para modularizar configuraciones.

## @PostConstruct
- **Que hace**: Ejecuta un metodo despues de crear e inyectar el bean.
- **Cuando usarla**: Inicializacion adicional.

