# Guia completa de desarrollo (sin Spring Boot) para un parcial tipo Taller 1

## Checklist inicial (para no perderse)
- Crear paquetes: `model`, `repository`, `service`, `servlet`, `bootstrap`, `webapp/WEB-INF/views`, `resources`.
- Modelos: `Artist` y `Track` con listas para la relacion many-to-many.
- Repositorios en memoria con `save`, `findAll`, `findById`, `deleteById`, `findByName`.
- Servicios con validacion y logica de negocio.
- Inicializacion: 10 artistas, 50 tracks, 5 tracks por artista.
- Servlets + JSP para listar, crear, buscar, eliminar.
- Inyeccion de dependencias: version XML, annotations, y configuracion.

---

## 0) Estructura del proyecto (como organizar el codigo)

**Paquetes sugeridos:**
- `model`: POJOs (Artist, Track).
- `repository`: datos en memoria.
- `service`: logica y validaciones.
- `servlet`: controladores HTTP.
- `bootstrap`: inicializacion de datos.
- `webapp/WEB-INF/views`: JSP.
- `resources`: XML de Spring.

**Regla de oro:**
- El servlet NO guarda datos. Solo recibe peticiones y llama servicios.
- El servicio NO hace HTML. Solo logica.
- El JSP NO tiene logica compleja. Solo presenta datos.

---

## 1) Modelos (POJOs)

### Artist
Campos obligatorios del ejercicio:
- `id: long`
- `name: String`
- `nationality: String`
- `tracks: List<Track>`

Reglas:
- Constructor vacio.
- Getters y setters.
- La lista nunca debe ser `null`.

Ejemplo de getter seguro:
```java
public List<Track> getTracks() {
    if (tracks == null) {
        tracks = new ArrayList<>();
    }
    return tracks;
}
```

### Track
Campos obligatorios:
- `id: long`
- `title: String`
- `genre: String`
- `duration: String`
- `albumTitle: String`
- `artists: List<Artist>`

Getter seguro (igual que en Artist).

### Relacion many-to-many
Siempre que agregues un artista a un track, agrega el track al artista:
```java
track.getArtists().add(artist);
artist.getTracks().add(track);
```

---

## 2) Repositorios (in-memory)

### ArtistRepository
Responsabilidad: almacenar la lista de artistas.

Metodos minimos:
- `save(Artist artist)`
- `findAll()`
- `findById(long id)`
- `findByName(String name)`
- `deleteById(long id)`

Puntos clave:
- `save` asigna ID incremental.
- `findAll` devuelve copia defensiva.
- `findByName` usa `equalsIgnoreCase` + `trim`.
- Nunca usar `==` con String.

### TrackRepository
Mismos metodos pero sin `findByName`.

---

## 3) Servicios (logica + validaciones)

### ArtistService
Metodos:
- `createArtist(String name, String nationality)`
  - validar `name` no vacio.
  - `trim()` para normalizar.
- `getAllArtists()`
- `getArtistByName(String name)`
- `deleteArtistById(long id)`

### TrackService
Metodos:
- `createTrack(String title, String genre, String duration, String albumTitle, List<Long> artistIds)`
  - validar `title` y `artistIds`.
  - `trim()` opcional en strings.
  - buscar cada artista por id y asociar en ambos lados.
- `getAllTracks()`
- `deleteTrackById(long id)`

Errores tipicos:
- No validar `artistIds`.
- Asociar solo en un lado.

---

## 4) Inicializacion de datos

Requisito:
- 10 artistas
- 50 tracks
- 5 tracks por artista

Estrategia simple:
- crear 10 artistas.
- por cada artista crear 5 tracks.
- asociar cada track con ese artista.

---

## 5) Servlets (controladores HTTP)

Reglas:
- `GET` muestra formulario o listado.
- `POST` ejecuta accion y luego redirect.

Servlets que casi siempre piden:
- `/artists` (GET): listado
- `/artists/new` (GET/POST): crear
- `/artists/search` (GET): buscar por nombre + mostrar tracks
- `/artists/delete` (GET/POST): eliminar por id
- `/tracks` (GET): listado con artistas
- `/tracks/new` (GET/POST): crear con artistas
- `/tracks/delete` (GET/POST): eliminar

