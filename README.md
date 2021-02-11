# movies_application


Esta aplicación permite ver un catalogo de peliculas de superheroes, para esto se hace conexión con el API REST https://comicvine.gamespot.com/api/ . Se hace uso de persistencia de datos cache en una base de datos local, esta base de datos se actualiza por medio de un servicio backgraund periodico una vez al dia cuando en nivel de bateria es alto.

![](4wvajk.gif)

## Tecnologia aplicada

MVVM: arquitectura. <br />
Room: Base de datos local. <br />
Retrofit: Conexion a servio REST. <br />
Corutines: corutinas para manejar ejecucion de codigo asincrono.<br />
WorkManager: Servicio de proceso backgraound para actualizar la base de datos. <br />
Navigation: navegacion entre fragments. <br />
DataBinding: Union entre componentes layout y controladores. <br />

![Screenshot](final-architecture.png)

## License

Application developed by Miguel Angel Bello Garcia, github @miguelmabellog
