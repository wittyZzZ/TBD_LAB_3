
-- Creación de la base de datos
\c tbd_lab_1

\encoding UTF8

insert into CATEGORIA (id_categoria, nombre)
values (1, 'Tecnología'),
       (2, 'Electrodomésticos'),
       (3, 'Muebles para Oficina'),
       (4, 'Accesorios para Vehículos');

insert into PRODUCTO (nombre, descripcion, precio, stock, estado, id_categoria)
values ('Mouse Silencioso RGB',
 	'Mouse Silencioso RGB\nMarca: Generic\nModel: SMRGBCD001\nDescripción: Mouse inhalámbrico de botones silenciosos y luces RGB',
 	4500, 12, 'Disponible', 1),
	('Teclado DELL',
 	'Teclado inhalámbrico DELL\nMarca: DELL\nModel: KM632\nDescripción: Teclado inhalámbrico DELL distribución Latinoamericana con pad numérico',
	 23000, 5, 'Disponible', 1),
	('Notebook Asus Gamer',
	 'Notebook para juegos con gráfica integrada',
	 500000, 3, 'Disponible', 1),
	('Pendrive 32 GB',
 	'Este dispositivo posee una gran velocidad para transferir datos',
	 6500, 20, 'Disponible', 1),
	('Batería portátil',
	 'Esta batería te permite llevar contigo sin preocupaciones tus dispositivos por un largo período de tiempo',
	 500000, 4, 'Disponible', 1),
	('Computador Gamer Ryzen 5500',
	 'Computador diseñado para videojuegos de altos requisitos junto con una NVIDIA 3060',
	 720000, 1, 'Disponible', 1),
	('Smartphone Samsung Galaxy A52',
 	'Teléfono inteligente con pantalla AMOLED y cámara de alta resolución',
 	280000, 10, 'Disponible', 1),
	('Audífonos Bluetooth Sony WH-1000XM4',
 	'Audífonos inalámbricos con cancelación de ruido y sonido de alta fidelidad',
 	150000, 8, 'Disponible', 1),
	('Refrigerador LG InstaView',
	 'Refrigerador de alta capacidad con panel táctil y eficiencia energética clase A++',
	 900000, 3, 'Disponible', 2),
	('Horno Microondas Panasonic',
	 'Horno microondas con múltiples configuraciones y grill integrado',
	 120000, 7, 'Disponible', 2),
	('Escritorio Ejecutivo',
 	'Escritorio de madera con acabado premium y amplio espacio de almacenamiento',
 	180000, 5, 'Disponible', 3),
	('Silla Ergonómica para Oficina',
	 'Silla ergonómica con soporte lumbar ajustable y reposabrazos',
	 85000, 10, 'Disponible', 3),	
	('Cargador Rápido para Auto',
	 'Cargador rápido USB con soporte para carga dual',
	 12000, 15, 'Disponible', 4);
		
