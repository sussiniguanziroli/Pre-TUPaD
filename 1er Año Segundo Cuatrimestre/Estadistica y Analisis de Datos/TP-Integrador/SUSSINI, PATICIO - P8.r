# --- TRABAJO PRÁCTICO INTEGRADOR - PUNTO 8 ---
# Alumno: SUSSINI, PATRICIO
# Script para la consigna 8: Muestreo Aleatorio Simple


# --- 1. Definir Parámetros del Muestreo ---
set.seed(123) # Fija la semilla para que el muestreo sea reproducible
n_muestras <- 6
tamano_muestra <- 20

# --- 2. Calcular el Parámetro Poblacional (Valor Verdadero) ---

parametro_media_peso <- mean(datos$"PESO KG.", na.rm = TRUE)
N_poblacion <- nrow(datos)

# --- 3. Realizar el Muestreo Aleatorio Simple (MAS) ---
medias_muestrales <- numeric(n_muestras)

for (i in 1:n_muestras) {
  
  indices_poblacion <- 1:N_poblacion
  indices_seleccionados <- sample(indices_poblacion, tamano_muestra, replace = FALSE)
  muestra <- datos[indices_seleccionados, ]
  
  
  media_muestra_actual <- mean(muestra$"PESO KG.", na.rm = TRUE)
  
  # Guardar el resultado en el vector
  medias_muestrales[i] <- media_muestra_actual
}

# --- 4. Mostrar los Resultados en la Consola ---
print("--- ANÁLISIS DE MUESTREO (PUNTO 8) ---")
print(paste("Variable analizada: PESO KG."))
print(paste("Tamaño Total de la Población (N):", N_poblacion))
print(paste("Tamaño de cada muestra (n):", tamano_muestra))
print(paste("Cantidad de muestras tomadas:", n_muestras))
print("-------------------------------------------------")

# Imprimir Parámetro y Estadísticos (redondeados)
print(paste("PARÁMETRO (Media de Peso Real de la Población):", round(parametro_media_peso, 2)))
print("-------------------------------------------------")
print("ESTADÍSTICOS (Medias de Peso de las 6 Muestras):")
print(round(medias_muestrales, 2))
print("-------------------------------------------------")

# --- 5. Tabla Comparativa 
tabla_comparativa <- data.frame(
  Muestra = 1:n_muestras,
  Media_Muestral_Peso = round(medias_muestrales, 2),
  Parametro_Poblacional = round(parametro_media_peso, 2),
  Diferencia_vs_Parametro = round(medias_muestrales - parametro_media_peso, 2)
)

print("Tabla Comparativa de Resultados:")
print(tabla_comparativa)
