Algoritmo miniCalculadora
	//Vamos a hacer el ejemplo de la mini calculadora
	//Definir numero1 Como Entero
	//Definir numero2 Como Entero
	//Definir resultado Como Entero
	//Declaramos las variables arriba en 3 linea o abajo en una sola
	
	
	Definir resultado, numero1, numero2 Como Real
	
	Definir operador Como Caracter
	Definir operadorValido Como Logico
	
	
	//Mostrar en pantalla el primer mensaje 
	Escribir "Ingrese el primer numero"
	//leemos el numero ingresado
	Leer numero1
	//escribimos el 2do mensaje
	Escribir "Ingrese el segundo numero"
	Leer numero2
	Escribir "Ingrese un operador: (+), (-),(*), (/)"
	Leer operador
	// aca se fija que el operador sea valido, es decir que tenga que
	// ver con la lista de operadores posibles en la matematica
	Segun operador Hacer
		opcion "+":
			resultado = numero1 * numero2
			operadorValido = Verdadero
			
		opcion "-": 
			resultado = numero1 - numero2
			operadorValido = Verdadero
			
		opcion "*" : 
			resultado = numero1 * numero2
			operadorValido = Verdadero
			
		opcion "/" : 
			resultado = numero1 / numero2
			operadorValido = Verdadero
			
		De Otro Modo:
			operadorValido = Falso
	FinSegun
	
	// estructura de condicional doble
	Si operadorValido es Verdadero Entonces
		//mostramos en pantalla el resultado
		Escribir "El resultado de la operacion entre ",numero1, "y " , numero2, "es", resultado
	SiNo
		//mostramos en pantalla el error
		Escribir "El Operador ingresado no es valido, reinicie el programa y vuelva a intentar"
		
	FinSi 
	
	
FinAlgoritmo
