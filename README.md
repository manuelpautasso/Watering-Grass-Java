# Problema 'Watering grass' de Online Judge 10382

## Descripción del problema
Tenemos un campo de largo l, ancho w y una cantidad n de aspersores a distribuir en el campo. Cada aspersor tiene una posicion con respecto al largo y un radio que cubre. Se busca la mínima cantidad de aspersores para poder cubrir todo el campo.

Pueden encontrar la descripción completa con los inputs en la página online judge en https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1323

Allí pueden subir sus propias versiones del algoritmo.

## Algoritmo
Se calculan los límites del área que cubre cada aspersor del campo y que tienen ancho w. Se ordenan los aspersores según uno de los límites y luego se recorre el array agregando los aspersores a la solución bajo una estrategia Greedy.
