insert into tp.marca values
	('Scania'),
	('Iveco'),
	('Renault'),
	('Volvo'),
	('Man'),
	('Daf'),
	('Mercedes-benz'),
	('Nissan'),
	('Isuzu'),
	('Mitsubishi');

insert into tp.modelo values
('modelo1','Scania'),
('modelo2','Iveco'),
('modelo3','Renault'),
('modelo4','Volvo'),
('modelo5','Man'),
('modelo6','Daf');


insert into tp.planta values
(1, 'Planta1'),
(2, 'Planta2'),
(3, 'Planta3'),
(4, 'Planta4'),
(5, 'Planta5');

insert into tp.ruta values
('r1',1,2,200,60,1000),
('r2',1,3,50,120,3000),
('r3',1,4,150,60,1000),
('r4',2,5,200,60,1000),
('r5',3,5,200,120,1000),
('r6',3,4,50,120,2000),
('r7',4,5,50,60,3000);


insert into tp.camion values
('IXM242',1,'modelo1',0.0,400,1000,'2017-01-10'),
('NIM094',2,'modelo2',0.0,400,1000,'2020-01-17');

insert into tp.Insumo values 
(default,'Arena','KILO',670),
(default,'Agua','LITRO',500),
(default,'Aceite','METRO_CUBICO',30),
(default,'Soja','KILO',146),
(default,'Fertilizantes','GRAMO',34),
(default,'Desinfectante','LITRO',339),
(default,'Lavandina','PIEZA',16),
(default,'Caucho','METRO_CUADRADO',980);

insert into tp.InsumoGeneral values 
(1,500),
(4,90),
(5,307),
(8,900);

insert into tp.InsumoLiquido values 
(2,1),
(3, 0.8),
(6, 2.5),
(7, 1.2);

insert into tp.pedido values
(default,null,5,null,'08-03-2020',null,'10-10-2020','CREADA',null);

insert into tp.detallepedido values
(1,1,5);


