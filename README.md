FarmaciaDS
=================

Sistema de farmacias para la Práctica 2 de la asignatura Desarrollo del Software.

##Arquitectura Software

###Protocolo para obtener la representación de una Arquitectura Software 

1. Definir los aspectos estructurales como una composición de componentes
2. Las estructuras globales de control
3. Los protocolos de comunicación
4. La sincronización y acceso a los datos
5. La asignación de la funcionalidad a los elementos del diseño
6. La composición de estos elementos
7. Su distribución física
8. Su escalabilidad y su desempeño  

###Descripción de una Arquitectura Software

1. Guiar en la construcción y mantenimiento del sistema 
2. Ayudar a planear los costes y evolución del sistema 
3. Servir como un medio para el análisis, evaluación o comparación de arquitecturas software 
4. Facilitar la comunicación entre las partes interesadas en las arquitecturas y los sistemas 
5. Documentar el conocimiento arquitectónico más allá del ámbito de los proyectos individuales 
6. Capturar idiomas arquitectónicos reutilizables (tales como estilos arquitectónicos y patrones) 

Se pueden utilizar lenguajes de propósito general como **UML como ADLs** así como para modelar procesos de negocio y similares. 

###Ejemplos de Estilos Arquitectónicos
Pipes and filters, Tipos de datos abstractos y OO, Repositorios, Capas, Basados en Eventos, Interpretes, etc.

###Estilo Arquitectónico elegido

Usaremos una **Arquitectura Centradas en los Datos** ya que vamos a tener una base de datos en el servidor a la que van a acceder los clientes web y sobre la que vamos a realizar sincronizaciones con la base de datos de SQLite en los dispositivos Android. Además las aplicaciones de los clientes Android acceden a los datos y ejecutan sus propias operaciones.

##Patrones que vamos a usar

###Aplicación Android y Web
- `Singleton` conector BD y Cesta
- `Fachada` en conector de base de datos
- Patrón `Delegación` (conector BD a otra clase que hace las operaciones)
- `Inmutable` Producto
- `Builder` para rellenar el catalogo de productos
- `Delegación` para cargar catalogo al seleccionar farmacia para visualizar posteriormente al pinchar en mostrar catalogo
- `Delegación` carga de puntos Google Maps¿?
- Patrón `Bridge` para implementación de los métodos de cliente y administrador (abstracta Usuario), también se podía haber usado `Factoria`
- `Observable Observador` ya que varios clientes tienen reservas de productos, con este patron podemos informar a todos cuando estén disponibles.
- `Visitante` realizar la misma operacion en cada farmacia y acumular el resultado.
- `Factoría` o `Factoría abstracta`para la creación de los Productos.

##Consultas al profesor

Tenemos que hacer los diagramas de casoss de uso y el de secuencia para la interacción entre la aplicacion y el cliente web.

##Clases

*Tanto la clase* ***Cliente*** *como la clase* ***Administrador*** *que serían especializaciones de Usuario sobrarían usando el patrón de diseño* `Bridge`

###Aspectos comunes a Clientes y Administradores
- Atributos
	- Ubicación
	- Email
	- Contraseña
	- Lista de productos actual
	- Nombre
	- Apellidos
	- Ult Conexión

###Clase Farmacia
- Atributos
	- CIF
	- Ubicación
	- Horario
	- Nombre
	- Personal
	- Telefono
	- Email
	- ListaServicios

##Referencias

- [Patrones Software](https://sourcemaking.com/design_patterns)

***Autores: Román Arranz Guerrero y Hugo Maldonado Cózar***