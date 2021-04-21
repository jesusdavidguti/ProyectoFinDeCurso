package com.proyectofincurso.appValores.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

@javax.persistence.Entity(name = "divisahist")
public class Divisahist {

	@EmbeddedId
	private DivisahistID divisaHistID;
	
	@Column(name="cotizacionUSdolar")
	private double cotizacionUSdolar;

	public Divisahist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Divisahist(DivisahistID divisaHistID, double cotizacionUSdolar) {
		super();
		this.divisaHistID = divisaHistID;
		this.cotizacionUSdolar = cotizacionUSdolar;
	}

	public DivisahistID getDivisaHistID() {
		return divisaHistID;
	}

	public void setDivisaHistID(DivisahistID divisaHistID) {
		this.divisaHistID = divisaHistID;
	}

	public double getCotizacionUSdolar() {
		return cotizacionUSdolar;
	}

	public void setCotizacionUSdolar(double cotizacionUSdolar) {
		this.cotizacionUSdolar = cotizacionUSdolar;
	}	
}
