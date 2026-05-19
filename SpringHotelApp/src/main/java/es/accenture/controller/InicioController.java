package es.accenture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador web de inicio de la aplicación.
 * Redirige la URL raíz a la página con el formulario de login.
 *
 * @author jorge martin perez moreno
 * @version 1.0
 */
//Anotacion que define esta clase como controlador web.
@Controller
public class InicioController {
	
	/**
     * Metodo que mapea la URL raíz ("/") de la aplicación y redirige a /usuarios/login.
     *
     * @return redirige a /usuarios/login, el formulario del login
     */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/usuarios/login";
    }
	

}
