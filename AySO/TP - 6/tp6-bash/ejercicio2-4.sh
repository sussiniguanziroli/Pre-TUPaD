#!/bin/bash

intentos=0

while [ $intentos -lt 3 ]; do
	read -p "Cuando es 2+2? " respuesta

	if [ "$respuesta" -eq 4 ]; then
		echo "CORRECTO, acertado en el intento $((intentos+1))"
		exit 0
	else
		intentos=$((intentos+1))
		if [ $intentos -lt 3 ]; then
			echo "Te quedan $((3-intentos)) intentos"
		fi
	fi

done

echo "===> Game Over <==="
