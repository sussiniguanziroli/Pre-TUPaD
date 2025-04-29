#!/bin/bash

contador=1
suma=0

while [ $contador -le 100 ]; do
	suma=$((suma + contador))
	contador=$((contador + 1))
done

echo "La suma de los numeros del 1 al 100 es: $suma"

