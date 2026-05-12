package es.accenture.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.accenture.entity.Huesped;

/**
 * Clase que implementa metodos de la interfaz IHuespedDao para la entidad 'Huesped'.
 * Contiene las operaciones CRUD contra la base de datos.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
@Transactional
@Repository
public class HuespedDao implements IHuespedDao{

//	Declaramos atributo de tipo SessionFactory para poder usar la conexion activa.
//	Spring inyecta automaticamente objeto @Bean SessionFactory que creamos en la clase HibernateConfig.java
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Huesped> obtenerHuespedes() {
		
//		Obtener la sesión activa de la conexion a la BBDD
		Session session = sessionFactory.getCurrentSession();	
		
//		Busca todos los registros y los guarda en una lista 'huespedes'
		List<Huesped> huespedes = session.createQuery("FROM Huesped", Huesped.class).getResultList();
			
//		Devuelve la lista de los huespedes
		return huespedes;
	}

	@Override
	public Huesped obtenerHuesped(int id) {
		
//		Obtener la sesión activa de la conexion a la BBDD
		Session session = sessionFactory.getCurrentSession();
		
//		Busca el huesped por su id y lo almacena en el objeto de tipo Huesped 'huesped'
		Huesped huesped = session.get(Huesped.class, id);
		
//		Devuelve el objeto huesped o null si no existe
		return huesped;
	}

	@Override
	public void guardarHuesped(Huesped huesped) {
		
//		Obtener la sesión activa de la conexion a la BBDD
		Session session = sessionFactory.getCurrentSession();
		
//		Guardamos el objeto 'huesped'
		session.save(huesped);
		
	}

	@Override
	public void actualizarHuesped(Huesped huesped) {
		
//		Obtener la sesión activa de la conexion a la BBDD
		Session session = sessionFactory.getCurrentSession();
		
//		Actualiza el objeto completo 'huesped'
		session.update(huesped);
	
	}

	@Override
	public void eliminarHuesped(int id) {
		
//		Obtener la sesión activa de la conexion a la BBDD
		Session session = sessionFactory.getCurrentSession();
		
//		Busca el huesped por su id y lo almacena en el objeto de tipo Huesped 'huesped'
		Huesped huesped = session.get(Huesped.class, id);
		
//		Elimina el objeto completo 'huesped'
		session.delete(huesped);
	}
	
}
