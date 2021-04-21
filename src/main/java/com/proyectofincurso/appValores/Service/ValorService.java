package com.proyectofincurso.appValores.Service;

import java.util.List;

import com.proyectofincurso.appValores.entity.Valor;

public interface ValorService {

	public List<Valor> findAll();

    public Valor findById(int id);

	public Valor findByNombre(String nombre);
	 
    public void save(Valor valor);

    public void deleteById(int id);	
}
