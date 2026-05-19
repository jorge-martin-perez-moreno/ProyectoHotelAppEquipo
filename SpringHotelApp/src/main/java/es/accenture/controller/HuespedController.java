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
import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedCamposVaciosException;
import es.accenture.exceptions.HuespedDatosNoValidosException;
import es.accenture.exceptions.HuespedNoEncontradoException;
import es.accenture.interfaces.IHuespedService;

/**
 * Clase Controlador. 
 * La clase que maneja las peticiones HTTP.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 *
 */
//Anotacion que define esta clase como controlador web.
@Controller
//Anotacion que define que todas las URLs de esta clase empiezan por /huespedes
@RequestMapping("/huespedes")
public class HuespedController {
	
//	Declaramos atributo de tipo IHuespedService 'huespedService'.
//	Spring inyecta automaticamente el bean HuespedService (@Service).
	@Autowired
	private IHuespedService huespedService;
	
	/**
	 * Metodo Get que muestra todos los huespedes y los muestra en la vista.
	 * Si no hay sesion activa redirige al login.
	 * 
	 * @param modelo objeto de tipo Model para pasar datos a la vista.
	 * @param session objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @return "Huespedes" vista con la lista de los huespedes.
	 */
	@GetMapping("")
	public String obtenerHuespedes(Model modelo, HttpSession session) {
		
//		Comprobamos que el nombre de usuario sea null, lo redirigimos a login.
		if (session.getAttribute("user") == null) {
	        return "redirect:/usuarios/login";
	    }
		
//		Llamamos al metodo 'obtenerHuespedes() de Service para obtener todos los huespedes y lo guardamos en otra lista llamada 'huespedes'
		List<Huesped> huespedes = huespedService.obtenerHuespedes();
		
//		Guardamos la lista en el objeto 'modelo' de tipo Model para que la JSP pueda mostrarla.
		modelo.addAttribute("huespedes", huespedes);
		modelo.addAttribute("rol", session.getAttribute("rol"));
		
//		Devuelve el nombre de la vista
		return "Huespedes";

	}
	
	/**
	 * Metodo Get que muestra un huesped en concreto a partir de su id.
	 * 
	 * @param id, identificar del huesped que queremos encontrar.
	 * @param modelo objeto de tipo Model para pasar datos a la vista.
	 * @param sesion objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @return "DetalleHuespes" vista con el detalle de un huesped en concreto.
	 */
	@GetMapping("/detalle")
	public String detalleHuesped(@RequestParam(value="id") int id, Model modelo, HttpSession session) {
		
//		Comprobamos que el nombre de usuario sea null, lo redirigimos a login.
		if (session.getAttribute("user") == null) {
		    return "redirect:/usuarios/login";
		}
		
		try {
//			Llamamos al metodo 'obtenerHuesped' de Service para obtener el detalle de un Huesped segun su id y lo guardamos en la variable 'huesped'
			Huesped huesped = huespedService.obtenerHuesped(id);

//			Guardamos el objeto 'huesped' en el objeto 'modelo' de tipo Model para que la JSP pueda mostrarla.
			modelo.addAttribute("huesped", huesped);
			
//			Devuelve el nombre de la vista
			return "DetalleHuesped";
			
//		Capturamos cualquiera de las excepciones que pueda lanzar el Service.	
		}catch(HuespedNoEncontradoException e){
			
//	      	Pasamos al modelo el mensaje de error para que lo muestre la vista.
	        modelo.addAttribute("error", e.getMessage());
//	        Pasamos el rol al modelo
	        modelo.addAttribute("rol", session.getAttribute("rol"));

//	      	Llamamos al metodo 'obtenerHuespedes()' del Service y lo almacenamos en la lista 'huespedes'
	        List<Huesped> huespedes = huespedService.obtenerHuespedes();
	        
//	        Pasamos al modelo la lista de huespedes para que las muestre en la vista.
	        modelo.addAttribute("huespedes", huespedes);
	        
//	        Devolvemos la vista de la lista de los huespedes 
	        return "Huespedes";
			
		}
		
	}
	
