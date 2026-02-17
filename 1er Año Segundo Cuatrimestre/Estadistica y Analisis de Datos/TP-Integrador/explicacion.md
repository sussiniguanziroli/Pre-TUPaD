Aquí tienes el desarrollo conceptual, punto por punto, explicando qué buscamos y cómo lo encontramos con R.

---

### Pregunta 5 (Distribución Binomial)

Para todos los incisos, usamos un modelo Binomial, que sirve para contar "éxitos" en un número fijo de intentos.
* **Intentos ($n$):** $16$ (la nueva muestra de estudiantes).
* **Probabilidad de Éxito ($p$):** La proporción que obtuvimos en la Entrega 1 (ej. "Muy Satisfecho" es $p = 143 / 240 \approx 0.5958$).

---
#### Punto 5.a

**Desarrollo**
* **Qué buscamos:** La probabilidad de que "más de 9" estudiantes estén *Muy Satisfechos*. En notación: $P(X > 9)$.
* **Datos:** $n = 16$, $p = 143 / 240 \approx 0.5958$.
* **Cómo lo calculamos:** Las funciones de R (como `pbinom`) calculan la probabilidad acumulada *hacia la izquierda* ($P(X \le k)$). Como queremos $P(X > 9)$, calculamos el complemento: $1 - P(X \le 9)$.
* **Comando R:** `1 - pbinom(9, size = 16, prob = 143/240)`

---
#### Punto 5.b

**Desarrollo**
* **Qué buscamos:** La probabilidad de que "entre 4 y 8" estudiantes estén *Satisfechos*. En notación: $P(4 \le X \le 8)$.
* **Datos:** $n = 16$, $p = 69 / 240 \approx 0.2875$.
* **Cómo lo calculamos:** Para un intervalo, restamos dos probabilidades acumuladas:
    1.  La probabilidad total hasta el límite superior (inclusive): $P(X \le 8)$.
    2.  La probabilidad total hasta el límite inferior (sin incluirlo): $P(X \le 3)$.
    * El cálculo es: $P(X \le 8) - P(X \le 3)$.
* **Comando R:** `pbinom(8, size = 16, prob = 69/240) - pbinom(3, size = 16, prob = 69/240)`

---
#### Punto 5.c

**Desarrollo**
* **Qué buscamos:** La probabilidad de que "menos de 5" estudiantes estén *Insatisfechos*. En notación: $P(X < 5)$, que para una variable discreta es lo mismo que $P(X \le 4)$.
* **Datos:** $n = 16$, $p = 16 / 240 \approx 0.0667$.
* **Cómo lo calculamos:** Es una probabilidad acumulada directa desde 0 hasta 4.
* **Comando R:** `pbinom(4, size = 16, prob = 16/240)`

---
#### Punto 5.d

**Desarrollo**
* **Qué buscamos:** La probabilidad de que "exactamente 10" estudiantes estén *Muy Insatisfechos*. En notación: $P(X = 10)$.
* **Datos:** $n = 16$, $p = 12 / 240 = 0.05$.
* **Cómo lo calculamos:** Usamos la función de densidad (`dbinom`), que calcula la probabilidad de un punto exacto, no una acumulación.
* **Comando R:** `dbinom(10, size = 16, prob = 12/240)`

---

### Pregunta 6 (Distribución de Poisson)

Para todos los incisos, usamos un modelo de Poisson, que sirve para medir la frecuencia de eventos en un intervalo de tiempo.
* **Tasa Base ($\lambda$):** $15$ consultas / $30$ minutos.
* **Tasa Unitaria ($\lambda$):** $0.5$ consultas / $1$ minuto.
Usaremos esta tasa unitaria para ajustar Lambda (la media esperada) a cada período de tiempo.

---
#### Punto 6.a

**Desarrollo**
* **Qué buscamos:** La probabilidad de que lleguen "por lo menos 6" consultas en $20$ minutos. En notación: $P(X \ge 6)$.
* **Datos:**
    * Período: $20$ minutos.
    * Nuevo Lambda ($\lambda$): $(0.5 \text{ cons./min}) \cdot 20 \text{ min} = 10$.
