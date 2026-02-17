CREATE DATABASE universidad_utn;
USE universidad_utn;
CREATE TABLE estudiantes(
	id_estudiantes INT PRIMARY KEY AUTO_INCREMENT, 
    nombre VARCHAR(25), 
    apellido VARCHAR(35),
    dni INT
);
SELECT *
FROM estudiantes;
CREATE TABLE profesores(
	id_profesores INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(25), 
    apellido VARCHAR(35),
    email VARCHAR(50)
);
SELECT *
FROM profesores;
SELECT *
FROM estudiantes;
CREATE TABLE materias(
	id_materias INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(25),
    id_profesores INT NOT NULL,
    foreign key (id_profesores) references profesores (id_profesores)
);
SELECT *
FROM materias;
CREATE TABLE cursadas(
	/* Vinculamos los alumnos con las materias que estan cursando */
	id_cursadas INT PRIMARY KEY AUTO_INCREMENT,
    id_estudiantes INT NOT NULL,
    id_materias INT NOT NULL,
    anio INT NOT NULL,
    nota_final FLOAT,
    foreign key (id_estudiantes) references estudiantes (id_estudiantes),
    foreign key (id_materias) references materias (id_materias)
);
SELECT *
FROM cursadas;