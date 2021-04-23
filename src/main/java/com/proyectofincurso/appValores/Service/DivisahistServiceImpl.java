package com.proyectofincurso.appValores.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.DAO.DivisahistDAO;
import com.proyectofincurso.appValores.entity.Divisahist;

@Service
public class DivisahistServiceImpl implements DivisahistService {

	@Autowired
	private DivisahistDAO divisahistDAO;
	
	@Override
	public List<Divisahist> findAll() {
		// TODO Auto-generated method stub
		
		List<Divisahist> listaDivisas = divisahistDAO.findAll();
		return listaDivisas;
	}

	@Override
	public Divisahist findById(String id, String fec) {
		// TODO Auto-generated method stub
		
		Divisahist divisahist = divisahistDAO.findById(id, fec);
		return divisahist;
	}

	@Transactional
	@Override
	public void save(Divisahist divisahist) {
		// TODO Auto-generated method stub

		divisahistDAO.save(divisahist);
	}
	
	@Transactional
	@Override
	public void deleteById(String id, String fec) {
		// TODO Auto-generated method stub

		divisahistDAO.deleteById(id,fec);
	}

}