* **Cómo lo calculamos:** Al igual que en 5.a, calculamos el complemento. $P(X \ge 6)$ es $1 - P(X < 6)$, que es $1 - P(X \le 5)$.
* **Comando R:** `1 - ppois(5, lambda = 10)`

---
#### Punto 6.b

**Desarrollo**
* **Qué buscamos:** La probabilidad de que lleguen "a lo sumo 12" consultas en $40$ minutos. En notación: $P(X \le 12)$.
* **Datos:**
    * Período: $40$ minutos.
    * Nuevo Lambda ($\lambda$): $(0.5 \text{ cons./min}) \cdot 40 \text{ min} = 20$.
* **Cómo lo calculamos:** Es una probabilidad acumulada directa.
* **Comando R:** `ppois(12, lambda = 20)`

---
#### Punto 6.c

**Desarrollo**
* **Qué buscamos:** La probabilidad de que lleguen "más de 7 y menos de 10" consultas en $30$ minutos. En notación: $P(7 < X < 10)$. Esto significa $P(X = 8) + P(X = 9)$.
* **Datos:**
    * Período: $30$ minutos.
    * Lambda ($\lambda$): $15$ (el dato original).
* **Cómo lo calculamos:** Usamos la función de densidad (`dpois`) para sumar las probabilidades exactas de 8 y 9.
* **Comando R:** `dpois(8, lambda = 15) + dpois(9, lambda = 15)`

---

### Pregunta 7 (Distribución Normal)

Para todos los incisos, usamos un modelo Normal (la "campana de Gauss"), que describe variables continuas.
* **Media ($\mu$):** $162.6917$ (calculada del archivo Excel).
* **Desvío Típico ($\sigma$):** $8.3963$ (calculado del archivo Excel).
Estos $\mu$ y $\sigma$ definen la forma y posición de nuestra campana de estaturas.

---
#### Punto 7.a

**Desarrollo**
* **Qué buscamos:** La probabilidad de que un estudiante mida "$179 \text{ cm}$ o más". En notación: $P(X \ge 179)$.
* **Datos:** $\mu = 162.6917$, $\sigma = 8.3963$.
* **Cómo lo calculamos:** Igual que 5.a y 6.a, calculamos el complemento. La probabilidad total ($1$) menos el área a la izquierda de $179$. $P(X \ge 179) = 1 - P(X < 179)$. (En variables continuas, $P(X < 179)$ es igual a $P(X \le 179)$).
* **Comando R:** `1 - pnorm(179, mean = media_estatura, sd = desvio_estatura)`

---
#### Punto 7.b

**Desarrollo**
* **Qué buscamos:** La probabilidad de que un estudiante mida "entre $147 \text{ cm}$ y $172 \text{ cm}$". En notación: $P(147 \le X \le 172)$.
* **Datos:** $\mu = 162.6917$, $\sigma = 8.3963$.
* **Cómo lo calculamos:** Restamos dos probabilidades acumuladas (como en 5.b).
    1.  Calculamos el área total desde la izquierda hasta $172$: $P(X \le 172)$.
    2.  Calculamos el área total desde la izquierda hasta $147$: $P(X \le 147)$.
    3.  La resta de ambas nos da el área del intervalo.
* **Comando R:** `pnorm(172, ...) - pnorm(147, ...)`

---
#### Punto 7.c

**Desarrollo**
* **Qué buscamos:** Un valor $k$ (una estatura) que "excede al $97.5\%$ de las estaturas". Esto significa que el $97.5\%$ de los estudiantes mide *más* que $k$ ($P(X > k) = 0.975$).
* **Datos:** $\mu = 162.6917$, $\sigma = 8.3963$.
* **Cómo lo calculamos:** Si el $97.5\%$ está a la derecha de $k$, significa que solo el $2.5\%$ está a la izquierda ($1 - 0.975 = 0.025$).
    * Buscamos el valor $k$ que corresponde a una probabilidad acumulada de $0.025$ (el percentil 2.5).
    * Para esto usamos la función "cuantil" (`qnorm`), que hace lo opuesto a `pnorm`: le damos una probabilidad y nos devuelve el valor $k$.
* **Comando R:** `qnorm(0.025, mean = media_estatura, sd = desvio_estatura)`