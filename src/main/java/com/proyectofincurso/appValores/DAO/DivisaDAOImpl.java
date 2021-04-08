package com.proyectofincurso.appValores.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Divisa;

@Repository
public class DivisaDAOImpl implements DivisaDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Divisa> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Divisa> theQuery = currentSession.createQuery("from divisa", Divisa.class);

        List<Divisa> divisas = theQuery.getResultList();

        return divisas;		
	}

	@Override
	public Divisa findById(String id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
        Divisa divisa = currentSession.get(Divisa.class, id);
        
        return divisa;
	}

	@Transactional
	@Override
	public void save(Divisa divisa) {
		Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(divisa);
	}

	@Transactional	
	@Override
	public void deleteById(String id) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Divisa> theQuery =  currentSession.createQuery("Delete from divisa where codDivisa=:codDivisa");
		  
		 theQuery.setParameter("codDivisa", id); 
		 theQuery.executeUpdate();
	}

}
