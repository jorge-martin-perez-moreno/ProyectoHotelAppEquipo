package es.accenture.interfaces;

import java.util.List;

import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedCamposVaciosException;
import es.accenture.exceptions.HuespedDatosNoValidosException;
import es.accenture.exceptions.HuespedNoEncontradoException;

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
	 * @return lista de objetos Huesped.
	 */
	public List<Huesped> obtenerHuespedes();
	
	/**
	 * Metodo busca un huesped por su id y devuelve el detalle de un huesped si existe.
	 * 
	 * @param id, identificador del huespeda a buscar
	 * @throws HuespedNoEncontradoException, lanza excepcion si huesped no encontrado en la base de datos. 
	 * @return objeto Huesped. 
	 */
	public Huesped obtenerHuesped(int id) throws HuespedNoEncontradoException;
	
	/**
	 * Metodo que guarda un nuevo huesped en la base de datos.
	 * 
	 * @param huesped objeto con los datos del huesped a guardar.
	 * @throws HuespedCamposVaciosException si algun campo esta vacio o es null
	 * @throws HuespedDatosNoValidosException si el formato de algun dato no es correcto
	 */
	public void guardarHuesped(Huesped huesped) throws HuespedCamposVaciosException, HuespedDatosNoValidosException;
	
	/**
	 * Metodo que actualiza los datos de un huesped ya existente.
	 * 
	 * @param huesped objeto con los datos del huesped a actualizar.
	 * @throws HuespedCamposVaciosException si algun campo esta vacio o es null
	 * @throws HuespedDatosNoValidosException si el formato de algun dato no es correcto
	 * @throws HuespedNoEncontradoException si el huesped no existe en la base de datos
	 */
	public void actualizarHuesped(Huesped huesped) throws HuespedCamposVaciosException, HuespedDatosNoValidosException, HuespedNoEncontradoException;

	/**
	 * Metodo que elimina un huesped de la base de datos buscandolo por su id.
	 * 
	 * @param id, identificador del huesped a eliminar
	 * @throws HuespedNoEncontradoException si el huesped no existe en la base de datos
	 */
	public void eliminarHuesped(int id) throws HuespedNoEncontradoException;
}
