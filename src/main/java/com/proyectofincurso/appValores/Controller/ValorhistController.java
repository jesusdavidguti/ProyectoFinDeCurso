package com.proyectofincurso.appValores.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofincurso.appValores.Service.ValorService;
import com.proyectofincurso.appValores.Service.ValorhistService;
import com.proyectofincurso.appValores.entity.Valorhist;
import com.proyectofincurso.appValores.entity.ValorhistID;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiValoresHist")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class ValorhistController {

	@Autowired
	private ValorhistService valorhistService;
	@Autowired
	private ValorService valorService;	
	// PETICONES GET
	
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "valorhistCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad valorhist.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/valoreshist")
    public List<Valorhist> findAll(){

        return valorhistService.findAll();
    }
	
	@GetMapping("/valoreshist/{valorId, fecha}")
    public Valorhist getValor(@PathVariable int valorHistId, Date fec){
        Valorhist valorhist = valorhistService.findById(valorHistId, fec);

        if(valorhist == null) {
            throw new RuntimeException("Valor histórico desconocido -"+valorHistId);
        }

        return valorhist;
    }

	 // ...aquí podríamos ampliar nuestra funcionalidad añadiendo distintas búsquedas.
	 
	// PETICONES POST	 
	
	 @PostMapping("/valoreshist")
	 public Valorhist addValorHist(@RequestBody Valorhist valorHist) {
		 
		//System.out.println("Divisa nombre: " + valor.getDivisa().getCodDivisa());
		//System.out.println("Divisa país: " + valor.getDivisa().getCodPais());				 
		 
        valorhistService.save(valorHist);
        
        return valorHist;	 
	 }
	 
	 @PostMapping("/load")
	 public Map<String, String> loadValorHist() {
		 

		 	java.util.Date fecha = new Date();
		 			 		
		 	fecha.setDate(1);
		 	ValorhistID valorhistID1 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist1 = new Valorhist(valorhistID1,1.24); 
		 	valorhistService.save(valorHist1);
		 		 
		 	fecha.setDate(2);		 			 			 	
		 	ValorhistID valorhistID2 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist2 = new Valorhist(valorhistID2,1.1); 
		 	valorhistService.save(valorHist2);
		 			 	
		 	fecha.setDate(3);		 			 			 	
		 	ValorhistID valorhistID3 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist3 = new Valorhist(valorhistID3,2.1); 
		 	valorhistService.save(valorHist3);

		 	fecha.setDate(4);		 			 			 	
		 	ValorhistID valorhistID4 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist4 = new Valorhist(valorhistID4,1.5); 
		 	valorhistService.save(valorHist4);
		 	
		 	fecha.setDate(5);		 			 			 	
		 	ValorhistID valorhistID5 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist5 = new Valorhist(valorhistID5,1.92); 
		 	valorhistService.save(valorHist5);
			        
	        HashMap<String, String> map = new HashMap<>();
		    map.put("Tabla:", "valorhist");
		    map.put("Carga:", "Finalizada");	    
		    return map;
	 }	 
	
	// PETICONES PUT
	 
	 @PutMapping("/valoreshist")
	 public Valorhist updateValorHist(@RequestBody Valorhist valorhist) {

	        valorhistService.save(valorhist);

	        return valorhist;
	 }

	// PETICONES DELETE
	 
	 @DeleteMapping("valores/{valorHist}")
	 public String deleteValorHist(@PathVariable int valorHistId, Date fec) {

	        Valorhist valorHist = valorhistService.findById(valorHistId, fec);

	        if(valorHist == null) {
	            throw new RuntimeException("Valor histórico desconocido id:"+valorHistId);
	        }

	        valorhistService.deleteById(valorHistId,fec);

	        return "Valor histórico borrado con id - "+valorHistId + " y fecha "+fec;
	  }	 	 
}
