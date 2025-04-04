# 1 - Crear un programa que imprima por pantalla el mensaje: “Hola Mundo!”
print("Hola Mundo")

# 2 - Crear un programa que pida al usuario su nombre e imprima por pantalla un saludo usando
#el nombre ingresado. Por ejemplo: si el usuario ingresa “Marcos”, el programa debe imprimir
#por pantalla “Hola Marcos!”. Consejo: esto será más sencillo si utilizas print(f…) para
#realizar la impresión por pantalla.
nombre = input("Ingresa tu nombre y recibe un saludo: ")
print(f"Hola {nombre}!")

# 3 - Crear un programa que pida al usuario su nombre, apellido, edad y lugar de residencia e
#imprima por pantalla una oración con los datos ingresados. Por ejemplo: si el usuario ingresa
#“Marcos”, “Pérez”, “30” y “Argentina”, el programa debe imprimir “Soy Marcos Pérez, tengo 30
#años y vivo en Argentina”. Consejo: esto será más sencillo si utilizas print(f…) para realizar
#la impresión por pantalla.
nombre = input("Ingrese su nombre: ")
apellido = input("Ingrese su apellido: ")
edad = int(input("Ingrese su edad: "))
residencia = input("Ingrese su lugar de residencia: ")
print(f"Hola! Soy {nombre} {apellido}, tengo {edad} años y vivo en {residencia}")


#4) Crear un programa que pida al usuario el radio de un círculo e imprima por pantalla su área y
#su perímetro.
radio = int(input("Ingrese el radio del circulo: "))
area = 3.1415 * radio**2
perimetro = 2 * 3.1415 * radio
print(f"Area: {area}, Perimetro: {perimetro}")

#5) Crear un programa que pida al usuario una cantidad de segundos e imprima por pantalla a
#cuántas horas equivale.
segundos = int(input("Ingrese una cantidad de segundos para saber a cuantas horas equivale: "))
horas = segundos / 3600
print(f"La cantidad de horas es: {horas}")

#6) Crear un programa que pida al usuario un número e imprima por pantalla la tabla de
#multiplicar de dicho número.
numero = int(input("Ingrese un numero entero para saber su tabla de multiplicar: "))
print(f"Tabla de multiplicar del numero {numero}: ")
for i in range(1, 11):
    print(f"{numero} x {i} = {numero * i}")

#7) Crear un programa que pida al usuario dos números enteros distintos del 0 y muestre por
#pantalla el resultado de sumarlos, dividirlos, multiplicarlos y restarlos.
print("Ingrese dos numeros enteros distintos de 0:")
numero1 = int(input("Ingrese el primer numero: "))
numero2 = int(input("Ingrese el segundo numero: "))
suma = numero1 + numero2
resta = numero1 - numero2
multiplicacion = numero1 * numero2
division = numero1 / numero2
print(f"Los resultados son: ")
print(f"{numero1} + {numero2} = {suma}")
print(f"{numero1} - {numero2} = {resta}")
print(f"{numero1} x {numero2} = {multiplicacion}")
print(f"{numero1} / {numero2} = {division}")

#8) Crear un programa que pida al usuario su altura y su peso e imprima por pantalla su índice
#de masa corporal. Tener en cuenta que el índice de masa corporal se calcula del siguiente
#modo:
#𝐼𝑀𝐶 =
#𝑝𝑒𝑠𝑜 𝑒𝑛 𝑘𝑔
#(𝑎𝑙𝑡𝑢𝑟𝑎 𝑒𝑛 𝑚)
#2
print("Calculadora de IMC")
altura = float(input("Ingrese su altura en Metros: "))
peso = int(input("Ingrese su peso en Kilogramos: "))
imc = peso/altura**2
print(f"Su IMC es: {imc}")

#9) Crear un programa que pida al usuario una temperatura en grados Celsius e imprima por
#pantalla su equivalente en grados Fahrenheit. Tener en cuenta la siguiente equivalencia:
#𝑇𝑒𝑚𝑝𝑒𝑟𝑎𝑡𝑢𝑟𝑎 𝑒𝑛 𝐹𝑎ℎ𝑟𝑒𝑛ℎ𝑒𝑖𝑡 =
#9
#5
#. 𝑇𝑒𝑚𝑝𝑒𝑟𝑎𝑡𝑢𝑟𝑎 𝑒𝑛 𝐶𝑒𝑙𝑠𝑖𝑢𝑠 + 32
celsius = float(input("Ingrese una temperatura en celsius: "))
fahrenheit = 9/5 * celsius + 32
print(f"La temperatura {celsius} en fahrenheit es: {fahrenheit}")

#10) Crear un programa que pida al usuario 3 números e imprima por pantalla el promedio de
#dichos números.
n1 = int(input("Ingrese el primer numero: "))
n2 = int(input("Ingrese el segundo numero: "))
n3 = int(input("Ingrese el tecer numero: "))
promedio = (n1 + n2 + n3) / 3.
print(f"El promedio de los 3 numeros ingresados es: {promedio}")