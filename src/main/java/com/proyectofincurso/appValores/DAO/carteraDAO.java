package com.proyectofincurso.appValores.DAO;

import java.util.List;

import com.proyectofincurso.appValores.entity.Cartera;

public interface carteraDAO {

	public List<Cartera> findAll();

	public Cartera findById(int id);
	 
	public void save(Cartera cartera);

	public void deleteById(int id);
	
}
