package com.proyectofincurso.appValores.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy="divisa" )
	private List<Valor> listaValores;
	
	public Divisa() {
		super();
		listaValores = new ArrayList<Valor>();
	}
	
	public Divisa(String codDivisa, String nombre, String codPais, double cambio, List<Valor> listaValores) {
		super();
		this.codDivisa = codDivisa;
		this.nombre = nombre;
		this.codPais = codPais;
		this.cambio = cambio;
		this.listaValores = listaValores;
	}

	public List<Valor> getListaValores() {
		return listaValores;
	}

	public void addValor(Valor v) {
		this.listaValores.add(v);
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