	/**
	 * Metodo Get que muestra el formulario para crear un nuevo huesped.
	 * 
	 * @param modelo objeto de tipo Model para pasar datos a la vista.
	 * @param session objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @return "FormularioHuesped", vista con el formulario para crear nuevo huesped.
	 */
	@GetMapping("/nuevo")
	public String nuevoHuesped(Model modelo, HttpSession session) {
		
//		Comprobamos si la sesion es null o no.
		if (session.getAttribute("user") == null) {
//			Redirigimos la vista al formulario de login y no hay sesion activa, es decir si es null
	        return "redirect:/usuarios/login";
	    }
		
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA devuelve la vista de la lista de los huespedes.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario con un objeto vacio de tipo 'Huesped'
		if (!"RECEPCIONISTA".equals(session.getAttribute("rol"))) {
//	        Redirigimos a la vista de la lista de huespedes
			return "redirect:/huespedes";
	    }
		
//		Guardamos el objeto vacio 'new Huesped()' en el objeto 'modelo' de tipo Model para que el formulario JSP pueda hacer el binding.
		modelo.addAttribute("huesped", new Huesped());
		
//		Devuelve el nombre del formulario del huesped para crear un nuevo huesped.
		return "FormularioHuesped";

	}
	
	/**
	 * Metodo Post procesa los datos del formulario de huesped.
	 * @ModelAttribute recoge todos los campos del formulario.
	 * huesped recibe todo los datos del formulario ya asignados.
	 * 
	 * @param huesped, objeto de tipo 'Huesped'
	 * @param session, objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @param modelo, objeto de tipo Model para pasar datos a la vista.
	 * @return redirige a la lista de huespedes.
	 */
	@PostMapping("/nuevo")
	public String guardarHuesped(@ModelAttribute("huesped") Huesped huesped, HttpSession session, Model modelo) {
		
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA devuelve la vista de la lista de los huespedes.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario con un objeto vacio de tipo 'Huesped'
		if (!"RECEPCIONISTA".equals(session.getAttribute("rol"))) {
	        return "redirect:/huespedes";
	    }
		
		try {
//			Llamamos al metodo 'guardarHuesped' de Service y le pasamos el huesped con todos los datos.
			huespedService.guardarHuesped(huesped);
			
//			Redirigimos el navegador a la vista de la lista de huespedes.
			return "redirect:/huespedes";
		
//		Capturamos cualquiera de las excepciones que pueda lanzar el Service.	
		}catch(HuespedCamposVaciosException | HuespedDatosNoValidosException e) {
			
//			Le pasamos al modelo el mensaje de error para que lo muestre la vista.
			modelo.addAttribute("error", e.getMessage());
//			Le pasamos al modelo el objeto 'huesped' para que los campos del formulario no pierda los datos.
	        modelo.addAttribute("huesped", huesped);
	        
//	        Devolvemos la vista del formulario del huesped.
	        return "FormularioHuesped";
		}

	}
	
	/**
	 * Metodo Get que muestra el formulario del huesped para editar un obejto 'huesped' 
	 * que buscamos en la BBDD por su id.
	 * 
	 * @param id, identificar del huesped que queremos encontrar.
	 * @param modelo, objeto de tipo Model para pasar datos a la vista.
	 * @param session, objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @return "FormularioHuesped"
	 */
	@GetMapping("/editar")
	public String editarHuesped(@RequestParam (value="id") int id, Model modelo, HttpSession session) {
		
//		Comprobamos si la sesion es null o no.
		if (session.getAttribute("user") == null) {
//			Redirigimos la vista al formulario de login y no hay sesion activa, es decir si es null
	        return "redirect:/usuarios/login";
	    }
		
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA devuelve la vista de la lista de los huespedes.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario con un objeto vacio de tipo 'Huesped'
		if (!"RECEPCIONISTA".equals(session.getAttribute("rol"))) {
	        return "redirect:/huespedes";
	    }
		
		try {
//			Buscamos el objeto huesped por su id y lo guardamos en la variable 'huesped'
			Huesped huesped = huespedService.obtenerHuesped(id);
			
//			Pasamos el objeto 'huesped' con sus datos para que el JSP lo muestre.
			modelo.addAttribute("huesped", huesped);
			
//			Devuelve el formulario de huesped
			return "FormularioHuesped";
			
//		Capturamos cualquiera de las excepciones que pueda lanzar el Service.
		}catch(HuespedNoEncontradoException  e) {
			
//	      	Pasamos al modelo el mensaje de error para que lo muestre la vista.
	        modelo.addAttribute("error", e.getMessage());
//	        Pasamos el rol al modelo
	        modelo.addAttribute("rol", session.getAttribute("rol"));

//	      	Llamamos al metodo 'obtenerHuespedes()' del Service y lo almacenamos en la lista 'huespedes'
	        List<Huesped> huespedes = huespedService.obtenerHuespedes();
	        
//	        Pasamos al modelo la lista de huespedes para que las muestre en la vista.
	        modelo.addAttribute("huespedes", huespedes);
	        
//	        Devolvemos la vista de la lista de los huespedes 
	        return "Huespedes";
			
		}

	}
	
