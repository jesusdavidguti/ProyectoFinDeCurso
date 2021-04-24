package com.proyectofincurso.appValores.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofincurso.appValores.entity.Divisahist;
import com.proyectofincurso.appValores.entity.Valor;

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
	public Divisahist findById(String id, String fec) {

		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		
		Date inicio = null;
				
		String fechaDesde = fec.substring(4, 8) + "-" + fec.substring(2, 4) + "-" + fec.substring(0, 2) + " 00:00:00";
		  				
		try {
			inicio = sourceFormat.parse(fechaDesde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		    	    				
   	    Date fecBusqueda = inicio;
   	       	    
		TypedQuery<Divisahist> query = entityManager.createQuery("select d from divisahist d where d.divisaHistID.divisa.codDivisa = ?1 and d.divisaHistID.fecDivisa = ?2", Divisahist.class);
	    query.setParameter(1, id);
	    query.setParameter(2, fecBusqueda, TemporalType.TIMESTAMP);
	    	    
	    return query.getSingleResult();			   
	}

	@Transactional
	@Override
	public void save(Divisahist divisahist) {
		Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(divisahist);
	}

	@Transactional	
	@Override
	public void deleteById(String id, String fec) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 Query<Divisahist> theQuery =  currentSession.createQuery("Delete from divisahist where codDivisa=:codDivisa and fecDivisa=:fecDivisa");
		  
		 theQuery.setParameter("codDivisa", id); 
		 theQuery.setParameter("fecDivisa", fec);
		 theQuery.executeUpdate();
	}

}
