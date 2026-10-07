# Pokémon Stadium Lite

## Requisitos
- Java 11 o superior
- IntelliJ IDEA
- Conexión a internet
- Librería org.json

## Instrucciones de ejecución
1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que `org.json` aparezca en External Libraries.
3. Ejecutar la clase que contiene `public static void main`.
4. Escribir el nombre de un Pokémon o usar Random.

## Diseño
La clase Pokemon es la encargada de guardar los datos de los pokemon y su HP Actual, los datos tales como Nombre, el sprite, la vida, el ataque, la defensa y la velocidad de cada pokemon.

PokeApiClient es la que consulta y convierte la respuesta de la PokeAPI en el pokemon que llama con todo y sus estadisticas.

La clase Battle es la encargada de calcular quien de los dos pokemones tiene la iniciativa teniendo en cuenta la velocidad del pokemon, tambien se encarga de calcular el daño.

PokeStadiumGUI muestra la interfaz grafica, donde muestra visualmente todos los datos de los pokemones, dando la opcion de seleccionar por nombre o por un boton random el pokemon elegido, Tambien muestra el log de la pelea, viendo los daños causados cada turno y quien es el vencedor.

BattleListener es el que comunica los turnos, los cambio de vida y el ganador.

SwingWorker carga los datos de fondo en segundo plano, todo esto es para evitar que se congele la ventana mientras se hace el llamado de la PokeAPI
