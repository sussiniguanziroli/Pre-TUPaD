nombre_ingresado = ""
lista_de_nombres = []
nombres_5 = []
cont = 0
repetidos = {}


while True:
    nombre_ingresado = input("Ingrese un nombre para agregar a la lista: \n(Ingrese -FIN- o -fin- para terminar): ")  
    if nombre_ingresado.upper() == "FIN":
        break
    lista_de_nombres.append(nombre_ingresado)
    


lista_completa_len = len(lista_de_nombres)

#printeamos respuestas
print(f"La lista ingresada completa es: {lista_de_nombres}")
print(f"Se ingresaron {lista_completa_len} nombres.")

for nombre in lista_de_nombres:
    if len(nombre) > 5:
        nombres_5.append(nombre)

print(f"Los nombres que tienen mas de 5 letras son: {nombres_5}")



# Verificar nombres repetidos
for nombre in lista_de_nombres:
    if lista_de_nombres.count(nombre) > 1:
        if nombre not in repetidos:
            repetidos[nombre] = lista_de_nombres.count(nombre)

if repetidos:
    print("Nombres repetidos:")
    for nombre, cantidad in repetidos.items():
        print(f"- {nombre} aparece {cantidad} veces")
else:
    print("No hay nombres repetidos.")




