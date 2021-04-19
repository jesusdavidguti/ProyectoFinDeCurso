package com.proyectofincurso.appValores.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Valorhist;

@Repository
public class ValorhistDAOImpl implements ValorhistDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Valorhist> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Valorhist> theQuery = currentSession.createQuery("from valorhist", Valorhist.class);

        List<Valorhist> valoreshist = theQuery.getResultList();

        return valoreshist;
	}

	@Override
	public Valorhist findById(int id, Date fec) {
		Session currentSession = entityManager.unwrap(Session.class);

        Valorhist valorhist = currentSession.get(Valorhist.class, id);

        return valorhist;
	}

	@Transactional
	@Override
	public void save(Valorhist valorhist) {
		Session currentSession = entityManager.unwrap(Session.class);
		
        currentSession.saveOrUpdate(valorhist);

	}

	@Transactional
	@Override
	public void deleteById(int id, Date fec) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Valorhist> theQuery =  currentSession.createQuery("Delete from valor where idValor=:idValor and fecValor=:fec");
		  
		 theQuery.setParameter("idValorhist", id); 
		 theQuery.setParameter("fecValor", fec);		 
		 theQuery.executeUpdate();
	}

}
