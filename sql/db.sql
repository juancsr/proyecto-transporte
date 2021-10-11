-- crear base de datos
BEGIN TRANSACTION;

CREATE TABLE genero (
  id serial PRIMARY KEY,
  nombre text UNIQUE
);

CREATE TABLE salario (
  id serial PRIMARY KEY,
  base bigint,
  valor_hora bigint,
  horas_extra_diu integer,
  horas_extra_noc integer
);

CREATE TABLE departamento (
  id serial PRIMARY KEY,
  nombre text UNIQUE
);

CREATE TABLE persona (
  id serial PRIMARY KEY,
  cedula text UNIQUE,
  nombres text,
  apellidos text,
  celular text,
  direccion text,
  telefono text,
  correoElectronico text UNIQUE
);

CREATE TABLE empleado (
  id serial PRIMARY KEY,
  salario_id int UNIQUE,
  persona_id int UNIQUE,
  genero_id integer,
  depto_id integer
);

ALTER TABLE empleado ADD FOREIGN KEY (salario_id) REFERENCES salario (id);

ALTER TABLE empleado ADD FOREIGN KEY (persona_id) REFERENCES persona (id);

ALTER TABLE empleado ADD FOREIGN KEY (genero_id) REFERENCES genero (id);

ALTER TABLE empleado ADD FOREIGN KEY (depto_id) REFERENCES departamento (id);

END TRANSACTION;

-- borrar base de datos
BEGIN TRANSACTION;
DROP TABLE empleado CASCADE;
DROP TABLE persona CASCADE;
DROP TABLE salario CASCADE;
DROP TABLE genero CASCADE;
DROP TABLE departamento CASCADE;
END TRANSACTION;

ROLLBACK;