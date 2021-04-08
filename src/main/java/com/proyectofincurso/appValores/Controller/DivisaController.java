package com.proyectofincurso.appValores.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

import com.proyectofincurso.appValores.Service.DivisaService;
import com.proyectofincurso.appValores.Service.DivisaServiceImpl;
import com.proyectofincurso.appValores.entity.Divisa;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiDivisas")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class DivisaController {

	//Inyectamos el servicio para poder hacer uso de el	
	@Autowired
	private DivisaService divisaService;
		
	@Bean 
	public DivisaService divisaService() { 
		return new DivisaServiceImpl(); 
	}
	
	// PETICONES GET
	
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "divisaCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad divisa.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/divisas")
    public List<Divisa> findAll(){

        return divisaService.findAll();
    }
	
	@GetMapping("/divisas/{divisaId}")
    public Divisa getDivisa(@PathVariable String divisaId){
		
		try {
	        Divisa divisa = divisaService.findById(divisaId);	        
	        return divisa;
		} catch (Exception e) {
			throw new RuntimeException("Error recuperación divisa "+divisaId);
		}				
    }
	
	// PETICONES POST	 
	
	 @PostMapping("/divisas")
	 public Divisa addDivisa(@RequestBody Divisa divisa) {
		 
	        //mercado.setCodMercado(0);

	        divisaService.save(divisa);

	        return divisa;	 
	 }
	 
	 @PostMapping("/load")
	 public Map<String, String> loadDivisa() {
		 
		 	Divisa divisaLoad1 = new Divisa("eur","Euro","EU",1.19);		 			 	
	        divisaService.save(divisaLoad1);
		 	Divisa divisaLoad2 = new Divisa("usdo","Dolar americano","USA",1.00);		 			 	
	        divisaService.save(divisaLoad2);
		 	Divisa divisaLoad3 = new Divisa("libr","Libra esterlina","GB",1.37);		 			 	
	        divisaService.save(divisaLoad3);
		 	Divisa divisaLoad4 = new Divisa("Yen","Yen japonés","JP",0.0092);		 			 	
	        divisaService.save(divisaLoad4);
	        
	        HashMap<String, String> map = new HashMap<>();
		    map.put("Tabla", "Divisas");
		    map.put("Carga", "Finalizada");	    
		    return map;
	 }

	 
	// PETICONES PUT
	 
	 @PutMapping("/divisas")
	 public Divisa updateDivisa(@RequestBody Divisa divisa) {

	        divisaService.save(divisa);

	        return divisa;
	 }
	
	// PETICONES DELETE
	 
	 @DeleteMapping("divisas/{divisaId}")
	 public String deleteDivisa(@PathVariable String divisaId) {

	        Divisa divisa = divisaService.findById(divisaId);

	        if(divisa == null) {
	            throw new RuntimeException("Divisa desconocida id:"+divisaId);
	        }

	        divisaService.deleteById(divisaId);

	        return "Divisa borrada con id - "+divisaId;
	 }	
	 
}
