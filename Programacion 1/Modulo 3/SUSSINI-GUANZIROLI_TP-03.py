#1) Escribir un programa que solicite la edad del usuario. Si el usuario es mayor de 18 años,
#deberá mostrar un mensaje en pantalla que diga “Es mayor de edad”.
edad_usuario = int(input("Ingrese su edad en anios: "))
if edad_usuario < 18:
    print("El usuario es menor de edad")
else: print("El usuario es mayor de edad")

#2) Escribir un programa que solicite su nota al usuario. Si la nota es mayor o igual a 6, deberá
#mostrar por pantalla un mensaje que diga “Aprobado”; en caso contrario deberá mostrar el
#mensaje “Desaprobado”.
nota_alumno = int(input("Ingrese la nota del alumno: "))
if nota_alumno >= 6:
    print("Aprobado")
elif nota_alumno < 6 and nota_alumno >= 0:
    print("Desaproado")
else: print("Nota Invalida")

#3) Escribir un programa que permita ingresar solo números pares. Si el usuario ingresa un
#número par, imprimir por en pantalla el mensaje "Ha ingresado un número par"; en caso
#contrario, imprimir por pantalla "Por favor, ingrese un número par". Nota: investigar el uso del
#operador de módulo (%) en Python para evaluar si un número es par o impar.
numero_usuario = int(input("Ingrese un numero: "))
if numero_usuario % 2 == 0:
    print("Ha ingresado un numero par")
else:
    print("Ingese un numero estrictamente par")

#4) Escribir un programa que solicite al usuario su edad e imprima por pantalla a cuál de las
#siguientes categorías pertenece:
#● Niño/a: menor de 12 años.
#● Adolescente: mayor o igual que 12 años y menor que 18 años.
#● Adulto/a joven: mayor o igual que 18 años y menor que 30 años.
#● Adulto/a: mayor o igual que 30 años.
edad_usuario = int(input("Ingrese su edad: "))
if edad_usuario < 12:
    print("Ud es un niño")
elif edad_usuario >= 12 and edad_usuario < 18:
    print("Ud es un Adolescente")
elif edad_usuario >= 18 and edad_usuario < 30:
    print("Ud es un Adulto/a Joven")
elif edad_usuario >= 30 and edad_usuario < 115:
    print("Ud es un Adulto/a")
else: 
    print("Edad invalida")

#5) Escribir un programa que permita introducir contraseñas de entre 8 y 14 caracteres
#(incluyendo 8 y 14). Si el usuario ingresa una contraseña de longitud adecuada, imprimir por en
#pantalla el mensaje "Ha ingresado una contraseña correcta"; en caso contrario, imprimir por
#pantalla "Por favor, ingrese una contraseña de entre 8 y 14 caracteres". Nota: investigue el uso
#de la función len() en Python para evaluar la cantidad de elementos que tiene un iterable tal
#como una lista o un string
MIN_LEN = 8
MAX_LEN = 14
pass_user = input("Ingrese su contrasenia: ")
if (len(pass_user) >= MIN_LEN) and (len(pass_user) <= MAX_LEN):
    print("Ha ingresado una contrasenia correcta y valida")
else: print("Por favor, ingrese una contrasenia de entre 8 y 14 caracteres.")

#6) programa de estadistica aleatoria
from statistics import mode, median, mean
import random 
numeros_aleatorios = [random.randint(1, 10) for i in range(5)]
media = mean(numeros_aleatorios)
mediana = median(numeros_aleatorios)
moda = mode(numeros_aleatorios)
print(f"Del siguiente conjunto de numeros: {numeros_aleatorios} sus estadisticas son:")
print(f"La media es {media}")
print(f"La mediana es {mediana}")
print(f"La moda es {moda}")

#7) Escribir un programa que solicite una frase o palabra al usuario. Si el string ingresado
#termina con vocal, añadir un signo de exclamación al final e imprimir el string resultante por
#pantalla; en caso contrario, dejar el string tal cual lo ingresó el usuario e imprimirlo por
#pantalla.
frase_usuario = input("Ingrese una frase para ver la sorpresa: ")
if frase_usuario[-1].lower() in "aeiou":
    print(f"{frase_usuario}!")
else: print(f"{frase_usuario}")

