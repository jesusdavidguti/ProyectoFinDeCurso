package com.proyectofincurso.appValores.DAO;

import java.util.List;

import com.proyectofincurso.appValores.entity.Valor;

public interface ValorDAO {

	 public List<Valor> findAll();

	 public Valor findById(int id);
	 
	 public void save(Valor valor);

	 public void deleteById(int id);
}
