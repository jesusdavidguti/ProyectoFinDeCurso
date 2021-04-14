package com.proyectofincurso.appValores.Service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Valor;

@Service
public class ValorServiceImpl implements ValorService {

	@Autowired
	private ValorDao valorDao;	
	
	@Override
	public List<Valor> findAll() {
		List<Valor> listaValores = valorDAO.findAll();
		return listaValores;
	}

	@Override
	public Valor findById(int id) {
		Valor valor = valorDAO.findById(id);
		return valor;
	}

	@Transactional
	@Override
	public void save(Valor valor) {
		valorDAO.save(valor);		
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		valorDAO.deleteById(id);		
	}

}
