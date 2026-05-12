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
import es.accenture.service.IUsuarioService;

/**
 * Clase Controlador. 
 * La clase que recibe las peticiones HTTP del DispatcherServlet y las maneja.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/usuarios") // prefijo comun para todas las URLs
public class LoginControlador {
	
//	Declaramos atributo de tipo IUsuarioService.
//	Spring inyecta automaticamente 
	@Autowired
    private IUsuarioService usuarioService;
	
	/**
	 * Metodo Get que devuelve la vista del Login 
	 * para que el usuario pueda loguearse
	 * 
	 * @return vista del login
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
		
//		Devuelve la vista 'InicioSesion'
		return "InicioSesion";
		
		
	}
	
	/**
	 * Metodo Post que va a procesar la informacion 
	 * que envia el usuario para loguearse.
	 * 
	 * @return la vista Bienvenida
	 */
	@PostMapping("/login")
	public String procesarLogin(@RequestParam("usuario") String username,
            					@RequestParam("password") String password,
            					HttpSession session,
            					Model modelo) {
		
		if (username.isEmpty() || password.isEmpty()) {
	        modelo.addAttribute("error", "Usuario y contraseña son obligatorios");
	        return "InicioSesion";
	    }
		
//		Buscamos el usuario en la BBDD
        Usuario usuario = usuarioService.buscarPorCredenciales(username, password);

//      Validamos credenciales incorrectas
        if (usuario == null) {
            modelo.addAttribute("error", "Usuario o contraseña incorrectos");
            return "InicioSesion";
        }

//      Guardamos en sesión el username y el rol
        session.setAttribute("user", usuario.getUsername());
        session.setAttribute("rol", usuario.getRol().toString());
	    
//      Devuelve la vista 'Bienvenida'
        return "Bienvenida";
		
	}
	
	
	/**
	 * Metodo Get que cierra la sesion del usuario y redirige al login.
	 *
	 * @param session sesion HTTP actual
	 * @return redireccion al login
	 */
	@GetMapping("/logout")
	public String cerrarSesion(HttpSession session) {

	    // Invalida la sesion — elimina todos los datos guardados
	    session.invalidate();

	    // Redirige al login
	    return "redirect:/usuarios/login";
	}
	
	/**
	 * Metodo Get que devuelve la vista de Bienvenida
	 *
	 * @return vista Bienvenida
	 */
	@GetMapping("/bienvenida")
	public String mostrarBienvenida() {
		
//      Devuelve la vista 'Bienvenida'
	    return "Bienvenida";
	}
	
}
