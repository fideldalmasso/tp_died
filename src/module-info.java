module tp_died {
	exports tp.dao;
	exports tp.test;
	exports tp.enumerados;
	exports tp.dominio;

	requires java.sql;
	requires junit;
	requires java.desktop;
	requires json.simple;
}