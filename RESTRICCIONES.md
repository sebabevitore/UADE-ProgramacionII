# Restricciones del Proyecto - Algorithms II

## Regla Principal

**NO se puede utilizar Ninguna implementación de Java para colecciones de datos.**

### Prohibido el uso de:
- `ArrayList`
- `LinkedList` (de `java.util`)
- `HashMap`, `HashSet`, `TreeMap`, `TreeSet`
- `Stack` (de `java.util`)
- `Queue`, `Deque` (de `java.util`)
- `Arrays.sort()`, `Arrays.binarySearch()`
- `Collections.sort()`, `Collections.reverse()`
- Cualquier otro método de Java que opere sobre colecciones

### Prohibido el uso de excepciones de Java:
- `IndexOutOfBoundsException`
- `IllegalArgumentException`
- `IllegalStateException`
- `RuntimeException`
- `NullPointerException`
- `UnsupportedOperationException`
- `ClassCastException`
- `ArrayIndexOutOfBoundsException`
- `DateTimeException`
- Cualquier otra excepción de Java

**Solo se pueden usar las excepciones implementadas en el proyecto.**

---

## Excepciones Disponibles

| Excepción | Uso |
|---|---|
| `EmptyADTException` | Estructura de datos vacía |
| `FullADTException` | Estructura de datos llena |
| `GenericADTException` | Error genérico de TDA |
| `NotFoundException` | Elemento no encontrado |
| `UnavailableDateException` | Fecha no disponible |
| `OpcionInvalida` | Opción de menú inválida |
| `InvalidIndexException` | Índice fuera de rango |

---

## TDA Disponibles para Uso

Se deben utilizar **únicamente** las implementaciones propias de TDA definidas en el proyecto.

### Estructuras de Datos Implementadas

| TDA | Implementación Dinámica | Implementación Estática | Util |
|---|---|---|---|
| **StackADT** | `DynamicStackADT<T>` | `StaticStackADT<T>` | `StackADTUtil` |
| **QueueADT** | `DynamicQueueADT<T>` | `StaticQueueADT<T>` | `QueueADTUtil` |
| **PriorityQueueADT** | `DynamicPriorityQueueADT<T>` | `StaticPriorityQueueADT<T>` | `PriorityQueueADTUtil` |
| **LinkedListADT** | `DynamicLinkedListADT<T>` | `StaticLinkedListADT<T>` | `LinkedListADTUtil` |
| **SetADT** | `DynamicSetADT<T>` | `StaticSetADT<T>` (STUB) | `SetADTUtil` |
| **SimpleDictionaryADT** | `DynamicSimpleDictionaryADT<K,V>` | No implementado | `SimpleDictionaryADTUtil` |

### TDA Interfaces (Sin Implementación)

- `MultipleDictionaryADT` - Diccionario múltiple (solo interfaz)
- `BinaryTreeADT` - Árbol binario (solo interfaz)
- `GraphADT` - Grafo (solo interfaz, nodos existen)

---

## Métodos Disponibles por TDA

### StackADT<T>
```java
T getElement()           // Retorna elemento superior
void add(T value)        // Agrega elemento al tope
void remove()            // Remueve elemento superior
boolean isEmpty()        // Verifica si está vacía
```

### QueueADT<T>
```java
T getElement()           // Retorna elemento frontal
void add(T value)        // Agrega elemento al final
void remove()            // Remueve elemento frontal
boolean isEmpty()        // Verifica si está vacía
```

### PriorityQueueADT<T>
```java
T getElement()           // Retorna elemento de mayor prioridad
int getPriority()        // Retorna prioridad del elemento frontal
void add(T value, int priority) // Agrega con prioridad
void remove()            // Remueve elemento de mayor prioridad
boolean isEmpty()        // Verifica si está vacía
```

### LinkedListADT<T>
```java
void add(T value)        // Agrega al final
void insert(int index, T value) // Inserta en índice
void remove(int index)   // Remueve en índice
T get(int index)         // Obtiene en índice
int size()               // Retorna cantidad
boolean isEmpty()        // Verifica si está vacía
```

### SetADT<T>
```java
boolean exist(T value)   // Verifica existencia
T choose()               // Retorna elemento aleatorio
void add(T value)        // Agrega (sin duplicados)
void remove(T element)   // Remueve elemento
boolean isEmpty()        // Verifica si está vacío
```

### SimpleDictionaryADT<K,V>
```java
void add(K key, V value) // Agrega par clave-valor
void remove(K key)       // Remueve por clave
V get(K key)             // Obtiene valor por clave
SetADT<K> getKeys()      // Retorna set de claves
boolean isEmpty()        // Verifica si está vacío
```

---

## Métodos Util Disponibles

### StackADTUtil<T>
```java
void print(StackADT<T> stack)
StackADT<T> copy(StackADT<T> stack)
boolean esPar(StackADT<T> stack)
StackADT<T> invertir(StackADT<T> stack)
boolean estaEnPila(T elemento, StackADT<T> pila)
```

### QueueADTUtil<T>
```java
QueueADT<T> copy(QueueADT<T> queue)
void print(QueueADT<T> queue)
```

### PriorityQueueADTUtil<T>
```java
void print(PriorityQueueADT<T> queue)
PriorityQueueADT<T> copy(PriorityQueueADT<T> priorityQueue)
```

### LinkedListADTUtil<T>
```java
void print(LinkedListADT<T> list)
LinkedListADT<T> copy(LinkedListADT<T> list)
boolean areEqual(LinkedListADT<T> listOne, LinkedListADT<T> listTwo)
```

### SetADTUtil<T>
```java
SetADT<T> copy(SetADT<T> set)
```

### SimpleDictionaryADTUtil<K,V>
```java
void print(SimpleDictionaryADT<K, V> dict)
```

---

## Entidades del Proyecto

- **Micro**: microbús con patente, tipo y lista de viajes (`LinkedListADT<Viaje>`)
- **Viaje**: viaje con ruta, fecha, prioridad y historial de cambios (`StackADT<CambioPrioridad>`)
- **Ruta**: código origen, código destino y paradas intermedias (`QueueADT<Terminal>`)
- **Terminal**: código y descripción
- **CambioPrioridad**: motivo y nueva prioridad
- **Tipo** (enum): CAMA, SEMICAMA, EJECUTIVO
- **Motivo** (enum): LLUVIA, VISIBILIDAD, OTRO
