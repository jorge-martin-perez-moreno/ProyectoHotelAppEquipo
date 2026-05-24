<!-- IA: se solicitó a chat gpt un fondo morado con un dibujo-->

<h1 align="center" style="
background: linear-gradient(135deg, #7b2ff7, #f107a3);
padding: 18px;
border-radius: 12px;
color: white;
font-size: 48px;
box-shadow: 0 0 15px rgba(241, 7, 163, 0.6);
">
🏨 SpringHotelApp
</h1>

<!--IA: esta descripción se solicitó a chat gpt-->
<!--IA: se solicitó a chat gpt que me dijera como poner los títulos más grandes y luego le pedí un icono para cada uno relaccionado con la temática-->

## 📌 Descripción:
Aplicación web desarrollada con Spring MVC y Hibernate para la gestión de un hotel. Permite administrar habitaciones, huéspedes, reservas e incidencias, así como autenticación de usuarios con distintos roles (recepcionista y supervisor).

El sistema cubre operaciones CRUD completas y simula un entorno real de gestión hotelera con control de acceso según perfil.

<!--se preguntó a chat gpt como poner una raya que dividiera apartados del readme y luego la puse en todos-->

---

## ⚙️ Requisitos previos:

- Java JDK 8+
- Apache Tomcat 9+
- MySQL 8.0
- Maven
- IDE (Eclipse)

---

## 🚀 Instalación y despliegue:

1. Clonar el repositorio:

```
git clone https://github.com/PrimeraEdicionFlexible/ProyectoFinalEquipoL.git
```

2. Importar el proyecto en el IDE (Eclipse como Maven project)

3. Configurar la conexión a la base de datos en:  

   `src/main/resources/application.properties`
   

4. Ejecutar los scripts SQL en MySQL Workbench

5. Cambia usuario y contraseña según tu MySQL en application.properties según tu configuración local

6. Desplegar en Tomcat

7. Acceder en el navegador:

```
http://localhost:8080/SpringHotelApp
```

---

## 🗄️ Creación de la BBDD:

ORDEN CORRECTO PARA EJECUTAR LOS SCRIPTS:

1. hoteldb_CREATE DATABASE.sql  
2. hoteldb_usuarios.sql  
3. hoteldb_huespedes.sql  
4. hoteldb_habitaciones.sql  
5. hoteldb_reservas.sql  
6. hoteldb_incidencias.sql  
7. hoteldb_datos_usuarios.sql
8. hoterdb_datos_huespedes.sql
9. hoteldb_datos_habitaciones.sql
10. hoteldb_datos_incidencias.sql
11. hoteldb_datos_reservas.sql

<!--IA: se pidió a chat gpt que hiciera responsive la imagen luego en las otras copié y pegué cambiando la imagen en cada una-->

<p align="center">
  <img src="docs/images/diagrama ER BBDD.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>diagrama ER</em>
</p>

Los scripts SQL se encuentran en la carpeta /sql del proyecto.

<h2 align="center">Tablas de la BBDD</h2>

<p align="center">
  <img src="docs/images/tabla usuarios.png" width="500" />
  <br>
  <em>tabla usuarios</em>
</p>

<br>

<p align="center">
  <img src="docs/images/tabla habitaciones.png" width="500" />
  <br>
  <em>tabla habitaciones</em>
</p>

<br><br>

<p align="center">
  <img src="docs/images/tabla huespedes.png" width="500" />
  <br>
  <em>tabla huespedes</em>
</p>

<br><br>

<p align="center">
  <img src="docs/images/tabla incidencias.png" width="500" />
  <br>
  <em>tabla incidencias</em>
</p>

<br>

<p align="center">
  <img src="docs/images/tabla reservas.png" width="500" />
  <br>
  <em>tabla reservas</em>
</p>
<br>

<h2 align="center">Relaciones de las tablas</h2>

<p align="center">
  <img src="docs/images/fk de incidencias.png" width="500" />
  <br>
  <em>FK de incidencias</em>
</p>

<br>

<p align="center">
  <img src="docs/images/fk de reservas.png" width="500" />
  <br>
  <em>FK de reservas</em>
</p>

---

## 🛠️ Tecnologías utilizadas

<!-- IA: se preguntó a chat gpt como poner el texto diferente que se viera más y me dió varias opciones y elegí esta-->


### 🔧 Backend
- ☕ **Java 11**  
- 🌱 **Spring MVC**  
- 🗄️ **Hibernate (ORM)**  

### 💾 Base de datos
- 🐬 **MySQL 8**

### 🎨 Frontend
- 🖥️ **JSP + JSTL**

### 🚀 Servidor
- 🌐 **Apache Tomcat 9**

---

## 🧱 Estructura del proyecto:

Arquitectura en capas:

<!-- IA: se preguntó a chat gpt como poner el texto diferente para direrenciarlo y me dió varias opciones y elegí esta-->


```
es.accenture.config       → configuración
es.accenture.controller   → gestión de peticiones HTTP
es.accenture.dao          → es el acceso a BBDD, lo usa Services por medio de Controller
es.accenture.entity       → entidades de la bbdd
es.accenture.exceptions   → excepciones personalizadas
es.accenture.interfaces   → son las interfaces que definen los contratos de métodos que se deben cumplir
es.accenture.services     → PAQUETE SERVICES: es la lógica, se manda a DAO para las consultas a BBDD
```
<p align="center">
  <img src="docs/images/estructura de paquetes.png" style="width: 50%; max-width: 900px; height: auto;" />
  <br>
  <em>imagen de los paquetes del proyecto</em>
</p>

### PAQUETE CONFIG: configuración de Spring, Beans, etc

```
AppConfig         → configura Spring (escaneo de componentes y beans generales)
AppInitializer    → arranque de la app (sustituye al web.xml)
WebConfig         → configura Spring MVC (las vistas, las rutas, el ViewResolver)
HibernateConfig   → configura conexión a base de datos + ORM + transacciones
```

### PAQUETE CONTROLLER: recibe las peticiones HTTP, llama al service y da la respuesta

```
LoginController → gestiona el login, el logout y la sesión de usuario
HabitacionController → gestiona las peticiones del CRUD de habitaciones
IncidenciaController → gestiona las peticiones del CRUD de incidencias
HuespedController → gestiona las peticiones del CRUD de huéspedes
ReservaController → gestiona las peticiones del CRUD de reservas
```

### PAQUETE DAO: es el acceso a BBDD, lo usa Services para el acceso a datos

```
LoginDao → establece la lógica del acceso a los datos de la funcionalidad Login
HabitacionDao → establece la lógica del acceso a los datos de la funcionalidad Habitaciones
IncidenciaDao → establece la lógica del acceso a los datos de la funcionalidad Incidencias
HuespedDao → establece la lógica del acceso a los datos de la funcionalidad Huespedes
ReservaDao → establece la lógica del acceso a los datos de la funcionalidad Reservas
```

### PAQUETE ENTITIES: entidades de la bbdd

```
Habitacion → es representación de la tabla habitaciones
Huesped → es representación de la tabla huéspedes
Reserva → es representación de la tabla reservas
Incidencia → es representación de la tabla incidencias
Usuario → es representación de la tabla usuarios (login y roles)
```

### PAQUETE EXCEPTIONS: son las excepciones personalizadas

```
GuardarException → error al guardar
EliminarException → error al eliminar
ActualizarException → error al actualizar
BuscarException → error al buscar
CampoCredencialesIncorrectasException → error en credenciales
CampoCredencialesVacioException → error por credenciales vacías
HuespedCamposVaciosException → error por campos vacíos
HuespedDatosNoValidosException → error en datos de huesped
HuespedNoEncontradoException → error al buscar huesped
```

### PAQUETE INTERFACES: son las interfaces que definen los contratos de métodos que se deben cumplir

```
IHabitacionDao → interfaz que establece el contrato de los métodos de HabitacionDao
IHabitacionService → interfaz que establece el contrato de los métodos de HabitacionService
IHuespedDao → interfaz que establece el contrato de los métodos de HuespedDao
IHuespedService → interfaz que establece el contrato de los métodos de HuespedService
IIncidenciaDao → interfaz que establece el contrato de los métodos de IncidenciaDao
IIncidenciaService → interfaz que establece el contrato de los métodos de IncidenciaService
ILoginDao → interfaz que establece el contrato de los métodos de LoginDao
ILoginService → interfaz que establece el contrato de los métodos de LoginService
IReservaDao → interfaz que establece el contrato de los métodos de ReservaDao
IReservaService → interfaz que establece el contrato de los métodos de ReservaService
```

### PAQUETE SERVICES: es la lógica, se manda a DAO para las consultas a BBDD

```
HabitacionService → servicio que se encarga de la lógica de Service
HuespedService → servicio que se encarga de la lógica de Huesped
IncidenciaService → servicio que se encarga de la lógica de Incidencia
LoginService → servicio que se encarga de la lógica de Login
ReservaService → servicio que se encarga de la lógica de Reservas
```

### VISTAS: (van en WebInf, Vistas menos Login que va en WebApp)

```
DetalleHabitacion.jsp → vista de la pantalla de detalle de cada habitación
DetalleIncidencia.jsp → vista de la pantalla de detalle de cada incidencia
DetalleHuesped.jsp → vista de la pantalla de detalle de cada huésped
DetalleReserva.jsp → vista de la pantalla de detalle de cada reserva
Principal.jsp → vista de la pantalla principal tras iniciar sesión
Huespedes.jsp → vista de la pantalla del listado de huespedes
Habitaciones.jsp → vista de la pantalla del listado de habitaciones
Incidencias.jsp → vista de la pantalla del listado de incidencias
Reservas.jsp → vista de la pantalla del listado de reservas
FormularioHabitacion.jsp → vista de la pantalla de creación para nueva habitación
FormularioIncidencia.jsp → vista de la pantalla de creación para nueva incidencia
FormularioHuesped.jsp → vista de la pantalla de creación para nuevo huésped
FormularioReserva.jsp → vista de la pantalla de creación para nueva reserva

Esta va en WebApp porque es la de acceso:
Login.jsp → vista de la pantalla de inicio de sesión para introducir credenciales
```

(en SRC, Main, Resources)

```
application.properties → contiene configuración de conexión a base de datos
```

carpeta sql/ en la raiz con los scrips de BBDD y su orden de ejecución

Separación de responsabilidades para seguir buenas prácticas

Arquitectura en capas (Controller → Service → DAO → DB)


---

## 🌿 Estructura de ramas:

```
main        → versión final estable
develop     → integración
feature/*   → desarrollo por funcionalidades
```

Ejemplos:

```
feature/setup
feature/sql
feature/habitaciones
feature/incidencias
feature/login
feature/huespedes
feature/reservas
```

---

## 🔄 Flujo de trabajo:

Cada funcionalidad en rama feature/*  
Pull Request a develop  
Revisión por compañero  
Merge a develop  
Merge final a main  

<p align="center">
  <img src="docs/images/dibujo git.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>imagen simplificada del flujo de trabajo</em>
</p>

---

## 👥 Reparto de tareas:

A Javi → Habitaciones + Incidencias + Reservas
<br>
B Jorge → Huéspedes + Login + Reservas

---

## ⚠️ Conflictos de merge:

Se resolvieron conflictos principalmente en:

- Conflicto 1:
<br>
	Motivo:<br>
	Porque la rama Login tenía comentarios en Usuario, UsuarioService y la lógica y en develop ya existían<br>
	Porque la rama Login tenía las jsp de otras ramas sin desarrollar con el nombre en minúsculas 	anterior al rename<br>
	Porque habían cambios en css compartidos<br>
	Solución:<br>
	Se priorizan los cambios de develop que es la rama más actualizada y se elimina css antiguo 	de principal en desuso<br>
	Modo:<br>
	Se resuelve con el editor manual (notepad)<br>
<br>
<p align="center">
  <img src="docs/images/errores github.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>Captura de algunos de los errores que marcaba Github</em>
</p>
	<br>
- Conflicto 2:<br>
	Motivo:<br>
	Porque la rama feature/habitaciones necesitaba actualizarse con los cambios recientes de 	develop<br>
	Porque develop ya contenía modificaciones en vistas jsp, controladores y navegación 	realizadas por otras ramas<br>
	Porque ambas ramas modificaban archivos relacionados con habitaciones y vistas copartidas 	(las minúsculas de rename)<br>
	Solución:<br>
	Se deja todo lo de develop que es lo más actualizado<br>
	Se adaptan las vistas y todo lo afectado<br>
	Modo:<br>
	Se resuelve con el editor manual (notepad)<br>
		<br>
	<p align="center">
  <img src="docs/images/conflicto 2a.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>captura de pantalla del conflicto</em>
</p>
	<br>
		<p align="center">
  <img src="docs/images/conflicto 2b.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>captura de pantalla del conflicto</em>
</p>
- Conflicto 3:<br>
	Motivo:<br>
	Porque la rama huesped contenía renombrados de vistas jsp y eliminación de archivos que en 	develop seguían existiendo o habían sido modificados:<br>
	Porque ambas ramas modificaban archivos compartidos como Usuario.java y vistas relacionadas 	con navegacion y login<br>
	Porque algunas jsp seguían usando nombres antiguos antes del rename realizado en develop<br>
	Solución:<br>
	En este punto se consultó a Chat GPT al persistir y haber borrado varias veces los errores y 	volver y nos aconsejó hacer una rama limpia para sincronizarla desde cero mejor<br>
	Se creó la rama feature/huesped-clean pero en vez de sincronizar aunque hubiéramos borrado 	los archivos incorrectos dio los mismos problemas así que se editó poco a poco<br>
	Primero se creó la rama feature/huesped-clean tras intentar borrar los archivos y 	sincronizar la rama huesped con develop para intentar hacer un merge limpio<br>
	Finalmente tras repetirse los mismos problemas se priorizan los cambios de develop que son 	los actualizados<br>
	Se mantuvieron los renames de las jsp y se borraron localmente las antiguas<br>
	Modo:<br>
	Se resuelve con el editor manual (notepad)<br>
		<br>
	<p align="center">
  <img src="docs/images/conflicto 3c.png" style="max-width: 100%; height: auto;" />
  <br><br><br>
  <br>
  <em>captura de pantalla del conflicto</em>
</p>
	<br>
		<p align="center">
  <img src="docs/images/conflicto 3d.png" style="max-width: 100%; height: auto;" />
  <br><br><br>
  <br>
  <em>captura de pantalla del conflicto</em>
</p>
	<br>
		<p align="center">
  <img src="docs/images/conflicto 3e.png" style="max-width: 100%; height: auto;" />
  <br><br><br>
  <br>
  <em>captura de pantalla del conflicto</em>
</p>
	<br>
- Conflicto 4:<br>
	Motivo:<br>
	Porque la rama incidencias modificaba archivos de configuración (comentarios de los dos) y 	vistas jsp que también habían sido modificados en develop (las mayúsculas) y seguía 	reconociendo los archivos con minúsculas aunque las habíamos borrado<br>
	Porque HibernateConfig.java tenía cambios en configuración e imports<br>
	Solución:<br>
	En este punto se consultó a Chat GPT al persistir y haber borrado varias veces los errores y 	volver y nos aconsejó hacer una rama limpia para sincronizarla de cero mejor<br>
	Primero se creó la rama feature/incidencias-clean tras intentar borrar los archivos y 	sincronizar la rama huesped con develop para intentar hacer un merge limpio<br>
	Finalmente tras repetirse los mismos problemas se priorizan los cambios de develop que son 	los actualizados<br>
	Se mantuvieron los renames de las jsp y se borraron localmente las antiguas<br>
	Modo:<br>
	Se resuelve con el editor manual (notepad)<br>
		<br>
- Conflicto 5:<br>
	Motivo:<br>
	Porque después de resolver el conflicto principal de incidencias seguían existiendo archivos 	pendientes y cambios sin sincronizar correctamente (aunque los habíamos borrado!!!)<br>
	Porque algunos cambios de la rama incidencias-clean seguían entrando en conflicto con 	develop tras el merge inicial<br>
	Porque seguían las diferencias en vistas jsp reconociendo archivos que borrábamos una y otra 	vez (los de las minúsculas del rename)<br>
	Porque HibernateConfig.java tenía cambios en configuración e imports (eran comentarios 	distintos de cada uno)<br>
	Solución:<br>
	En este punto se consultó a Chat GPT tras múltiples intentos de borrar los errores y 	sincronizar las ramas y nos aconsejó hacer una rama limpia para sincronizarla de cero 	mejor<br>
	Finalmente tras repetirse los mismos problemas se priorizan los cambios de develop que son 	los actualizados<br>
	Se mantuvieron los renames de las jsp y se borraron localmente las antiguas<br>
	Modo:<br>
	Se resuelve con el editor manual (notepad)<br>
		<br>
- Conflicto 6:<br>
	Motivo:<br>
	Porque Reservas contenía cambios en vistas jsp (mayúsculas) que también habían sido 	modificados en develop<br>
	Porque varias ramas trabajaban simultáneamente sobre archivos compartidos relacionados con 	reservas y menús de navegación (nos duplicó Reservas, interfaces y otros)<br>
	Porque seguían existiendo archivos antiguos que habíamos eliminado mil veces -_- y 	diferencias derivadas de los rename realizados anteriormente<br>
	Solución:<br>
	Se crea la rama feature/reservas-clean para intentar hacer el merge limpio (consejo de Chat 	GPT) pero da los mismos problemas y se tiene que hacer manual poco a poco.<br>
	Se priorizan los cambios de develop porque es la más actualizadazbrZ
	Se meten manualmente solo los cambios compatibles de Reservas<br>
	Se editan algunos comentarios que también daban conflicto por tenerlos puestos los dos 	diferentes<br>
	Se mantuvieron los renames de las jsp y se borraron localmente las antiguas<br>
	Modo:<br>
	Se resuelve con el editor manual (notepad)<br>
		<br>
	
	Se editan posteriormente de forma manual todas las ramas de Github desde la plataforma para eliminar archivos duplicados y fuera de lugar.
	
---

## ✅ Funcionalidades:

- Login con roles (recepcionista / supervisor)
- CRUD habitaciones
- CRUD huéspedes
- CRUD incidencias
- CRUD reservas
- Control de acceso por rol

---

# 🔄 Flujo completo de la App

## Vista (JSP)
- El usuario interactúa (formulario, botón…)
- Hace una petición HTTP

## Controller
- Recibe la petición (@Controller)
- Llama al servicie

## Service
- Valida datos básicos
- Lógica de negocio
- Llama al DAO

## DAO
- Interfaz + implementación
- Acceso a base de datos (Hibernate/JPA)

## Entity
- Clases mapeadas a tablas (@Entity)
- Representan los datos

## Base de datos (MySQL)
- Devuelve los datos a DAO

## Flujo de datos
BD → DAO → Service → Controller → JSP (vista con los datos)
Flujo de vuelta inverso → mediante returns

---

## Ejemplo (Habitaciones)

Habitaciones.jsp → click “ver listado”  
HabitacionController → obtenerHabitaciones()  
Service valida datos  
HabitacionDAO → query Hibernate  
Controller mete datos al Model  
Habitaciones.jsp muestra listado

---

## 🔒 Validaciones de las reglas de negocio

### 🏨 Eliminación de habitaciones

**Incidencias**
- abierta → ❌ No
- en_curso → ❌ No
- cerrada → ❌ No

**Reservas**
- pendiente → ❌ No
- confirmada → ❌ No
- cancelada → ❌ No

**Disponibilidad**
- disponible → ✔️ Sí
- ocupada → ❌ No

---

### 👤 Eliminación de huéspedes

- pendiente → ❌ No
- confirmada → ❌ No
- cancelada → ✔️ Sí
- finalizada → ❌ No

---

### ⚠️ Eliminación de incidencias

- abierta → ❌ No
- en_proceso → ❌ No
- cerrada → ✔️ Sí

### 📅 Eliminación de reservas

- pendiente → ❌ No
- confirmada → ❌ No
- cancelada → ❌ No

---

<!--IA: se preguntó a chat gpt como dejar el texto del Mapeo como lo hice y que saliera igual en el Preview y me dijo que poniendo esto al principio "```text" y esto al final "```"-->

## 🔄 MAPEO ORM Y RELACIONAL POR ENTITIES:
```text

ENTITY Habitacion:

@Entity
@Table(name="habitaciones")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@OneToMany(mappedBy="habitacion",fetch=FetchType.LAZY) -- es la relación con Incidencia
private List<Incidencia>incidencias;
@OneToMany(mappedBy="habitacion",fetch=FetchType.LAZY) -- es la relación con Reserva
private List<Reserva>reservas;

idHabitacion           - @Column(name="id_habitacion",nullable=false)                 - INT
numeroHabitacion       - @Column(name="numero_habitacion",nullable=false,unique=true) - INT
tipo (de habitación)   - @Column(name="tipo_habitacion",nullable=false)               - ENUM (INDIVIDUAL,DOBLE,SUITE)
precioPorNoche         - @Column(name="precio_noche",nullable=false)                  - BigDecimal (se usa para dinero)
disponibilidad         - @Column(name="disponibilidad_habitacion",nullable=false)     - ENUM (DISPONIBLE,OCUPADA,LIMPIEZA,MANTENIMIENTO)
orientacionHabitacion  - @Column(name="orientacion_habitacion")                       - ENUM (INTERIOR,EXTERIOR)


ENTITY Incidencia:

@Entity
@Table(name="incidencias")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@ManyToOne(fetch=FetchType.LAZY) -- es la relación con Habitación
@JoinColumn(name="id_habitacion")
private Habitacion habitacion;

idIncidencia                          - @Column(name="id_incidencia",nullable=false)     - INT
estadoIncidencia                      - @Column(name="estado_incidencia",nullable=false) - ENUM (ABIERTA,EN_CURSO,CERRADA)
prioridadIncidencia                   - @Column(name="prioridad",nullable=false)         - ENUM (BAJA,MEDIA,ALTA)
descripcionIncidencia                 - @Column(name="descripcion_incidencia")           - String
@DateTimeFormat(pattern="yyyy-MM-dd") - anotación para fecha
fechaApertura                         - @Column(name="fecha_apertura",nullable=false)    - Date
@DateTimeFormat(pattern="yyyy-MM-dd") - anotación para fecha
fechaCierre                           - @Column(name="fecha_cierre")                     - Date


ENTITY Usuario: -- sin relaciones, que esta no tiene

@Entity
@Table(name="usuarios")
@IdUsuario
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Enumerated(EnumType.STRING)

idUsuario  - @Column(name="id_usuario",nullable=false) - INT
username   - @Column(name="username",nullable=false)   - String
password   - @Column(name="password",nullable=false)   - String
rol        - @Column(name="rol",nullable=false)        - ENUM (RECEPCIONISTA,SUPERVISOR)


ENTITY Huesped:

@Entity
@Table(name="huespedes")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@OneToMany(mappedBy="huesped",fetch=FetchType.LAZY) -- es la relación con Reserva
private List<Reserva>reservas;

idHuesped  - @Column(name="id_huesped",nullable=false) - INT
nombre     - @Column(name="nombre",nullable=false)     - VARCHAR(50)
apellidos  - @Column(name="apellidos",nullable=false)  - VARCHAR(50)
direccion  - @Column(name="direccion",nullable=false)  - VARCHAR(50)
telefono   - @Column(name="telefono",nullable=false)   - VARCHAR(20)
email      - @Column(name="email",nullable=false)      - VARCHAR(20)


ENTITY Reserva:

@Entity
@Table(name="reservas")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@ManyToOne(fetch=FetchType.LAZY) -- es la relación con Habitación
@JoinColumn(name="id_habitacion")
private Habitacion habitacion;

@ManyToOne(fetch=FetchType.LAZY) -- es la relación con Huesped
@JoinColumn(name="id_huesped")
private Huesped huesped;

idReserva                             - @Column(name="id_reserva",nullable=false)       - INT
@DateTimeFormat(pattern="yyyy-MM-dd") - anotación para fecha
fechaEntrada                          - @Column(name="fecha_entrada",nullable=false)    - Date
@DateTimeFormat(pattern="yyyy-MM-dd") - anotación para fecha
fechaSalida                           - @Column(name="fecha_salida",nullable=false)     - Date
tipoPension                           - @Column(name="tipo_pension")                    - ENUM (PENDIENTE,CONFIRMADA,CANCELADA)
estadoReserva                         - @Column(name="estado_reserva")                  - ENUM (ALOJAMIENTO,MEDIA,COMPLETA)
numeroHuespedes                       - @Column(name="numero_huespedes",nullable=false) - INT
observaciones                         - @Column(name="observaciones")                   - String
```

---

## 🔑 Credenciales de prueba:

```
recepcionista / recep ---> recep123
supervisor    / super ---> admin123
```


  <img src="docs/images/datos usuarios.png" style="max-width: 100%; height: auto;" />
  <br>
  <br>
  <em>script creacion credenciales usuarios</em>
</p>
	<br>
	  <img src="docs/images/datos huespedes.png" style="max-width: 100%; height: auto;" />
  <br>
  <br>
  <em>script creacion huespedes de prueba</em>
</p>
	<br>
	<img src="docs/images/datos habitaciones.png" style="max-width: 100%; height: auto;" />
  <br>
  <br>
  <em>script creacion habitaciones de prueba</em>
</p>
	<br>
	<img src="docs/images/datos reservas.png" style="max-width: 100%; height: auto;" />
  <br>
  <br>
  <em>script creacion reservas de prueba</em>
</p>
	<br>
	<img src="docs/images/datos incidencias.png" style="max-width: 100%; height: auto;" />
  <br>
  <br>
  <em>script creacion incidencias de prueba</em>
</p>
	<br>
	
	

---

## 🗂️ Documentación:

Ver carpeta /docs:

Proyecto (PDF)

- [proyecto (PDF)](docs/proyecto.pdf)

---

<!--IA: se ha usado chat gpt para cambiar el formato de la tabla que entregó el profesor y que se vea bien en el preview -->

## 🤖 Uso de inteligencia artificial Alumno A
### Herramientas utilizadas
**Alumno A — Javier Roldán Pomareta
- ChatGPT (versión: 5.5)

Usos:

- Confirmar validez de las clases de configuración rápidamente.
- Resolver la falta de una dependencia del POM.
- Confirmar resto de dependencias del POM.
- Buscar fallos de sintáxis
- Pedir explicaciones de funcionamientos
- Pedir explicaciones de buenas prácticas
- Pedir explicaciones de apuntes y temarios que no entendía
- Preguntas sobre HTML básico que no me acordaba
- Pedir explicación sobre errores de consola
- Pedir explicación y consejo sobre resolución de conflictos merge repetidos
- Implementar estilos en vistas y arreglos css varios

---

### Áreas en las que se ha usado

<table>
<tr>
<th>Componente A</th>
<th>¿Has usado IA?</th>
<th>¿Para qué?</th>
</tr>

<tr>
<td>POM</td>
<td>Sí</td>
<td>En el Eclipse de mis compañeros saltaba un error en el POM (no me acuerdo cuál era) pero a mi sí me funcionaba, faltaba la dependencia de plugging de Maven porque yo uso una versión diferente porque aunque tengo todas instaladas me gusta más, se copió y se pegó y se comprobó que funcionaba</br>
Tengo estas dependencias que he ido buscando en foros y copiando de otros proyectos para mi proyecto es un proyecto de Spring MVC que vamos a usar Hibernate, mysql (y no me acuerdo qué más le puse en el prompt) dime si me falta alguna y me dijo que no.
</td>
</tr>

<tr>
<td>Configuración de Spring (XML / Java Config)</td>
<td>Sí</td>
<td>He copiado estas clases de configuración de un proyecto para meterlas en el mío que es un proyecto Maven con configuración por clases e Hibernate, dime si están bien y si los comentarios que he puesto son correctos para explicar cada cosa o estoy poniendo alguna burrada</td>
</tr>

<tr>
<td>Excepciones personalizadas</td>
<td>No</td>
<td>No</td>
<td></td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Habitaciones</td>
<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Incidencias</td>
<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Entidad Reserva + DAO de Reservas (común)</td>
<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Reservas — listado / detalle / eliminación + JSPs</td>

<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Entidad Usuario / DAO Usuario / LoginController</td>
<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Huéspedes</td>
<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Reservas — alta / modificación + FormularioReserva.jsp</td>
<td>No</td>
<td>No</td>
</tr>

<tr>
<td>Documentación (Readme)</td>
<td>Sí</td>
<td>Dime cómo se ponen los títulos en grande en el readme</br>
Dime cómo se pone color morado de fondo en el título principal del readme y ponle un dibujito</br>
Dame el código para meter una imagen en un div en el readme para que quede responsive</br>
El profe me ha dado esta tabla para meter en el readme pero se ve muy mal en el preview, dámela en h
tml</br>
He hecho una lista del Mapeo ORM y al pegarlo en el README de mi proyecto se pierde el formato, dime cómo mantengo el formato exacto del texto y me dijo que así, poniendo esto al principio: "```text" y esto al final: "```"
<br>
Dame un icono para pegar en cada título que corresponda con los nombres de los apartados</br>
Dime como meter una línea al final de cada apartado del readme</br>
Dime una descripción buena para poner en este readme</br>
Estos son los css que ha puesto mi compañero en las vistas del proyecto, dime en qué parte tengo que pegarlos y si tengo que cambiar algo más para que queden con su estilo, los puedo pegar en cualquier parte?
<br>
</td>
</tr>
</table>

<tr>
<td>Documentación (Javadoc)</td>
<td>No</td>
</tr>
</table>

---

### Compromiso

Declaro que entiendo todo el código que entrego.

Puedo explicar qué hace cada fragmento marcado como IA, justificar por qué encaja en el proyecto y modificarlo durante la defensa si fuera necesario.

— Alumno A: Javier Roldán Pomareta

---

## 🤖 Uso de inteligencia artificial Alumno B
### Herramientas utilizadas
**Alumno B — Jorge Martín Pérez-Nieto
- ChatGPT (versión: 5.5)

Usos:

- Confirmar validez de las clases de configuración rápidamente.
- Resolver la falta de una dependencia del POM.
- Confirmar resto de dependencias del POM.
- Buscar fallos de sintáxis
- Pedir explicaciones de funcionamientos
- Pedir explicaciones de buenas prácticas
- Pedir explicaciones de apuntes y temarios que no entendía
- Preguntas sobre HTML básico que no me acordaba
- Pedir explicación sobre errores de consola
- Pedir explicación y consejo sobre resolución de conflictos merge repetidos
- Implementar estilos en vistas y arreglos css varios

---

### Áreas en las que se ha usado

<table>
<tr>
<th>Componente A</th>
<th>¿Has usado IA?</th>
<th>¿Para qué?</th>
</tr>

<tr>
<td>POM</td>
<td>Sí</td>
<td>En Eclipse me dió un error en el POM, porque Javi tiene otra version de Eclipse, faltaba la dependencia de plugging de Maven</br>
</td>
</tr>

<tr>
<td>Configuración de Spring (XML / Java Config)</td>
<td>Sí</td>
<td>Hemos copiado estas clases de configuración de un proyecto para meterlas en el nuestro que es un proyecto Maven con configuración por clases e Hibernate. Que nos revise las clases configuracion y estudiar que hace cada clase y cada metodo para entender bien el flujo de la aplicación</td>
</tr>

<tr>
<td>Excepciones personalizadas</td>
<td>NO</td>
<td></td>
<td></td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Habitaciones</td>
<td>NO</td>
<td></td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Incidencias</td>
<td>Sí/NO</td>
<td>...</td>
</tr>

<tr>
<td>Entidad Reserva + DAO de Reservas (común)</td>
<td>NO</td>
<td></td>
</tr>

<tr>
<td>Reservas — listado / detalle / eliminación + JSPs</td>

<td>NO</td>
<td></td>
</tr>

<tr>
<td>Entidad Usuario / DAO Usuario / LoginController</td>
<td>NO</td>
<td></td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Huéspedes</td>
<td>NO</td>
<td></td>
</tr>

<tr>
<td>Reservas — alta / modificación + FormularioReserva.jsp</td>
<td>NO</td>
<td></td>
</tr>

<tr>
<td>Documentación (Readme)</td>
<td>Sí</td>
<td>Dime cómo se ponen los títulos en grande en el readme</br>
Dime cómo se pone color morado de fondo en el título principal del readme y ponle un dibujito</br>
Dame el código para meter una imagen en un div en el readme para que quede responsive</br>
El profe me ha dado esta tabla para meter en el readme pero se ve muy mal en el preview, dámela en h
tml</br>
He hecho una lista del Mapeo ORM y al pegarlo en el README de mi proyecto se pierde el formato, dime cómo mantengo el formato exacto del texto y me dijo que así, poniendo esto al principio: "```text" y esto al final: "```"
<br>
Dame un icono para pegar en cada título que corresponda con los nombres de los apartados</br>
Dime como meter una línea al final de cada apartado del readme</br>
Dime una descripción buena para poner en este readme</br>
Estos son los css que ha puesto mi compañero en las vistas del proyecto, dime en qué parte tengo que pegarlos y si tengo que cambiar algo más para que queden con su estilo, los puedo pegar en cualquier parte?
<br>
</td>
</tr>
</table>

<tr>
<td>Documentación (Javadoc)</td>
<td>NO</td>
<td></td>
</tr>
</table>

---

### Compromiso

Declaro que entiendo todo el código que entrego.

Puedo explicar qué hace cada fragmento marcado como IA, justificar por qué encaja en el proyecto y modificarlo durante la defensa si fuera necesario.

— Alumno B: Jorge Martín Pérez-Nieto

---

## Capturas de pantalla:

<p align="center">
  <img src="docs/images/login.png" width="500" />
  <br>
  <em>Captura de pantalla de login</em>
</p>
<br>
<p align="center">
  <img src="docs/images/principal.png" width="500" />
  <br>
  <em>Captura de pantalla de principal</em>
</p>
<br>
<p align="center">
  <img src="docs/images/lista habitaciones.png" width="500" />
  <br>
  <em>Captura de pantalla de lista habitaciones</em>
</p>
<br>
<p align="center">
  <img src="docs/images/detalle habitación.png" width="500" />
  <br>
  <em>Captura de pantalla de detalle habitación</em>
</p>
<br>
<p align="center">
  <img src="docs/images/editar habitación.png" width="500" />
  <br>
  <em>Captura de pantalla de editar habitación</em>
</p>
<br>
<p align="center">
  <img src="docs/images/huespedes.png" width="500" />
  <br>
  <em>Captura de pantalla de huespedes</em>
</p>
<br>
<p align="center">
  <img src="docs/images/detalle huesped.png" width="500" />
  <br>
  <em>Captura de pantalla de detalle huesped</em>
</p>
<br>
<p align="center">
  <img src="docs/images/editar huesped.png" width="500" />
  <br>
  <em>Captura de pantalla de editar huesped</em>
</p>
<br>
<p align="center">
  <img src="docs/images/reservas.png" width="500" />
  <br>
  <em>Captura de pantalla de reservas</em>
</p>
<br>
<p align="center">
  <img src="docs/images/detalle reserva.png" width="500" />
  <br>
  <em>Captura de pantalla de detalle reserva</em>
</p>
<br>
<p align="center">
  <img src="docs/images/editar reserva.png" width="500" />
  <br>
  <em>Captura de pantalla de editar reserva</em>
</p>
<br>
<p align="center">
  <img src="docs/images/incidencias.png" width="500" />
  <br>
  <em>Captura de pantalla de incidencias</em>
</p>
<br>
<p align="center">
  <img src="docs/images/detalle incidencia.png" width="500" />
  <br>
  <em>Captura de pantalla de detalle incidencia</em>
</p>
<br>
<p align="center">
  <img src="docs/images/editar incidencia.png" width="500" />
  <br>
  <em>Captura de pantalla de editar incidencia</em>
</p>
<br>
