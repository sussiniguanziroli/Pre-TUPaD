#!/bin/bash

echo "A continuacion introduzca su edad para saber si es mayor o menor: "
read edad

if [ $edad -ge 18 ]; then
	echo "Ud es mayor de edad, puede manejar"
else 
	echo "Ud es menor de edad, vaya en colectivo"
fi
