package es.accenture.exceptions;

/**
 * Clase de excepción personalizada. Esta clase extiende de RuntimeException.
 * Esta clase define mensajes de error como constantes.
 * Esta clase lanza excepciones cuando las credenciales son incorrectas.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class CampoCredencialesIncorrectasException extends RuntimeException{
	
	
//	Identificador único para la serialización de la clase
	private static final long serialVersionUID = 1L;
	
//	Declarar atributos constantes de la clase.
//	────────────────── Errores de credenciales incorrectas ──────────────────
    public static final String CREDENCIALES_INCORRECTAS = "El usuario o la contrasena son incorrectas";
	
//	Constructor con parametros.
	public CampoCredencialesIncorrectasException(String mensaje) {
		
		super(mensaje);
		
	}

}
