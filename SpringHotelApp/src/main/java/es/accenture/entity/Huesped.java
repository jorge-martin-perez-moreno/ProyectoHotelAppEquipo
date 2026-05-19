package es.accenture.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Clase entidad que representa la tabla 'huespedes' de la bbdd.
 * Permite crear y gestionar objetos Huesped.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
//Anotacion que le dice a Hibernate que esta clase es una entidad y se corresponde con una tabla de la BBDD.
@Entity
//Anotacion que le dice a Hibernate con que tabla exacta de la BBDD se corresponde esta entidad.
@Table(name="huespedes")
public class Huesped {
	
//	Declaracion de atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_huesped", nullable=false)
	private int idHuesped;
	@Column(name="nombre", nullable=false)
	private String nombre;
	@Column(name="apellidos", nullable=false)
	private String apellidos;
	@Column(name="direccion", nullable=false)
	private String direccion;
	@Column(name="telefono", nullable=false)
	private String telefono;
	@Column(name="email")
	private String email;
	
//	Constructor vacio
	public Huesped() {
		
	}
	
//	Constructor con parametros
	public Huesped(String nombre, String apellidos, String direccion, String telefono, String email) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}
	
//	──────────────────────── Metodos Getter y Setter ─────────────────────────
//	Metodos publicos para acceder y modificar los atributos privados.

	/**
	 * Metodo que devuelve el idHuesped
	 * 
	 * @return idHuesped
	 */
	public int getIdHuesped() {
		return idHuesped;
	}

	/**
	 * Metodo para dar un valor o modificar el idHuesped
	 * 
	 * @param idHuesped
	 */
	public void setIdHuesped(int idHuesped) {
		this.idHuesped = idHuesped;
	}

	/**
	 * Metodo que devuelve el nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para dar un valor o modificar el nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que devuelve los apellidos
	 * 
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo para dar un valor o modificar los apellidos
	 * 
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Metodo que devuelve la direccion
	 * 
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo para dar un valor o modificar la direccion
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo que devuelve el telefono
	 * 
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo para dar un valor o modificar el telefono
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo que devuelve el email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo para dar un valor o modificar el email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	//	Metodo toString para mostrar informacion.
	@Override
	public String toString() {
		return "Huesped id_huesped: " + idHuesped + ", nombre: " + nombre + ", apellidos: " + apellidos + ", direccion: "
				+ direccion + ", telefono: " + telefono + ", email: " + email;
	}

}
