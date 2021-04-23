package com.proyectofincurso.appValores.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Divisahist;

@Repository
public class DivisahistDAOImpl implements DivisahistDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Divisahist> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Divisahist> theQuery = currentSession.createQuery("from divisahist", Divisahist.class);

        List<Divisahist> divisas = theQuery.getResultList();

        return divisas;		
	}

	@Override
	public Divisahist findById(String id, Date fec) {
		Session currentSession = entityManager.unwrap(Session.class);
				
        Divisahist divisahist = currentSession.get(Divisahist.class, id);
        
        return divisahist;
	}

	@Transactional
	@Override
	public void save(Divisahist divisahist) {
		Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(divisahist);
	}

	@Transactional	
	@Override
	public void deleteById(String id, Date fec) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Divisahist> theQuery =  currentSession.createQuery("Delete from divisahist where codDivisa=:codDivisa and fecDivisa=:fecDivisa");
		  
		 theQuery.setParameter("codDivisa", id); 
		 theQuery.setParameter("fecDivisa", fec);
		 theQuery.executeUpdate();
	}

}
