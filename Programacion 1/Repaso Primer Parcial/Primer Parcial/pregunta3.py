#output r = 6 (que va en los ?)

lista = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

lista[0] = 1
lista[1] = 1
lista[2] = 1
lista[3] = 2
lista[4] = 1

n = 5
r = 0

if n == 0:
    r = 0
else:
    r = lista[n-1]
    for i in range(n-1, 0, -1):
        r += lista[i-1]

print(r)