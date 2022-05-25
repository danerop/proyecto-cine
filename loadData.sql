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

INSERT INTO cine(id, direccion, email, nombreLocal, telefono, urlImagenCine)
VALUES(1, "cine sonic", "email","CINE NOW","telefono", "url de la imagen"),
	  (2, "cine Strange", "email","CINEMARKO","telefono", "url de la imagen");

INSERT INTO sala(id, cine_id, cantFilas, cantColumnas)
VALUES(1, 1, 5, 10),
	  (2, 2, 5, 8);

INSERT INTO butaca(id, numColumna, numFila, ocupada, sala_id)
VALUES(1,3,3,true,1),(2,4,3,false,1),(3,5,3,false,1),(4,3,4,false,1),(5,4,4,false,1),
      (6,5,4,false,1),(7,6,4,false,1),(8,1,5,false,1),
      (9,3,4,true,2),(10,2,5,true,2),(11,1,2,true,2),(12,2,3,false,2),
      (13,3,3,false,2),(14,4,3,false,2),(15,5,3,false,2),(16,6,3,false,2),
      (17,7,3,false,2),(18,8,3,false,2);

INSERT INTO funcion(id, entradasDisponibles, pelicula_id, cine_id, sala_id, fechaHora, hora)
VALUES (1, 50, 1, 1, 1, "2022-05-13", "23:00"),
	   (2, 100, 2, 2, 2, "2022-05-20", "20:00"),
       (3, 100, 1, 2, 2, "2022-05-19", "20:00");

select * from pelicula;
select * from funcion;
select * from sala;
select * from cine;
select * from usuario;
select * from boleto;
select * from rol;

