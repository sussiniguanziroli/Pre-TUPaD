#Crea una función recursiva que calcule el factorial de un número. Luego, utiliza esa 
#función para calcular y mostrar en pantalla el factorial de todos los números enteros 
#entre 1 y el número que indique el usuario
def fact_numero(num):
    if num == 0:
        return 1
    else:
        return num * fact_numero(num - 1)
print(fact_numero(4))

#Crea una función recursiva que calcule el valor de la serie de Fibonacci en la posición 
#indicada. Posteriormente, muestra la serie completa hasta la posición que el usuario 
#especifique. 
def fibonacci_recursivo(n):
    if n <= 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci_recursivo(n - 1) + fibonacci_recursivo(n - 2)
def generar_serie_fibonacci(posicion):
    serie = []
    for i in range(posicion + 1):
        serie.append(fibonacci_recursivo(i))
    return serie
if __name__ == "__main__":
        posicion = int(input("Ingrese la posición hasta la que desea generar la serie de Fibonacci: "))
        if posicion < 0:
            print("La posición debe ser un número entero positivo")
        else:
            serie = generar_serie_fibonacci(posicion)
            print(f"Serie de Fibonacci hasta la posición {posicion}: {serie}")
            print(f"Valor en la posición {posicion}: {serie[-1]}")

#Crea una función recursiva que calcule la potencia de un número base elevado a un 
#exponente, utilizando la fórmula 𝑛𝑚 = 𝑛 ∗ 𝑛(𝑚−1). Prueba esta función en un 
#algoritmo general. 
def funcion_re_pot(n, m):
    if m == 0:
        return 1
    elif m == 1:
        return n
    else:
        return n * funcion_re_pot(n, m - 1)
print(funcion_re_pot(2,2))

#Crear una función recursiva en Python que reciba un número entero positivo en base 
#decimal y devuelva su representación en binario como una cadena de texto. 
#Cuando representamos un número en binario, lo expresamos usando solamente ceros (0) y 
#unos (1), en base 2. Para convertir un número decimal a binario, se puede seguir este 
#procedimiento: 
#1. Dividir el número por 2. 
#2. Guardar el resto (0 o 1). 
#3. Repetir el proceso con el cociente hasta que llegue a 0. 
#4. Los restos obtenidos, leídos de abajo hacia arriba, forman el número binario.
def decimal_a_binario(n):
    if n == 0:
        return "0"
    elif n == 1:
        return "1"
    else:
        cociente = n // 2
        resto = n % 2
        return decimal_a_binario(cociente) + str(resto)
print(decimal_a_binario(10))

#Implementá una función recursiva llamada es_palindromo(palabra) que reciba una 
#cadena de texto sin espacios ni tildes, y devuelva True si es un palíndromo o False si no 
#lo es. 
#     Requisitos: 
#La solución debe ser recursiva. 
#No se debe usar [::-1] ni la función reversed(). 
def es_palindromo(palabra):
    if len(palabra) <= 1:
        return True
    if palabra[0] != palabra[-1]:
        return False
    return es_palindromo(palabra[1:-1])
print(es_palindromo("neuquen"))

#Escribí una función recursiva en Python llamada suma_digitos(n) que reciba un 
#número entero positivo y devuelva la suma de todos sus dígitos. 
#     Restricciones: 
#No se puede convertir el número a string. 
#Usá operaciones matemáticas (%, //) y recursión. 
#Ejemplos: 
#suma_digitos(1234)   → 10  (1 + 2 + 3 + 4) 
#suma_digitos(9)      → 9 
#suma_digitos(305)    → 8   (3 + 0 + 5) 
def suma_digitos(n):
    if n < 10:
        return n
    else:
        return (n % 10) + suma_digitos(n // 10)
print(suma_digitos(12))

#Un niño está construyendo una pirámide con bloques. En el nivel más bajo coloca n 
#bloques, en el siguiente nivel uno menos (n - 1), y así sucesivamente hasta llegar al 
#último nivel con un solo bloque. 
# 
#Escribí una función recursiva contar_bloques(n) que reciba el número de bloques en el 
#nivel más bajo y devuelva el total de bloques que necesita para construir toda la 
#pirámide. 
# 
#      Ejemplos: 
#contar_bloques(1)   → 1         (1) 
#contar_bloques(2)   → 3         (2 + 1) 
#contar_bloques(4)   → 10        (4 + 3 + 2 + 1)
def contar_bloques(n):
    if n == 1:
        return 1
    else:
        return n + contar_bloques(n - 1)
print(contar_bloques(4))

#Escribí una función recursiva llamada contar_digito(numero, digito) que reciba un 
#número entero positivo (numero) y un dígito (entre 0 y 9), y devuelva cuántas veces 
#aparece ese dígito dentro del número. 
#      Ejemplos: 
#contar_digito(12233421, 2)   → 3   
#contar_digito(5555, 5)       → 4  
def contar_digito(numero, digito):
    if numero == 0:
        return 0
    else:
        ultimo_digito = numero % 10
    if ultimo_digito == digito:
        return 1 + contar_digito(numero // 10, digito)
    else:
        return 0 + contar_digito(numero // 10, digito)
print(contar_digito(5555,5))