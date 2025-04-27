#!/bin/bash

echo "Ingrese la primera cadena:"
read cadena1

echo "Ingrese la segunda cadena:"
read cadena2

if [ -z "$cadena1" ]; then
	echo "La cadena1 esta vacia"
else 
	echo "La cadena1 no esta vacia"
fi

if [ -z "$cadena2" ]; then
	echo "La cadena2 esta vacia"
else
	echo "La cadena2 no esta vacia"
fi

if [ "$cadena1" = "$cadena2" ]; then
	echo "Cadena1 y cadena2 son iguales"
else 
	echo "Cadena1 y cadena2 son diferentes"
fi

