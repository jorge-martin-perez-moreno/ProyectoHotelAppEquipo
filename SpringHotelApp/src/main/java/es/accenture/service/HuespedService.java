package es.accenture.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedCamposVaciosException;
import es.accenture.exceptions.HuespedDatosNoValidosException;
import es.accenture.exceptions.HuespedNoEncontradoException;
import es.accenture.interfaces.IHuespedDao;
import es.accenture.interfaces.IHuespedService;

/**
 * Clase que implementa metodos de la interfaz IHuespedService para la entidad 'Huesped'
 * Contiene la logica de negocio para la gestión de huespedes.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
//Anotacion que define esta clase como bean de la capa Service.
@Service
public class HuespedService implements IHuespedService{
	
//	Declaramos atributo de tipo IHuespedDao.
//	Spring inyecta automaticamente el bean HuespedDao (@Repository) en este atributo.
	@Autowired
	private IHuespedDao huespedDao;
	
	@Override
	public List<Huesped> obtenerHuespedes() {
		
//		Llamamos al metodo obtenerHuespedes() del DAO.
//		Devuelve la lista de objetos Huesped.
		return huespedDao.obtenerHuespedes();
	}

	@Override
	public Huesped obtenerHuesped(int id) throws HuespedNoEncontradoException{
		
//		Buscamos el huesped en la BBDD llamando al metodo del DAO 'obtenerHuesped()'.
//		Y lo guardamos en la variable 'huesped'.
		Huesped huesped = huespedDao.obtenerHuesped(id);
		
//		Comprobamos si el huesped esta vacio o es null.
		if(huesped == null) {
//			Lanza excepcion 'HuespedNoEncontradoException'.
			throw new HuespedNoEncontradoException(HuespedNoEncontradoException.HUESPED_NO_ENCONTRADO);
		}

//		Devuelve el objeto Huesped.
		return huesped;
	}

	@Override
	public void guardarHuesped(Huesped huesped) throws HuespedCamposVaciosException, HuespedDatosNoValidosException {
		
//		Llamamos al metodo 'validarCamposVacios()'
		validarCamposVacios(huesped);
		
//		Llamamos al metodo 'validarDatos()'
		validarDatos(huesped);
		
//	    Llamamos al metodo guardarHuesped del DAO. Le pasamos como parametro el objeto 'huesped'.
		huespedDao.guardarHuesped(huesped);
		
	}

	@Override
	public void actualizarHuesped(Huesped huesped) throws HuespedCamposVaciosException, HuespedDatosNoValidosException, HuespedNoEncontradoException {
		
//		Llamamos al metodo 'getIdHuesped' de Huesped, y se lo pasamos como parametro al metodo 'obtenerHuesped' de HuespedDAO.
//		Lo guardamos en la variable 'huespedExistente'.
		Huesped huespedExistente = huespedDao.obtenerHuesped(huesped.getIdHuesped());
	   
//		Comprobamos que el huesped existe en la BBDD.
		if (huespedExistente == null) {
	        throw new HuespedNoEncontradoException(HuespedNoEncontradoException.HUESPED_NO_ENCONTRADO);
	    }
		
//		Llamamos al metodo 'validarCamposVacios()'
		validarCamposVacios(huesped);
		
//		Llamamos al metodo 'validarDatos()'
		validarDatos(huesped);
		
//	    Llamamos al metodo actualizaHuesped() del DAO.
		huespedDao.actualizarHuesped(huesped);
	
	}

	@Override
	public void eliminarHuesped(int id) throws HuespedNoEncontradoException{
		
//		Buscamos el huesped en la BBDD llamando al metodo del DAO 'obtenerHuesped()'.
//		Y lo guardamos en la variable 'huesped'.
		Huesped huesped = huespedDao.obtenerHuesped(id);
		
//		Comprobamos si el huesped esta vacio o es null.
		if(huesped == null) {
//			Lanza excepcion 'HuespedNoEncontradoException'.
			throw new HuespedNoEncontradoException(HuespedNoEncontradoException.HUESPED_NO_ENCONTRADO);
		}
		
//		Llamamos al metodo 'eliminarHuesped()' del DAO.
		huespedDao.eliminarHuesped(id);
	}
	
	/**
	 * Metodo que valida si los campos del formulario del huesped son null o estan vacios
	 * 
	 * @param huesped, objeto Huesped.
	 * @throws HuespedCamposVaciosException si algun campo esta vacio o es null
	 */
	private void validarCamposVacios(Huesped huesped) throws HuespedCamposVaciosException{
		
//		Comprobamos si el nombre del huesped es null o el campo está vacio.
		if(huesped.getNombre() == null || huesped.getNombre().isEmpty()) {
//			Lanzamos mensaje de error
			throw new HuespedCamposVaciosException(HuespedCamposVaciosException.NOMBRE_VACIO);
		
//		Comprobamos si el apellido del huesped es null o el campo está vacio.
		}else if(huesped.getApellidos() == null || huesped.getApellidos().isEmpty()) {
//			Lanzamos mensaje de error
			throw new HuespedCamposVaciosException(HuespedCamposVaciosException.APELLIDOS_VACIO);
		
//		Comprobamos si la direccion del huesped es null o el campo está vacio.
		}else if(huesped.getDireccion() == null || huesped.getDireccion().isEmpty()) {
//			Lanzamos mensaje de error
			throw new HuespedCamposVaciosException(HuespedCamposVaciosException.DIRECCION_VACIO);
		
//		Comprobamos si el telefono del huesped es null o el campo está vacio.
		}else if(huesped.getTelefono() == null || huesped.getTelefono().isEmpty()) {
//			Lanzamos mensaje de error
			throw new HuespedCamposVaciosException(HuespedCamposVaciosException.TELEFONO_VACIO);
		
//		Comprobamos si el email del huesped es null o el campo está vacio.
		}else if(huesped.getEmail() == null || huesped.getEmail().isEmpty()) {
//			Lanzamos mensaje de error
			throw new HuespedCamposVaciosException(HuespedCamposVaciosException.EMAIL_VACIO);
		}
	}
	
	/**
	 * Metodo que valida el formato de los datos del huesped.
	 * 
	 * @param huesped, objeto Huesped.
	 * @throws HuespedDatosNoValidosException, si el formato del telefono o email no es correcto.
	 */
	private void validarDatos(Huesped huesped) throws HuespedDatosNoValidosException{
		
//		Comprobamos que el formato del telefono es distinto de 9 digitos.
		if(!huesped.getTelefono().matches("\\d{9}")) {
//			Lanzamos mensaje de error
			throw new HuespedDatosNoValidosException(HuespedDatosNoValidosException.TELEFONO_INVALIDO);
			
		}
		
//		Comprobamos que el email no contiene el caracter @.
		if(!huesped.getEmail().contains("@")) {
//			Lanzamos mensaje de error
			throw new HuespedDatosNoValidosException(HuespedDatosNoValidosException.EMAIL_INVALIDO);
		}
		
	}
	
}
