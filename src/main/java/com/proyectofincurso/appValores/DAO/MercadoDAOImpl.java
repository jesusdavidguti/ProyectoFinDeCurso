package com.proyectofincurso.appValores.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Mercado;

@Repository
public class MercadoDAOImpl implements mercadoDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Mercado> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Mercado> theQuery = currentSession.createQuery("from mercado", Mercado.class);

        List<Mercado> mercados = theQuery.getResultList();

        return mercados;
	}

	@Override
	public Mercado findById(String id) {
		Session currentSession = entityManager.unwrap(Session.class);

        Mercado mercado = currentSession.get(Mercado.class, id);

        return mercado;
	}
	
	@Transactional
	@Override
	public void save(Mercado mercado) {
		Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(mercado);
	}

	@Transactional	
	@Override
	public void deleteById(String id) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Mercado> theQuery =  currentSession.createQuery("Delete from mercado where codMercado=:codMercado");
		  
		 theQuery.setParameter("codMercado", id); 
		 theQuery.executeUpdate();
	}
}
