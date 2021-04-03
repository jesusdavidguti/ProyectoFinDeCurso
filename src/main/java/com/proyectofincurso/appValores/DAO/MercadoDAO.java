package com.proyectofincurso.appValores.DAO;

import java.util.List;

import com.proyectofincurso.appValores.entity.Mercado;

public interface MercadoDAO {

	 public List<Mercado> findAll();

	 public Mercado findById(String id);
	 
	 public void save(Mercado mercado);

	 public void deleteById(String id);	
}
