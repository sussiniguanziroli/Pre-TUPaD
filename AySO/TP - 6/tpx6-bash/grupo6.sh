#!/bin/bash

#Ejercicio 1
clear
read -p "Ingrese cadena de caracteres: " cadena
echo "Los primeros 8 caracteres son ${cadena:0:8}"

#Ejercicio 2
clear
read -p "Ingrese cadena de caracteres: " cadena
echo "${cadena/error/problemita}"

#Ejercicio 3
clear
read -p "Ingrese un texto: " txt
echo "${txt,,}"
