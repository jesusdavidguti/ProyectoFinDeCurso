package com.proyectofincurso.appValores.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		 
		 	Divisa divisaLoad1 = new Divisa("eur","Euro","EUR",1.19);		 			 	
	        divisaService.save(divisaLoad1);	        	        
		 	Divisa divisaLoad2 = new Divisa("usdo","Dolar americano","USD",1.00);		 			 	
	        divisaService.save(divisaLoad2);	        	        
		 	Divisa divisaLoad3 = new Divisa("libr","Libra esterlina","GBP",1.37);		 			 	
	        divisaService.save(divisaLoad3);	        	        
		 	Divisa divisaLoad4 = new Divisa("yen","Yen japonés","JPY",0.0092);		 			 	
	        divisaService.save(divisaLoad4);	        	        
		 	Divisa divisaLoad5 = new Divisa("test","test divisas","xx",0.01);		 			 	
	        divisaService.save(divisaLoad5);
		 	Divisa divisaLoad6 = new Divisa("bado","Dolar de las Bahamas","BSD",0.98);		 			 	
	        divisaService.save(divisaLoad6);
		 	Divisa divisaLoad7 = new Divisa("neru","Rupia nepalí","NPR",0.05);		 			 	
	        divisaService.save(divisaLoad7);
		 	Divisa divisaLoad8 = new Divisa("rubl","Rublo ruso","RUBL",1.43);		 			 	
	        divisaService.save(divisaLoad8);
		 	Divisa divisaLoad9 = new Divisa("sufr","Franco suizo","CHF",1.17);		 			 	
	        divisaService.save(divisaLoad9);
		 	Divisa divisaLoad10 = new Divisa("suco","Corona sueca","SEC",1.23);		 			 	
	        divisaService.save(divisaLoad10);
	        
	        List<Divisa> divisas = divisaService.findAll();
	        	        	        
	        HashMap<String, String> map = new HashMap<>();
		    map.put("Tabla:", "Divisas insertadas:");
		    map.put("Carga:", "Finalizada con "+divisas.size()+" elementos.");	    
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
		 	        
        try {
	        Divisa divisa = divisaService.findById(divisaId);	        
	        if(divisa == null) {
	            throw new RuntimeException("Divisa desconocida id:"+divisaId);
	        }
	        divisaService.deleteById(divisaId);
	        return "Divisa borrada con id - "+divisaId;
	        
		} catch (Exception e) {
			throw new RuntimeException("Error recuperación divisa "+divisaId);
		}					        
	 }		 
}
