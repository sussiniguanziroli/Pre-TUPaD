library(readxl)

datos_planilla <- read_excel("plantilla3-datos.xlsx")

media_estatura <- mean(datos_planilla$"ESTATURA CM.", na.rm = TRUE)
desvio_estatura <- sd(datos_planilla$"ESTATURA CM.", na.rm = TRUE)

print(paste("Media Estatura (μ):", media_estatura))
print(paste("Desvío Estatura (σ):", desvio_estatura))

p_ms <- 143 / 240
p_s  <- 69 / 240
p_i  <- 16 / 240
p_mi <- 12 / 240

n_binomial <- 16

prob_5a <- 1 - pbinom(9, size = n_binomial, prob = p_ms)
cat("--- Pregunta 5.a (Binomial) ---\n")
cat("P(X > 9 'Muy Satisfechos') =", prob_5a, "\n\n")

prob_5b <- pbinom(8, size = n_binomial, prob = p_s) - pbinom(3, size = n_binomial, prob = p_s)
cat("--- Pregunta 5.b (Binomial) ---\n")
cat("P(4 <= X <= 8 'Satisfechos') =", prob_5b, "\n\n")

prob_5c <- pbinom(4, size = n_binomial, prob = p_i)
cat("--- Pregunta 5.c (Binomial) ---\n")
cat("P(X < 5 'Insatisfechos') =", prob_5c, "\n\n")

prob_5d <- dbinom(10, size = n_binomial, prob = p_mi)
cat("--- Pregunta 5.d (Binomial) ---\n")
cat("P(X = 10 'Muy Insatisfechos') =", prob_5d, "\n\n")

lambda_20min <- 10
prob_6a <- 1 - ppois(5, lambda = lambda_20min)
cat("--- Pregunta 6.a (Poisson) ---\n")
cat("P(X >= 6 en 20 min) =", prob_6a, "\n\n")

lambda_40min <- 20
prob_6b <- ppois(12, lambda = lambda_40min)
cat("--- Pregunta 6.b (Poisson) ---\n")
cat("P(X <= 12 en 40 min) =", prob_6b, "\n\n")

lambda_30min <- 15
prob_6c <- dpois(8, lambda = lambda_30min) + dpois(9, lambda = lambda_30min)
cat("--- Pregunta 6.c (Poisson) ---\n")
cat("P(7 < X < 10 en 30 min) =", prob_6c, "\n\n")

cat("--- Pregunta 7 (Normal) ---\n")
cat("Usando Media (μ) =", media_estatura, "y Desvío Típico (σ) =", desvio_estatura, "\n\n")

prob_7a <- 1 - pnorm(179, mean = media_estatura, sd = desvio_estatura)
cat("--- Pregunta 7.a (Normal) ---\n")
cat("P(Estatura >= 179 cm) =", prob_7a, "\n\n")

prob_7b_sup <- pnorm(172, mean = media_estatura, sd = desvio_estatura)
prob_7b_inf <- pnorm(147, mean = media_estatura, sd = desvio_estatura)
prob_7b <- prob_7b_sup - prob_7b_inf
cat("--- Pregunta 7.b (Normal) ---\n")
cat("P(147 cm <= Estatura <= 172 cm) =", prob_7b, "\n\n")

prob_acumulada_7c <- 0.025
valor_k <- qnorm(prob_acumulada_7c, mean = media_estatura, sd = desvio_estatura)
cat("--- Pregunta 7.c (Normal) ---\n")
cat("El valor de estatura que excede al 97.5%% de las estaturas es (Percentil 2.5):", valor_k, "cm\n\n")

prob_acumulada_7cb <- 0.975
valor_kb <- qnorm(prob_acumulada_7cb, mean = media_estatura, sd = desvio_estatura)
cat("--- Pregunta 7.c (Normal) ---\n")
cat("El valor de estatura que excede al 97.5%% de las estaturas es (Percentil 2.5):", valor_kb, "cm\n\n")