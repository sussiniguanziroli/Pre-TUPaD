#10) Escribe un programa que invierta el orden de los dígitos de un número ingresado por el
#usuario. Ejemplo: si el usuario ingresa 547, el programa debe mostrar 745
numero = int(input("Ingrese un número entero: "))
numero_abs = abs(numero)
invertido = 0
while numero_abs > 0:
    digito = numero_abs % 10
    invertido = invertido * 10 + digito
    numero_abs //= 10
if numero < 0:
    invertido *= -1
print(f"El número invertido es: {invertido}")
print("Fin")