-- Inserción de datos en la tabla CLIENTE sin caracteres especiales
INSERT INTO CLIENTE (username, direccion, email, contrasena, telefono, rol, coordenadas) VALUES
('user_1', 'Calle 123, Santiago Centro', 'carlos.mendoza@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-1234', 'USER', ST_SetSRID(ST_MakePoint(-70.6450, -33.4550), 4326)),
('user_2', 'Av. Central 45, Providencia', 'lucia.fernandez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-5678', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.6410, -33.4520), 4326)),
('user_3', 'Calle Los Robles 85, Las Condes', 'andres.castillo@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-8765', 'USER', ST_SetSRID(ST_MakePoint(-70.5750, -33.4150), 4326)),
('user_4', 'Av. Los Alamos 10, Ñuñoa', 'maria.lopez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-4321', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.6150, -33.4630), 4326)),
('user_5', 'Calle Primavera 21, Ñuñoa', 'jorge.martinez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-2468', 'USER', ST_SetSRID(ST_MakePoint(-70.6180, -33.4660), 4326)),
('user_6', 'Av. La Esperanza 99, Maipú', 'ana.ruiz@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-1357', 'USER', ST_SetSRID(ST_MakePoint(-70.7550, -33.5020), 4326)),
('user_7', 'Calle Las Palmas 7, Maipú', 'pedro.gonzalez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-9753', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.7570, -33.5070), 4326)),
('user_8', 'Calle San Juan 88, Renca', 'laura.gil@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-3579', 'USER', ST_SetSRID(ST_MakePoint(-70.7300, -33.4000), 4326)),
('user_9', 'Av. Norte 66, Pudahuel', 'sofia.duarte@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-9632', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.8000, -33.4300), 4326)),
('user_10', 'Calle del Sol 41, La Florida', 'ramon.perez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-4682', 'USER', ST_SetSRID(ST_MakePoint(-70.5900, -33.5400), 4326)),
('user_11', 'Av. Oriente 23, La Florida', 'carmen.vazquez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-7524', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.5950, -33.5350), 4326)),
('user_12', 'Calle Pino 12, Peñalolén', 'diego.sanchez@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-6142', 'USER', ST_SetSRID(ST_MakePoint(-70.5500, -33.4900), 4326)),
('user_13', 'Av. Libertad 14, Santiago Centro', 'paula.reyes@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-8237', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.6500, -33.4510), 4326)),
('user_14', 'Calle Nueva 33, Providencia', 'alberto.morales@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-7481', 'USER', ST_SetSRID(ST_MakePoint(-70.6380, -33.4390), 4326)),
('user_15', 'Av. Los Pinos 54, Ñuñoa', 'marta.salinas@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-2153', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.6200, -33.4700), 4326)),
('user_16', 'Calle Cedros 98, Santiago Centro', 'luis.rojas@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-9425', 'USER', ST_SetSRID(ST_MakePoint(-70.6520, -33.4525), 4326)),
('user_17', 'Av. Amanecer 60, Renca', 'gloria.vega@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-7893', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.7280, -33.4020), 4326)),
('user_18', 'Calle Luna 67, Ñuñoa', 'tomas.rivera@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-1568', 'USER', ST_SetSRID(ST_MakePoint(-70.6170, -33.4640), 4326)),
('user_19', 'Av. Principal 5, Maipú', 'isabel.romero@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-8947', 'ADMIN', ST_SetSRID(ST_MakePoint(-70.7500, -33.5000), 4326)),
('user_20', 'Calle Real 19, Peñalolén', 'vicente.navarro@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-3256', 'USER', ST_SetSRID(ST_MakePoint(-70.5450, -33.4920), 4326)),
('user_21', '24 Nte., Viña del Mar', 'vicente.peralta@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-3256', 'USER', ST_SetSRID(ST_MakePoint(-71.54226306197091, -33.002067847491986), 4326)),
-- Región de Valparaíso
('user_22', 'Calle Lima 202, Viña del Mar', 'user.vina1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-1111', 'USER', ST_SetSRID(ST_MakePoint(-71.540, -33.025), 4326)),
('user_23', 'Calle Miraflores 101, San Antonio', 'user.san1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-2222', 'USER', ST_SetSRID(ST_MakePoint(-71.618, -33.585), 4326)),
-- Región del Biobío
('user_24', 'Calle Freire 404, Concepción', 'user.conce1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-3333', 'USER', ST_SetSRID(ST_MakePoint(-73.045, -36.820), 4326)),
('user_25', 'Calle Independencia 306, Chillán', 'user.chillan1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-4444', 'USER', ST_SetSRID(ST_MakePoint(-72.100, -36.610), 4326)),
-- Región de La Araucanía
('user_26', 'Calle Alemania 1201, Temuco', 'user.temuco1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-5555', 'USER', ST_SetSRID(ST_MakePoint(-72.590, -38.730), 4326)),
-- Región de Los Lagos
('user_27', 'Calle Chinquihue 321, Puerto Montt', 'user.pm1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-6666', 'USER', ST_SetSRID(ST_MakePoint(-72.940, -41.465), 4326)),
-- Región de Antofagasta
('user_28', 'Calle Baquedano 101, Antofagasta', 'user.antofa1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-7777', 'USER', ST_SetSRID(ST_MakePoint(-70.398, -23.648), 4326)),
-- Región de Tarapacá
('user_29', 'Calle Tarapacá 202, Iquique', 'user.iquique1@mail.com', '$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.', '555-8888', 'USER', ST_SetSRID(ST_MakePoint(-70.140, -20.210), 4326));

