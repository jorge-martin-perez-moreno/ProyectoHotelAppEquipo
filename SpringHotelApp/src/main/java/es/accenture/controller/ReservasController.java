package es.accenture.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.accenture.entity.Habitacion;
import es.accenture.entity.Huesped;
import es.accenture.entity.Reserva;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.interfaces.IHabitacionService;
import es.accenture.interfaces.IHuespedService;
import es.accenture.interfaces.IReservasService;
/**
 * Controlador encargado de gestionar las peticiones relacionadas con las reservas.
 * 
 * Esta clase permite hacer el crud de reservas, asi como gestionar las relaciones
 * entre huespedes y habitaciones.
 * 
 * @author jorge y javi
 * @version 1.0
 */
@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/reservas") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class ReservasController {
	
	/**
	 * Controlador encargado de gestionar las peticiones relacionadas con las reservas.
	 * 
	 * Esta clase permite hacer el crud de reservas, asi como gestionar las relaciones
	 * entre huespedes y habitaciones.
	 * 
	 * @author danih y javi
	 * @version 1.0
	 */
	private IReservasService reservaService;
	
	/*
	 * Atributo donde se almacena el servicio de habitaciones
	 * para obtener informacion de las habitaciones.
	 */
	private IHuespedService huespedService;
	
	/*
	 * Atributo donde se almacena el servicio de huespedes
	 * para obtener informacion de los huespedes que hay.
	 */
	private IHabitacionService habitacionService;
	
	/**
	 * Constructor por parametros en el que se realiza la inyeccion de dependencias.
	 * 
	 * @param reservaService     servicio encargado de la logica de reservas
	 * @param habitacionService  servicio encargado de la logica de habitaciones
	 * @param huespedService     servicio encargado de la logica de huespedes
	 */
	@Autowired // inyección en el constructor
	public ReservasController(IReservasService reservaService, IHuespedService huespedService, IHabitacionService habitacionService) {
	    this.reservaService = reservaService;
	    this.huespedService = huespedService;
	    this.habitacionService = habitacionService;
	}
	
	/**
	 * Metodo que recoge las peticiones de la vista principal de reservas
	 * obteniene el listado de reservas que hay.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista 'Reservas'
	 */
    @GetMapping //Anotación que dice cuál es la url de entrada
    public String obtenerReservas(Model model, HttpSession sesion) {

//    	Comprobamos que hay sesion activa.
//    	Si la sesion es null, es decir no hay sesion activa, mostramos el login para que el usuario se loguee.
        if (sesion.getAttribute("user") == null) {
//          
//        	Redirigimos la vista a login
        	return "redirect:/usuarios/login";
        }
    	
    	
        List<Reserva>reservas=reservaService.buscarReservas();

        model.addAttribute("reservas",reservas);

//      Añadimos el rol a la sesion.
        model.addAttribute("rol", sesion.getAttribute("rol") );
        
        
        return "WEB-INF/vistas/Reserva";
        
    }
	
    /**
     * Metodo que recoge las peticiones de la vista de detalle de una reserva.
     * 
     * @param idReserva identificador de la reserva que se desea consultar
     * @param model     objeto que permite almacenar atributos y los mensajes de error
     * @return String con la vista DetalleReserva o return a la lista
     */
    // método para ver el detalle de una reserva
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada
    public String detalleReserva(@RequestParam int id,Model model) {
    	
    	try {
    		
        Reserva reserva=reservaService.buscarReservaPorId(id);

        model.addAttribute("reserva",reserva);

        return "WEB-INF/vistas/DetalleReserva";
        
    }catch(BuscarException e) {
    	
    	model.addAttribute("error", e.getMessage());

		model.addAttribute("reservas",reservaService.buscarReservas());

		return "WEB-INF/vistas/Reserva";
		
    	}
    
    }

    /**
     * Metodo que recoge las peticiones de eliminacion de una reserva.
     * 
     * @param idReserva identificador de la reserva que se quiere eliminar
     * @param model     objeto que permite almacenar atributos y losmensajes de error
     * @return redireccion a la vista principal de reservas
     */
    // método para eliminar reserva de bbdd
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada
    public String eliminarReserva(@RequestParam int idReserva,Model model) {
    	
    	try {
    		
    		reservaService.eliminarReserva(idReserva);
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage());
    		
    		model.addAttribute("reservas",reservaService.buscarReservas());

    		return "Reserva"; //vuelve a la jsp reservas
    		
    	}
    		
        return "redirect:/reserva";
        
    }
    
    /**
	 * Metodo que recoge las peticiones de creacion de una nueva reserva, prepara
	 *  el formulario y carga habitaciones y huespedes.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista FormularioReserva
	 */
    @GetMapping("/nuevo")
    public String nuevoReserva(Model modelo, HttpSession sesion) {
    	
//    	Comprobamos que hay una sesion activa.
//		Comprobamos si la sesion es null o no.
		if (sesion.getAttribute("user") == null) {
//			Redirigimos la vista al formulario de login si no hay sesion activa, es decir si es null
	        return "redirect:/usuarios/login";
	    }
    	
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA redirige la vista de la lista de las reservas.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario de reserva.
		if (!"RECEPCIONISTA".equals(sesion.getAttribute("rol"))) {
//	        Redirigimos a la vista de la lista de huespedes
			return "redirect:/reserva";
	    }
    	
//		Pasamos al modelo un objeto 'Reserva' vacio para cuando haga el binding en el formulario.
		modelo.addAttribute("reserva", new Reserva());
		
//		Pasamos la lista de huespedes para el selector del formulario.
		modelo.addAttribute("huespedes", huespedService.obtenerHuespedes());
		
//		Pasamos la lista de habitaciones para el selector del formulario.
		modelo.addAttribute("habitaciones", habitacionService.buscarHabitaciones());
		
//		Devolvemos el formulario.
		return "WEB-INF/vistas/FormularioReserva";
    	
    }
    
    /**
	 * Metodo que recoge las peticiones de guardar reserva a partir de los datos
	 * de el formulario.
	 * 
	 * @param reserva objeto reserva con la informacion introducida
	 * @param model   objeto que permite almacenar atributos y los mensajes de error
	 * @return redireccion a la vista principal o return al formulario
	 */
    @PostMapping("/nuevo")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva, 
    							 @RequestParam("idHuesped") int idHuesped,
    							 @RequestParam("idHabitacion") int idHabitacion,
    							 Model modelo, HttpSession sesion) {
		
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA redirige la vista de la lista de las reservas.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario de reserva.
		if (!"RECEPCIONISTA".equals(sesion.getAttribute("rol"))) {
//	        Redirigimos a la vista de la lista de huespedes
			return "redirect:/reserva";
	    }
		
		try {
			
//			Buscamos los objetos completos y los asignamos a la reserva
	        Huesped huesped = huespedService.obtenerHuesped(idHuesped);
	        Habitacion habitacion = habitacionService.buscarHabitacionPorId(idHabitacion);
	        reserva.setHuesped(huesped);
	        reserva.setHabitacion(habitacion);
	        
//			Llamamos al metodo 'guardarReserva()' de Service para guardar la reserva.
			reservaService.guardarReserva(reserva);
			
//			Redirigimos a reservas
			return "redirect:/reservas";
			
		}catch(Exception e) {
			
//			Pasamos el mensaje de error al modelo
			modelo.addAttribute("error", e.getMessage());
			
//			Pasamos la reserva con los datos introducidos para que el formulario lo muestre.
			modelo.addAttribute("reserva", reserva);
			
//			Volvemos a pasar la lista de huespedes para el selector del formulario.
			modelo.addAttribute("huespedes", huespedService.obtenerHuespedes());
			
//			Volvemos a pasar la lista de habitaciones para el selector del formulario.
			modelo.addAttribute("habitaciones", habitacionService.buscarHabitaciones());
			
//			Devolvemos el Formulario
			return "WEB-INF/vistas/FormularioReserva";
			
		}
    
    }
    
    /**
	 * Metodo que recoge las peticiones de editar una reserva, carga previamente
	 * los datos quehay junto con las habitaciones y huespedes.
	 * 
	 * @param idReserva identificador de la reserva que se desea editar
	 * @param model     objeto que permite almacenar atributos y los mensajes de error
	 * @return String con la vista FormularioReserva
	 */
    @GetMapping("/editar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
    public String editarReserva(@RequestParam int idReserva, Model modelo, HttpSession sesion) {
    	
//    	Comprobamos que hay una sesion activa.
//		Comprobamos si la sesion es null o no.
		if (sesion.getAttribute("user") == null) {
//			Redirigimos la vista al formulario de login si no hay sesion activa, es decir si es null
	        return "redirect:/usuarios/login";
	    }
		
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA redirige la vista de la lista de las reservas.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario de reserva.
		if (!"RECEPCIONISTA".equals(sesion.getAttribute("rol"))) {
//	        Redirigimos a la vista de la lista de huespedes
			return "redirect:/reserva";
	    }
		
		try {
			
//			Buscamos la reserva por su id.
			Reserva reserva = reservaService.buscarReservaPorId(idReserva);
			
//			Pasamos la reserva con los datos introducidos para que el formulario lo muestre.
			modelo.addAttribute("reserva", reserva);
			
//			Volvemos a pasar la lista de huespedes para el selector del formulario.
			modelo.addAttribute("huespedes", huespedService.obtenerHuespedes());
			
//			Volvemos a pasar la lista de habitaciones para el selector del formulario.
			modelo.addAttribute("habitaciones", habitacionService.buscarHabitaciones());
			
//			Devolvemos el Formulario
			return "WEB-INF/vistas/FormularioReserva";
			
		}catch(BuscarException e) {
			
//			Pasamos el mensaje de error al modelo
			modelo.addAttribute("error", e.getMessage());
			
//			Pasamos al modelo la lista de reservas.
			modelo.addAttribute("reservas", reservaService.buscarReservas());
			
//			Devolvemos la vista de Reservas.
			return "WEB-INF/vistas/Reserva";
		
		}
    }
    
    /**
	 * Metodo que recoge las peticiones de creacion de una nueva reserva, prepara
	 *  el formulario y carga habitaciones y huespedes.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista FormularioReserva
	 */
    @PostMapping("/editar")
	public String actualizarReserva(@ModelAttribute("reserva") Reserva reserva,@RequestParam("idReserva") int id, Model modelo, HttpSession sesion) {
			
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA redirige la vista de la lista de las reservas.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario de reserva.
		if (!"RECEPCIONISTA".equals(sesion.getAttribute("rol"))) {
//	        Redirigimos a la vista de la lista de huespedes
			return "redirect:/reserva";
	    }
    	
		try {
			
			reserva.setIdReserva(id);
			
//			Llamamos al metodo 'actualizarReserva() de Service.
			reservaService.actualizarReserva(reserva);
			
//			Redirigimos la vista a 'reservas'
			return "redirect:/reservas";
						
		}catch(ActualizarException e) {
			
//	      Pasamos el mensaje de error al modelo
		  modelo.addAttribute("error", e.getMessage());
//		
//		  Pasamos la reserva con los datos introducidos para que el formulario lo muestre.
		  modelo.addAttribute("reserva", reserva);
			
//		  Volvemos a pasar la lista de huespedes para el selector del formulario.
		  modelo.addAttribute("huespedes", huespedService.obtenerHuespedes());
		
//		  Volvemos a pasar la lista de habitaciones para el selector del formulario.
		  modelo.addAttribute("habitaciones", habitacionService.buscarHabitaciones());
			
//		  Devolvemos el Formulario
		  return "WEB-INF/vistas/FormularioReserva";
		}
    }

}
