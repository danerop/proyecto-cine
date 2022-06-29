use db;

INSERT INTO genero (id, descripcion, nombre)
VALUES (1, "accion", "Accion"),
	   (2, "comedia", "Comedia"),
       (3, "terror", "Terror"),
       (4, "ciencia ficcion", "Ciencia Ficcion"),
       (5, "suspenso", "Suspenso"),
       (6, "fantacia", "Fantasia"),
       (7, "aventura", "Aventura"),
       (8, "documental", "Documental");

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

INSERT INTO peliculagenero(id, pelicula_id, genero_id)
VALUES(1,1,6),
	  (2,1,2),
      (3,2,1),
      (4,2,2),
      (5,3,6),
      (6,4,8),
      (7,5,5),
      (8,5,3),
      (9,6,5);

INSERT INTO detallesuscripcion(id, cantidadBoletosGratis, cuota, descuentoEnBoletos, tipo)
VALUES(1, 0, 0.0, 0.0, "comun"),
	  (2, 2, 900.0, 15.0, "gold"),
      (3, 4, 1250.0, 25.0, "premium");

INSERT INTO suscripcion(id, cantidadDeBoletosGratisRestantes, detalleSuscripcion_id)
VALUES (1, 0, 1),
	   (2, 2, 2),
       (3, 0, 1);

INSERT INTO usuario(id, activo, email, password, rol, urlImagenUsuario, suscripcion_id)
VALUES (1 , true, "elian@gmail.com", "1234", "admin", "img", 1),
       (2, true, "hector@gmail.com", "1234", "usuario", "img", 2),
       (3, true, "braian@gmail.com", "1234", "recepcionista", "img", 3);

INSERT INTO favorito (id, activo, genero_id, usuario_id)
VALUES (1, true, 1, 2),
	   (2, true, 3, 2),
       (3, true, 2, 1);

INSERT INTO cine(id, nombreLocal, direccion, latitud, longitud, email, telefono, urlImagenCine)
VALUES(1, "CINEMARKO", "Calle Anonima 567", -34.6356, -58.5420, "cinemarko@gmail.com", "4444-3333", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1d/90/24/84/cinemark.jpg?w=1200&h=-1&s=1"),
	  (2, "GRANPANTALLA", "Buitre 452", -34.5602, -59.1284, "granpantalla@gmail.com", "4848-5353", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Sala_de_cine.jpg/1200px-Sala_de_cine.jpg"),
	  (3, "IMAX", "Av. Corta 123", -45.8687, -67.5196, "imax@gmail.com", "4141-4523", "https://www.adslzone.net/app/uploads-adslzone.net/2018/04/IMAXNewTheater.jpg");

INSERT INTO sala(id, tipo, cine_id)
VALUES(1,"Comun",1),
	  (2,"GoldenClass",1),
	  (3,"Sala3D",1),
	  (4,"Sala4D",2);
      
INSERT INTO butaca(id, nroUbicacion, sala_id)
VALUES(1, 205, 1),
	  (2, 206, 1),
	  (3, 208, 1),
	  (4, 100, 2),
	  (5, 100, 3),
	  (6, 100, 4);
      
INSERT INTO funcion(id, entradasDisponibles, fechaHora, hora, precioMayor, precioMenor, cine_id, pelicula_id, sala_id)
VALUES(1,50,'2022-06-02',"20:30", 500, 250, 1, 1, 1),
	  (2,100,'2022-06-23',"19:00", 500, 250, 1, 1, 1);

INSERT INTO butacafuncion(id, ocupada, butaca_id, funcion_id)
VALUES(1, 1, 1, 1),
	  (2, 0, 2, 1),
      (3, 0, 3, 1),
      (4, 0, 1, 2),
	  (5, 0, 2, 2),
      (6, 0, 3, 2); 
       
select * from favorito;       
select * from pelicula;
select * from funcion;
select * from sala;
select * from cine;
select * from boleto;
select * from butacafuncion;
select * from usuario;
select * from butaca;
select * from suscripcion;
select * from detallesuscripcion;