package com.proyectofincurso.appValores.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Cartera;

@Repository
public class CarteraDAOImpl implements CarteraDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Cartera> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Cartera> theQuery = currentSession.createQuery("from cartera", Cartera.class);

        List<Cartera> carteras = theQuery.getResultList();

        return carteras;		
	}

	@Override
	public Cartera findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

        Cartera cartera = currentSession.get(Cartera.class, id);

        return cartera;
	}

	@Transactional
	@Override
	public void save(Cartera cartera) {
		Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(cartera);  
	}

	@Transactional	
	@Override
	public void deleteById(int id) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Cartera> theQuery =  currentSession.createQuery("Delete from cartera where idCartera=:idCartera");
		  
		 theQuery.setParameter("idCartera", id); 
		 theQuery.executeUpdate();
	}

}
