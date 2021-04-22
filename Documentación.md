# App valores







![AppValores](https://github.com/jesusdavidguti/ProyectoFinDeCurso/blob/img/bolsa.png "AppValores")





​																									                                                                                Módulo: DAW

​																									                                                                                Proyecto: appValores

​																									                                                                                Fecha de exposición: xx/xx/xx

​																									                                                                                Alumno: Jesús David Gutiérrez Delgado

​																									                                                                                Tutor: Alfonso 



[TOC]



# Introducción

​		El objetivo de este proyecto es el desarrollo de una aplicación web capaz de almacenar y mostrar información de valores bursátiles y su evolución en el tiempo. La aplicación se podrá ejecutar en los principales navegadores así como en distintos dispositivos móviles. Su funcionalidad será muy básica en un primer momento por lo que será susceptible de mejoras en un futuro. Esta funcionalidad básica es lo que hace que vaya orientada al gran público o a personas con pocos o ningún conocimiento sobre infromática y que sólo dispongan de un dispositivo móvil o tablet.

# Objetivos

​		El objetivo general es tener una app capaz de mostrar información comparativa de valores bursátiles y que dicha información sea accesible por distintos sistemas. La información será recogida y almacenada por un sistema back-end, que también desarrollaremos, y que permitirá no sólo el acceso sino también su mantenimiento (CRUD).

​		De lo dicho anteriormente se deduce que los objetivos específicos a conseguir serán los siguientes:

- Desarrollar un Back-End que, mediante API, permita:
  - Hacer CRUD de mercados.
  - Hacer CRUD de valores bursátiles.
  - Hacer CRUD de divisas.
  - Añadir información de precios para un valor o divisa determinado en el tiempo.
  - Consultar el historial de precios para un valor o divisa determinado.

- Desarrollar un Front-End que, haciendo uso de la API:
  - Muestre una portada con los 5 valores que más suben y los que más bajan en cada mercado.
  - Permita consultar el historial de un valor, mostrando gráficas con su evolución en 1 día, 5 días, una semana, un mes, 6 meses y un año. Se podrá consultar más de un valor a la vez.
  - Permita definir una cartera de inversión (añadir cuántas acciones se dispone de cada valor) y muestre gráficas e información sobre la composición de la cartera, su precio y su historial.

# Análisis previo.

​		Para la consecución de nuestros objetivos deberemos contar con un software capaz de acceder a un sistema remoto de almacenamiento donde tendremos los datos. Deberemos contar también con un gestor de BB.DD (SGBD) y las herramientas necesarias para interactuar con el. La interfaz con el usuario, o front-end, será independiente del sistema de gestión de los datos, de hecho tendrán alojamiento en distintos servidores. 

​	La facilidad de mantenimiento, la seguridad y la interoperabilidad futura con otros sistemas son los motivos que nos han llevado a plantear una solución de este tipo. Veamos cada uno de ellos con más detalle:

- Mantenimiento: al estar dividido el sistema en dos partes bien diferenciadas, back-end y front-end, su mantenimiento y solución de incidencias en mucho más simple, evitando que el fallo puntual de alguna de sus funcionalidades paralice todo el sistema.
- Seguridad: ambos sistemas estarán alojados en servidores distintos, lo que reducirá a la mitad la posibilidad de que haya una pérdida total del sistema.
- Interoperabilidad: nuestro back-end podrá ser utilizado por futuras aplicaciones, no sólo la nuestra, sin necesidad de realizar cambios en la misma.

# Recursos

​		Para llevar a cabo este proyecto, necesitaremos contar con los siguientes recursos:

- Hardware: para el trabajo de desarrollo, propiamente dicho, necesitamos contar con un equipo capaz de hacer correr a la vez todos los recursos software que veremos en el siguiente punto. Se trata de un equipo dotado de un procesador Intel i7 a 3Ghz de velocidad y con 32 Gb de RAM. 

- Software: son varias las aplicaciones y herramientas que necesitaremos. Serían las siguientes:

  - S.O: Windows 10 Pro.
  - IDE: utilizaremos Eclipse en la versión 2019-12
  - SGBD: MySql administrado desde phpMyAdmin
  - Pruebas: Postman 8.1
  - Documentación: utilizaremos Typora como editor para Markdown.,

- Repositorios: para la gestión documental y del desarrollo nos apoyaremos en Github. Nuestro repositorio de Github se llamará [ProyectoFinDeCurso](https://github.com/jesusdavidguti/ProyectoFinDeCurso) y contará con las siguientes ramas:

  - [Main](https://github.com/jesusdavidguti/ProyectoFinDeCurso): resumen visual y bibliografía

  - [Backend](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/backend): objetos del proyecto en Spring y JPA

  - [Frontend](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/frontend): páginas y código en JavaScript

  - [Doc](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/doc): documentación asociada al proyecto, diagramas en formato drawio, etc.

  - [Img](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/img): imágenes o gráficos explicativos

    

- Servicios online:

  - Despliegue back-end: Heroku
  - Despliegue front-end: Vercel
  - Despliegue SGBD: Gearhost

  ​																																																																																																																						

