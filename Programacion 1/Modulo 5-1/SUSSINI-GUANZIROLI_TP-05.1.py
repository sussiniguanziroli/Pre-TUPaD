#1 Crear una lista con los números del 1 al 100 que sean múltiplos de 4. Utilizar la función 
#range. 
multiplos_de_4 = []

for numero in range(1, 101):
    if numero % 4 == 0:
        multiplos_de_4.append(numero)
    else:
        pass
    
print(multiplos_de_4)

#2) Crear una lista con cinco elementos (colocar los elementos que más te gusten) y mostrar el 
#penúltimo. ¡Puedes hacerlo como se muestra en los videos o bien investigar cómo funciona el 
#indexing con números negativos!
mi_lista = ['Jose', 'Paula', 'Luciana', 'Agustin', 'Patricio']
penultimo = mi_lista[3]
print(penultimo)

#3) Crear una lista vacía, agregar tres palabras con append e imprimir la lista resultante por 
#pantalla. Pista: para crear una lista vacía debes colocar los corchetes sin nada en su interior. Por 
#ejemplo: 
#lista_vacia = [] 
lista_vacia_ejercicio = []
lista_vacia_ejercicio.append("Patricio")
lista_vacia_ejercicio.append('Rosendo')
lista_vacia_ejercicio.append("Chalchalero")
print(lista_vacia_ejercicio)

#4) Reemplazar el segundo y último valor de la lista “animales” con las palabras “loro” y “oso”, 
#respectivamente.  Imprimir la lista resultante por pantalla. ¡Puedes hacerlo como se muestra 
#en los videos o bien investigar cómo funciona el indexing con números negativos! 
animales = ["perro", "gato", "conejo", "pez"]
print(f"Lista original de consigna: {animales}")
animales[1] = "loro"
animales[-1] = "oso"
print(f"Lista modificada con Loro y Oso: {animales}")

#5) Analizar el siguiente programa y explicar con tus palabras qué es lo que realiza.
#programa en foto
print("Es un programa que busca maximos dentro de la lista numeros. \n El mismo elimina el numero mas grande de la lista, " \
"\n y si hay varios iguales solo elimina el primer numero maximo que encuentra")

#6) Crear una lista con números del 10 al 30 (incluído), haciendo saltos de 5 en 5 y mostrar por 
#pantalla los dos primeros. 
numeros = list(range(10 , 30, 5)) #arranca en 10 termina en 30 y tiene saltos de a 5 
print(f"Lista completa: {numeros}")
print(f"Los dos primeros elementos: {numeros[:2]}")

#7) Reemplazar los dos valores centrales (índices 1 y 2) de la lista “autos” por dos nuevos valores 
#cualesquiera. 
autos = ["sedan", "polo", "suran", "gol"]
print(f"Autos lista original: {autos}")
autos[1] = "saveiro"
autos[2] = "up!"
print(f"Lista modificada: {autos}")

#8) Crear una lista vacía llamada "dobles" y agregar el doble de 5, 10 y 15 usando append 
#directamente. Imprimir la lista resultante por pantalla.
dobles = []
dobles.append(5 * 2)
dobles.append(10 * 2)
dobles.append(15 * 2)
print(dobles)

