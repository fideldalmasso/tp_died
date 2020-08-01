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
(default, 'Planta1'),
(default, 'Planta2'),
(default, 'Planta3'),
(default, 'Planta4'),
(default, 'Planta5');

insert into tp.camion values
('IXM242',1,'modelo1',0.0,400,1000,'2017-01-10'),
('NIM094',2,'modelo2',0.0,400,1000,'2020-01-17');


insert into tp.insumo values 
(default,'Arena','KILO',100);


insert into tp.stockInsumo values
(0,1,0,0);

