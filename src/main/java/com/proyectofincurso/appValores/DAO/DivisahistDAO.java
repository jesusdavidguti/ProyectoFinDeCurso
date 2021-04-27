package com.proyectofincurso.appValores.DAO;

import java.util.Date;
import java.util.List;

import com.proyectofincurso.appValores.entity.Divisahist;
import com.proyectofincurso.appValores.entity.Valorhist;

public interface DivisahistDAO {

	public List<Divisahist> findAll();

	public Divisahist findById(String id, String fec);
	
	public List<Divisahist> findByIdBetweenFecs(String id, String fecD, String fecH);
	 
	public void save(Divisahist divisahist);

	public void deleteById(String id, String fec);	
}
