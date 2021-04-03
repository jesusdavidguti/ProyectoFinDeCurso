package com.proyectofincurso.appValores.Service;

import java.util.List;

import com.proyectofincurso.appValores.entity.Divisa;

public interface DivisaService {

	public List<Divisa> findAll();

    public Divisa findById(String id);

    public void save(Divisa divisa);

    public void deleteById(String id);
	
}
