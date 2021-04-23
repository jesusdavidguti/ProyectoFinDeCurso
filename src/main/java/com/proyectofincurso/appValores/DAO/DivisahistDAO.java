package com.proyectofincurso.appValores.DAO;

import java.util.Date;
import java.util.List;

import com.proyectofincurso.appValores.entity.Divisahist;

public interface DivisahistDAO {

	public List<Divisahist> findAll();

	public Divisahist findById(String id, Date fec);
	 
	public void save(Divisahist divisahist);

	public void deleteById(String id, Date fec);	
}
