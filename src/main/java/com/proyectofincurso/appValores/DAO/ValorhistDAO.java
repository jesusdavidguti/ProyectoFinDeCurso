package com.proyectofincurso.appValores.DAO;

import java.util.Date;
import java.util.List;

import com.proyectofincurso.appValores.entity.Valorhist;

public interface ValorhistDAO {

	 public List<Valorhist> findAll();

	 public Valorhist findById(int id, String fec);
	 
	 public List<Valorhist> findByIdBetweenFecs(int id, String fecD, String fecH);

	 public List<Valorhist> findTopLowValor(int id, String fecD, String fecH);	 
	 
	 public void save(Valorhist valorhist);

	 public void deleteById(int id, String fec);
}
