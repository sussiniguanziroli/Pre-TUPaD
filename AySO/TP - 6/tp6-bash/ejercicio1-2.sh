#!/bin/bash

echo "Ingrese el nombre del usuario:"
read usuario

echo "Ingrese el mensaje:"
read mensaje

echo "$usuario: $mensaje" > mensaje.txt

write $usuario < mensaje.txt

cat mensaje.txt