	/**
	 * Metodo Post que procesa los datos que le enviamos a traves del formulario de edicion del huesped
	 * y actualiza el objeto 'huesped' que le pasamos en el metodo.
	 * 
	 * @param huesped, objeto de tipo 'Huesped'
	 * @param session, objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @param modelo, objeto de tipo Model para pasar datos a la vista.
	 * @return redirige la lista de los huespedes.
	 */
	@PostMapping("/editar")
	public String guardarCambiosHuesped(@ModelAttribute("huesped") Huesped huesped, HttpSession session, Model modelo) {
	    
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA devuelve la vista de la lista de los huespedes.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario con un objeto vacio de tipo 'Huesped'
		if (!"RECEPCIONISTA".equals(session.getAttribute("rol"))) {
	        
//		    Redirige a la lista de huespedes. 
			return "redirect:/huespedes";
	    }
		
		try {
//			Llamamos al metodo 'actualizarHuesped()' de Service y actualiza el registro en la BBDD.
		    huespedService.actualizarHuesped(huesped);
		    
//		    Redirige a la lista de huespedes. 
			return "redirect:/huespedes";
		
//		Capturamos cualquiera de las excepciones que pueda lanzar el Service.	
		}catch(HuespedCamposVaciosException | HuespedDatosNoValidosException | HuespedNoEncontradoException e) {
			
//			Le pasamos al modelo el mensaje de error para que lo muestre la vista.
			modelo.addAttribute("error", e.getMessage());
//			Le pasamos al modelo el objeto 'huesped' para que los campos del formulario no pierda los datos.
	        modelo.addAttribute("huesped", huesped);
	        
//	        Devolvemos la vista del formulario del huesped.
	        return "FormularioHuesped";
		}

	}
	
	/**
	 * Metodo Get que muestra la lista de los huespedes una vez eliminado el huesped en concreto.
	 * 
	 * @param id, identificar del huesped que queremos encontrar.
	 * @param session, objeto de tipo HttpSesion con la sesion activa del usuario.
	 * @param modelo, objeto de tipo Model para pasar datos a la vista.
	 * @return "redirect:/huespedes", redirigimos a la vista de la lista de huespedes
	 */
	@GetMapping("/eliminar")
	public String eliminarHuesped(@RequestParam (value="id") int id, HttpSession session, Model modelo) {
		
//		Comprobamos que el rol es 'RECEPCIONISTA'.
//		Si rol NO es RECEPCIONISTA devuelve la vista de la lista de los huespedes.
//		Si rol SI es RECEPCIONISTA le mostramos la vista del formulario con un objeto vacio de tipo 'Huesped'
		if (!"RECEPCIONISTA".equals(session.getAttribute("rol"))) {
	        return "redirect:/huespedes";
	    }
		
		try {
//			Llamamos al metodo 'eliminarHuesped()' de Service para eliminar el huesped
			huespedService.eliminarHuesped(id);
			
//			Redirigir el navegador a la lista de huespedes.
//			Si no usamos redirect: cada vez que el usuario actualiza la pagina, se guarda un nuevo huesped.
			return "redirect:/huespedes";
	
//		Capturamos cualquiera de las excepciones que pueda lanzar el Service.
		}catch(HuespedNoEncontradoException e) {
			
//	      	Pasamos al modelo el mensaje de error para que lo muestre la vista.
	        modelo.addAttribute("error", e.getMessage());
//	        Pasamos el rol al modelo
	        modelo.addAttribute("rol", session.getAttribute("rol"));

//	      	Llamamos al metodo 'obtenerHuespedes()' del Service y lo almacenamos en la lista 'huespedes'
	        List<Huesped> huespedes = huespedService.obtenerHuespedes();
	        
//	        Pasamos al modelo la lista de huespedes para que las muestre en la vista.
	        modelo.addAttribute("huespedes", huespedes);
	        
//	        Devolvemos la vista de la lista de los huespedes 
	        return "Huespedes";
			
		}

	}

}
