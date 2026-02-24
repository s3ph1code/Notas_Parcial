# Taller Intro Spring - Servlets

## Rutas disponibles

- `/artists` listado de artistas.
- `/artists/new` crear artista.
- `/artists/search` buscar artista por nombre.
- `/artists/delete` eliminar artista por id.
- `/tracks` listado de tracks.
- `/tracks/new` crear track con artistas.
- `/tracks/delete` eliminar track por id.

## Notas

- Los servicios se inicializan en `AppContextListener` y se cargan datos en `DataInitializer`.
- La aplicacion expone los formularios desde `index.jsp`.

## Build

```powershell
.\mvnw.cmd -DskipTests package
```

