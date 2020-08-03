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

insert into tp.envio values
(1,null,null);

insert into tp.insumo values 
(1,'Arena','KILO',100);

insert into tp.insumoLiquido values 
(1,300);

insert into tp.pedido values
(1,null,5,null,'03-08-2020',null,'10-10-2020','CREADA',null);

insert into tp.detallepedido values
(1,1,5);

insert into tp.stockinsumo values
(1,1,100,20);

