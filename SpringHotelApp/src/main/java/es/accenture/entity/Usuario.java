package es.accenture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Clase entidad que representa la tabla 'usuarios' de la bbdd.
 * Permite crear y gestionar objetos Usuario.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
//Anotacion que le dice a Hibernate que esta clase es una entidad y se corresponde con una tabla de la BBDD.
@Entity
//Anotacion que le dice a Hibernate con que tabla exacta de la BBDD se corresponde esta entidad.
@Table(name="usuarios")
public class Usuario {
	
	/**
	 * Clase enum que define los roles del usuario en el sistema.
	 * @author jorge martin perez moreno
	 * @version 1.0
	 */
	public enum UsuarioRol{
		RECEPCIONISTA,
		SUPERVISOR;
	}
	
//	Declaracion de atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario", nullable=false)
	private int idUsuario;
	@Column(name="username", nullable=false,  unique=true)
	private String username;
	@Column(name="password", nullable=false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name="rol", nullable=false)
	private UsuarioRol rol;
	
//	Constructor vacio. Obligatorio para Hibernate.
	public Usuario() {
		
	}
	
//	Constructor con parametros.
	public Usuario(String username, String password, UsuarioRol rol) {
		this.username = username;
		this.password = password;
		this.rol = rol;
	}
	
//	──────────────────────── Metodos Getter y Setter ─────────────────────────
//	Metodos publicos para acceder y modificar los atributos privados.

	
	/**
	 * Metodo que devuelve el idUsuario
	 * 
	 * @return idUsuario 
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	
	
	/**
	 * Metodo para dar un valor o modificar el idUsuario
	 * 
	 * @param idUsuario, identificador unico del usuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Metodo que devuelve el username.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Metodo para dar un valor o modificar el username.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Metodo que devuelve el password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo para dar un valor o modificar el password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Metodo que devuelve el rol
	 * 
	 * @return rol
	 */
	public UsuarioRol getRol() {
		return rol;
	}

	/**
	 * Metodo para dar un valor o modificar el rol
	 * 
	 * @param rol
	 */
	public void setRol(UsuarioRol rol) {
		this.rol = rol;
	}

//	Metodo toString para mostrar informacion.
	@Override
	public String toString() {
		return "Usuario id_usuario: " + idUsuario + ", username: " + username + " rol: "+ rol;
	}
	
}
