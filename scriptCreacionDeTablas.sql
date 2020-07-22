--https://www.tutorialspoint.com/how-to-run-sql-script-using-jdbc

CREATE SCHEMA tp;
CREATE TABLE tp.Planta(
	id_planta varchar(16) primary key
);
CREATE TABLE tp.Ruta(
	id_ruta varchar(16) primary key,
	id_planta_origen varchar(16) references tp.Planta(id_planta),
	id_planta_destino varchar(16) references tp.Planta(id_planta),
	distancia_en_km double precision,
	duracion_en_minutos double precision,
	peso_maximo_por_dia_en_kg double precision
);
CREATE TABLE tp.Marca(
	nombre varchar(16) primary key
);
CREATE TABLE tp.Modelo(
	nombre varchar(16) primary key,
	marca varchar(16) references tp.Marca(nombre)
);
CREATE TABLE tp.Camion(
	id_camion varchar(16) primary key,
	id_planta varchar(16) references tp.Planta(id_planta),
	modelo varchar(16) references tp.Modelo(nombre),
	distancia_recorrida_en_km double precision,
	costo_por_km double precision,
	costo_por_hora double precision,
	fecha_de_compra date
);
CREATE TABLE tp.Envio(
	id_envio varchar(16) primary key,
	id_camion varchar(16) references tp.Camion(id_camion)
);
CREATE TABLE tp.ASeguirEn(
	id_envio varchar(16) references tp.Envio(id_envio),
	id_ruta varchar(16) references tp.Ruta(id_ruta),
	orden integer
);
CREATE TYPE tp.EstadoPedido AS ENUM('CREADA','PROCESADA','ENTREGADA','CANCELADA');
CREATE TABLE tp.Pedido(
	id_pedido varchar(16) primary key,
	id_planta_origen varchar(16) references tp.Planta(id_planta),
	id_planta_destino varchar(16) references tp.Planta(id_planta),
	id_envio varchar(16) references tp.Envio(id_envio),
	fecha_solicitud date,
	fecha_entrega date,
	fecha_maxima date,
	estado_pedido tp.EstadoPedido,
	costo double precision
);
CREATE TYPE tp.UnidadDeMedida AS ENUM('KILO','PIEZA','GRAMO','METRO','LITRO','METROCUADRADO','METROCUBICO');
CREATE TABLE tp.Insumo(
	id_insumo varchar(16) primary key,
	descripcion varchar(256),
	unidad_de_medida tp.unidadDeMedida,
	costo_unidad double precision
	);
CREATE TABLE tp.InsumoGeneral(
	id_insumo varchar(16) references tp.Insumo(id_insumo),
	id_insumo_general varchar(16),
	primary key (id_insumo,id_insumo_general)
);
CREATE TABLE tp.InsumoLiquido(
	id_insumo varchar(16) references tp.Insumo(id_insumo),
	id_insumo_liquido varchar(16),
	densidad double precision
);
CREATE TABLE tp.Detallepedido(
	id_insumo varchar(16) references tp.Insumo(id_insumo),
	id_pedido varchar(16) references tp.Pedido(id_pedido),
	cantidad_de_unidades integer,
	primary key (id_insumo, id_pedido)
);
CREATE TABLE tp.StockInsumo(
	id_planta varchar(16) references tp.Planta(id_planta) unique,
	id_insumo varchar(16) references tp.Insumo(id_insumo) unique,
	stock integer,
	punto_de_pedido integer,
	primary key (id_planta, id_insumo)
);
CREATE TABLE tp.Registro(
	id_planta varchar(16) references tp.StockInsumo(id_planta),
	id_insumo varchar(16) references tp.StockInsumo(id_insumo),
	fecha_registro date,
	stock integer,
	puntod_de_pedido integer
);