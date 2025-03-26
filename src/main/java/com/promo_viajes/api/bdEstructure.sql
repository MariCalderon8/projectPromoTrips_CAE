CREATE DATABASE tallerviajes_cae;

USE tallerviajes_cae;

CREATE TABLE admin (
	id_admin INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	contrasena VARCHAR(60) NOT NULL,
	documento VARCHAR(20) NOT NULL UNIQUE,
	correo VARCHAR(50) NOT NULL,
	direccion VARCHAR(100),
	telefono VARCHAR(15) NOT NULL
);

CREATE TABLE destino (
	id_destino INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	ciudad VARCHAR(100) NOT NULL,
	pais VARCHAR(30) NOT NULL,
	clima VARCHAR(50) NOT NULL,
	descripcion TEXT
);

CREATE TABLE viaje (
	id_viaje INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	precio DECIMAL(6,2) NOT NULL,
	dias_duracion INT NOT NULL,
	fecha DATETIME NOT NULL,
	descripcion TEXT NOT NULL
);

CREATE TABLE viaje_destino (
	id_viaje INT NOT NULL,
	id_destino INT NOT NULL,
	
	FOREIGN KEY (id_viaje) REFERENCES viaje(id_viaje) ON DELETE CASCADE,
	FOREIGN KEY (id_destino) REFERENCES destino(id_destino) ON DELETE CASCADE
);

CREATE TABLE promocion (
	id_promo INT AUTO_INCREMENT PRIMARY KEY,
	creado_por INT NOT NULL,
	porcentaje_descuento DECIMAL(3,2) NOT NULL,
	id_viaje INT NOT NULL,
	fecha_inicio DATETIME NOT NULL,
	fecha_fin DATETIME NOT NULL,
	condiciones TEXT NOT NULL,
	descripcion TEXT NOT NULL,
	
	FOREIGN KEY (creado_por) REFERENCES admin(id_admin) ON DELETE CASCADE,
	FOREIGN KEY (id_viaje) REFERENCES viaje(id_viaje) ON DELETE CASCADE
);