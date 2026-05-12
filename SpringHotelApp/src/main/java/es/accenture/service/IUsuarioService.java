package es.accenture.service;

import es.accenture.entity.Usuario;

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
	 * @param username nombre de usuario
	 * @param password contrasena de usuario
	 * @return usuario si existe o null si no existe
	 */
	public Usuario buscarPorCredenciales(String username, String password);
	
}
