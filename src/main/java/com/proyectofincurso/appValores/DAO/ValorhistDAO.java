package com.proyectofincurso.appValores.DAO;

import java.util.Date;
import java.util.List;

import com.proyectofincurso.appValores.entity.Valorhist;

public interface ValorhistDAO {

	 public List<Valorhist> findAll();

	 public Valorhist findById(int id, Date fec);
	 
	 public void save(Valorhist valorhist);

	 public void deleteById(int id, Date fec);
}
