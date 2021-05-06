package com.proyectofincurso.appValores.entity;

import java.util.Date;

public class Valorhistmaxmin {

	private double cotizacionUSdolarHoy;
	
	private double cotizacionUSdolarAyer;

	private String valorNombre;
	
	private String mercadoNombre;
	
	private Date fecha;

	private double diferenciaCotizacion;	
	
	public Valorhistmaxmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Valorhistmaxmin(double cotizacionUSdolarHoy, double cotizacionUSdolarAyer, String valorNombre,
			String mercadoNombre, Date fecha, double diferenciaCotizacion) {
		super();
		this.cotizacionUSdolarHoy = cotizacionUSdolarHoy;
		this.cotizacionUSdolarAyer = cotizacionUSdolarAyer;
		this.valorNombre = valorNombre;
		this.mercadoNombre = mercadoNombre;
		this.fecha = fecha;
		this.diferenciaCotizacion = diferenciaCotizacion;
	}

	public double getCotizacionUSdolarHoy() {
		return cotizacionUSdolarHoy;
	}

	public void setCotizacionUSdolarHoy(double cotizacionUSdolarHoy) {
		this.cotizacionUSdolarHoy = cotizacionUSdolarHoy;
	}

	public double getCotizacionUSdolarAyer() {
		return cotizacionUSdolarAyer;
	}

	public void setCotizacionUSdolarAyer(double cotizacionUSdolarAyer) {
		this.cotizacionUSdolarAyer = cotizacionUSdolarAyer;
	}

	public double getDiferenciaCotizacion() {
		return diferenciaCotizacion;
	}

	public void setDiferenciaCotizacion(double diferenciaCotizacion) {
		this.diferenciaCotizacion = diferenciaCotizacion;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public String getMercadoNombre() {
		return mercadoNombre;
	}

	public void setMercadoNombre(String mercadoNombre) {
		this.mercadoNombre = mercadoNombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
		
}
