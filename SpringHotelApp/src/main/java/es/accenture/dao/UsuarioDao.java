package es.accenture.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Usuario;
import es.accenture.interfaces.IUsuarioDao;

/**
 * Clase que implementa metodos de la interfaz IUsuarioDao para la entidad 'Usuario'.
 * Contiene las operaciones CRUD contra la base de datos.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
//Anotacion que gestiona las transacciones automaticamente.
@Transactional
//Anotacion que define esta clase como DAO, Objeto de Acceso a Datos.
@Repository
public class UsuarioDao implements IUsuarioDao{
	
//	Declaramos atributo de tipo SessionFactory para poder usar la conexion activa.
//	Spring inyecta automaticamente objeto @Bean SessionFactory que creamos en la clase HibernateConfig.java
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Usuario buscarPorUsername(String username) {
		
//		Obtener la sesión activa de la conexion a la BBDD.
		Session sesion = sessionFactory.getCurrentSession();	
		
//		Busca el usuario por username en la BBDD.
		Usuario usuario = sesion.createQuery("FROM Usuario WHERE username = :username",
			    							 Usuario.class)
//											 sustituimos :username por el valor de la variable username.
			    							 .setParameter("username", username)
//			    							 uniqueResult() devuelve el objeto si existe o null si no existe
			    							 .uniqueResult();
		
//		Devuelve el objeto 'usuario' si existe o null si no existe.
		return usuario;
	}


}
