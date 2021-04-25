package com.proyectofincurso.appValores.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@javax.persistence.Entity(name = "valor")
public class Valor {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idValor")
	private int idValor;
	
	@Column(name="nombre",length=50)
	private String nombre;

	@Column(name="sector",length=20)
	private String sector;
	
	@ManyToOne
    @JoinColumn(name="codMercado")
    private Mercado mercado;
		
	@ManyToOne
    @JoinColumn(name="codDivisa")
    private Divisa divisa;

	
	public Valor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Valor(int idVAlor, String nombre, String sector, Mercado mercado, Divisa divisa) {
		super();
		this.idValor = idVAlor;
		this.nombre = nombre;
		this.sector = sector;
		this.mercado = mercado;
		this.divisa = divisa;
	}

	public int getIdVAlor() {
		return idValor;
	}

	public void setIdVAlor(int idVAlor) {
		this.idValor = idVAlor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}
	
}
