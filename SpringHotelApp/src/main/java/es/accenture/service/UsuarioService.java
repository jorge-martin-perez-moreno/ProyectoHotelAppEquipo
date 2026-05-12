package es.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.dao.IUsuarioDao;
import es.accenture.entity.Usuario;

/**
 * Clase que implementa metodos de la interfaz IUsuarioService para la entidad 'Usuario'
 * Contiene la logica de negocio para la gestión de usuarios.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
@Service
public class UsuarioService implements IUsuarioService{

//	Declaramos atributo de tipo IHuespedDao.
//	Spring inyecta automaticamente 
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public Usuario buscarPorCredenciales(String username, String password) {
		
		return usuarioDao.buscarPorCredenciales(username, password);
	}
	
}
