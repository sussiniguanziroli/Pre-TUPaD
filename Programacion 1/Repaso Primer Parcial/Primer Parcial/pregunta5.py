#tiene que sacar 12357

for n in range(1, 11):
    bandera = False
    d = 0

    for c in range(1, n + 1):
        if n% c == 0:
            d += 1

            if d <= 2:
                bandera = True
            else:
                bandera = False
    if bandera:
        print(n, end=" ")
    