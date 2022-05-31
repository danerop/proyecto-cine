use db;
/*Pelicula*/
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

INSERT INTO rol(id, nombre, descripcion)
values (1, "admin", "Usuario con gestion de rol"),
	   (2, "usuario", "Usuario"),
	   (3, "recepcionista", "Usuario que puede recibir boletos");

INSERT INTO usuario(id, activo, email, password, urlImagenUsuario, rol_id)
values (1, true, "elian@gmail.com", "1234", "img", 1),
       (2, true, "hector@gmail.com", "1234", "img", 2),
       (3, true, "braian@gmail.com", "1234", "img", 3);

INSERT INTO cine(id, nombreLocal, direccion, email, telefono, urlImagenCine)
VALUES(1, "CINEMARKO", "Calle Anonima 567", "cinemarko@gmail.com", "4444-3333", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1d/90/24/84/cinemark.jpg?w=1200&h=-1&s=1"),
	  (2, "GRANPANTALLA", "Buitre 452", "granpantalla@gmail.com", "4848-5353", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Sala_de_cine.jpg/1200px-Sala_de_cine.jpg"),
      (3, "IMAX", "Av. Corta 123", "imax@gmail.com", "4141-4523", "https://www.adslzone.net/app/uploads-adslzone.net/2018/04/IMAXNewTheater.jpg");

INSERT INTO sala(id, tipo, cine_id)
VALUES(1,"Comun",1),
	  (2,"GoldenClass",1),
      (3,"Sala3D",1),
      (4,"Sala4D",2);
      
INSERT INTO butaca(id, nroUbicacion, ocupada, sala_id)
VALUES(1, 205, 0, 1),
	  (2, 206, 0, 1),
      (3, 100, 1, 1),
      (4, 100, 0, 2),
      (5, 100, 0, 3),
      (6, 100, 0, 4);
      
INSERT INTO funcion(id, entradasDisponibles, fechaHora, hora, precioMayor, precioMenor, cine_id, pelicula_id, sala_id)
VALUES(1,50,'2022-05-24',"20:30", 500, 250, 1, 1, 1);

