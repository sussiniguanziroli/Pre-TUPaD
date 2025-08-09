#!/bin/bash

archivo_temp="numeros.txt"
> "$archivo_temp"

while true; do
	echo -n "Ingrese un numero (999 para terminar): "
	read numero

	if [[ "$numero" == "999" ]]; then
		break
	fi

	if ! [[ "$numero" =~ ^-?[0-9]+$ ]]; then
		echo "Por favor, ingrese un numero valido."
		continue
	fi

	echo "$numero" >> "$archivo_temp"
done

echo -n "Quieres ver los numeros introducidos? (s/n): "
read respuesta 

if [[ "$respuesta" =~ ^[sS]$ ]]; then
	echo -n "Orden de ingreso, ascendiente o descendiente? (o/a/d): "
	read orden


	case "$orden" in

		o|O)
			echo "Numeros en orden de ingreso:"
			cat "$archivo_temp"
			;;
		a|A) 
			echo "Numeros en orden ascendente:"
			sort -n "$archivo_temp"
			;;
		d|D)
			echo "Numeros en orden descendiente:"
			sort -nr "$archivo_temp"
			;;
		*)
			echo "Opcion no valida"
			;;
	esac
fi

rm -f "$archivo_temp"

echo "===>> Hasta la vista!! <<==="
