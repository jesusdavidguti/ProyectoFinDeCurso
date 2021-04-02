package com.proyectofincurso.appValores.Service;

import java.util.List;

import com.proyectofincurso.appValores.entity.Mercado;

public interface mercadoService {

	public List<Mercado> findAll();

    public Mercado findById(String id);

    public void save(Mercado mercado);

    public void deleteById(String id);
}
