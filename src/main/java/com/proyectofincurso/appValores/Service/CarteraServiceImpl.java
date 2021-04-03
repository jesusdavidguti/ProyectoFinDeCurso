package com.proyectofincurso.appValores.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.DAO.carteraDAO;
import com.proyectofincurso.appValores.entity.Cartera;

@Service
public class CarteraServiceImpl implements carteraService {

	@Autowired
	private carteraDAO carteraDAO; 
	
	@Override
	public List<Cartera> findAll() {
		// TODO Auto-generated method stub
		List<Cartera> listCarteras = carteraDAO.findAll();
		
		return listCarteras;
	}

	@Override
	public Cartera findById(int id) {
		// TODO Auto-generated method stub
		
		Cartera cartera = carteraDAO.findById(id);
		return cartera;
	}

	@Transactional
	@Override
	public void save(Cartera cartera) {
		// TODO Auto-generated method stub

		carteraDAO.save(cartera);
	}

	@Transactional	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

		carteraDAO.deleteById(id);
	}

}
