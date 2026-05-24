package es.accenture.interfaces;

import es.accenture.entity.Usuario;
import es.accenture.exceptions.CampoCredencialesIncorrectasException;
import es.accenture.exceptions.CampoCredencialesVacioException;

/**
 * Interfaz que define las operaciones del Service para la entidad 'Usuario'
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public interface IUsuarioService {
	
	/**
	 * Metodo que busca un usuario por sus credenciales
	 * 
	 * @param username, nombre de usuario
	 * @param password, contrasena del usuario
	 * @return usuario si credenciales existen.
	 * @throws CampoCredencialesVacioException si username o password estan vacios.
	 * @throws CampoCredencialesIncorrectasException si username o password son incorrectas.
	 */
	public Usuario buscarPorCredenciales(String username, String password) 
			throws CampoCredencialesVacioException, CampoCredencialesIncorrectasException;
	
}