-- Inserción de datos en la tabla Tienda
INSERT INTO TIENDA (nombre, direccion, coordenadas) 
VALUES ('Tienda Costanera Center', 'Av. Andres Bello 2425, Providencia, Santiago', ST_SetSRID(ST_MakePoint(-70.6056, -33.4170), 4326)),
('Tienda La Reina', 'Av. Blest Gana 5962, La Reina, Santiago', ST_SetSRID(ST_MakePoint(-70.5685, -33.4553), 4326)),
('Tienda Las Nieves', 'Los Mallines 1996, Puente Alto, Santiago', ST_SetSRID(ST_MakePoint(-70.57602256706053, -33.59415557033961), 4326)),
('Tienda Maipú', 'Rafael Frontaura 5-99, Maipú, Santiago', ST_SetSRID(ST_MakePoint(-70.75585819880487, -33.514533912551755), 4326)),
('TecTec - Tecnología y Computación','Oficina Contable - Ventas Solo en Nuestra Web - Moneda 812, Santiago, Región Metropolitana', ST_SetSRID(ST_MakePoint(-70.64745903544836, -33.44157280323984), 4326)),
('ABCDIN Estado', 'Paseo Estado 73, 8320256 Santiago, Región Metropolitana',ST_SetSRID(ST_MakePoint(-70.64899653193504, -33.44174084107714), 4326)),
('Tecnodetodo', 'Av. Domingo Sta. María 3584, 8640722 Renca, Región Metropolitana', ST_SetSRID(ST_MakePoint(-70.69452693082134, -33.40700876892923), 4326)),
('Techcenter', 'San Diego 335, 8320000 Santiago, Región Metropolitana', ST_SetSRID(ST_MakePoint(-70.65089521869716, -33.4484501686001), 4326)),
('Tecnozona', 'Francisco Bilbao 1344, Local 103, 7500000 Providencia, Región Metropolitana', ST_SetSRID(ST_MakePoint(-70.6096002299189, -33.437859593913444), 4326)),
('Informatica y electronica Lufraja - Punto Blue express', 'Alberdi 01781, 8500020 Santiago, Quinta Normal, Región Metropolitana', ST_SetSRID(ST_MakePoint(-70.70295864279399, -33.42567739504398), 4326)),
('Tienda Viña Centro', 'Av. Valparaíso 101, Viña del Mar, Valparaíso', ST_SetSRID(ST_MakePoint(-71.543, -33.020), 4326)),
('Tienda San Antonio', 'Calle Blanco 505, San Antonio, Valparaíso', ST_SetSRID(ST_MakePoint(-71.621, -33.590), 4326)),
-- Región del Biobío
('Tienda Concepción Centro', 'Av. Paicaví 1234, Concepción, Biobío', ST_SetSRID(ST_MakePoint(-73.048, -36.826), 4326)),
('Tienda Chillán', 'Calle Libertad 345, Chillán, Biobío', ST_SetSRID(ST_MakePoint(-72.105, -36.606), 4326)),
-- Región de La Araucanía
('Tienda Temuco Centro', 'Av. Alemania 789, Temuco, La Araucanía', ST_SetSRID(ST_MakePoint(-72.593, -38.735), 4326)),
-- Región de Los Lagos
('Tienda Puerto Montt', 'Calle Antonio Varas 123, Puerto Montt, Los Lagos', ST_SetSRID(ST_MakePoint(-72.936, -41.468), 4326)),
-- Región de Antofagasta
('Tienda Antofagasta Centro', 'Av. Brasil 456, Antofagasta, Antofagasta', ST_SetSRID(ST_MakePoint(-70.400, -23.650), 4326)),
-- Región de Tarapacá
('Tienda Iquique', 'Av. Arturo Prat 789, Iquique, Tarapacá', ST_SetSRID(ST_MakePoint(-70.142, -20.215), 4326));

-- Inserción de datos en la tabla Repartidor
INSERT INTO REPARTIDOR (nombre, coordenadas)
VALUES ('Tomás Soto', ST_SetSRID(ST_MakePoint(-70.60836609, -33.50862745), 4326)),
('Carlos Barra', ST_SetSRID(ST_MakePoint(-70.6453284199841, -33.45860089018716), 4326)),
('Benjamín Tapia', ST_SetSRID(ST_MakePoint(-70.7103335299697, -33.50162765386649), 4326)),
('Matías Silva', ST_SetSRID(ST_MakePoint(-70.76772848894939, -33.44778595432466), 4326)),
('Andrés Garrido', ST_SetSRID(ST_MakePoint(-71.54492236676212, -33.02332421999087), 4326)),
-- Región de Valparaíso
('Joaquín Valdés', ST_SetSRID(ST_MakePoint(-71.538, -33.024), 4326)),
('Francisca Araya', ST_SetSRID(ST_MakePoint(-71.620, -33.590), 4326)),
-- Región del Biobío
('Cristóbal Morales', ST_SetSRID(ST_MakePoint(-73.043, -36.823), 4326)),
('Daniela Sánchez', ST_SetSRID(ST_MakePoint(-72.102, -36.609), 4326)),
-- Región de La Araucanía
('Matías Herrera', ST_SetSRID(ST_MakePoint(-72.594, -38.732), 4326)),
-- Región de Los Lagos
('Sofía Pérez', ST_SetSRID(ST_MakePoint(-72.938, -41.467), 4326)),
-- Región de Antofagasta
('Felipe Gutiérrez', ST_SetSRID(ST_MakePoint(-70.399, -23.652), 4326)),
-- Región de Tarapacá
('Camila Rojas', ST_SetSRID(ST_MakePoint(-70.141, -20.212), 4326));


