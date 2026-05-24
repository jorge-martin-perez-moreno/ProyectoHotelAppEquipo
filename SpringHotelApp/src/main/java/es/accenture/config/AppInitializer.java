package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Clase que arranca toda la aplicación Spring MVC. 
 * Esta clase extiende de  AbstractAnnotationConfigDispatcherServletInitializer que es una clase abstracta de Spring.
 * Esta clase hace automaticamente tres cosas:  
 * 1. Registra el DispatcherServlet.
 * 2. Crea el contexto de Spring.
 * 3. Carga las clases de configuracion indicadas.
 * 
 * @author jorge martin perez moreno
 * @author javier roldan pomareta
 * @version 1.0
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Metodo define las clases de configuracion del contexto raiz.
	 * 
	 * @return devuelve un array de las clases AppConfig.class, HibernateConfig.class
	 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return new Class[] { AppConfig.class, HibernateConfig.class };
    }

    /**
     * Metodo define las clases de configuracion del contexto web.
     * 
     * @return devuelve un array de WebConfig.class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    /**
     * Metodo le dice a Tomcat que el DispatcherServlet de Spring gestione todas las 
     * peticiones HTTP que lleguen a la aplicación.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}