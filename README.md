# UADE: Algoritmos Y Estructuras De Datos II - Trabajo Práctico I

**Sistema de Operaciones para Empresa de Transporte**

## 👥 
* Bevitore Sebastian Ivan (LU: 1211500)

---

## 📌 Descripción del Proyecto
Este proyecto es un sistema de gestión integral para una empresa de transporte terrestre. Permite la planificación y búsqueda de rutas, la gestión de una flota de micros, la programación de viajes con sistema de prioridades variables, y un motor de reportes estadísticos avanzados.

El desarrollo se realizó cumpliendo estrictamente con las normas de la cátedra: 
- **Ausencia total de colecciones nativas de Java** (`java.util.*`). Todo el sistema se apoya sobre Tipos de Datos Abstractos (TDA) propios construidos desde cero.
- **Implementaciones Dinámicas y Genéricas:** Uso de nodos enlazados y clases parametrizadas (`<T>`, `<K,V>`) para optimizar el uso de memoria RAM y asegurar la escalabilidad.
- **Manejo de Excepciones:** Creación de un paquete de excepciones personalizadas (`EmptyADTException`, `NotFoundException`, etc.) para controlar el flujo de errores de negocio.

---

## TDAs Utilizados 

Para resolver los distintos requerimientos del dominio, cada estructura se eligió basándose estrictamente en lo solicitado por el enunciado del Trabajo Práctico:

### 1. Grafo Dinámico Dirigido (`DynamicGraphADT`)
* **Uso:** Red de terminales y conexiones (rutas).
* **Justificación:** El TP exige *"representar los terminales y sus posibles conexiones"* y *"determinar todas las rutas posibles [...] considerando un máximo de paradas"*. El grafo permite modelar esta red de vías y aplicar algoritmos de búsqueda para detectar terminales desconectadas.

### 2. Diccionario Simple (`DynamicSimpleDictionaryADT`)
* **Uso:** Catálogo de Micros y Terminales.
* **Justificación:** La consigna solicita explícitamente *"utilizar una estructura que permita, dados los identificadores de los micros, identificar qué micros fueron asignados"* a partir de su *"identificador único (patente)"*. El Diccionario mapea Clave-Valor resolviendo este requerimiento de forma nativa y eficiente.

### 3. Cola con Prioridad (`DynamicPriorityQueueADT`)
* **Uso:** Gestión de viajes pendientes.
* **Justificación:** El requerimiento indica *"gestionar los viajes de los micros teniendo en cuenta la prioridad de los mismos"* y *"modificar la prioridad por cuestiones meteorológicas"*. Esta estructura garantiza que al despachar, siempre se atienda primero el viaje más urgente de forma automática.

### 4. Conjunto (`DynamicSetADT`)
* **Uso:** Almacenamiento de rutas registradas y control de nodos.
* **Justificación:** El enunciado establece como regla de negocio que *"no es posible tener rutas repetidas, es decir, el mismo origen-destino"*. El Conjunto, impide la existencia de elementos duplicados.

### 5. Lista Enlazada (`DynamicLinkedListADT`)
* **Uso:** Historial de despachos y recolección de datos.
* **Justificación:** Necesaria para cumplir con *"Generar reportes detallados"* y *"Mostrar los micros con la mayor cantidad de asignaciones"*, agrupando y almacenando resultados de longitud dinámica para su posterior análisis.

### 6. Cola Simple (`DynamicQueueADT`)
* **Uso:** Paradas intermedias de una ruta.
* **Justificación:** El enunciado pide planificar rutas y sus *"conexiones/paradas"*. La Cola respeta el orden cronológico estricto (FIFO) en el que el micro recorrerá geográficamente las ciudades.

### 7. Pila (`DynamicStackADT`)
* **Uso:** Historial de motivos en los cambios de prioridad.
* **Justificación:** Como el TP exige *"modificar la prioridad de un viaje por cuestiones meteorológicas, de visibilidad, etc."*, la Pila permite guardar el historial de motivos (LIFO), manteniendo siempre el último cambio (el más reciente e importante) en la cima para su rápida consulta.

