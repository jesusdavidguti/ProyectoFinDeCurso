package com.proyectofincurso.appValores.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.DAO.DivisaDAO;
import com.proyectofincurso.appValores.entity.Divisa;

@Service
public class DivisaServiceImpl implements divisaService {

	@Autowired
	private DivisaDAO divisaDAO;
	
	@Override
	public List<Divisa> findAll() {
		// TODO Auto-generated method stub
		
		List<Divisa> listaDivisas = divisaDAO.findAll();
		return listaDivisas;
	}

	@Override
	public Divisa findById(String id) {
		// TODO Auto-generated method stub
		
		Divisa divisa = divisaDAO.findById(id);
		return divisa;
	}

	@Transactional
	@Override
	public void save(Divisa divisa) {
		// TODO Auto-generated method stub

		divisaDAO.save(divisa);
	}
	
	@Transactional
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

		divisaDAO.deleteById(id);
	}

}
