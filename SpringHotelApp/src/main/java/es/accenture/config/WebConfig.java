package es.accenture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Clase configuracion del contexto web.
 * 
 * @author jorge martin perez moreno
 * @author javier roldan pomareta
 * @version 1.0
 */
//Anotacion que dice a Spring que esta clase es de configuracion. Registra los @Bean.
@Configuration
//Activa el soporte completo de Spring. Es decir, procesa @Controller, @RequestMapping, @GetMapping, etc..
@EnableWebMvc 
//Anotacion que escanea los paquetes y subpaquetes y registra automaticamente los @Component.
@ComponentScan(basePackages = "es.accenture.controller")
public class WebConfig {

	/**
	 * Metodo traduce el nombre logico de las vistas que devuelve el @Controller en la ruta fisica del JSP
	 * ViewResolver es una interfaz de SpringMVC.
	 * 
	 * @return devuelve el objeto 'vr' con la vista completa prefix + nombre que le pasa el controller + sufix
	 */
    @Bean
    public ViewResolver viewResolver() { //Convierte lo que le devuelve el controller en una jsp
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/vistas/");
        vr.setSuffix(".jsp");
        return vr;
    }
}