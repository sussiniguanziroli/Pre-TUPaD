# imprime valor 6

num1= 3
vector = [4, 6, 1]

mayor = vector[0]

for i in range(1, num1):
    if vector[i] > mayor:
        mayor = vector[i]

print(mayor)