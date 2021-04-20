package com.proyectofincurso.appValores.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

@javax.persistence.Entity(name = "valorhist")
public class Valorhist{

//	@Id
//	@ManyToOne
//	@JoinColumn(name="idValor")
//	private Valor valor;
//	
//	@Id
//	@Temporal(TemporalType.TIMESTAMP)	
//	@Column(name="fecValor")
//	private Date fecValor;
	
	@EmbeddedId
	private ValorhistID valorHistID;
	
	@Column(name="cotizacionUSdolar")
	private double cotizacionUSdolar;
	
	public Valorhist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Valorhist(ValorhistID valorHistID, double cotizacionUSdolar) {
		super();
		this.valorHistID = valorHistID;
		this.cotizacionUSdolar = cotizacionUSdolar;
	}

	public ValorhistID getValorHistID() {
		return valorHistID;
	}

	public void setValorHistID(ValorhistID valorHistID) {
		this.valorHistID = valorHistID;
	}

	public double getCotizacionUSdolar() {
		return cotizacionUSdolar;
	}

	public void setCotizacionUSdolar(double cotizacionUSdolar) {
		this.cotizacionUSdolar = cotizacionUSdolar;
	}

}
