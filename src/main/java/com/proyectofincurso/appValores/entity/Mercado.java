package com.proyectofincurso.appValores.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity(name = "mercado")
public class Mercado {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="codMercado")
	private String codMercado;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="codPais")
	private String codPais;
	
	@Column(name="continente")
	private String continente;
	
	public Mercado() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Mercado(String codMercado, String nombre, String codPais, String continente) {
		super();
		this.codMercado = codMercado;
		this.nombre = nombre;
		this.codPais = codPais;
		this.continente = continente;
	}

	public String getCodMercado() {
		return codMercado;
	}

	public void setCodMercado(String codMercado) {
		this.codMercado = codMercado;
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

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	@Override
	public String toString() {
		return "mercado [codMercado=" + codMercado + ", nombre=" + nombre + ", codPais=" + codPais + ", continente="
				+ continente + "]";
	}	
}
