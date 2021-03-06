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
('RN021',2,1,50  ,37.5,598517.6),
('RN031',3,1,600  ,480,599999.6),
('RN032',3,2,80  ,30,599999.6),
('RN043',4,3,230  ,180,599999.6),
('RN053',5,3,224.9  ,300,599.6),
('RN064',6,4,160  ,300,599983.6),
('RN095',9,5,476  ,327,198353.6),
('RN102',10,4,893  ,598.3,599983.6),
('RN03',2,5,3060,2295,56705.7),
('RN05',4,5,545 ,408.75,783060.9),
('RN07',4,5,1224,918,245206.4),
('RN08',4,5,695 ,521.25,385843.4),
('RN48',4,8,695 ,503.40,385843.4),
('RN047',4,7,300 ,350,385843.4),
('RN078',7,8,100 ,200.30,385843.4),
('RN067',6,7,60 ,50.30,385843.4),
('RN167',6,7,62 ,49.30,3843.4),
('RN09',3,4,1967,1475.2,922756.2),
('RN11',3,4,980 ,735,767173.0),
('RN134',3,4,1560,1170,492579.7),
('RN34',3,4,1127,845.25,693260.6),
('RN059',5,9,340 ,250,5601.0),
('RN039',3,9,60 ,50.30,385843.4),
('RN14',1,4,337 ,252.75,561901.0),
('RN170',1,5,33 ,252.75,561901.0),
('RN171',1,5,330 ,252.75,561901.0),
('RN156',5,6,40 ,25,561901.0),
('RN136',3,6,30 ,70,561901.0),
('RN710',7,10,100 ,50.30,843.4),
('RN910',9,10,38 ,22.5,843.4),
('RN0610',6,10,300 ,50.30,385.4),
('RN16',1,6,437 ,210.75,561901.0),
('RN18',1,4,200 ,170.25,853246.3),
('RN20',1,4,582 ,436.5,339037.1),
('RN22',3,5,685 ,513.75,126983.2),
('RN38',1,3,807 ,605.25,48850.1),
('RN023',2,3,60 ,50.30,385843.4),
('RN012',1,2,524,782,842250.1);

insert into tp.camion values
('QAO986', 1 , 'Tector Evo', 312694.4, 82.4, 3889.2, '2001-12-23'),
('MZM881', 2 , 'Tector Evo', 317145.4, 144.3, 6669.3, '2007-10-08'),
('PGC698', 3 , 'N230UB', 220877.2, 131.3, 8382.5, '2008-05-09'),
('MHY150', 4 , 'XF 95.480', 3321.8, 64.7, 3080.4, '2009-07-13'),
('AVC718', 5 , 'Tector Attack', 405658.0, 41.6, 4259.1, '2000-12-20'),
('AHH635', 6 , 'T High 520', 325946.2, 70.6, 8964.0, '2003-10-05'),
('OOI264', 7 , 'TGM', 493823.6, 83.2, 7237.5, '2013-01-25'),
('ZJA097', 8 , 'D 280', 266777.8, 75.9, 6749.9, '2010-08-05'),
('HJP970', 9 , 'N270UB', 237522.7, 54.8, 10014.8, '2006-03-24'),
('HDG584', 10, 'VM', 305729.8, 147.0, 7238.5, '2010-05-25'),
('MYY775', 1 , 'Tector Attack', 310495.9, 58.3, 10008.8, '2009-04-26'),
('AJA983', 2 , 'T High 520', 19165.1, 100.0, 3472.4, '2018-11-07'),
('TIP586', 3 , 'XF Space', 329312.5, 39.2, 3536.6, '2013-05-24'),
('YII204', 4 , 'VM', 455927.4, 45.1, 11739.5, '2010-03-10'),
('HHY963', 5 , 'TGX D38', 357573.9, 47.6, 6446.4, '2013-03-13'),
('MVA219', 6 , 'Tector Attack', 458801.1, 32.6, 7396.5, '2000-01-02'),
('LTN739', 7 , 'K250IB', 426897.1, 60.0, 7486.8, '2012-01-31'),
('NKZ791', 8 , 'T High 520', 75629.7, 49.1, 6497.3, '2002-04-02'),
('WRJ991', 9 , 'VM', 393304.4, 144.7, 9781.6, '2019-08-03'),
('ZWX581', 10, 'TGX D38', 427723.8, 32.9, 3340.7, '2014-06-25');


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