-- Inserción de datos en la tabla Orden
INSERT INTO ORDEN (fecha_orden, estado, id_cliente, total, id_repartidor, id_tienda) VALUES
(TO_CHAR(CURRENT_TIMESTAMP, 'DD/MM/YY HH24:MI:SS'), 'Pagada', 1, 24000.00, null, 1),
(TO_CHAR(CURRENT_TIMESTAMP, 'DD/MM/YY HH24:MI:SS'), 'Enviada', 2, 30000.00, 1, 2),
(TO_CHAR(CURRENT_TIMESTAMP, 'DD/MM/YY HH24:MI:SS'), 'Enviada', 21, 21000.00, 5, 5),
(TO_CHAR(CURRENT_TIMESTAMP, 'DD/MM/YY HH24:MI:SS'), 'Enviada', 12, 3000.00, 3, 6),
(TO_CHAR(CURRENT_TIMESTAMP, 'DD/MM/YY HH24:MI:SS'), 'Completada', 3, 15000.00, 2, 1);

-- Inserción de datos en la tabla Poligono
INSERT INTO ZONA_REPARTO (nombre, poligono) VALUES 
('Zona Centro', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-70.68965994719805 -33.419622713280866, -70.63343880436038 -33.436767034525424, -70.60684528230406 -33.41658069751892, -70.58021703839037 -33.48742592788876, -70.69165479386703 -33.49488968909503, -70.68965994719805 -33.419622713280866)')), 4326)),
('Zona Puente Alto', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-70.55608391865024 -33.54342781519428, -70.6196885668074 -33.54201144195442, -70.59856793794873 -33.62695273425572, -70.53423499229359 -33.6061296330255, -70.55608391865024 -33.54342781519428)')), 4326)),
('Zona Viña del Mar 1', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-71.54938886905275 -33.000659557745905, -71.50974757352077 -33.006616685862916, -71.50745616915475 -33.04504009861507, -71.56817838485404 -33.023717172932265, -71.54938886905275 -33.000659557745905)')), 4326)),
('Zona Santiago', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-70.76233181623087 -33.383944888168216, -70.66545818578015 -33.36756690642463, -70.60721514415948 -33.38642613149192, -70.54203392164673 -33.329007657048415, -70.49355731556004 -33.33735851993195, -70.53503729769275 -33.609990046732385, -70.7064545773473 -33.644944725790786, -70.86187957211999 -33.5750211910383, -70.76233181623087 -33.383944888168216)')), 4326)),
-- Región de Valparaíso
('Zona Viña del Mar 2', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-71.550 -33.010, -71.530 -33.010, -71.530 -33.030, -71.550 -33.030, -71.550 -33.010)')), 4326)),
('Zona San Antonio', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-71.630 -33.580, -71.610 -33.580, -71.610 -33.600, -71.630 -33.600, -71.630 -33.580)')), 4326)),
-- Región del Biobío
('Zona Concepción', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-73.050 -36.810, -73.040 -36.810, -73.040 -36.830, -73.050 -36.830, -73.050 -36.810)')), 4326)),
('Zona Chillán', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-72.110 -36.600, -72.100 -36.600, -72.100 -36.620, -72.110 -36.620, -72.110 -36.600)')), 4326)),
-- Región de La Araucanía
('Zona Temuco', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-72.600 -38.720, -72.590 -38.720, -72.590 -38.740, -72.600 -38.740, -72.600 -38.720)')), 4326)),
-- Región de Los Lagos
('Zona Puerto Montt', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-72.950 -41.460, -72.930 -41.460, -72.930 -41.480, -72.950 -41.480, -72.950 -41.460)')), 4326)),
-- Región de Antofagasta
('Zona Antofagasta', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-70.410 -23.640, -70.390 -23.640, -70.390 -23.660, -70.410 -23.660, -70.410 -23.640)')), 4326)),
-- Región de Tarapacá
('Zona Iquique', ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(-70.150 -20.200, -70.130 -20.200, -70.130 -20.220, -70.150 -20.220, -70.150 -20.200)')), 4326));
