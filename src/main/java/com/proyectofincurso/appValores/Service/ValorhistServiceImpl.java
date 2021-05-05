package com.proyectofincurso.appValores.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.DAO.ValorhistDAO;
import com.proyectofincurso.appValores.entity.Valorhist;

@Service
public class ValorhistServiceImpl implements ValorhistService {

	@Autowired
	private ValorhistDAO valorhistDAO;	
	
	@Override
	public List<Valorhist> findAll() {
		List<Valorhist> listaValoresHist = valorhistDAO.findAll();
		return listaValoresHist;
	}

	@Override
	public Valorhist findById(int id, String fec) {
		Valorhist valorhist = valorhistDAO.findById(id,fec);
		return valorhist;
	}

	@Override
	public List<Valorhist> findByIdBetweenFecs(int id, String fecD, String fecH) {
		// TODO Auto-generated method stub
		return valorhistDAO.findByIdBetweenFecs(id, fecD, fecH);
	}

	@Override
	public List<Valorhist> findTopLowValor(int id, String fecD, String fecH) {
		// TODO Auto-generated method stub
		return valorhistDAO.findTopLowValor(id, fecD, fecH);
	}	
	
	@Transactional
	@Override
	public void save(Valorhist valorhist) {
		valorhistDAO.save(valorhist);		
	}

	@Transactional
	@Override
	public void deleteById(int id, String fec) {
		valorhistDAO.deleteById(id,fec);		
	}

}
