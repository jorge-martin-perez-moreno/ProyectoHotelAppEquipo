package es.accenture.controller;                        //esto entre los 2 A y B mucho cuidado

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

@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/reservas") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class ReservasController {
	
	private IReservasService reservaService;
	private IHuespedService huespedService;
	private IHabitacionService habitacionService;
	
	@Autowired // inyección en el constructor
	public ReservasController(IReservasService reservaService, IHuespedService huespedService, IHabitacionService habitacionService) {
	    this.reservaService = reservaService;
	    this.huespedService = huespedService;
	    this.habitacionService = habitacionService;
	}
	
	
    @GetMapping //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en reservas, se ejecuta el método listarReservas, va al service, de ahí al dao, lo saca de bbdd, lo guarda en la lista y devuelve la vista reservas con el listado
    public String obtenerReservas(Model model, HttpSession sesion) {

//    	Comprobamos que hay sesion activa.
//    	Si la sesion es null, es decir no hay sesion activa, mostramos el login para que el usuario se loguee.
        if (sesion.getAttribute("user") == null) {
//          
//        	Redirigimos la vista a login
        	return "redirect:/usuarios/login";
        }
    	
    	
        List<Reserva>reservas=reservaService.buscarReservas(); //llama al service de ahí al dao y a bbdd y lo guarda en la lista reservas

        model.addAttribute("reservas",reservas); //model es la caja que guarda la lista reservas en model con el nombre reservas

//      Añadimos el rol a la sesion.
        model.addAttribute("rol", sesion.getAttribute("rol") );
        
        
        return "Reserva"; //devuelve la vista jsp de reservas y muestra el listado
        
    }
	
    // método para ver el detalle de una reserva
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en id se ejecuta el método verDetalle, hace la caja en model con "reserva" y devuelve la vista detalleReserva
    public String detalleReserva(@RequestParam int id,Model model) { //se pone RequestParam para ocultar la url por contraseñas, así no usamos REST con PathVariable
    	
    	try {
    		
        Reserva reserva=reservaService.buscarReservaPorId(id); //obtiene una reserva por su Id a través del service

        model.addAttribute("reserva",reserva); //model es la caja que guarda el objeto reserva en model con el nombre reserva

        return "DetalleReserva"; //devuelve la jsp de detalle
        
    }catch(BuscarException e) {
    	
    	model.addAttribute("error", e.getMessage());

		model.addAttribute("reservas",reservaService.buscarReservas());

		return "Reserva";
		
    	}
    
    }

    // método para eliminar reserva de bbdd
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien pinche en eliminar se ejecuta el método eliminarReserva, ejecuta el método del service y lo borra de bbdd a través del dao, luego redirige a la jsp reservas y muestra el listado
    public String eliminarReserva(@RequestParam int idReserva,Model model) {
    	
    	try {
    		
    		reservaService.eliminarReserva(idReserva); // borra de bbdd a través del service
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage()); //crea la caja model donde se añade el error que recupera el mensaje de la excepción
    		
    		model.addAttribute("reservas",reservaService.buscarReservas()); //se vuelve a cargar la lista de reservas porque sino al salir el mensaje en rojo no aparece

    		return "Reserva"; //vuelve a la jsp reservas
    		
    	}
    		
        return "redirect:/reserva"; // Redirige a la jsp reservas y muestra el listado si todo sale bien
        
    }
    
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
		return "FormularioReserva";
    	
    }
    
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
			return "FormularioReserva";
			
		}
    
    }
    
    @GetMapping("/editar")
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
			return "FormularioReserva";
			
		}catch(BuscarException e) {
			
//			Pasamos el mensaje de error al modelo
			modelo.addAttribute("error", e.getMessage());
			
//			Pasamos al modelo la lista de reservas.
			modelo.addAttribute("reservas", reservaService.buscarReservas());
			
//			Devolvemos la vista de Reservas.
			return "Reserva";
		
		}
    }
    
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
		  return "FormularioReserva";
		}
    }

}
