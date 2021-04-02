package com.proyectofincurso.appValores.Service;

import com.proyectofincurso.appValores.entity.Cartera;
import java.util.List;

public interface carteraService {

	public List<Cartera> findAll();

    public Cartera findById(int id);

    public void save(Cartera cartera);

    public void deleteById(int id);
	
}
