package es.accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.accenture.entity.Habitacion;
import es.accenture.entity.Incidencia;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IHabitacionService;
import es.accenture.interfaces.IIncidenciaService;

/**
 * Controlador encargado de gestionar las peticiones relacionadas con las incidencias.
 * 
 * Esta clase permite hacer el crud.
 * 
 * @author jorge y javi
 * @version 1.0
 */
@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/incidencias") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class IncidenciaController {

	/*
	 * Atributo donde se almacena el servicio de incidencias, ahí va la
	 * logica de negocio de las incidencias.
	 */
	@Autowired 
	private IIncidenciaService incidenciaService;

	/*
	 * Atributo donde se almacena el servicio de habitaciones utilizado para
	 * obtener la informacion de las habitaciones relacionadas incidencias.
	 */
	@Autowired
	private IHabitacionService habitacionService;

	/**
	 * Metodo que recoge las peticiones de la vista principal de incidencias,
	 * obteniene el listado completo de incidencias.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista Incidencias
	 */
	// método para listar incidencias
	@GetMapping //Anotación que dice cuál es la url de entrada
	public String obtenerIncidencias(Model model) {

		List<Incidencia>incidencias=incidenciaService.buscarTodasIncidencias();

		model.addAttribute("incidencias",incidencias);

		return "WEB-INF/vistas/Incidencias";
	}

	/**
	 * Metodo que recoge las peticiones de la vista de detalle de una incidencia.
	 * 
	 * @param id    identificador de la incidencia que se desea consultar
	 * @param model objeto que permite almacenar atributos y los mensajes de error
	 * @return String con la vista DetalleIncidencia o return a la lista
	 */
	// método para ver el detalle de una incidencia
	@GetMapping("/detalle") //Anotación que dice cuál es la url de entrada
	public String detalleIncidencia(@RequestParam int id,Model model) {
		
		try {
			
		Incidencia incidencia=incidenciaService.buscarIncidenciaPorId(id);

		model.addAttribute("incidencia",incidencia);

		return "WEB-INF/vistas/DetalleIncidencia";
		
		}catch(BuscarException e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("incidencias",incidenciaService.buscarTodasIncidencias());
			
			return "WEB-INF/vistas/Incidencias";
			
		}

	}
	
	/**
	 * Metodo que recoge las peticiones dirigidas de creacion de una incidencia
	 * preparando formulario y cargan las habitaciones disponibles.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista FormularioIncidencia
	 */
	// método para mostrar el formulario de alta
	@GetMapping("/nueva") //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en nueva se ejecuta el método mostrarFormularioAlta, hace la caja en model con "habitaciones" y con "incidencias" que es la lista donde se guardan y lo muestra devolviendo la vista
	public String nuevaIncidencia(Model model) {

		model.addAttribute("incidencia",new Incidencia()); //model es la caja que guarda un objeto creado nuevo de tipo incidencia en model con el nombre incidencia

		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); //obtiene todas las habitaciones para mostrarlas en el select

		model.addAttribute("habitaciones",habitaciones); //guarda la lista habitaciones en model para usarla en el formulario

		return "WEB-INF/vistas/FormularioIncidencia"; //devuelve el jsp del formularioAltaIncidencia
	}

	/**
	 * Metodo que recoge las peticiones de guardar o actualizar incidencia
	 * a partir de los datos recibidos desde formulario.
	 * 
	 * @param incidencia objeto incidencia con los datos introducidos o modificados
	 * @param model      objeto que permite almacenar atributos y los mensajes de error
	 * @return redireccion a la vista principal o return al formulario
	 */
	// método para guardar incidencia nueva
	@PostMapping("/guardar") //Anotación que dice cuál es la url de entrada
	public String guardarIncidencia(@ModelAttribute Incidencia incidencia,Model model) {
	
	try {
		if(incidencia.getIdIncidencia()!=0){
					
				incidenciaService.actualizarIncidencia(incidencia);
			
		}else {
			
			incidenciaService.guardarIncidencia(incidencia);
			
		}

		
	}catch(GuardarException|ActualizarException e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("incidencia",incidencia);
			
			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
			
			return "WEB-INF/vistas/FormularioIncidencia";
			
		}
		
		return "redirect:/incidencias"; // Redirige a la jsp incidencias y muestra el listado
		
	}

	/**
	 * Metodo que recoge las peticiones de edicion de una incidencia, carga previamente
	 * los datos existentes y las habitaciones disponibles.
	 * 
	 * @param id    identificador de la incidencia que se quiere editar
	 * @param model objeto que permite almacenar atributos y los mensajes de error
	 * @return String con la vista FormularioIncidencia
	 */
	// método para mostrar el formulario para editar
	@GetMapping("/editar") //Anotación que dice cuál es la url de entrada
	public String editarIncidencia(@RequestParam int id,Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como incidencias/5 o incidencias/4 si se cambia el valor de id por ejemplo REST

		try {
			
		Incidencia incidencia=incidenciaService.buscarIncidenciaPorId(id); //obtiene una incidencia por su id a través del service,dao,bbdd

		model.addAttribute("incidencia",incidencia); //model es la caja que guarda el objeto incidencia en model con el nombre incidencia, luego desde la jsp se recoge con ${expression language}

		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); //obtiene todas las habitaciones y las guarda en la lista habitaciones

		model.addAttribute("habitaciones",habitaciones); //guarda la lista habitaciones en model para usarla en el formulario, luego desde jsp se cogen con ${expresion languanges}

		return "WEB-INF/vistas/FormularioIncidencia"; //devuelve la jsp formularioEditarIncidencia
		
		}catch(BuscarException e) {
			
			model.addAttribute("error",e.getMessage());

			model.addAttribute("incidencias",incidenciaService.buscarTodasIncidencias());

			return "WEB-INF/vistas/Incidencias";
			
		}
		
	}

	/**
	 * Metodo que recoge las peticiones de eliminacion de una incidencia.
	 * 
	 * @param id    identificador de la incidencia que se desea eliminar
	 * @param model objeto que permite almacenar atributos y los mensajes de error
	 * @return redireccion a la vista principal de incidencias
	 */
	// método para eliminar incidencia de bbdd
	@GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada
	public String eliminarIncidencia(@RequestParam int id,Model model){

		try {

			incidenciaService.eliminarIncidencia(id);

		}catch(EliminarException |BuscarException e){

			model.addAttribute("error",e.getMessage());

			model.addAttribute("incidencias",incidenciaService.buscarTodasIncidencias());

			return "WEB-INF/vistas/Incidencias";
		}

		return "redirect:/incidencias"; // Redirige a la jsp incidencias y muestra el listado si todo sale bien
	}

	/**
	 * Metodo que recoge las peticiones de obtencion de incidencias relacionadas
	 * con una habitacion concreta.
	 * 
	 * @param idHabitacion identificador de la habitacion cuyas incidencias se quiere consultar
	 * @param model        objeto que permite almacenar atributos y losmensajes de error
	 * @return String con la vista Incidencias o return a la vista de habitaciones
	 */
	// método para obtener incidencias por el id de una habitación
	@GetMapping("/habitacion") //Anotación que dice cuál es la url de entrada que coge el método
	public String obtenerIncidenciasPorHabitacion(@RequestParam int idHabitacion, Model model) {
		
		try {
			
		List<Incidencia>incidencias=incidenciaService.buscarIncidenciasPorIdHabitacion(idHabitacion);

		Habitacion habitacion=habitacionService.buscarHabitacionPorId(idHabitacion);

		model.addAttribute("incidencias",incidencias);

		model.addAttribute("habitacion",habitacion);

		return "WEB-INF/vistas/Incidencias";
		
		}catch (BuscarException e) {
			
			model.addAttribute("error", e.getMessage());

			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
		
			return "WEB-INF/vistas/Habitaciones";
			
		}
		
	}
	
}