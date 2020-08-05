insert into tp.marca values
	('Scania'),
	('Iveco'),
	('Renault'),
	('Volvo'),
	('Man'),
	('Daf');

insert into tp.modelo values
('K250IB','Scania'),
('N230UB','Scania'),
('N270UB','Scania'),

('Tector Attack','Iveco'),
('Tector Evo','Iveco'),
('Stralis','Iveco'),

('T High 440','Renault'),
('T High 520','Renault'),
('D 280','Renault'),

('FMX','Volvo'),
('VM','Volvo'),
('FH','Volvo'),

('TGX D38','Man'),
('L 2000','Man'),
('TGM','Man'),

('XF Space','Daf'),
('XF95 Tropco','Daf'),
('XF 95.480','Daf');


insert into tp.planta values
(default, 'COFCO'),
(default, 'AGD'),
(default, 'Vicentín'),
(default, 'A.D.M. Agro'),
(default, 'Cargill'),
(default, 'Bunge'),
(default, 'Oleaginosa Moreno'),
(default, 'Amaggi'),
(default, 'Cargill'),
(default, 'ACA');

insert into tp.ruta values
('RN01',4,5,50  ,37.5,598517.6),
('RN03',2,5,3060,2295,56705.7),
('RN05',4,5,545 ,408.75,783060.9),
('RN07',4,5,1224,918,245206.4),
('RN08',4,5,695 ,521.25,385843.4),
('RN09',3,4,1967,1475.2,922756.2),
('RN11',3,4,980 ,735,767173.0),
('RN12',3,4,1560,1170,492579.7),
('RN14',3,4,1127,845.25,693260.6),
('RN17',1,4,337 ,252.75,561901.0),
('RN18',1,4,227 ,170.25,853246.3),
('RN20',1,4,582 ,436.5,339037.1),
('RN22',3,5,685 ,513.75,126983.2),
('RN38',1,3,807 ,605.25,48850.1),
('RN40',1,2,5224,3918,842250.1);

insert into tp.camion values
('IXM242',1,'K250IB',0.0,400,1000,'2017-01-10'),
('NIM094',2,'N230UB',0.0,400,1000,'2020-01-17');

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

insert into tp.stockinsumo values
(1,1,100,20);

insert into tp.pedido values
(default,null,5,null,'08-03-2020',null,'10-10-2020','CREADA',null);

insert into tp.detallepedido values
(1,1,5);