Patron minimo:
1) Servlet obtiene datos del servicio.
2) `request.setAttribute`.
3) `forward` a JSP.

---

## 6) JSP (vistas)

### Que es JSP
Es HTML con Java embebido. Sirve para mostrar datos del servlet.

### Reglas basicas
- `<%= %>` imprime.
- `<% %>` bloque Java.
- Guardar JSP en `/WEB-INF/views`.
- El servlet hace `forward` a la vista.

### Ejemplo de JSP con lista
```jsp
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.taller_intro_spring.model.Artist" %>
<% List<Artist> artists = (List<Artist>) request.getAttribute("artists"); %>
<table>
  <% for (Artist a : artists) { %>
    <tr>
      <td><%= a.getId() %></td>
      <td><%= a.getName() %></td>
    </tr>
  <% } %>
</table>
```

### Ejemplo de JSP con formulario
```jsp
<form method="post" action="/artists/new">
  <input type="text" name="name" required />
  <input type="text" name="nationality" />
  <button type="submit">Guardar</button>
</form>
```

---

## 7) Inyeccion de dependencias (3 versiones)

### Version 1: XML
- Crear `applicationContext.xml` en `resources`.
- Declarar beans con `<bean>`.
- Cargar el contexto en un `ServletContextListener`.

### Version 2: Annotations
- En repositorios: `@Repository`.
- En servicios: `@Service`.
- Crear clase `@Configuration` con `@ComponentScan`.

### Version 3: Configuracion (Java Config)
- Clase `@Configuration`.
- Metodos `@Bean` para repos y services.

---

## 8) Errores comunes
- Comparar String con `==`.
- No inicializar listas.
- Guardar track sin asociar artistas.
- No validar entradas nulas.
- JSP en raiz (deben ir en `WEB-INF/views`).

---

## 9) Mini flujo mental del parcial
1. Crear modelos y relacion.
2. Crear repositorios.
3. Crear servicios con validaciones.
4. Inicializar datos.
5. Crear servlets y JSP.
6. Configurar DI.
7. Probar listados y formularios.

---

# Guia practica: AppContextListener, Servlets y JSP (sin Spring Boot)

## 1) AppContextListener: que es, para que sirve y como se usa

### Idea practica (en palabras simples)
En una app con servlets necesitas **crear una sola vez** los servicios y repositorios, y compartirlos con todos los servlets. Si cada servlet hiciera `new` cada vez, perderias datos y tendrias inconsistencia. El `AppContextListener` se ejecuta **cuando arranca la aplicacion web** y es el lugar perfecto para **crear e inicializar** todo.

### Que hace en la practica
- Arranca la aplicacion.
- Carga el contexto de Spring (XML).
- Obtiene los beans (ArtistService, TrackService).
- Carga datos iniciales (seed).
- Guarda los servicios en el `ServletContext` para que los servlets los puedan usar.

### Flujo real (paso a paso)
1. El servidor inicia tu app.
2. Se llama `contextInitialized` del listener.
3. Se lee `applicationContext.xml`.
4. Spring crea `ArtistRepository`, `TrackRepository`, `ArtistService`, `TrackService`.
5. Se llama `DataInitializer.seed(...)`.
6. Se guardan los servicios en `ServletContext`.
7. Los servlets ya pueden usar esos servicios.

### Codigo real (resumido)
```java
public class AppContextListener implements ServletContextListener {
  private ClassPathXmlApplicationContext applicationContext;

  public void contextInitialized(ServletContextEvent sce) {
    applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    ArtistService artistService = applicationContext.getBean(ArtistService.class);
    TrackService trackService = applicationContext.getBean(TrackService.class);

    DataInitializer.seed(artistService, trackService);

    ServletContext ctx = sce.getServletContext();
    ctx.setAttribute("artistService", artistService);
    ctx.setAttribute("trackService", trackService);
  }

  public void contextDestroyed(ServletContextEvent sce) {
    if (applicationContext != null) {
      applicationContext.close();
    }
  }
}
```

### Por que se crea?
- Para **no repetir** la creacion de servicios en cada servlet.
- Para asegurar que todos los servlets usan **la misma instancia**.
- Para inicializar datos solo **una vez**.

---

## 2) Servlets: como funcionan de verdad (explicacion practica)