#8) Escribir un programa que solicite al usuario que ingrese su nombre y el número 1, 2 o 3
#dependiendo de la opción que desee:
#1. Si quiere su nombre en mayúsculas. Por ejemplo: PEDRO.
#2. Si quiere su nombre en minúsculas. Por ejemplo: pedro.
#3. Si quiere su nombre con la primera letra mayúscula. Por ejemplo: Pedro.
#El programa debe transformar el nombre ingresado de acuerdo a la opción seleccionada por el
#usuario e imprimir el resultado por pantalla. Nota: investigue uso de las funciones upper(),
#lower() y title() de Python para convertir entre mayúsculas y minúsculas.
nombre_usuario = input("Ingrese su nombre: ")
opcion1 = 1
opcion2 = 2
opcion3 = 3
print("Seleccione el estilo que desea aplicar:\nPara todo mayusculas: 1\nPara todo minusculas: 2\nPara mayusculacion: 3")
opcion_seleccionada = int(input("Seleccione una Opcion: "))
if opcion_seleccionada == opcion1: 
    nombre_nuevo = nombre_usuario.upper()
elif opcion_seleccionada == opcion2:
    nombre_nuevo = nombre_usuario.lower()
elif opcion_seleccionada == opcion3:
    nombre_nuevo = nombre_usuario.title()
else: print("Ha ingresado una opcion invalida: ")
print(f"Producto final: {nombre_nuevo}")

#9) Escribir un programa que pida al usuario la magnitud de un terremoto, clasifique la
#magnitud en una de las siguientes categorías según la escala de Richter e imprima el resultado
#por pantalla:
#● Menor que 3: "Muy leve" (imperceptible).
#● Mayor o igual que 3 y menor que 4: "Leve" (ligeramente perceptible).
#● Mayor o igual que 4 y menor que 5: "Moderado" (sentido por personas, pero
#generalmente no causa daños).
#● Mayor o igual que 5 y menor que 6: "Fuerte" (puede causar daños en estructuras
#débiles).
#● Mayor o igual que 6 y menor que 7: "Muy Fuerte" (puede causar daños significativos).
#● Mayor o igual que 7: "Extremo" (puede causar graves daños a gran escala).
magnitud_registrada = int(input("Ingrese la magnitud del terremoto registrado: "))
if magnitud_registrada < 3:
    print("Según la escala de Richter ha sido: Muy leve (imperceptible).")
elif 3 <= magnitud_registrada < 4:
    print("Según la escala de Richter ha sido: Leve (ligeramente perceptible).")
elif 4 <= magnitud_registrada < 5:
    print("Según la escala de Richter ha sido: Moderado (sentido por personas, pero generalmente no causa daños).")
elif 5 <= magnitud_registrada < 6:
    print("Según la escala de Richter ha sido: Fuerte (puede causar daños en estructuras débiles).")
elif 6 <= magnitud_registrada < 7:
    print("Según la escala de Richter ha sido: Muy Fuerte (puede causar daños significativos).")
else:
    print("Según la escala de Richter ha sido: Extremo (puede causar graves daños a gran escala).")

#10) programa de hemisferio y estaciones
hemisferio = input("Ingrese su hemisferio (N/S): ").strip().upper()
mes = int(input("Ingrese el número de mes (1-12): "))
dia = int(input("Ingrese el día del mes: "))
fecha = (mes, dia)
if hemisferio == "N":
    if (mes == 12 and dia >= 21) or (1 <= mes <= 2) or (mes == 3 and dia <= 20):
        estacion = "Invierno"
    elif (mes == 3 and dia >= 21) or (4 <= mes <= 5) or (mes == 6 and dia <= 20):
        estacion = "Primavera"
    elif (mes == 6 and dia >= 21) or (7 <= mes <= 8) or (mes == 9 and dia <= 20):
        estacion = "Verano"
    elif (mes == 9 and dia >= 21) or (10 <= mes <= 11) or (mes == 12 and dia <= 20):
        estacion = "Otoño"
elif hemisferio == "S":
    if (mes == 12 and dia >= 21) or (1 <= mes <= 2) or (mes == 3 and dia <= 20):
        estacion = "Verano"
    elif (mes == 3 and dia >= 21) or (4 <= mes <= 5) or (mes == 6 and dia <= 20):
        estacion = "Otoño"
    elif (mes == 6 and dia >= 21) or (7 <= mes <= 8) or (mes == 9 and dia <= 20):
        estacion = "Invierno"
    elif (mes == 9 and dia >= 21) or (10 <= mes <= 11) or (mes == 12 and dia <= 20):
        estacion = "Primavera"
else:
    estacion = "Hemisferio no válido."
print("Estación del año:", estacion)