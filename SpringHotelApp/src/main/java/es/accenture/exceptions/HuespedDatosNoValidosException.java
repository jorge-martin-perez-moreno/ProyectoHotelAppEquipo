package es.accenture.exceptions;

/**
 * Clase de excepción personalizada. Esta clase extiende de RuntimeException.
 * Esta clase lanza excepciones cuando el formato de los datos del huesped no son validos.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class HuespedDatosNoValidosException extends RuntimeException{
	
//	Identificador único para la serialización de la clase.
	private static final long serialVersionUID = 1L;
	
//	Declarar atributos constantes de la clase.
//	────────────────── Errores de datos invalidos ──────────────────
	public static final String TELEFONO_INVALIDO = "El formato del telefono no es correcto";
	public static final String EMAIL_INVALIDO = "El formato del email no es correcto";
	
//	Constructor con parametros.
	public HuespedDatosNoValidosException(String mensaje) {
		
		super(mensaje);
		
	}

}
