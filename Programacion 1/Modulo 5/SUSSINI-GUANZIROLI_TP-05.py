#Crear una función llamada imprimir_hola_mundo que imprima por
#pantalla el mensaje: “Hola Mundo!”. Llamar a esta función desde el
#programa principal.
def imprimir_hola_mundo():
    return print("Hola Mundo!")
imprimir_hola_mundo()

#Crear una función llamada saludar_usuario(nombre) que reciba
#como parámetro un nombre y devuelva un saludo personalizado.
#Por ejemplo, si se llama con saludar_usuario("Marcos"), deberá de
#volver: “Hola Marcos!”. Llamar a esta función desde el programa
#principal solicitando el nombre al usuario
def saludar_usuario(nombre):
    return print(f"Hola {nombre}!")
saludar_usuario("Marcos")

#Crear una función llamada informacion_personal(nombre, apellido,
#edad, residencia) que reciba cuatro parámetros e imprima: “Soy
#[nombre] [apellido], tengo [edad] años y vivo en [residencia]”. Pe-
#dir los datos al usuario y llamar a esta función con los valores in-
#gresados.
def informacion_personal(nombre, apellido, edad, residencia):
    return print(f"Soy {nombre} {apellido}, tengo {edad} años y vivo en {residencia}")
n = input("Ingrese su nombre: ")
a = input("Ingrese su apellido: ")
e = input("Ingrese su edad en anios: ")
r = input("Ingrese su provincia de residencia: ")
informacion_personal(n, a, e, r)

#Crear dos funciones: calcular_area_circulo(radio) que reciba el ra-
#dio como parámetro y devuelva el área del círculo. calcular_peri-
#metro_circulo(radio) que reciba el radio como parámetro y devuel-
#va el perímetro del círculo. Solicitar el radio al usuario y llamar am-
#bas funciones para mostrar los resultados.
def calcular_area_circulo(radio):
    ar = 3.1416 * (radio^2)
    return ar
def calcular_perimetro_circulo(radio):
    per = (2 * 3.1416 * radio)
    return per
r_usuario = int(input("Ingrese el radio del circulo para saber su area y su perimetro: "))
area_usuario = calcular_area_circulo(r_usuario)
per_usuario = calcular_perimetro_circulo(r_usuario)
print(f"El area es {area_usuario} y el perimetro es {per_usuario}")

#Crear una función llamada segundos_a_horas(segundos) que reciba
#una cantidad de segundos como parámetro y devuelva la cantidad
#de horas correspondientes. Solicitar al usuario los segundos y mos-
#trar el resultado usando esta función
def segundos_a_horas(segundos):
    return segundos / 60
s_user = float(input("Ingrese segundos para convertir a horas: "))
print(f"{s_user} segs en horas son: ",segundos_a_horas(s_user),"horas.")

#Crear una función llamada tabla_multiplicar(numero) que reciba un
#número como parámetro y imprima la tabla de multiplicar de ese
#número del 1 al 10. Pedir al usuario el número y llamar a la fun-
#ción.
def tabla_multiplicar(numero):
    i = 0
    for i in range(0, 11):
        mult = numero * i 
        i =+ 1
        print(mult)
    return  
num_user = int(input("Ingrese un numero para ver su tabla de mult: "))
tabla_multiplicar(num_user)

#Crear una función llamada operaciones_basicas(a, b) que reciba
#dos números como parámetros y devuelva una tupla con el resulta-
#do de sumarlos, restarlos, multiplicarlos y dividirlos. Mostrar los re-
#sultados de forma clara.
def operaciones_basicas(a, b):
    suma = a + b
    resta = a - b
    multiplicacion = a * b
    division = a / b if b != 0 else "error! No se puede dividir por 0"
    return (suma, resta, multiplicacion, division)
num1 = float(input("ingrese el primer numero: "))
num2 = float(input("ingrese el segundo numero: "))
resultados = operaciones_basicas(num1, num2)
print(f"\nResultados:")
print(f"Suma: {resultados[0]}")
print(f"Resta: {resultados[1]}")
print(f"Multiplicacion: {resultados[2]}")
print(f"Division: {resultados[3]}")

#Crear una función llamada calcular_imc(peso, altura) que reciba el
#peso en kilogramos y la altura en metros, y devuelva el índice de
#masa corporal (IMC). Solicitar al usuario los datos y llamar a la fun-
#ción para mostrar el resultado con dos decimales.
def calcular_imc(peso, altura):
    imc = peso / (altura ** 2)
    return round(imc, 2)
print(f"Ingrese a continuacion su peso y altura para saber su IMC =====>")
peso_user = float(input("\nIngrese su peso (kg):"))
height_user = float(input("Ingrese su altura (m):"))
print(f"\nSu IMC con peso: {peso_user} y altura: {height_user} es:"
      , calcular_imc(peso_user, height_user))

#Crear una función llamada celsius_a_fahrenheit(celsius) que reciba
#una temperatura en grados Celsius y devuelva su equivalente en
#Fahrenheit. Pedir al usuario la temperatura en Celsius y mostrar el
#resultado usando la función.
def celsius_a_fahrenheit(celsius):
    f = (celsius * (9/5) + 32) 
    return f
c_user = float(input("Ingrese una temp en celsius: "))
print(f"{c_user} grados en fahrenheit es: ", celsius_a_fahrenheit(c_user))

#Crear una función llamada calcular_promedio(a, b, c) que reciba
#tres números como parámetros y devuelva el promedio de ellos.
#Solicitar los números al usuario y mostrar el resultado usando esta
#función.
def calcular_promedio(a, b, c):
    pr = ((a + b + c) / 3)
    return pr
n1 = float(input("Ingrese el 1er num:"))
n2 = float(input("Ingrese el 2do num:"))
n3 = float(input("Ingrese el 3er num:"))
print(f"El promedio entre {n1}, {n2}, {n3} es:",calcular_promedio(n1,n2,n3))