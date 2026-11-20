# APE 4 — Red social: Fases 1 y 2

**Autor:** Camacho Monta Josue Jampier  
**Asignatura:** Estructura de Datos  
**Modalidad:** Individual  
**Lenguaje:** Java

## Descripción

El proyecto representa una red social mediante un grafo. Permite registrar personas, gestionar amistades y seguimientos, consultar relaciones y eliminar usuarios junto con sus conexiones.

La fase 2 continúa el trabajo individual de la fase 1. Este repositorio conserva ambas versiones en carpetas separadas para revisar su evolución.

- [Repositorio original de la fase 1](https://github.com/jampiercamacho94-cloud/APE4-Fase1-Camacho-Josue)
- [Repositorio de entrega de la fase 2](https://github.com/jampiercamacho94-cloud/APE4-Fase2-Camacho-Josue)

## Organización del repositorio

| Ruta                       | Contenido                                                      |
| -------------------------- | -------------------------------------------------------------- |
| `fase1/src/`               | Código original de la primera fase.                            |
| `fase1/docs/`              | Documentación de la primera fase.                              |
| `fase1/evidencias/`        | Capturas de la primera fase.                                   |
| `fase2/src/fase2/`         | Código de la segunda fase.                                     |
| `fase2/test/fase2/`        | Pruebas automatizadas.                                         |
| `fase2/docs/EVIDENCIAS.md` | Descripción de las evidencias de ejecución.                    |
| `fase2/evidencias/`        | Capturas de la segunda fase.                                   |
| `.vscode/settings.json`    | Configuración de las carpetas de código en VS Code.            |
| `out/`                     | Archivos compilados locales, excluidos del seguimiento de Git. |

## Implementación de la fase 2

El grafo utiliza una lista enlazada de vértices. Cada vértice contiene un usuario y una lista enlazada de aristas salientes.

Los vértices y las relaciones se almacenan mediante nodos propios, sin utilizar HashMap, HashSet, ArrayList ni LinkedList como almacenamiento del grafo.

| Clase               | Responsabilidad                                                        |
| ------------------- | ---------------------------------------------------------------------- |
| `Usuario`           | Almacena el identificador y el nombre de una persona.                  |
| `Vertice`           | Contiene el usuario, sus aristas y la referencia al siguiente vértice. |
| `Arista`            | Contiene el destino, el tipo de relación y la siguiente arista.        |
| `TipoRelacion`      | Define los tipos `AMISTAD` y `SEGUIR`.                                 |
| `GrafoSocial`       | Implementa registros, relaciones, consultas y eliminaciones.           |
| `Main`              | Presenta el menú de demostraciones.                                    |
| `PruebaGrafoSocial` | Ejecuta 60 comprobaciones automatizadas.                               |

El archivo `Amistad.java` se conserva del código inicial. La implementación de la fase 2 gestiona las relaciones mediante `Arista` y `TipoRelacion`.

## Reglas de funcionamiento

- Los identificadores se almacenan en minúsculas.
- Las búsquedas admiten identificadores en mayúsculas o minúsculas.
- Se rechazan identificadores nulos, vacíos, con espacios o con comas.
- Se rechazan nombres nulos o en blanco.
- Una amistad se representa mediante dos aristas, una en cada dirección.
- Un seguimiento se representa mediante una sola arista dirigida.
- No se admiten relaciones consigo mismo ni duplicados del mismo tipo.
- Una amistad y un seguimiento pueden coexistir entre las mismas personas.
- Eliminar una amistad retira sus dos aristas.
- Eliminar un seguimiento conserva las demás relaciones.
- Eliminar una persona retira todas sus relaciones entrantes y salientes.

Las operaciones booleanas devuelven `false` cuando la solicitud no es válida o no puede realizarse.

Las consultas de listas devuelven `null` si el usuario no existe y `"[]"` si existe pero no tiene relaciones del tipo consultado.

El método `contarAmigos` devuelve `-1` cuando el usuario no existe.

## Datos de demostración

Se mantienen los usuarios y las amistades del ejemplo individual de la fase 1.

| Identificador | Nombre    | Amigos iniciales |
| ------------- | --------- | ---------------: |
| u1            | Irene     |                3 |
| u2            | Nicolás   |                2 |
| u3            | Paula     |                2 |
| u4            | Sergio    |                2 |
| u5            | Valentina |                1 |
| u6            | Emilia    |                0 |

Las cinco amistades iniciales son:

- Irene–Nicolás.
- Nicolás–Paula.
- Paula–Sergio.
- Sergio–Irene.
- Irene–Valentina.

Estas cinco amistades se representan internamente mediante diez aristas de tipo `AMISTAD`.

## Requisitos

- JDK 17 o superior.
- Terminal situada en la raíz del repositorio.

Los siguientes comandos están preparados para PowerShell en Windows.

## Compilar y ejecutar la fase 2

```powershell
New-Item -ItemType Directory -Force out/fase2 | Out-Null

javac -encoding UTF-8 -d out/fase2 fase2/src/fase2/*.java

if ($LASTEXITCODE -eq 0) {
    java -cp out/fase2 fase2.Main
}
```

### Menú

| Opción | Demostración                                                  |
| ------ | ------------------------------------------------------------- |
| 1      | Registro, red inicial y rechazo de identificadores inválidos. |
| 2      | Amistades, seguimientos y rechazo de relaciones no válidas.   |
| 3      | Eliminación de relaciones y personas.                         |
| 4      | Consultas de amigos, seguidos, seguidores y conteos.          |
| 0      | Salir.                                                        |

Cada opción crea una red nueva con los datos iniciales. Los cambios de una demostración no se conservan al seleccionar otra opción.

## Ejecutar las pruebas automatizadas

```powershell
New-Item -ItemType Directory -Force out/fase2 | Out-Null

javac -encoding UTF-8 -d out/fase2 fase2/src/fase2/*.java fase2/test/fase2/*.java

if ($LASTEXITCODE -eq 0) {
    java -cp out/fase2 fase2.PruebaGrafoSocial
}
```

Resultado obtenido:

```text
OK - Registro, validaciones y normalizacion
OK - Secuencia de comprobacion de la fase 2
OK - Direccion, duplicados y tipos independientes
OK - Eliminacion de vertices y referencias entrantes

Todas las pruebas pasaron.
Comprobaciones: 60
```

Las pruebas comprueban registros inválidos, normalización, duplicados, dirección de los seguimientos, independencia entre tipos de relación y consultas de usuarios inexistentes.

También verifican la eliminación de vértices al inicio, en medio y al final de la lista, la eliminación de referencias entrantes, el vaciado del grafo y el registro posterior de un nuevo usuario.

## Resultados principales

| Comprobación                                          | Resultado                           |
| ----------------------------------------------------- | ----------------------------------- |
| Red inicial                                           | 6 usuarios y 5 amistades.           |
| Amigos de Irene                                       | Valentina, Sergio y Nicolás.        |
| Amigos de Emilia                                      | Lista vacía y cantidad 0.           |
| Seguidores de Irene en la demostración                | Emilia y Nicolás.                   |
| Consulta de lista de un usuario inexistente           | `null`.                             |
| Conteo de amigos de un usuario inexistente            | `-1`.                               |
| Eliminar la amistad Irene–Valentina y después a Irene | 5 usuarios y 2 amistades restantes. |
| Eliminar nuevamente a Irene                           | `false`.                            |
| Pruebas automatizadas                                 | 60 comprobaciones superadas.        |

## Complejidad

Se utiliza `V` para la cantidad de vértices, `A` para la cantidad de aristas almacenadas y `d(u)` para la cantidad de aristas salientes de un usuario.

Las cotas consideran los recorridos de nodos y suponen un costo constante para comparar identificadores. Las operaciones que producen texto también requieren el costo de construir su salida.

| Operación                             | Peor caso            |
| ------------------------------------- | -------------------- |
| Buscar o registrar una persona        | `O(V)`               |
| Agregar amistad o seguimiento desde u | `O(V + d(u))`        |
| Eliminar amistad entre u y v          | `O(V + d(u) + d(v))` |
| Eliminar seguimiento desde u          | `O(V + d(u))`        |
| Listar amigos o seguidos de u         | `O(V + d(u))`        |
| Contar amigos de u                    | `O(V + d(u))`        |
| Listar seguidores                     | `O(V + A)`           |
| Eliminar una persona                  | `O(V + A)`           |
| Contar usuarios                       | `O(V)`               |
| Contar amistades o mostrar la red     | `O(V + A)`           |

El almacenamiento del grafo es `O(V + A)`.

## Compilar y ejecutar la fase 1

La primera fase se conserva y puede ejecutarse por separado:

```powershell
New-Item -ItemType Directory -Force out/fase1 | Out-Null

javac -encoding UTF-8 -d out/fase1 fase1/src/*.java

if ($LASTEXITCODE -eq 0) {
    java -cp out/fase1 Main
}
```

## Evidencias

- [Evidencias de la fase 1](fase1/docs/EVIDENCIAS.md)
- [Evidencias de la fase 2](fase2/docs/EVIDENCIAS.md)

Las evidencias de la fase 2 incluyen registro, relaciones, eliminaciones, consultas, pruebas automatizadas e historial de commits en GitHub.

## Conclusión

La fase 2 implementa una red social con amistades recíprocas y seguimientos dirigidos mediante nodos enlazados propios. Las 60 comprobaciones superadas verifican las operaciones y los casos límite incluidos en la clase de pruebas.
