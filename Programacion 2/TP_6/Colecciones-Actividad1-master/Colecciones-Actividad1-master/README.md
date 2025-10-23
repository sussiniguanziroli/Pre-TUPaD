# 🚗 Proyecto Java – ArrayList, Relaciones 1 a N y Colecciones de Objetos

Este proyecto está dividido en tres partes y tiene como objetivo explorar el uso de colecciones dinámicas en Java, específicamente `ArrayList`, y cómo modelar relaciones 1 a N entre objetos. A través de ejemplos prácticos, se ilustra la implementación de atributos de tipo colección y su manipulación eficiente.

---

## 📚 Tabla de Contenidos

- [ArrayList en Java](#-parte-1-arraylist-en-java)
- [Relaciones 1-a-N en Clases UML](#-parte-2-relaciones-1-a-n-en-clases-uml)
- [Atributos Tipo Colección de Objetos](#-parte-3-atributos-tipo-colección-de-objetos)
- [Conclusión y Aprendizajes](#-parte-4-conclusión-y-aprendizajes)

---

## 🧩 Parte 1: ArrayList en Java

Esta sección introduce el uso de `ArrayList` como una estructura dinámica para almacenar y manipular datos en Java.

### ✨ Objetivo

Comprender cómo utilizar `ArrayList` para gestionar colecciones de objetos, aprovechando sus métodos para agregar, eliminar y acceder a elementos.

### 📦 Conceptos clave

- Uso de `ArrayList<E>` para almacenar objetos de tipo `E`.
- Métodos principales: `add()`, `get()`, `remove()`, `size()`, y `isEmpty()`.
- Recorrido de colecciones con bucles `for`.

### 📦 Clases involucradas

- **Auto**: Representa un vehículo con atributos como patente y color.
- **Principal**: Clase principal que demuestra el uso de `ArrayList` para gestionar una colección de autos.

### ✅ Conclusión

`ArrayList` es una herramienta poderosa para manejar colecciones dinámicas en Java, permitiendo una manipulación eficiente de datos y adaptándose a las necesidades del programa.

---

## 🧩 Parte 2: Relaciones 1-a-N en Clases UML

Esta parte aborda cómo modelar relaciones uno-a-muchos entre objetos en diagramas UML y su implementación en Java.

### ✨ Objetivo

Modelar y programar relaciones 1-a-N entre clases, donde una entidad contiene múltiples instancias de otra.

### 📦 Clases involucradas

- **Concesionaria**: Representa una concesionaria que contiene una colección de autos.
- **Auto**: Clase que representa los autos gestionados por la concesionaria.

### 📦 Características implementadas

- Inicialización de la colección en el constructor.
- Métodos para agregar y mostrar autos.
- Manejo de listas vacías con mensajes adecuados.

### ✅ Conclusión

Las relaciones 1-a-N son esenciales para modelar escenarios del mundo real en programación orientada a objetos, permitiendo una representación clara y eficiente de las interacciones entre entidades.

---

## 🧩 Parte 3: Atributos Tipo Colección de Objetos

Esta sección muestra cómo encapsular colecciones dentro de clases, proporcionando una API clara para interactuar con ellas.

### ✨ Objetivo

Implementar atributos de tipo colección en clases, respetando principios de encapsulación y separación de responsabilidades.

### 📦 Clases involucradas

- **Concesionaria**: Contiene una colección de autos y métodos para interactuar con ella.
- **Principal**: Clase principal que demuestra cómo interactuar con la concesionaria y sus autos.

### ✅ Conclusión

Encapsular colecciones dentro de clases permite mantener la integridad del modelo de datos y proporciona una interfaz clara para interactuar con las colecciones.

---

## ✅ Parte 4: Conclusión y Aprendizajes

Este proyecto cubre varios aspectos fundamentales del uso de colecciones y relaciones en Java:

- **Uso de `ArrayList`** para manejar colecciones dinámicas de objetos.
- **Modelado de relaciones 1-a-N** entre clases, tanto en UML como en código.
- **Encapsulación de colecciones** dentro de clases para mantener la cohesión y la separación de responsabilidades.
- **Manipulación eficiente de datos** mediante métodos de `ArrayList`.

Estas herramientas son esenciales para desarrollar aplicaciones bien estructuradas y escalables, especialmente en dominios con relaciones complejas entre objetos.

---

### 🎥 Recursos del Proyecto

- **ArrayList en Java: Lo básico para arrancar**  
  Archivos:  
  - `COLECCIONES_ARRAYLIST/src/main/java/com/mycompany/colecciones_arraylist/Auto.java`  
  - `COLECCIONES_ARRAYLIST/src/main/java/com/mycompany/colecciones_arraylist/Principal.java`

- **Relaciones 1 a N en Clases UML**  
  Archivo:  
  - `COLECCIONES_ARRAYLIST/src/main/java/com/mycompany/colecciones_arraylist/Concesionaria.java`

- **Atributos tipo Colección de Objetos en Java**  
  Archivos:  
  - `COLECCIONES_RELACIONES/src/main/java/com/mycompany/colecciones_relaciones/Auto.java`  
  - `COLECCIONES_RELACIONES/src/main/java/com/mycompany/colecciones_relaciones/Concesionaria.java`  
  - `COLECCIONES_RELACIONES/src/main/java/com/mycompany/colecciones_relaciones/Principal.java`

> *Este repositorio es parte de ejemplos educativos sobre programación Java. Desarrollado con Java 8+ y orientado a estudiantes o desarrolladores que desean dominar los conceptos de Colecciones en profundidad.*
