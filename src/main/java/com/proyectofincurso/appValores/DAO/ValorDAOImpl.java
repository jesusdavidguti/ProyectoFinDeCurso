package com.proyectofincurso.appValores.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Valor;

@Repository
public class ValorDAOImpl implements ValorDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Valor> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

        Query<Valor> theQuery = currentSession.createQuery("from valor", Valor.class);

        List<Valor> valores = theQuery.getResultList();

        return valores;
	}

	@Override
	public Valor findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

        Valor valor = currentSession.get(Valor.class, id);

        return valor;
	}

	@Transactional
	@Override
	public void save(Valor valor) {
		Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(valor);

	}

	@Transactional
	@Override
	public void deleteById(int id) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Valor> theQuery =  currentSession.createQuery("Delete from valor where idValor=:idValor");
		  
		 theQuery.setParameter("idValor", id); 
		 theQuery.executeUpdate();
	}

}
