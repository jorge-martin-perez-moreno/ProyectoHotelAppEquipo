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

import es.accenture.entity.Huesped;
import es.accenture.service.IHuespedService;

/**
 * Clase Controlador. 
 * La clase que maneja las peticiones HTTP.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/huespedes")
public class HuespedControlador {
	
//	Declaramos atributo de tipo IHuespedService
//	Spring lo inyecta automaticamente
	@Autowired
	private IHuespedService huespedService;
	
	
	@GetMapping("")
	public String obtenerHuespedes(Model modelo) {
		
//		Llamamos al metodo Service para obtener todos los huespedes y lo guardamos en otra lista llamada 'huespedes'
		List<Huesped> huespedes = huespedService.obtenerHuespedes();
		
//		Guardamos la lista en el objeto 'modelo' de tipo Model para que la JSP pueda mostrarla.
		modelo.addAttribute("huespedes", huespedes);

//		Devuelve el nombre de la vista
		return "Huespedes";

	}
	
	@GetMapping("/detalle")
	public String detalleHuesped(@RequestParam(value="id") int id, Model modelo) {
		
//		Llamamos al metodo Service para obtener el detalle de un Huesped segun su id y lo guardamos en el objeto 'huesped'
		Huesped huesped = huespedService.obtenerHuesped(id);

//		Guardamos el objeto 'huesped' en el objeto 'modelo' de tipo Model para que la JSP pueda mostrarla.
		modelo.addAttribute("huesped", huesped);
		
//		Devuelve el nombre de la vista
		return "DetalleHuesped";

	}
	
	@GetMapping("/nuevo")
	public String nuevoHuesped(Model modelo) {
		
//		Guardamos el objeto vacio 'new Huesped()' en el objeto 'modelo' de tipo Model para que el formulario JSP pueda hacer el binding.
		modelo.addAttribute("huesped", new Huesped());
		
//		Devuelve el nombre de la vista
		return "FormularioHuesped";

	}
	
	@PostMapping("/nuevo")
	public String guardarHuesped(@ModelAttribute("huesped") Huesped huesped) {

//		Llamamos al Service para guardar el huesped
		huespedService.guardarHuesped(huesped);
		
//		Redirigir el navegador a otra URL
//		Si no usamos redirect: cada vez que el usuario actualiza la pagina, se guarda un nuevo huesped.
		return "redirect:/huespedes";

	}
	
	
	@GetMapping("/editar")
	public String editarHuesped(@RequestParam (value="id") int id, Model modelo) {
		
//		Buscar el huesped por su id y lo guardamos en el objeto 'huesped'
		Huesped huesped = huespedService.obtenerHuesped(id);
		
//		Guardamos el objeto 'huesped' en el objeto 'modelo' para que el JSP lo muestre.
		modelo.addAttribute("huesped", huesped);
		
//		Devuelve el nombre de la vista
		return "FormularioHuesped";

	}
	
	@PostMapping("/editar")
	public String guardarCambiosHuesped(@ModelAttribute("huesped") Huesped huesped) {

//		Llamamos al Service para actualiza el huesped
		huespedService.actualizarHuesped(huesped);
		
//		Redirigir el navegador a otra URL
//		Si no usamos redirect: cada vez que el usuario actualiza la pagina, se guarda un nuevo huesped.
		return "redirect:/huespedes";
		
	}
	
	
	@GetMapping("/eliminar")
	public String eliminarHuesped(@RequestParam (value="id") int id) {
		
//		Llamamos al Service para eliminar el huesped
		huespedService.eliminarHuesped(id);
		
//		Redirigir el navegador a otra URL
//		Si no usamos redirect: cada vez que el usuario actualiza la pagina, se guarda un nuevo huesped.
		return "redirect:/huespedes";

	}
	
}
