# salida = 51

num1 = 3
num2 = 7
num3 = 4

if num2 % 2 == 0:
    x = num2 * 2
else: 
    x = 3 * num2 #x aca vale 21

if x % 2 == 0:
    t = x + num3
else:
    t = x - num3 #T aca vale 17

if t > 10:
    resultado_final = t * num1
else:
    resultado_final = t + num1

print(f"Resultado Final: {resultado_final}")