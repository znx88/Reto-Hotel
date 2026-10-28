# HotelApp – Sistema de reservas (Parte 1)

Aplicación de escritorio desarrollada con **JavaFX** que lee los datos de un hotel desde un archivo **XML** y permite consultar la disponibilidad de sus habitaciones.

## ✨ Funcionalidades

- Carga de los datos del hotel (nombre, ciudad) desde `reservas.xml`.
- Listado de todas las habitaciones con su tipo, capacidad, precio por noche y estado.
- Búsqueda de una habitación por número, con respuesta visual por colores:
  -  **Verde**: habitación disponible.
  -  **Rojo**: habitación ocupada.
  -  **Morado**: la habitación no existe.
  -  **Naranja**: no se ha escrito ningún número.

##  Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| JavaFX (controls + fxml) | 21.0.4 |
| Maven | 3.x |
| javafx-maven-plugin | 0.0.8 |

El XML se procesa con el parser DOM estándar de Java (`javax.xml.parsers`).

##  Estructura del proyecto

```
HotelApp_Parte1/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── Main.java               # Punto de entrada, carga la vista FXML
    │   └── HotelController.java    # Lógica: lectura del XML, listado y búsqueda
    └── resources/
        ├── vista.fxml              # Interfaz gráfica
        └── reservas.xml            # Datos del hotel (Hotel Sol, Madrid)
```

##  Datos (`reservas.xml`)

Contiene el **Hotel Sol** (Madrid, 4 estrellas) con **30 habitaciones** repartidas en 3 plantas (Individual, Doble, Familiar y Suite). Cada habitación incluye: número, estado, planta, tipo, capacidad, precio por noche, vista y accesibilidad.

##  Cómo ejecutarlo

### Requisitos
- JDK 21 o superior
- Maven

### Con Maven

```bash
git clone https://github.com/znx88/Reto-Hotel.git
cd Reto-Hotel
mvn clean javafx:run
```

### Desde VS Code / otro IDE
Si ejecutas sin Maven, necesitas el [SDK de JavaFX](https://openjfx.io/) y añadir estos argumentos de VM:

```
--module-path "<ruta-al-sdk>/lib" --add-modules javafx.controls,javafx.fxml
```

##  Uso

1. Al abrir la aplicación se muestra la lista de habitaciones.
2. Escribe un número de habitación (por ejemplo `205`) en el campo de texto.
3. Pulsa **BUSCAR** para ver si está disponible u ocupada.



