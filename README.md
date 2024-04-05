# Descripción del Proyecto

El proyecto despliega una aplicación que proporciona funcionalidades para la ordenación de números.
Solo hay que ejecutar el docker-compose.yaml de la carpeta raíz. Un vez desplegado, se dispone
de dos endpoints:

```json
ENUNCIADO:

Implementa un endpoint REST que reciba como parámetro de entrada una lista de enteros.
El endpoint deberá devolver la lista de enteros ordenada en base a los siguientes criterios:

Cuanto mayor sea el número de unos en la representación binaria del número entero,
más cerca estará el elemento del índice 0.

En caso de que dos o más números tengan el mismo número de unos en su representación binaria,
el número decimal más pequeño aparece primero en la lista ordenada.

Ejemplo:
Para la entrada: [1, 15, 5, 7, 3] la salida deberá de ser: [15, 7, 3, 5, 1]

Explicación:
        
          |---------|---------|
          | Decimal | Binario |
          |---------|---------|
          |    1    |   1     |
          |    15   |  1111   |
          |    5    |   101   |
          |    7    |   111   |
          |    3    |   11    |
          |---------|---------|
        
El número decimal cuya representación binaria contiene más unidades es 15 (1111 en binario),
por lo que va primero (índice = 0). El siguiente es el 7, con tres unos en su representación binaria.
Luego hay 2 números cuya representación binaria contiene la misma cantidad de unos, estos decimales
son 5 y 3, ambos con 2 unos. En este caso, el número 3 va primero (más cerca del índice = 0)
porque su representación decimal es más pequeña (3 < 5).
```

## Endpoints de Ordenación de Números

### 1) Ordenación de Números desde Base de Datos

Este endpoint se conecta a una base de datos PostgreSQL para recuperar y ordenar los valores almacenados.

- **Método:** GET
- **URL:** [http://localhost:8080/ordenar-num-bbdd](http://localhost:8080/ordenar-num-bbdd)

### 2) Ordenación de Números mediante una Lista

Este endpoint ordena una lista de números proporcionados en el cuerpo de la solicitud.

- **Método:** POST
- **URL:** [http://localhost:8080/ordenar](http://localhost:8080/ordenar)
- **Ejemplo de Cuerpo de Solicitud:**
  ```json
  [1, 15, 5, 7, 3]