insert into tp.StockInsumo values
(1,1,100,3),
(2,1,200,3),
(3,1,300,3),
(4,1,400,3),
(5,1,500,3),
(6,1,600,3),
(7,1,700,3),
(8,1,800,3),
(9,1,900,3),
(10,1,1000,3),
(1,2,100,3),
(2,2,200,3),
(3,2,300,3),
(4,2,400,3),
(5,2,500,3),
(6,2,600,3),
(7,2,700,3),
(8,2,800,3),
(9,2,900,3),
(10,2,1000,3),
(1,3,100,3),
(2,3,200,3),
(3,3,300,3),
(4,3,400,3),
(5,3,500,3),
(6,3,600,3),
(7,3,700,3),
(8,3,800,3),
(9,3,900,3),
(10,3,1000,3),
(1,4,100,3),
(2,4,200,3),
(3,4,300,3),
(4,4,400,3),
(5,4,500,3),
(6,4,600,3),
(7,4,700,3),
(8,4,800,3),
(9,4,900,3),
(10,4,1000,3),
(1,5,100,3),
(2,5,200,3),
(3,5,300,3),
(4,5,400,3),
(5,5,500,3),
(6,5,600,3),
(7,5,700,3),
(8,5,800,3),
(9,5,900,3),
(10,5,1000,3),
(1,6,100,3),
(2,6,200,3),
(3,6,300,3),
(4,6,400,3),
(5,6,500,3),
(6,6,600,3),
(7,6,700,3),
(8,6,800,3),
(9,6,900,3),
(10,6,1000,3),
(1,7,100,3),
(2,7,200,3),
(3,7,300,3),
(4,7,400,3),
(5,7,500,3),
(6,7,600,3),
(7,7,700,3),
(8,7,800,3),
(9,7,900,3),
(10,7,1000,3),
(1,8,100,3),
(2,8,200,3),
(3,8,300,3),
(4,8,400,3),
(5,8,500,3),
(6,8,600,3),
(7,8,700,3),
(8,8,800,3),
(9,8,900,3),
(10,8,1000,3);


insert into tp.pedido values
(default,null,4,null,'08-04-2020',null,'10-09-2020','CREADA',null),
(default,null,5,null,'08-03-2020',null,'10-12-2022','CREADA',null),
(default,null,1,null,'06-19-2019',null,'10-10-2021','CREADA',null),
(default,null,3,null,'07-07-2017',null,'12-05-2027','CREADA',null),
(default,null,6,null,'01-01-2011',null,'12-05-2027','CREADA',null),
(default,null,7,null,'04-14-2014',null,'12-05-2027','CREADA',null),
(default,null,8,null,'09-09-2016',null,'12-05-2027','CREADA',null),
(default,null,9,null,'02-17-2008',null,'12-05-2027','CREADA',null),
(default,null,10,null,'11-08-2010',null,'12-05-2027','CREADA',null),
(default,null,2,null,'02-11-2018',null,'09-11-2025','CREADA',null);



insert into tp.detallepedido values
(1,1,5),
(2,1,5),
(1,2,5),
(2,2,5),
(1,3,5),
(2,4,5),
(3,4,5),
(1,4,5),
(1,5,5),
(1,6,5),
(2,6,5),
(3,6,5),
(4,6,5),
(5,6,5),
(1,7,5),
(2,7,5),
(3,7,5),
(1,8,5),
(2,8,5),
(3,8,5),
(1,9,5),
(1,10,5),
(2,10,5);




