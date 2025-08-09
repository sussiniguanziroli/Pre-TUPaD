#!/bin/bash

until [ "$password" = "secreto" ]; do 
	read -p "Ingrese la clave: " password
done

echo "Clave correcta! Acceso concedido."

