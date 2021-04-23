package com.proyectofincurso.appValores.Service;

import java.util.Date;
import java.util.List;

import com.proyectofincurso.appValores.entity.Divisahist;

public interface DivisahistService {

	public List<Divisahist> findAll();

    public Divisahist findById(String id, String fec);

    public void save(Divisahist divisahist);

    public void deleteById(String id, String fec);
	
}
