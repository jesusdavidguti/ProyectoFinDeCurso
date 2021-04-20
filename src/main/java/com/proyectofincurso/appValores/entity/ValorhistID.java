package com.proyectofincurso.appValores.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ValorhistID implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name="idValor")
	private Valor valor;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="fecValor")
	private Date fecValor;

	public ValorhistID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValorhistID(Valor valor, Date fecValor) {
		super();
		this.valor = valor;
		this.fecValor = fecValor;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	@Override
    public int hashCode() {
        return Objects.hash(getValor(), getFecValor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValorhistID)) return false;
        ValorhistID that = (ValorhistID) o;
        return Objects.equals(getValor(), that.getValor()) &&
                Objects.equals(getFecValor(), that.getFecValor());
    }	
}
