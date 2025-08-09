#!/bin/bash

while true; do
	echo "-------- Menu Calculos --------"
	echo "1. Calcular el area de un rectangulo"
	echo "2. Calcular el perimetro de un rectangulo"
	echo "3. Salir"
	echo "Seleccione una opcion:"
	read opcion

	case $opcion in
		1) 
			echo "Ingrese la base del rectangulo: "
			read base
			echo "Ingrese la altura del rectangulo: "
			read altura
			area=$((base * altura))
			echo "El area del rectangulo es: $area"
			;;
		2)
			echo "Ingrese la base del rectangulo:"
			read base
			echo "Ingese la alrutra del rectangulo:"
			read altura
			perimetro=$(((base + altura) * 2))
			echo "El perimetro del rectangulo es: $perimetro"
			;;
		3)
			echo "Hasta la proxima!"
			exit 0
			;;
		*)
			echo "Opcion invalida. Por favor, seleccione 1, 2 o 3."
			;;
		esac
	
	done
