package com.proyectofincurso.appValores.entity;

import javax.persistence.Column;
import javax.persistence.Id;

@javax.persistence.Entity(name = "divisa")
public class Divisa {

	@Id
    @Column(name="codDivisa",length=4)
	private String codDivisa;
	
	@Column(name="nombre",length=50)
	private String nombre;
	
	@Column(name="codPais",length=4)
	private String codPais;
		
	@Column(name="cambio")
	private double cambio;
	
	public Divisa() {
		super();
	}
	
	public Divisa(String codDivisa, String nombre, String codPais, double cambio) {
		super();
		this.codDivisa = codDivisa;
		this.nombre = nombre;
		this.codPais = codPais;
		this.cambio = cambio;
	}
		
	public String getCodDivisa() {
		return codDivisa;
	}

	public void setCodDivisa(String codDivisa) {
		this.codDivisa = codDivisa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public double getCambio() {
		return cambio;
	}

	public void setCambio(double cambio) {
		this.cambio = cambio;
	}

	@Override
	public String toString() {
		return "divisa [codDivisa=" + codDivisa + ", nombre=" + nombre + ", codPais=" + codPais + ", cambio=" + cambio
				+ "]";
	}
		
}
