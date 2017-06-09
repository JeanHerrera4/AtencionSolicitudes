-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-04-26 22:02:05.604

-- tables
-- Table: Cliente
CREATE TABLE Cliente (
    cedula varchar(30) NOT NULL,
    nombre varchar(30) NOT NULL,
    apellido varchar(30) NOT NULL,
    email varchar(50) NOT NULL,
    telefono varchar(15) NULL,
    direccion varchar(30) NULL,
    usuario varchar(25) NULL,
    CONSTRAINT Cliente_pk PRIMARY KEY (cedula)
);

-- Table: Empleado
CREATE TABLE Empleado (
    cedula varchar(30) NOT NULL,
    nombre varchar(30) NOT NULL,
    apellido varchar(30) NOT NULL,
    email varchar(50) NOT NULL,
    usuario varchar(25) NULL,
    CONSTRAINT Empleado_pk PRIMARY KEY (cedula)
);

-- Table: RespuestaEncuesta
CREATE TABLE RespuestaEncuesta (
    id int NOT NULL AUTO_INCREMENT,
    satisfaccion int NOT NULL,
    cliente varchar(30) NOT NULL,
    CONSTRAINT RespuestaEncuesta_pk PRIMARY KEY (id)
);

-- Table: Rol
CREATE TABLE Rol (
    id int NOT NULL,
    descripcion varchar(15) NOT NULL,
    CONSTRAINT Rol_pk PRIMARY KEY (id)
);

-- Table: Solicitud
CREATE TABLE Solicitud (
    id int NOT NULL AUTO_INCREMENT,
    descripcion varchar(500) NOT NULL,
    complejidad varchar(15) NULL,
    fechaSolicitud date NOT NULL,
    fechaRespuesta date NULL,
    respuestaSolicitud varchar(500) NULL,
    cliente varchar(30) NOT NULL,
    sucursal int NULL,
    tipoSolicitud int NOT NULL,
    responsable varchar(30) NULL,
    CONSTRAINT Solicitud_pk PRIMARY KEY (id)
);

-- Table: Sucursal
CREATE TABLE Sucursal (
    id int NOT NULL AUTO_INCREMENT,
    nombre varchar(50) NULL,
    direccion varchar(50) NOT NULL,
    telefono varchar(15) NOT NULL,
    CONSTRAINT Sucursal_pk PRIMARY KEY (id)
);

-- Table: TipoSolicitud
CREATE TABLE TipoSolicitud (
    id int NOT NULL,
    descripcion varchar(15) NULL,
    CONSTRAINT TipoSolicitud_pk PRIMARY KEY (id)
);

-- Table: Usuario
CREATE TABLE Usuario (
    user varchar(25) NOT NULL,
    password varchar(50) NULL,
    rol int NULL,
    CONSTRAINT Usuario_pk PRIMARY KEY (user)
);

-- foreign keys
-- Reference: Cliente_Usuario (table: Cliente)
ALTER TABLE Cliente ADD CONSTRAINT Cliente_Usuario FOREIGN KEY Cliente_Usuario (usuario)
    REFERENCES Usuario (user);

-- Reference: Empleado_Usuario (table: Empleado)
ALTER TABLE Empleado ADD CONSTRAINT Empleado_Usuario FOREIGN KEY Empleado_Usuario (usuario)
    REFERENCES Usuario (user);

-- Reference: RespuestaEncuesta_Cliente (table: RespuestaEncuesta)
ALTER TABLE RespuestaEncuesta ADD CONSTRAINT RespuestaEncuesta_Cliente FOREIGN KEY RespuestaEncuesta_Cliente (cliente)
    REFERENCES Cliente (cedula);

-- Reference: Solicitud_Cliente (table: Solicitud)
ALTER TABLE Solicitud ADD CONSTRAINT Solicitud_Cliente FOREIGN KEY Solicitud_Cliente (cliente)
    REFERENCES Cliente (cedula);

-- Reference: Solicitud_Empleado (table: Solicitud)
ALTER TABLE Solicitud ADD CONSTRAINT Solicitud_Empleado FOREIGN KEY Solicitud_Empleado (responsable)
    REFERENCES Empleado (cedula);

-- Reference: Solicitud_Sucursal (table: Solicitud)
ALTER TABLE Solicitud ADD CONSTRAINT Solicitud_Sucursal FOREIGN KEY Solicitud_Sucursal (sucursal)
    REFERENCES Sucursal (id);

-- Reference: Solicitud_TipoSolicitud (table: Solicitud)
ALTER TABLE Solicitud ADD CONSTRAINT Solicitud_TipoSolicitud FOREIGN KEY Solicitud_TipoSolicitud (tipoSolicitud)
    REFERENCES TipoSolicitud (id);

-- Reference: Usuario_Rol (table: Usuario)
ALTER TABLE Usuario ADD CONSTRAINT Usuario_Rol FOREIGN KEY Usuario_Rol (rol)
    REFERENCES Rol (id);

-- Datos 
INSERT INTO Rol (id, descripcion) VALUES ('1', 'Gerente'); 

INSERT INTO Usuario (user, password, rol) VALUES ('Sergio', 'lindo', 1);

INSERT INTO Cliente (cedula, nombre, apellido, email, telefono, direccion, usuario) VALUES ('1484635', 'Jean', 'Herrera', 'Jea@gmail.com', '34545', 'Sevilla', 'Sergio');

INSERT INTO Sucursal (nombre, direccion, telefono) VALUES ('Barbosa', 'Lasdaud', '542154');

INSERT INTO Empleado (cedula, nombre, apellido, email, usuario) VALUES ('45466', 'Sergio', 'busquet', 'email@sf', 'Sergio');


-- End of file.

