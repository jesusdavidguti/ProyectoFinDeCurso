package com.proyectofincurso.appValores.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@javax.persistence.Entity(name = "mercado")
public class Mercado {

	@Id
    @Column(name="codMercado",length=4)
	private String codMercado;
	
	@Column(name="nombre",length=50)
	private String nombre;
	
	@Column(name="codPais",length=4)
	private String codPais;
	
	@Column(name="continente",length=20)
	private String continente;
	
	@OneToMany(mappedBy="valor" )
	private List<Valor> listaValores;
		
	public Mercado() {
		super();
		listaValores = new ArrayList<Valor>();
	}

	public Mercado(String codMercado, String nombre, String codPais, String continente, List<Valor> listaValores) {
		super();
		this.codMercado = codMercado;
		this.nombre = nombre;
		this.codPais = codPais;
		this.continente = continente;
		this.listaValores = listaValores;
	}

	public List<Valor> getListaValores() {
		return listaValores;
	}

	public void addValor(Valor v) {
		this.listaValores.add(v);
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
