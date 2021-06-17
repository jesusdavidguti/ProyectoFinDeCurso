package com.proyectofincurso.appError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MercadoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3777837701299228962L;

	public MercadoNotFoundException (String id) {
		super("Mercado con id "+id+" inexistente.");
	}
	
}
