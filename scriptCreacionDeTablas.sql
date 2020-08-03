CREATE SCHEMA tp;
CREATE TABLE tp.Planta(
	id_planta serial primary key,
	nombre varchar(64)
);
CREATE TABLE tp.Ruta(
	id_ruta varchar(16) primary key,
	id_planta_origen serial references tp.Planta(id_planta) on update cascade on delete cascade,
	id_planta_destino serial references tp.Planta(id_planta) on update cascade on delete cascade,
	distancia_en_km double precision,
	duracion_en_minutos double precision,
	peso_maximo_por_dia_en_kg double precision
);
CREATE TABLE tp.Marca(
	nombre varchar(16) primary key
);
CREATE TABLE tp.Modelo(
	nombre varchar(16) primary key,
	marca varchar(16) references tp.Marca(nombre) on update cascade on delete cascade
);
CREATE TABLE tp.Camion(
	id_camion varchar(16) primary key,
	id_planta serial references tp.Planta(id_planta) on update cascade on delete cascade,
	modelo varchar(16) references tp.Modelo(nombre) on update cascade on delete cascade,
	distancia_recorrida_en_km double precision,
	costo_por_km double precision,
	costo_por_hora double precision,
	fecha_de_compra date
);
CREATE TABLE tp.Envio(
	id_envio serial primary key,
	id_camion varchar(16) references tp.Camion(id_camion) on update cascade on delete cascade,
	costo_envio double precision
);
CREATE TABLE tp.ASeguirEn(
	id_envio serial references tp.Envio(id_envio) on update cascade on delete cascade,
	id_ruta varchar(16) references tp.Ruta(id_ruta) on update cascade on delete cascade,
	orden integer,
	primary key (id_envio, id_ruta)
);
CREATE TYPE tp.EstadoPedido AS ENUM('CREADA','PROCESADA','ENTREGADA','CANCELADA');
CREATE TABLE tp.Pedido(
	id_pedido serial primary key,
	id_planta_origen serial references tp.Planta(id_planta) on update cascade on delete cascade,
	id_planta_destino serial references tp.Planta(id_planta) on update cascade on delete cascade,
	id_envio serial references tp.Envio(id_envio) on update cascade on delete cascade,
	fecha_solicitud date,
	fecha_entrega date,
	fecha_maxima date,
	estado_pedido tp.EstadoPedido,
	costo_pedido double precision
);
CREATE TYPE tp.UnidadDeMedida AS ENUM('KILO','PIEZA','GRAMO','METRO','LITRO','METRO_CUADRADO','METRO_CUBICO');
CREATE TABLE tp.Insumo(
	id_insumo serial primary key,
	descripcion varchar(256) unique,
	unidad_de_medida tp.UnidadDeMedida,
	costo_por_unidad double precision
	);
CREATE TABLE tp.InsumoGeneral(
	id_insumo serial references tp.Insumo(id_insumo) on update cascade on delete cascade,
	peso_por_unidad double precision,
	primary key (id_insumo)
);
CREATE TABLE tp.InsumoLiquido(
	id_insumo serial references tp.Insumo(id_insumo) on update cascade on delete cascade,
	densidad double precision,
	primary key (id_insumo)
);
CREATE TABLE tp.Detallepedido(
	id_insumo serial references tp.Insumo(id_insumo) on update cascade on delete cascade,
	id_pedido serial references tp.Pedido(id_pedido) on update cascade on delete cascade,
	cantidad_de_unidades integer,
	primary key (id_insumo, id_pedido)
);
CREATE TABLE tp.StockInsumo(
	id_planta serial references tp.Planta(id_planta) on update cascade on delete cascade ,
	id_insumo serial references tp.Insumo(id_insumo) on update cascade on delete cascade ,
	stock integer,
	punto_de_pedido integer,
	primary key (id_planta, id_insumo) 
);
CREATE TABLE tp.Registro(
	id_planta serial ,
	id_insumo serial ,
	fecha_registro date,
	stock integer,
	variacion integer,
	punto_de_pedido integer,
	primary key (id_planta,id_insumo,fecha_registro)
);