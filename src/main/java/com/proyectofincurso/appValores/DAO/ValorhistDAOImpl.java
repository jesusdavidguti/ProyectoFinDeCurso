package com.proyectofincurso.appValores.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.proyectofincurso.appValores.entity.Valorhist;
import com.proyectofincurso.appValores.entity.Valorhistmaxmin;

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
	public Valorhist findById(int id, String fec) {
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
   	       	    
		TypedQuery<Valorhist> query = entityManager.createQuery("select v from valorhist v where v.valorHistID.valor.idValor = ?1 and v.valorHistID.fecValor = ?2", Valorhist.class);
	    query.setParameter(1, id);
	    query.setParameter(2, fecBusqueda, TemporalType.TIMESTAMP);
	    	    
	    return query.getSingleResult();
	}

	@Override
	public List<Valorhist> findByIdBetweenFecs(int id, String fecD, String fecH) {
		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		
		Date inicio = null;
		Date fin = null;
				
		String fechaDesde = fecD.substring(4, 8) + "-" + fecD.substring(2, 4) + "-" + fecD.substring(0, 2) + " 00:00:00";
		String fechaHasta = fecH.substring(4, 8) + "-" + fecH.substring(2, 4) + "-" + fecH.substring(0, 2) + " 00:00:00";		
		  				
		try {
			inicio = sourceFormat.parse(fechaDesde);
			fin = sourceFormat.parse(fechaHasta);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		    	    				
   	    Date fecIni = inicio;
   	    Date fecFin = fin;
   	    
   	    //System.out.println("fecIni: "+fecIni);
   	    //System.out.println("fecFin: "+fecFin);   	    
   	    
		TypedQuery<Valorhist> query = entityManager.createQuery("select v from valorhist v where v.valorHistID.valor.idValor = ?1 and v.valorHistID.fecValor between ?2 and ?3", Valorhist.class);
	    query.setParameter(1, id);
	    query.setParameter(2, fecIni, TemporalType.TIMESTAMP);
	    query.setParameter(3, fecFin, TemporalType.TIMESTAMP);	    
	    	    
	    return query.getResultList();   	    		
	}
		
	@Override
	public List<Valorhistmaxmin> findTopLowValor(int orden, String fecD, String fecH) {
				
		String sOrden, fechaDesde = null, fechaHasta = null;
		Date dHoy = Calendar.getInstance().getTime();
		Date dAyer = Calendar.getInstance().getTime();
		
		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat formatter = new SimpleDateFormat("ddMMyyyy");		
		Date inicio = null;
		Date fin = null;

		if (orden == 1) {
			sOrden = "ASC";
		}
		else {
			sOrden = "DESC";
		}
		
		// Si fechaD y fechaH están informadas
		//
		if ((fecD.trim().length() > 0) && (fecD.trim().length() > 0)) {		
			fechaDesde = fecD.substring(4, 8) + "-" + fecD.substring(2, 4) + "-" + fecD.substring(0, 2) + " 00:00:00";
			fechaHasta = fecH.substring(4, 8) + "-" + fecH.substring(2, 4) + "-" + fecH.substring(0, 2) + " 00:00:00";			
		}
		
		// Si fechaD y fechaH NO están informadas, las informamos con hoy y ayer
		//		
		if ((fecD.trim().length() == 0) && (fecH.trim().length() == 0)) {

			// Fecha de hoy
			fecD = formatter.format(dHoy);							
			fechaDesde = fecD.substring(4, 8) + "-" + fecD.substring(2, 4) + "-" + fecD.substring(0, 2) + " 00:00:00";
			
			// Fecha de ayer						
			Calendar c = Calendar.getInstance();
	        c.setTime(dHoy);
	        c.add(Calendar.DAY_OF_MONTH, -1);
	        dAyer = c.getTime();	
	        fecH = formatter.format(dAyer);		        
			fechaHasta = fecH.substring(4, 8) + "-" + fecH.substring(2, 4) + "-" + fecH.substring(0, 2) + " 00:00:00";
		}
				
		try {
			inicio = sourceFormat.parse(fechaDesde);
			fin = sourceFormat.parse(fechaHasta);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		    	    				
   	    Date fecIni = inicio;
   	    Date fecFin = fin;
   	       	    
		//TypedQuery<Valorhist> query = entityManager.createQuery("select v from valorhist v where v.valorHistID.valor.idValor = ?1 and v.valorHistID.fecValor between ?2 and ?3", Valorhist.class);
   	    TypedQuery<Valorhistmaxmin> query = entityManager.createQuery("select NEW com.proyectofincurso.appValores.entity.Valorhistmaxmin(" 
																+ "h.cotizacionUSdolar,"
																+ "h_ayer.cotizacionUSdolar,"
																+ "v.nombre,"
																+ "m.nombre,"
																+ "h.valorHistID.fecValor,"
																+ "round((h.cotizacionUSdolar - h_ayer.cotizacionUSdolar),2))"																
																+ " FROM valorhist h, valor v, mercado m,"
																+ "valorhist h_ayer"
																+ " WHERE "
																+ "h.valorHistID.fecValor = ?1 "
																+ "AND h_ayer.valorHistID.fecValor = ?2 "
																+ "AND h.valorHistID.valor.idValor = h_ayer.valorHistID.valor.idValor "
																+ "AND m.codMercado = v.mercado.codMercado "
																+ "AND v.idValor = h.valorHistID.valor.idValor "
																+ "order by (h.cotizacionUSdolar - h_ayer.cotizacionUSdolar)" + sOrden
																, Valorhistmaxmin.class);
				
	    query.setParameter(1, fecIni, TemporalType.TIMESTAMP);
	    query.setParameter(2, fecFin, TemporalType.TIMESTAMP);	    
	    
//	    select 	h.cotizacionusdolar 'Hoy', 
//				h_ayer.cotizacionusdolar 'Ayer', 
//				(h.cotizacionusdolar - h_ayer.cotizacionusdolar) 'Diferencia', 
//				v.nombre 'Valor', 
//				m.nombre 'Mercado',
//				h.fec_valor
//        FROM 	valorhist h, valor v, mercado m,
//				valorhist h_ayer
//		WHERE	
//				h.fec_valor = "2021-03-15"
//		AND     h_ayer.fec_valor = "2021-03-14"
//		AND		h.id_valor = h_ayer.id_valor
//		AND		m.cod_mercado = v.cod_mercado
//		AND		v.id_valor = h.id_valor
//		order by (h.cotizacionusdolar - h_ayer.cotizacionusdolar) DESC	    
	    	    	    
	    return query.getResultList();
	}
			
	@Transactional
	@Override
	public void save(Valorhist valorhist) {
		Session currentSession = entityManager.unwrap(Session.class);
		
        currentSession.saveOrUpdate(valorhist);

	}

	@Transactional
	@Override
	public void deleteById(int id, String fec) {
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		
		 Date inicio = null;
				
		 String fechaDesde = fec.substring(4, 8) + "-" + fec.substring(2, 4) + "-" + fec.substring(0, 2) + " 00:00:00";
		  				
		 try {
			inicio = sourceFormat.parse(fechaDesde);
		 } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }		 
		    	    				
   	     Date fecBorrado = inicio;
		 
		 Query<Valorhist> theQuery =  currentSession.createQuery("Delete from valor v where v.valorHistID.valor.idValor = :id and v.valorHistID.fecValor = :fecBorrado");
		  
		 theQuery.setParameter("idValorhist", id);
		 theQuery.setParameter("fecValor", fecBorrado);
		 theQuery.executeUpdate();
	}

}
