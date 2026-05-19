package es.accenture.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.accenture.entity.Usuario;
import es.accenture.exceptions.CampoCredencialesIncorrectasException;
import es.accenture.exceptions.CampoCredencialesVacioException;
import es.accenture.interfaces.IUsuarioService;

/**
 * Clase Controlador web que recibe las peticiones HTTP recibidas del DispatcherServlet y
 * devuelve vistas.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 *
 */
//Anotacion que define esta clase como controlador web.
@Controller
//Anotacion que define que todas las URLs de esta clase empiezan por /usuarios
@RequestMapping("/usuarios")
public class LoginController {
	
//	Declaramos atributo de tipo IUsuarioService.
//	Spring inyecta automaticamente el bean IUsuarioService en este atributo.
	@Autowired
    private IUsuarioService usuarioService;
	
	/**
	 * Metodo Get que muestra el formulario del login. 
	 * Si el usuario ya esta logueado lo redirige a Bienvenida.
	 * 
	 * @param session, objeto sesion HTTP del usuario.
	 * @return InicioSesion, vista del formulario con el login.
	 */
	@GetMapping("/login")
	public String mostrarLogin(HttpSession session) {
		
//		Comprobamos si hay una sesion activa.
//		Comprobamos que hay un usuario logueado, "user" no sea null.
		if(session.getAttribute("user")!= null) {
//			Si el usuario no es null, esta logueado, le mostramos la pagina de Bienvenida.
			return "redirect:/usuarios/bienvenida";
			
		}
//		Si el usuario es null, es decir, no esta logueado, le mostramos la pagina de InicioSesion.
//		Devuelve la vista 'InicioSesion'
		return "InicioSesion";
			
	}
	
	/**
	 * Metodo Post que procesa la informacion del formulario de login
	 * Valida que los campos no esten vacios y busca al usuario en la bbdd.
	 * 
	 * @param username nombre de usuario introducido en el formulario
	 * @param password contrasena introducida en el formulario
	 * @param session sesion HTTP del usuario
	 * @param modelo objeto para pasar mensajes de error a la vista
	 * @return InicioSesion si hay error, redirige a /huespedes si las credenciales son correctas
	 */
	@PostMapping("/login")
	public String procesarLogin(@RequestParam("usuario") String username,
            					@RequestParam("password") String password,
            					HttpSession session,
            					Model modelo) {
		
//		Ejecuta el codigo, si lanza una excepcion, el flujo salta al catch.
		try {
			
//			UsuarioService valida los campos vacio y las credenciales.
//			Si hay algun error lanza excepciones.
//			Si no hay error lo almacena en 'usuario'
			Usuario usuario = usuarioService.buscarPorCredenciales(username, password);
			
//			Guardamos el username del objeto usuario autenticado en la sesion.
			session.setAttribute("user", usuario.getUsername());
//			Guardamos el rol del objeto usuario autenticado en la sesion.
			session.setAttribute("rol", usuario.getRol().toString());
		
//			Redirige a /huespedes
			return "redirect:/huespedes";

//		Capturamos cualquiera de las dos excepciones que pueda lanzar el Service.
		}catch(CampoCredencialesVacioException | CampoCredencialesIncorrectasException e) {
			
//			Añadimos el mensaje de error al modelo para que lo muestre la vista JSP.
			modelo.addAttribute("error", e.getMessage());
			
//			Devolvemos la vista 'InicioSesion', que es el formulario de login.
			return "InicioSesion";
		}
	}
	
	/**
	 * Metodo Get que cierra la sesion del usuario y redirige al login.
	 *
	 * @param session sesion HTTP actual
	 * @return redirige al login
	 */
	@GetMapping("/logout")
	public String cerrarSesion(HttpSession session) {

//	    Destruye la sesion, elimina todos los datos guardados.
	    session.invalidate();

//	    Redirige al formulario de login.
	    return "redirect:/usuarios/login";
	}
	
	/**
	 * Metodo Get devuelve la vista de Bienvenida.
	 * Si no hay sesion activa redirige al login.
	 *
	 * @param session sesion HTTP del usuario
	 * @return vista Bienvenida
	 */
	@GetMapping("/bienvenida")
	public String mostrarBienvenida(HttpSession session) {
//		Comprobamos que el usuario es null, y no hay sesion activa.
		if(session.getAttribute("user") == null) {
//			Redirige a la vista /usuarios/login para que el usuario vuelva a loguarse.
			return "redirect:/usuarios/login";
		}
		
//		Si el usuario no es null, es que hay una sesion activa.
//      Devuelve la vista 'Bienvenida'
	    return "Bienvenida";
	}
	
}
