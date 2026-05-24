package es.accenture.service;

import java.util.List;

import es.accenture.entity.Huesped;

/**
 * Interfaz que define las operaciones del Service para la entidad 'Huesped'
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public interface IHuespedService {

	/**
	 * Metodo que devuelve todos los huespedes de las base de datos.
	 * 
	 * @return lista de todos los huespedes
	 */
	public List<Huesped> obtenerHuespedes();
	
	/**
	 * Metodo busca un huesped por su id y devuelve el detalle de un huesped si existe.
	 * 
	 * @param id del huespeda a buscar
	 * @return devuelve los detalles de un huesped si existe o null si no existe. 
	 */
	public Huesped obtenerHuesped(int id);
	
	/**
	 * Metodo que guarda un nuevo huesped en la base de datos.
	 * 
	 * @param huesped objeto huesped a guardar.
	 */
	public void guardarHuesped(Huesped huesped);
	
	/**
	 * Metodo que actualiza los datos de un huesped ya existente.
	 * 
	 * @param huesped objeto huesped a actualizar.
	 */
	public void actualizarHuesped(Huesped huesped);

	/**
	 * Metodo que elimina un huesped de la base de datos buscandolo por su id.
	 * 
	 * @param id del huesped a eliminar
	 */
	public void eliminarHuesped(int id);
}
