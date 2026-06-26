#!/bin/bash

set -e

MONGO_URI="mongodb+srv://db_admin:admin1234@cluster-techstore.bvsefu1.mongodb.net/"
DB_NAME="techstore_db"
FECHA_HOY=$(date +"%Y-%m-%d")
CARPETA_DESTINO="./resguardos_tpi/${FECHA_HOY}"

echo "============================================="
echo " Backup de ${DB_NAME}"
echo " Fecha: ${FECHA_HOY}"
echo "============================================="

mkdir -p "${CARPETA_DESTINO}"
echo "Carpeta creada: ${CARPETA_DESTINO}"

mongodump --uri="${MONGO_URI}" --db="${DB_NAME}" --out="${CARPETA_DESTINO}"

echo "============================================="
echo " Backup finalizado: ${CARPETA_DESTINO}/${DB_NAME}"
echo "============================================="
