#!/bin/bash

echo "Ingrese una nota numerica: "
read nota

if [[ ! $nota =~ ^[0-9]+$ ]]; then
	echo "Por favor, introduce un numero valido."
	exit 1
fi

if [ "$nota" -ge 9 ]; then
	echo "Sobresaliente"
elif [ "$nota" -ge 7 ]; then
	echo "Notable"
elif [ "$nota" -ge 6 ]; then
	echo "Bien"
elif [ "$nota" -ge 5 ]; then
	echo "Suficiente"
else 
	echo "Insuficiente"
fi
