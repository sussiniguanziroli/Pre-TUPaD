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

