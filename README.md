FarmaciaDS
=================

Sistema de farmacias para la Práctica 2 de la asignatura Desarrollo del Software.

##Protocolo para obtener la representación de una AS 

1. Definir los aspectos estructurales como una composición de componentes
2. Las estructuras globales de control
3. Los protocolos de comunicación
4. La sincronización y acceso a los datos
5. La asignación de la funcionalidad a los elementos del diseño
6. La composición de estos elementos
7. Su distribución física
8. Su escalabilidad y su desempeño  

##Descripción de una AS

1. Guiar en la construcción y mantenimiento del sistema 
2. Ayudar a planear los costes y evolución del sistema 
3. Servir como un medio para el análisis, evaluación o comparación de arquitecturas software 
4. Facilitar la comunicación entre las partes interesadas en las arquitecturas y los sistemas 
5. Documentar el conocimiento arquitectónico más allá del ámbito de los proyectos individuales 
6. Capturar idiomas arquitectónicos reutilizables (tales como estilos arquitectónicos y patrones) 

Se pueden utilizar lenguajes de propósito general como **UML como ADLs** así como para modelar procesos de negocio y similares. 

##Patrones que vamos a usar

###Aplicación Android y Web
- Singleton conector BD
- Fachada en conector de base de datos
- Patrón Delegación (conector BD a otra clase que hace las operaciones)
- Singleton Cesta¿?
- Inmutable Producto
- Builder para rellenar el catalogo de productos
- Delegación para cargar catalogo al seleccionar farmacia para visualizar posteriormente al pinchar en mostrar catalogo
- Proxy carga de puntos Google Maps¿?
- Patrón Bridge para implementación de los métodos de cliente y administrador (abstracta Usuario)

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