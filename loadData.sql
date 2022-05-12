use db;

INSERT INTO pelicula(nombre, anio, descripcion, duracion, urlImagenPelicula)
VALUES("Sonic 2", 2022, "descripcion", 150, "img/pelicula/sonic2.jpg"),
	  ("Dr Strange: Multiverso de la locura", 2022, "descripcion", 150, "img/pelicula/strange.jpg"),
      ("Animales Fantasticos 3", 2022, "descripcion", 150, "img/pelicula/animalesf.jpg"),
      ("Bombonera: La película del templo", 2022, "descripcion", 150, "img/pelicula/bombonera.jpg"),
      ("La Medium", 2022, "descripcion", 150, "img/pelicula/lamedium.jpg"),
      ("En la mira", 2022, "descripcion", 150, "img/pelicula/enlamira.jpg"),
      ("El hombre del norte", 2022, "descripcion", 150, "img/pelicula/elhombredelnorte.jpg"),
      ("La ciudad perdida", 2022, "descripcion", 150, "img/pelicula/ciudadperdida.jpg"),
      ("Llamas de venganza", 2022, "descripcion", 150, "img/pelicula/llamas.jpg");

INSERT INTO usuario(id, activo, email, password, urlImagenUsuario)
values (1, true, "aaa@hotmail.com", "lacontra", "www,aaaa.com");

INSERT INTO cine(id, direccion, email, nombreLocal, telefono, urlImagenCine)
VALUES(1, "cine sonic", "email","cine-id-3","telefono", "url de la imagen");

INSERT INTO sala(id, cine_id)
VALUES(1, 1);

INSERT INTO funcion(id, entradasDisponibles, pelicula_id, cine_id, sala_id, fechaHora)
VALUES (2, 50, 1, 1, 1, "2022-05-12");

select * from pelicula;
select * from funcion;
select * from sala;
select * from cine;

select * from boleto;
