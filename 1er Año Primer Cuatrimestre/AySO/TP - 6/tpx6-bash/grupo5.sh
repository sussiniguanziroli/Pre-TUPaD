#!/bin/bash

#Ejercicio 1
clear
read -p "Ingrese nombre: " nombre
read -p "Ingrese apellido: " apellido
echo "$nombre $apellido" | tr '[:lower:]' '[:upper:]'

#Ejercicio 2
clear
read -p "Ingrese una palabra: " palabra
echo "La palabra $palabra tiene ${#palabra} caracteres"

#Ejercicio 3
clear
read -s -p "Ingrese password: " contra
echo "Su password es: $contra"
