# UADE: Algoritmos Y Estructuras De Datos II - Trabajo Práctico I

**Sistema de Operaciones para Empresa de Transporte Terrestre**

---

## 📌 Descripción del Proyecto
Este proyecto es un sistema de gestión integral para una empresa de transporte terrestre. Permite la planificación y búsqueda de rutas, la gestión de una flota de micros, la programación de viajes con sistema de prioridades variables, y un motor de reportes estadísticos avanzados.

El desarrollo se realizó cumpliendo estrictamente con las normas de la cátedra: 
- **Ausencia total de colecciones nativas de Java** (`java.util.*`). Todo el sistema se apoya sobre Tipos de Datos Abstractos (TDA) propios construidos desde cero.
- **Implementaciones Dinámicas y Genéricas:** Uso de nodos enlazados y clases parametrizadas (`<T>`, `<K,V>`) para optimizar el uso de memoria RAM y asegurar la escalabilidad.
- **Manejo de Excepciones:** Creación de un paquete de excepciones personalizadas (`EmptyADTException`, `NotFoundException`, etc.) para controlar el flujo de errores de negocio.

---

## 🏗️ TDAs Utilizados y Justificación Arquitectónica

Para resolver los distintos requerimientos del dominio, se seleccionaron y adaptaron las siguientes estructuras de datos:

### 1. Grafo Dinámico Dirigido (`DynamicGraphADT`)
* Modelado del mapa de Terminales (Vértices) y las Rutas/Conexiones entre ellas (Aristas con peso en Km).

### 2. Diccionario Simple Dinámico (`DynamicSimpleDictionaryADT`)
* 1. Catálogo de Micros y Terminales (Clave: Patente / Código, Valor: Objeto).
* 2. Motor de Reportes Estadísticos (Mapas de Frecuencias).

### 3. Cola con Prioridad Dinámica (`DynamicPriorityQueueADT`)
* Gestión de la cola de Viajes Pendientes de despacho.

### 4. Conjunto Dinámico (`DynamicSetADT`)
* Almacenamiento de Rutas Activas del sistema, control de nodos visitados en el Backtracking, y extracción de claves únicas de los diccionarios.

### 5. Lista Enlazada Dinámica (`DynamicLinkedListADT`)
* Historial de viajes despachados y almacenamiento de resultados de consultas (vecinos de un nodo, lista de rutas encontradas).

### 6. Cola Dinámica Pura (`DynamicQueueADT`)
* Almacenamiento de las "Paradas Intermedias" de una Ruta.

### 7. Pila Dinámica (`DynamicStackADT`)
* Historial de "Cambios de Prioridad" (Auditoría) dentro de un Viaje.
