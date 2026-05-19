package es.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.entity.Usuario;
import es.accenture.exceptions.CampoCredencialesIncorrectasException;
import es.accenture.exceptions.CampoCredencialesVacioException;
import es.accenture.interfaces.IUsuarioDao;
import es.accenture.interfaces.IUsuarioService;

/**
 * Clase que implementa metodos de la interfaz IUsuarioService para la entidad 'Usuario'
 * Contiene la logica de negocio para la gestión de usuarios.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
//Anotacion que dice a Spring que esta clase es un bean de la capa Service.
@Service
public class UsuarioService implements IUsuarioService{

//	Declaramos atributo de tipo IUsuarioDao.
//	Spring inyecta automaticamente el bean de IUsuarioDao en el atributo
	@Autowired
	private IUsuarioDao usuarioDao;
	
	/**
	 * Metodo que comprueba si los campos username y password estan vacios.
	 * 
	 * @param username, nombre de usuario.
	 * @param password, contrasena del usuario.
	 * @throws CampoCredencialesVacioException si username o passwrod estan vacias o son null
	 */
	private void validarCamposVacios(String username, String password) throws CampoCredencialesVacioException {
		
//		Comprobamos que el username y la contrasena son null o estan vacias. 
		if((username == null || username.isEmpty()) && (password == null ||password.isEmpty())) {
//			Si username y password son null o vacios lanza esta excepcion.
			throw new CampoCredencialesVacioException(CampoCredencialesVacioException.USUARIO_PASSWORD_VACIOS);
//		
//		Comprobamos que solo el username esta vacio. 
		}else if(username == null || username.isEmpty()){
//			Si username null o vacio lanza esta excepcion.
			throw new CampoCredencialesVacioException(CampoCredencialesVacioException.USUARIO_VACIO);
		
//		Comprobamos que solo el password esta vacio.
		}else if(password == null || password.isEmpty()) {
//			Si password null o vacio lanza esta excepcion.
			throw new CampoCredencialesVacioException(CampoCredencialesVacioException.PASSWORD_VACIO);
			
		}
		
	}
	
	/**
	 * Metodo que comprueba si los campos username y password son correctos en la bbdd.
	 * 
	 * @param username, nombre de usuario.
	 * @param password, contrasena del usuario.
	 * @return usuario si las credenciales son correctas
	 * @throws CampoCredencialesIncorrectasException si las credenciales son incorrectas
	 */
	private Usuario validarCredenciales(String username, String password) throws CampoCredencialesIncorrectasException {
		
//		Buscamos el usuario en la BBDD por username.
		Usuario usuario = usuarioDao.buscarPorUsername(username);
		
//		Si usuario no existe en la BBDD o password incorrecta en la BBDD, lanza excepcion
		if(usuario == null || !usuario.getPassword().equals(password)) {
//			Lanza excepcion
			throw new CampoCredencialesIncorrectasException(
		            CampoCredencialesIncorrectasException.CREDENCIALES_INCORRECTAS);
		}
		
//		Devuelve el objeto usuario si las credenciales son correctas.
		return usuario;
		
	}

	@Override
	public Usuario buscarPorCredenciales(String username, String password)
			throws CampoCredencialesVacioException, CampoCredencialesIncorrectasException {
		
//		Llamamos al metodo validarCamposVacios().
		validarCamposVacios(username, password);
		
//		Lalamos al metodo validarCredenciales().
		return validarCredenciales(username, password);
	}
	
}
