# Entrada de datos (tiene que sacar la letra D)
dia = int(input("Introduce el valor N°1: "))   # Día
mes = int(input("Introduce el valor N°2: "))   # Mes
anio = int(input("Introduce el valor N°3: "))  # Año

if mes in [1, 3, 5, 7, 8, 10, 12]:
    dd = 31
elif mes in [4, 6, 9, 11]:
    dd = 30
elif mes == 2:
    if (anio % 4 == 0 and anio % 100 != 0) or (anio % 400 == 0):
        dd = 29
    else:
        dd = 28
else:
    print("A")
    dd = -1

if dd != -1:
    if dia < 1 or dia > dd:
        print("B")
    elif mes < 1 or mes > 12:
        print("C")
    else:
        print("D") #==> Salida deseada
