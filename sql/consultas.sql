-- departamento
BEGIN TRANSACTION;

SELECT * FROM departamento d;

INSERT INTO departamento VALUES (1, 'Gerencia');
INSERT INTO departamento VALUES (2, 'Administrativo');
INSERT INTO departamento VALUES (3, 'Planta');

END TRANSACTION;

-- genero
BEGIN TRANSACTION;

SELECT * FROM genero g ;

INSERT INTO genero VALUES (1, 'Femenino');
INSERT INTO genero VALUES (2, 'Masculino');

END TRANSACTION;

-- salario 
BEGIN TRANSACTION;

SELECT * FROM salario s;

INSERT INTO salario VALUES (1, 1200000, 100, 100, 150);
INSERT INTO salario VALUES (2, 3000000, 20, 200, 250);
INSERT INTO salario VALUES (3, 2000000, 10, 100, 250);
INSERT INTO salario VALUES (4, 1000000, 50, 200, 500);

END TRANSACTION;

-- persona
BEGIN TRANSACTION;

SELECT * FROM persona p;

INSERT INTO persona 
	VALUES (1, '1030340142', 'Julian', 'Vaca', '322345123', 
	'Calle falsa 123', '4542312', 'jvaca@correo.udistrital.edu.co');
INSERT INTO persona 
	VALUES (2, '1010235234', 'Juan', 'Sarmiento', '3134556234', 
	'Calle falsa 312', '4542345', 'jsarmiento@correo.udistrital.edu.co');
INSERT INTO persona 
	VALUES (3, '103223010', 'Andres', 'Ramirez', '31335623413', 
	'Calle falsa 456', '4523410', 'aramirez@correo.udistrital.edu.co');
INSERT INTO persona 
	VALUES (4, '101023440', 'Esteban', 'Martinez', '3013412340',
	'Calle falsa 654', '9050131', 'emartinez@correo.udistrital.edu.co');

END TRANSACTION;

-- empleado
BEGIN TRANSACTION;

SELECT * FROM empleado e ;

INSERT INTO empleado VALUES (1, 1, 1, 2, 3);
INSERT INTO empleado VALUES (2, 2, 2, 2, 1);
INSERT INTO empleado VALUES (3, 3, 3, 2, 2);
INSERT INTO empleado VALUES (4, 4, 4, 2, 2);

END TRANSACTION;


-- lista de empleados
SELECT e.id, p.cedula, p.nombres, p.apellidos, 
p.celular, g.nombre , p.direccion , p.telefono ,
p.correoelectronico, d.nombre , s.base ,
s.valor_hora , s.horas_extra_diu , s.horas_extra_noc , 
(s.valor_hora * s.horas_extra_diu) vhed,
(s.valor_hora * s.horas_extra_noc) vhen,
106.454 transporte,
(s.base + 106454 + (s.valor_hora * s.horas_extra_noc) + (s.valor_hora * s.horas_extra_diu)) salario
FROM empleado e,
persona p ,
departamento d ,
genero g ,
salario s
WHERE e.persona_id = p.id 
AND e.depto_id = d.id
AND e.genero_id = g.id 
AND e.salario_id = s.id ;

-- todo de un empleado
SELECT e.id, p.cedula, p.nombres, p.apellidos, 
p.celular, g.nombre , p.direccion , p.telefono ,
p.correoelectronico, d.nombre , s.base ,
s.valor_hora , s.horas_extra_diu , s.horas_extra_noc , 
(s.valor_hora * s.horas_extra_diu) vhed,
(s.valor_hora * s.horas_extra_noc) vhen,
106.454 transporte,
(s.base + 106454 + (s.valor_hora * s.horas_extra_noc) + (s.valor_hora * s.horas_extra_diu)) salario,
p.id,
s.id 
FROM empleado e,
persona p ,
departamento d ,
genero g ,
salario s
WHERE e.persona_id = p.id 
AND e.depto_id = d.id
AND e.genero_id = g.id 
AND e.salario_id = s.id 
AND p.cedula = '1010225713';

-- borrar todo
DELETE FROM empleado;
DELETE FROM departamento ;
DELETE FROM genero;
DELETE FROM persona;
DELETE FROM salario;

-- si la transaccion falla correr este comando
ROLLBACK;