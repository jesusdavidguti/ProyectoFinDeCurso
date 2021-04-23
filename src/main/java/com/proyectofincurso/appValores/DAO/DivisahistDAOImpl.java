package com.proyectofincurso.appValores.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
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

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = null;
		
		String fechaDesde = fec.substring(0, 2)+"/"+fec.substring(2, 4)+"/"+fec.substring(4, 8);
		  		 
		try {
			inicio = sourceFormat.parse(fechaDesde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		 
   	    Date fecBusqueda = inicio;

   	    //
   	    System.out.println("Fecha: " + fecBusqueda);
   	    //
   	    
		TypedQuery<Divisahist> query = entityManager.createQuery("select vh from valorhist vh where vh.nombre = ?1 and vh.fec_valor = ?2", Divisahist.class);
	    query.setParameter(1, id);
	    query.setParameter(1, fecBusqueda);
	    
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
