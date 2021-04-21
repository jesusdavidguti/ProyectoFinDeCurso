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
public class DivisahistID implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name="codDivisa")
	private Divisa divisa;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="fecDivisa")
	private Date fecDivisa;

	public DivisahistID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DivisahistID(Divisa divisa, Date fecDivisa) {
		super();
		this.divisa = divisa;
		this.fecDivisa = fecDivisa;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public Date getFecDivisa() {
		return fecDivisa;
	}

	public void setFecDivisa(Date fecDivisa) {
		this.fecDivisa = fecDivisa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(getDivisa(), getFecDivisa());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivisahistID)) return false;
        DivisahistID that = (DivisahistID) o;
        return Objects.equals(getDivisa(), that.getDivisa()) &&
                Objects.equals(getFecDivisa(), that.getFecDivisa());
    }		
	
}
