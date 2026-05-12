package es.accenture.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.accenture.dao.IHuespedDao;
import es.accenture.entity.Huesped;

/**
 * Clase que implementa metodos de la interfaz IHuespedService para la entidad 'Huesped'
 * Contiene la logica de negocio para la gestión de huespedes.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
@Service
public class HuespedService implements IHuespedService{
	
//	Declaramos atributo de tipo IHuespedDao.
//	Spring inyecta automaticamente 
	@Autowired
	private IHuespedDao huespedDao;
	
	@Override
	public List<Huesped> obtenerHuespedes() {
			
//		Devuelve la lista de los huespedes
		return huespedDao.obtenerHuespedes();
	}

	@Override
	public Huesped obtenerHuesped(int id) {
		
//		Devuelve el objeto huesped o null si no existe
		return huespedDao.obtenerHuesped(id);
	}

	@Override
	public void guardarHuesped(Huesped huesped) {
		
		huespedDao.guardarHuesped(huesped);
		
	}

	@Override
	public void actualizarHuesped(Huesped huesped) {
		
		huespedDao.actualizarHuesped(huesped);
	
	}

	@Override
	public void eliminarHuesped(int id) {
		
		huespedDao.eliminarHuesped(id);
	}
	
}
