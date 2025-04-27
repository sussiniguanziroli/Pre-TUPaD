#!/bin/bash

num1=""
num2=""
calcular=0

if [ "$#" -eq 0 ]; then
	#Caso 0 parametros
	echo "No ha introducido ninguno. Quiere ahora? s/n"
	read -p "Respuesta (s/n): " respuesta

	if [[ "$respuesta" == "s" || "$respuesta" == "S" ]]; then
		read -p "Ingrese el primer numero: " num1
		read -p "Ingrese el segundo numero: " num2
		calcular=1
	else
		echo "Operacion Cancelada."
		calcular=0
	fi

elif [ "$#" -eq 1 ]; then
	#Caso 1 parametro
	echo "Ha introducido uno. Quiere ahora introducir el segundo? s/n"
	num1=$1
	read -p "Respuesta (s/n)" respuesta
	if [[ "$respuesta" == "s" || "$respuesta" == "S" ]]; then
		read -p "Ingrese el segundo numero: " num2
		calcular=1
	else 
		echo "Operacion cancelada."
		calcular=0
	fi


elif [ "$#" -eq 2 ]; then
	#Caso con dos parametros
	echo "CORRECTO"
	num1=$1
	num2=$2
	calcular=1

else 
	#Caso con demasiados parametros
	echo "Demasiados parametros, tomo los dos primeros."
	num1=$1
	num2=$2
	calcular=1
fi

echo "---Fin de los parametros---"

if [ "$calcular" -eq 1 ]; then
	echo "-----------------------------------------------"
	echo "---Realizando operaciones con $num1 y $num2:---"
	echo "-----------------------------------------------"

	#Suma ==>
	suma=$(($num1 + $num2))
	echo "Suma==> $num1 + $num2 = $suma"

	#Resta ==>
	resta=$(($num1 - $num2))
	echo "Resta==> $num1 - $num2 = $resta"

	#Multiplicacion ==>
	multi=$(($num1 * $num2))
	echo "Multiplicacion==> $num1 * $num2 = $multi"

	#Division y checkeo de 0==>
	if [ "$num2" -eq 0 ]; then
		echo "Division==> Error: Division por 0 no permitido"
	else 
		division=$(($num1 / $num2))
		echo "Division==> $num1 / $num2 = $division"
	fi
	echo "--------- FIN DE LAS OPERACIONES -------------"
else
	echo "--- No se realizaron operaciones ---"
	echo "===> No se obtuvieron los parametros necesarios"
fi

echo "Fin del script"

