package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
public class WebConfig implements WebMvcConfigurer{

	/**
	 * Metodo traduce el nombre logico de las vistas que devuelve el @Controller en la ruta fisica del JSP
	 * ViewResolver es una interfaz de SpringMVC.
	 * 
	 * @return devuelve el objeto 'vr' con la vista completa prefix + nombre que le pasa el controller + sufix
	 */
    @Bean
    public ViewResolver viewResolver() { //Convierte lo que le devuelve el controller en una jsp
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/");
        vr.setSuffix(".jsp");
        return vr;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
//    	Esto permite acceder a /resources/css/huespedes.css
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        
//      Esto permite acceder a /css/bootstrap.min.css
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }
}