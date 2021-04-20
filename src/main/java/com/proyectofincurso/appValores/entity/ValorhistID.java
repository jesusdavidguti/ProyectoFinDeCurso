package com.proyectofincurso.appValores.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ValorhistID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
    @JoinColumn(name="idValor")
	private Valor valor;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="fecValor")
	private Date fecValor;

	
	
}
