#!/bin/bash

#Ejercicio 1
clear
read -p "Ingrese su nombre: " nombre
read -p "Ingrese su edad: " edad
if [ $edad -ge 16 ]; then
        echo "$nombre puede votar"
else
        echo "$nombre no puede votar"
fi

#Ejercicio2
clear
nombre1="lauti"
nombre2="Pedro"
nombre3="Juan"
echo $nombre1 >> nombres.txt
echo $nombre2 >> nombres.txt
echo $nombre3 >> nombres.txt
for linea in $(cat "nombres.txt");
do
        echo "Hola $linea"
done

#Ejercicio 3
clear
sumatoria=0
cantidad=5
for (( i=1; i<=$cantidad; i++ ));
do
        read -p "Ingrese un numero: " nro
        ((sumatoria+=nro))
done
echo "El promedio es: $((sumatoria/cantidad))"
