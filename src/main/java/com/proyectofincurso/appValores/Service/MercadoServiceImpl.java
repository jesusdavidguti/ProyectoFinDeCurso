package com.proyectofincurso.appValores.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.DAO.MercadoDAO;
import com.proyectofincurso.appValores.entity.Mercado;

@Service
public class MercadoServiceImpl implements MercadoService {

	@Autowired
	private MercadoDAO mercadoDAO;
	
	@Override
	public List<Mercado> findAll() {
		// TODO Auto-generated method stub
		
		List<Mercado> listaMercados = mercadoDAO.findAll();
		return listaMercados;
	}

	@Override
	public Mercado findById(String id) {
		// TODO Auto-generated method stub
		
		Mercado mercado = mercadoDAO.findById(id);
		return mercado;
	}

	@Transactional
	@Override
	public void save(Mercado mercado) {
		// TODO Auto-generated method stub

		mercadoDAO.save(mercado);
	}

	@Transactional
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

		mercadoDAO.deleteById(id);
	}

}
