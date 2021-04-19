package com.proyectofincurso.appValores.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@javax.persistence.Entity(name = "valorhist")
public class Valorhist {

	@Id
	@ManyToOne
    @JoinColumn(name="idValor")
	private Valor valor;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="fecValor")
	private Date fecValor;
	
	@Column(name="cotizacionUSdolar")
	private double cotizacionUSdolar;

	public Valorhist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Valorhist(Valor valor, Date fecValor, double cotizacion) {
		super();
		this.valor = valor;
		this.fecValor = fecValor;
		this.cotizacionUSdolar = cotizacion;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	public Date getFecValor() {
		return fecValor;
	}

	public void setFecValor(Date fecValor) {
		this.fecValor = fecValor;
	}

	public double getCotizacion() {
		return cotizacionUSdolar;
	}

	public void setCotizacion(double cotizacion) {
		this.cotizacionUSdolar = cotizacion;
	}
		

	
	
}
