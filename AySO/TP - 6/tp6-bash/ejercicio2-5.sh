#!/bin/bash

aciertos=0

while read -r linea || [ -n "$linea" ]; do
	pregunta=$(echo "$linea" | cut -d ';' -f1)
	respuesta_correcta=$(echo "$linea" | cut -d ';' -f2)

	echo "$pregunta?"
	read -r respuesta_usuario

	if [ "$respuesta_usuario" == "$respuesta_correcta" ]; then
		aciertos=$((aciertos+1))
	fi

done < pregyresp.txt


echo "Tienes $aciertos aciertos"
