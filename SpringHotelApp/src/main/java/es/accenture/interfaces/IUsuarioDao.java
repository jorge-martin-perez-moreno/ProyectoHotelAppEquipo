package es.accenture.interfaces;

import es.accenture.entity.Usuario;

/**
 * Interfaz que define las operaciones CRUD para la entidad 'Usuario'
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public interface IUsuarioDao {
	
	/**
	 * Metodo que busca un usuario en la BBDD por su username.
	 * 
	 * @param username, nombre de usuario
	 * @return usuario si existe o null si no existe.
	 */
	public Usuario buscarPorUsername(String username);
	
	
}
