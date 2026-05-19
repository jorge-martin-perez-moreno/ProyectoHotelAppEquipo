package es.accenture.exceptions;

/**
 * Clase de excepción personalizada. Esta clase extiende de RuntimeException.
 * Esta clase lanza excepciones cuando el campo de algun dato del huesped esta vacio.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class HuespedCamposVaciosException extends RuntimeException{
	
//	Identificador único para la serialización de la clase.
	private static final long serialVersionUID = 1L;
	
//	Declarar atributos constantes de la clase.
//	────────────────── Errores de campos vacios ──────────────────
	public static final String NOMBRE_VACIO = "El nombre del huesped no puede estar vacio";
	public static final String APELLIDOS_VACIO = "Los apellidos del huesped no pueden estar vacios";
	public static final String DIRECCION_VACIO = "La direccion del huesped no puede estar vacia";
	public static final String TELEFONO_VACIO = "El telefono del huesped no puede estar vacio";
	public static final String EMAIL_VACIO = "El email del huesped no puede estar vacio";
	
//	Constructor con parametros.
	public HuespedCamposVaciosException(String mensaje) {
		
		super(mensaje);
		
	}

}