### Idea base
Un servlet es un **controlador**: recibe una peticion, decide que hacer y devuelve una respuesta. En tu taller, el servlet no hace HTML directamente, sino que **prepara datos** y los envia a un JSP.

### Flujo mental (muy practico)
- Usuario entra a una URL.
- El servlet de esa URL se ejecuta.
- El servlet llama al servicio.
- El servlet guarda datos en `request`.
- El servlet manda a un JSP para renderizar HTML.

### GET vs POST en la vida real
- **GET**: mostrar pagina o formulario.
- **POST**: procesar un formulario.

Ejemplo real: crear artista
1) Usuario abre `/artists/new`.
2) `doGet` muestra el formulario.
3) Usuario envia el formulario.
4) `doPost` valida y guarda.
5) Redirige al listado `/artists`.

### Codigo real (minimo)
```java
@WebServlet("/artists/new")
public class ArtistCreateServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/artist-form.jsp").forward(req, res);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("name");
    String nationality = req.getParameter("nationality");

    ArtistService artistService = (ArtistService) getServletContext().getAttribute("artistService");
    try {
      artistService.createArtist(name, nationality);
      res.sendRedirect(req.getContextPath() + "/artists");
    } catch (IllegalArgumentException ex) {
      req.setAttribute("error", ex.getMessage());
      req.getRequestDispatcher("/WEB-INF/views/artist-form.jsp").forward(req, res);
    }
  }
}
```

### Que debe saber un profesor que sabes
- Sabes separar GET (mostrar) y POST (guardar).
- Sabes recuperar parametros con `request.getParameter`.
- Sabes pasar datos con `request.setAttribute`.
- Sabes usar `forward` vs `sendRedirect`.

**Regla de oro:**
- `forward` = misma peticion, se muestran errores.
- `redirect` = nueva peticion, para volver a listado.

---

## 3) JSP: como funciona de forma practica

### Idea base
JSP es una forma rapida de escribir HTML que **puede leer datos Java** enviados por el servlet.

Tu servlet manda datos con:
```java
request.setAttribute("artists", artists);
```
En JSP los lees con:
```jsp
<% List<Artist> artists = (List<Artist>) request.getAttribute("artists"); %>
```

### Ejemplo practico: listado de artistas
**Servlet:**
```java
List<Artist> artists = artistService.getAllArtists();
request.setAttribute("artists", artists);
request.getRequestDispatcher("/WEB-INF/views/artists-list.jsp").forward(request, response);
```

**JSP:**
```jsp
<%@ page import="java.util.List" %>
<%@ page import="com.icesi.taller_intro_spring.model.Artist" %>
<% List<Artist> artists = (List<Artist>) request.getAttribute("artists"); %>

<table>
  <% for (Artist a : artists) { %>
    <tr>
      <td><%= a.getId() %></td>
      <td><%= a.getName() %></td>
      <td><%= a.getNationality() %></td>
    </tr>
  <% } %>
</table>
```

### Ejemplo practico: mostrar errores en formulario
**Servlet:**
```java
request.setAttribute("error", "Nombre requerido");
request.getRequestDispatcher("/WEB-INF/views/artist-form.jsp").forward(request, response);
```

**JSP:**
```jsp
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
  <p style="color:red;"><%= error %></p>
<% } %>
```

### Por que JSP va en WEB-INF/views
Para que nadie abra el JSP directamente en el navegador. Siempre se debe entrar a traves del servlet.

---

## 4) Mini ejemplo completo (un flujo entero)

### Objetivo
- Mostrar un formulario para crear artista.
- Guardar el artista.
- Volver al listado.

### Flujo
1) GET `/artists/new` -> JSP con formulario.
2) POST `/artists/new` -> guardar en servicio.
3) Redirect a `/artists`.
4) GET `/artists` -> JSP con lista.

Este flujo es el patron que repites para casi todo en el parcial.

---

## 5) Consejos practicos finales
- Si no sabes por donde empezar: crea modelos, luego repos, luego servicios, luego servlets y JSP.
- Siempre separa: servlet (control), service (logica), JSP (vista).
- Si algo no aparece en la pagina, revisa que el servlet este usando `setAttribute` y el JSP lo este leyendo.
- Si sale error de null: revisa listas y validaciones.
