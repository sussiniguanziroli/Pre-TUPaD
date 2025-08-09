#!/bin/bash

echo "Ingrese la nota del alumno: "
read nota

if [ $nota -gt 10 ]; then
	echo "====== Nota ingresada invalida ======"
elif [ $nota -ge 9 ]; then
	echo "Excelente"
elif [ $nota -ge 6 ]; then
        echo "Aprobado"
elif [ $nota -ge 0 ]; then
        echo "Reprobado"
else 
	echo "========== Nota ingresada invalida ========="
fi

