N = 5
A = [0] * N
B = [0] * N

# Poblar la lista A
for i in range(N):
    A[i] = i + i + i  # A = [0, 3, 6, 9, 12]

# Poblar la lista B
for i in range(N):
    B[i] = i * 2  # B = [0, 2, 4, 6, 8]

contador = 0
for i in range(N):
    # Condicional con las respuestas correctas
    if A[0] != A[i] and A[0] < B[i]:
        contador += 1
        N = N - contador

resultado = str(contador)

# Verificación de A[0]
if A[0] != 1:
    resultado = "VERDADERO"
elif A[0] < 2:
    resultado = "2"
elif A[0] == 3:
    resultado = "FALSO"

print(resultado)
