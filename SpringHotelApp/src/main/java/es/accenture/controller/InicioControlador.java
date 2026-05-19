package es.accenture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de inicio de la aplicación.
 * Redirige la raíz a la página Principal.
 *
 * @author jorge martin perez moreno
 * @version 1.0
 */
@Controller
public class InicioControlador {
	
	/**
     * Mapea la raíz de la aplicación y redirige a Principal.jsp
     *
     * @return redirección a Principal.jsp
     */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/Principal.jsp";
    }
	

}
