package com.proyectofincurso.appValores.Service;

import java.util.Date;
import java.util.List;

import com.proyectofincurso.appValores.entity.Valorhist;

public interface ValorhistService {

	public List<Valorhist> findAll();

    public Valorhist findById(int id, Date fec);

    public void save(Valorhist valorhist);

    public void deleteById(int id, Date fec);	
}
