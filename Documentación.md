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
  - Documentación: utilizaremos Typora como editor para Markdown así como LibreOffice Writter

- Repositorios: para la gestión documental y del desarrollo nos apoyaremos en Github. Nuestro repositorio de Github se llamará [ProyectoFinDeCurso](https://github.com/jesusdavidguti/ProyectoFinDeCurso) y contará con las siguientes ramas:

  - [Main](https://github.com/jesusdavidguti/ProyectoFinDeCurso): resumen visual y bibliografía
  - [Backend](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/backend): objetos del proyecto en Spring y JPAl. Desde esta rama se realizará el despliegue en Heroku
  - [Frontend](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/frontend): páginas y código en JavaScript. Desde esta rama se realizará el despliegue en Vercel
  - [Doc](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/doc): documentación asociada al proyecto, diagramas en formato drawio, etc.
  - [Img](https://github.com/jesusdavidguti/ProyectoFinDeCurso/tree/img): imágenes o gráficos explicativos

- Servicios online:

  - Despliegue back-end: [Heroku](https://id.heroku.com/login)
  - Despliegue front-end: [Vercel](https://vercel.com/login)
  - Despliegue SGBD: [Gearhost](https://www.gearhost.com/)																																																																														


# Desarrollo del proyecto.

## Implantación.

La implantación deberá ser realizada en las siguientes fases para ir comprobando el correcto funcionamiento de la aplicación.

- BB.DD: crearemos nuestra base de datos en GearHost indicando que se trata de una BB.DD de MySql. Tomaremos la cadena de conexión así como el usuario y la contraseña.





- Backend.
  - Crearemos en Heroku nuestra aplicación dándola de alta y, utilizando la rama de backend de github, realizaremosl
- Frontend

## Programación.

### Guía de estilo

### Organización del código

El código del proyecto estará estructurado en cuatro paquetes básicos que nos darán una idea bastante clara de la jerarquía de los objetos que contienen. Son los siguientes:

- Entidades: representarán a la tupla de la BBDD y contendrán los constructores y métodos básicos de acceso a sus propiedades. En algunos casos también contendrá a las clases "clave" que nos servirán para identificar al objeto univocamente utilizando otro objeto contenido en el.
- Acceso a datos (DAO): estos objetos serán los responsables de interactuar con JPA y, utilizando los métodos necesarios, interactuar con la BBDD.
- Servicios: los servicios serán la herramienta o capa visible que utilizará el desarrollador para acceder a los datos e interactuar con ellos.
- Controladores: serán los que reciban las peticiones http y en función de las mismas, realicen la acción que se les solicite (GET, POST, PUT y DELETE). Serán la capa visible de cara al frontend.

Además de estos paquetes básicos, tendremos también otros como el de recursos donde almacenaremos la parametrización de la conexión a BBDD.

### Clases y métodos

Nuestra arquitectura de clases se base en cuatro elementos básicos sobre los que se ha construido todo el sistema. En el diagrama de clases podemos ver más gráficamente cómo se estructuran las diferentes clases y cuales son sus propiedades y métodos. Los objetos serían los siguientes:

- Entidad: será nuestra vía para poder interaccionar con la tabla correspondiente en BB.DD. Sus propiedades o atributos serán los campos de la tabla. Contaremos con las siguientes entidades:
  - Divisa: este objeto será el encargado de almacenar la información relacionada con las divisas (moneda de un país) con las que puede operar el sistema.
  - Divisahist: representa los distintos valores, en dolares, que ha tenido la divisa en cuestión a lo largo del tiempo.
  - DivisahistID: clase creada como propiedad de la anterior y que actua como clave de la misma. Se utiliza a nivel interno por la arquitectura.
  - Mercado: sería cada una de las bolsas donde se gestiona un valor.
  - Valor: es la accion propiamante dicha que cotiza en un mercado a un precio en dólares.
  - Valorhist: al igual que en la divisa, este objeto representa las distintas cotizaciones que ha tenido un determinado valor a lo largo del tiempo.
  - ValorhistID: clase creada como propiedad de la anterior y que actua como clave de la misma. Se utiliza a nivel interno por la arquitectura.
  - Valorhistmaxmin: objeto creado a nivel de arquitectura como necesidad de mapear una consulta muy concreta. Estos objetos son necesarios debido a que las consultas gestionadas por Springboot sólo devuelven tipos "objeto" que hay que devolver a través del controlador.
- Controladores: son la puerta de entrada a la API. Reciben las peticiones y las enrutan según su tipo (GET, PUT.,etc) y nombre. Su función principal es recibir la petición HTTP y llamar al servicio correspondientes.
  - DivisaController: gestiona las llamadas a la API en relación con las divisas.
  - DivisaHistController: gestiona las llamadas a la API en relación con el histórico de divisas.
  - MercadoController: gestiona las llamadas a la API en relación con los mercados.
  - ValorController: gestiona las llamadas a la API en relación con los valores.
  - ValorhistController: gestiona las llamadas a la API en relación con el histórico de valores.
- Servicios: son el nexo entre el objeto de acceso a datos (DAO) y el controlador. Su misión es indipendizar ambas capas para permitir un mantenimiento más simple. Se ha generado una interfaz para cada uno de ellos  en previsión de posibles cambios futuros, como así ha sido. Esto evita que haya que propagar el cambio realizado en la interfaz, debido a una funcionalidad específica del objeto, en todos los objetos afectados.
  - DivisaServiceImpl: gestiona las peticiones realizadas en relación a las divisas y las redirecciona a su objeto DAO correspondiente.
  - DivisahistServiceImpl: gestiona las peticiones realizadas en relación al histórico de divisas y las redirecciona a su objeto DAO correspondiente.
  - MercadoServiceImpl: gestiona las peticiones realizadas en relación a los mercados y las redirecciona a su objeto DAO correspondiente.
  - ValorServiceImpl: gestiona las peticiones realizadas en relación a los valores y las redirecciona a su objeto DAO correspondiente.
  - ValorhistServiceImpl: gestiona las peticiones realizadas en relación al histórico de valores y las redirecciona a su objeto DAO correspondiente.
- Objeto de acceso a datos (DAO): estos serán los responsables realmente de acceder a la BB.DD. utilizando las entidades creadas al efecto. Gestionará las llamadas al servicio mediante los correspondientes métodos y utiliza también interfaces para su construcción.



### Pruebas

#### Backend

Las pruebas en backend estarán gestionadas por Postman. Para cada una de los objetos/entidades se ha creado una batería de pruebas que engloba todo el ciclo de vida del mismo (CRUD). Además, se incluyen llamadas a métodos de carga masiva, aleatoria y parametrizable para cada una de dichas entidades y así facilitar la casuística de las pruebas. Estas baterías de pruebas, agrupadas por entidad en carpetas, son ejecutables masivamente utilizando la herramienta "Runner" de Postman. No se descarta realizar pruebas unitarias más específicas desde el IDE basándonos en Junit si se dispone en un futuro de tiempo para ello.

Para algunos puntos de entrada algo más específicos, como el de localización de valores que más suben o bajan entre dos fechas, se ha realizado una prueba más específica para detectar posibles errores en los valores devueltos.

![Pruebas con Postman](https://github.com/jesusdavidguti/ProyectoFinDeCurso/blob/img/Postman.PNG "Pruebas con Postman")

## Base de Datos.

Al haber optado por JPA a la hora de gestionar y desarrollar nuestra capa de persistencia, la BB.DD. aparece reflejada como entidades que se relacionan entre sí utilizando etiquetas. De esta forma hemos evitado la creación de un script de creación propiamente dicho (create, alters, etc.) pero, por otra parte, hemos tenido que ser más cuidadosos a la hora del diseño de las entidades ya que son estas un reflejo de las tablas, como veremos más adelante. En cualquier caso, se ha diseñado un diagrama E/R como elemento previo y de ayuda para el diseño de las clases y su etiquetado.

![Diagrama E/R](https://github.com/jesusdavidguti/ProyectoFinDeCurso/blob/img/Diagrama%20E-R.png "Diagrama E/R")



