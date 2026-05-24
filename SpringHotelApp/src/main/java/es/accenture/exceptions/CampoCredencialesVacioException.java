package es.accenture.exceptions;

/**
 * Clase de excepción personalizada. Esta clase extiende de RuntimeException.
 * Esta clase lanza excepciones cuando las credenciales estan vacias.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class CampoCredencialesVacioException extends RuntimeException{
	
//	Identificador único para la serialización de la clase.
	private static final long serialVersionUID = 1L;
	
//	Declarar atributos constantes de la clase.
//	────────────────── Errores de credenciales vacias ──────────────────
	public static final String USUARIO_VACIO = "El nombre de usuario no puede estar vacio";
    public static final String PASSWORD_VACIO = "La contraseña no puede estar vacia";
    public static final String USUARIO_PASSWORD_VACIOS = "El usuario y la contraseña no pueden estar vacios";
	
//	Constructor con parametros.
	public CampoCredencialesVacioException(String mensaje) {
		
		super(mensaje);
		
	}
}

