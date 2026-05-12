package es.accenture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Clase entidad 'Huesped'
 * Clase que nos permite crear objetos Huespedes
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
@Entity
@Table(name="huespedes")
public class Huesped {
	
//	Declaracion de atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_huesped")
	private int idHuesped;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellidos")
	private String apellidos;
	@Column(name="direccion")
	private String direccion;
	@Column(name="telefono")
	private String telefono;
	@Column(name="email")
	private String email;
	
//	Constructor vacio
	public Huesped() {
		
	}
	
//	Constructor con parametros
	public Huesped(String nombre, String apellido, String direccion, String telefono, String email) {
		this.nombre = nombre;
		this.apellidos = apellido;
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
	 * Metodo que devuelve el apellido
	 * 
	 * @return apellido
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo para dar un valor o modificar el apellido
	 * 
	 * @param apellido
	 */
	public void setApellido(String apellidos) {
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
		return "Huesped id_huesped: " + idHuesped + ", nombre: " + nombre + ", apellido: " + apellidos + ", direccion: "
				+ direccion + ", telefono: " + telefono + ", email: " + email;
	}

}
