package es.accenture.entity;                          //esto lo hace cualquiera entero A o B


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase entidad que representa una reserva registrada en el hotel.
 * 
 * Esta clase almacena la informacion de las reservas,
 * incluyendo el huesped, la habitacion, las fechas de estancia,
 * el tipo de pension y el estado de la reserva.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Entity // Anotación para representar una tabla en BBDD
@Table(name="reservas") // Anotación que indica cómo se llama la tabla que representa
public class Reserva {
	
	/**
	 * Enumeracion que define los posibles estados de una reserva. ENUM
	 */
	public enum EstadoReserva {PENDIENTE,CONFIRMADA,CANCELADA}
	/**
	 * Enumeracion que define los distintos tipos de pension disponibles. ENUM
	 */
	public enum TipoPension {ALOJAMIENTO,MEDIA,COMPLETA}
	
	/*
	 * Identificador unico de la reserva.
	 */
	@Id //clave primaria INT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reserva",nullable=false)
	private int idReserva;
	
	/*
	 * Huesped de la reserva.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_huesped",nullable=false)
	private Huesped huesped;
	
	/*
	 * Habitacion de la reserva.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_habitacion",nullable=false)
	private Habitacion habitacion;
	
	/*
	 * Fecha de entrada de la reserva.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_entrada",nullable=true)
	private Date fechaEntrada;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_salida",nullable=true)
	private Date fechaSalida;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pension")
	private TipoPension tipoPension;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado_reserva")
	private EstadoReserva estadoReserva;
	
	@Column(name="numero_huespedes",nullable=false)
	private int numeroHuespedes;
	
	@Column(name="observaciones") 
	private String observaciones;
	
	public Reserva() {
		
	}
	
	public Reserva(Huesped huesped,Habitacion habitacion,java.sql.Date fechaEntrada,java.sql.Date fechaSalida,TipoPension tipoPension,EstadoReserva estadoReserva,int numeroHuespedes,String observaciones) {
				
		this.huesped=huesped;
		this.habitacion=habitacion;
		this.fechaEntrada=fechaEntrada;
		this.fechaSalida=fechaSalida;
		this.tipoPension=tipoPension;
		this.estadoReserva=estadoReserva;
		this.numeroHuespedes=numeroHuespedes;
		this.observaciones=observaciones;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}
	
	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) { //este setter establece la relacion con Habitacion
		this.habitacion = habitacion;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public TipoPension getTipoPension() {
		return tipoPension;
	}

	public void setTipoPension(TipoPension tipoPension) {
	    this.tipoPension = tipoPension;
	}
	
	public EstadoReserva getEstadoReserva() {
	    return estadoReserva;
	}
	
	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public int getNumeroHuespedes() {
		return numeroHuespedes;
	}

	public void setNumeroHuespedes(int numeroHuespedes) {
		this.numeroHuespedes = numeroHuespedes;
	}
	
	public String getObservaciones() {
	    return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
	    this.observaciones = observaciones;
	}
	
	@Override
	public String toString() {
		return "Reserva [ID Reserva = "
				+ idReserva
				+ ", Huesped = "
				+ huesped.getIdHuesped()
				+ ", Habitación = "
				+ habitacion.getIdHabitacion()
				+ ", Fecha de Entrada = "
				+ fechaEntrada
				+ ", Fecha de Salida = "
				+ fechaSalida
				+ ", Tipo de Pensión = "
				+ tipoPension
				+ ", Número de Huespedes = "
				+ numeroHuespedes
				+ ", Observaciones = "
				+ observaciones
				+ "]";
		
	}

}
