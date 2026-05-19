package es.accenture.config;  //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase de configuracion general del contenedor Spring.
 * 
 * @author jorge martin perez moreno
 * @author javier roldan pomareta
 * @version 1.0
 */
//Anotacion que dice a Spring que esta clase es de configuracion, define la configuración del contenedor Spring 
@Configuration 
//Anotacion que escanea los paquetes y subpaquetes y registra automaticamente los @Component.
@ComponentScan(basePackages = "es.accenture") 
//Anotacion que carga el application.properties
@PropertySource("classpath:application.properties") 
public class AppConfig {

}