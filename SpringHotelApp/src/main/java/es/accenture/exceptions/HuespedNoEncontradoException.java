package es.accenture.exceptions;

/**
 * Clase de excepción personalizada. Esta clase extiende de RuntimeException.
 * Esta clase lanza excepciones cuando el huesped no existe en la base de datos.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class HuespedNoEncontradoException extends RuntimeException{
	
//	Identificador único para la serialización de la clase.
	private static final long serialVersionUID = 1L;
	
//	Declarar atributos constantes de la clase.
//	────────────────── Errores de Huesped no encontrado ──────────────────
	public static final String HUESPED_NO_ENCONTRADO = "El huesped no existe en la base de datos";
	
//	Constructor con parametros.
	public HuespedNoEncontradoException(String mensaje) {
		
		super(mensaje);
		
	}
}

