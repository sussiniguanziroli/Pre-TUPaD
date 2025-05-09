# Ejercicio de rpeaso 1
palabras = ["computadora", "teclado", "pantalla", "ratón", "código", "memoria", "proceso"]
contador = 0
palabras_a_o = []
substring = ""
palabras_substring = []

for palabra in palabras:
    if len(palabra) == 7:
        contador += 1

for palabra in palabras:
    if palabra[-1] == "o" or palabra[-1] == "a":
        palabras_a_o.append(palabra)

#palabra mas larga
palabra_mas_larga = max(palabras, key=len)

#Escribo las respuestas
print(f"La palabra mas larga es: {palabra_mas_larga}")
print(f"Las palabras que terminan con A y O son: {palabras_a_o}")
print(f"La cantidad de palabras que tienen 7 letras es: {contador}")

#voy a pedir un substring luego de todo esto
substring = input("Ingrese una silaba para buscar dentro de las palabras de la lista de palabras: ")

for palabra in palabras:
    if substring in palabra:
        palabras_substring.append(palabra)

print(f"Las palabras que contienen la silaba ingresada son: {palabras_substring}")