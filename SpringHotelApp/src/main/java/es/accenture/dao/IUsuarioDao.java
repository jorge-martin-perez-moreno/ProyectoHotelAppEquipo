package es.accenture.dao;

import es.accenture.entity.Usuario;

/**
 * Interfaz que define las operaciones CRUD para la entidad 'Usuario'
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public interface IUsuarioDao {
	
	/**
	 * Metodo que busca un usuario por sus credenciales
	 * 
	 * @param username nombre del usuario
	 * @param password contrasena del usuario 
	 * @return usuario encontrado o null si las credenciales son incorrectas.
	 */
	public Usuario buscarPorCredenciales(String username, String password);
	
}
