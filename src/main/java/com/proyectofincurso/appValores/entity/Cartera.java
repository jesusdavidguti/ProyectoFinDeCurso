package com.proyectofincurso.appValores.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity(name = "cartera")
public class Cartera {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idCartera")
	private int idCartera;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;	
	
	@Column(name="fecInicio")
	private Date fecInicio;	
	
	@Column(name="fecFin")
	private Date fecFin;
	
	public Cartera() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cartera(int idCartera, String nombre, String descripcion, Date fecInicio, Date fecFin) {
		super();
		this.idCartera = idCartera;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecInicio = fecInicio;
		this.fecFin = fecFin;
	}

	public int getIdCartera() {
		return idCartera;
	}

	public void setIdCartera(int idCartera) {
		this.idCartera = idCartera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getFecFin() {
		return fecFin;
	}

	public void setFecFin(Date fecFin) {
		this.fecFin = fecFin;
	}

	@Override
	public String toString() {
		return "cartera [idCartera=" + idCartera + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", fecInicio=" + fecInicio + ", fecFin=" + fecFin + "]";
	}	
}
