# Cursos TECSUP (Compose + Room + Retrofit)

Aplicación móvil que permite consultar y sincronizar cursos disponibles en TECSUP. La app persiste información en el dispositivo con Room, consume un servicio REST con Retrofit y presenta la lista de cursos usando Jetpack Compose dentro de una arquitectura MVVM.

## Características principales
- Lista de cursos en una `LazyColumn` con diseño moderno.
- Botón “Sincronizar” que borra la base local, descarga la última versión desde la API y muestra un `Snackbar` de confirmación.
- Datos precargados en Room para que la app funcione sin conexión desde el primer arranque.
- Manejo de estados (cargando, vacío, éxito, error) y mensajes amigables para el usuario.

## Arquitectura
- **UI (Compose y ViewModel):**
  - `CursosScreen` renderiza la interfaz y reacciona a los cambios de estado.
  - `CursoViewModel` expone un `StateFlow` con los cursos, gestiona el botón de sincronización y emite mensajes.
- **Domain / Data:**
  - `Curso` (modelo de dominio) y `CursoEntity` (modelo Room).
  - `CursoDao`, `CursoDatabase`: operaciones y configuración de Room.
  - `CursoDto`, `CursoApiService`, `RetrofitClient`: integración con Retrofit.
  - `CursoRepository`: orquestador que obtiene datos remotos, los guarda en Room y expone flujos para la UI.
- **Application:**
  - `CursoApplication` inicializa Room y registra cursos locales de ejemplo la primera vez que se instala la app.

## Tecnologías utilizadas
- Kotlin, Coroutines y Flow.
- Jetpack Compose (Material 3, íconos extendidos).
- Room (DAO, Database, Entities).
- Retrofit + OkHttp Logging Interceptor + Gson Converter.
- MVVM sin framework extra (ViewModel + Repository).

## Configuración rápida
1. Crea un recurso `cursos` en [MockAPI](https://mockapi.io/) u otro servicio similar con los campos requeridos.
2. Copia la URL base (termina con `/`) y reemplázala en `API_BASE_URL` dentro de `app/build.gradle.kts`.
3. Sincroniza Gradle y ejecuta la app.
4. Para volver a ver los cursos precargados, desinstala la app o borra sus datos antes de abrirla nuevamente.

## Estructura de datos
Cada curso contiene:
```json
{
  "id": 1,
  "nombre": "Construcción y Prueba de Software",
  "docente": "Jaime Farfán",
  "creditos": 3,
  "ciclo": "IV"
}
```

## Licencia
Proyecto académico para el curso de Programación Móvil en TECSUP. Uso educativo.

