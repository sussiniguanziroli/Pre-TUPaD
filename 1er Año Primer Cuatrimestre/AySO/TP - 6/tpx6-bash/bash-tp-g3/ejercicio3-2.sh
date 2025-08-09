#!/bin/bash

archivo="resumen.txt"

if [ -f "$archivo" ]; then
	echo "El archivo existe"
else 
	echo "El archivo no existe."
fi
