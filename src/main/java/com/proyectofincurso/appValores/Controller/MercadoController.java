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

import com.proyectofincurso.appValores.Service.MercadoService;
import com.proyectofincurso.appValores.Service.MercadoServiceImpl;
import com.proyectofincurso.appValores.entity.Divisa;
import com.proyectofincurso.appValores.entity.Mercado;

// Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiMercados")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class MercadoController {

	//Inyectamos el servicio para poder hacer uso de el	
	@Autowired
	private MercadoService mercadoService;
		
	@Bean 
	public MercadoService mercadoService() { 
		return new MercadoServiceImpl(); 
	}
	 	
	// PETICONES GET
			
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "mercadoCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad mercado.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/mercados")
    public List<Mercado> findAll(){

        return mercadoService.findAll();
    }
	
	@GetMapping("/mercados/{mercadoId}")
    public Mercado getUser(@PathVariable String mercadoId){
        Mercado mercado = mercadoService.findById(mercadoId);

        if(mercado == null) {
            throw new RuntimeException("Mercado desconocido -"+mercadoId);
        }

        return mercado;
    }

	 // ...aquí podríamos ampliar nuestra funcionalidad añadiendo distintas búsquedas.
	 	 
	// PETICONES POST	 
	
	 @PostMapping("/mercados")
	 public Mercado addMercado(@RequestBody Mercado mercado) {
		 
	        //mercado.setCodMercado(0);

	        mercadoService.save(mercado);

	        return mercado;	 
	 }
	 
	 @PostMapping("/load")
	 public Map<String, String> loadMercado() {
		 
		 	Mercado mercadoLoad1 = new Mercado("DJ","Dow Jones","USA","América");		 			 	
	        mercadoService.save(mercadoLoad1);
		 	Mercado mercadoLoad2 = new Mercado("IBEX","Ibex 35","ES","Europa");		 			 	
	        mercadoService.save(mercadoLoad2);
		 	Mercado mercadoLoad3 = new Mercado("NIKK","Nikkei 225","JP","Asia");		 			 	
	        mercadoService.save(mercadoLoad3);	        
		 	Mercado mercadoLoad4 = new Mercado("NDQ","Nasdaq composite","USA","América");		 			 	
	        mercadoService.save(mercadoLoad4);	        
		 	Mercado mercadoLoad5 = new Mercado("FTSE","FTSE 100","FR","Europa");		 			 	
	        mercadoService.save(mercadoLoad5);	        
		 	Mercado mercadoLoad6 = new Mercado("DAX","DAX 30","DE","Europa");		 			 	
	        mercadoService.save(mercadoLoad6);	        	        
		 	Mercado mercadoLoad7 = new Mercado("TEST","TEST Mercados","XX","XXXXXX");		 			 	
	        mercadoService.save(mercadoLoad7);	        	        

	        
	        HashMap<String, String> map = new HashMap<>();
		    map.put("Tabla:", "Mercados");
		    map.put("Carga:", "Finalizada");	    
		    return map;
	 }	 
	 
	// PETICONES PUT
	 
	 @PutMapping("/mercados")
	 public Mercado updateMercado(@RequestBody Mercado mercado) {

	        mercadoService.save(mercado);

	        return mercado;
	    }
	 
	// PETICONES DELETE
	 
	 @DeleteMapping("mercados/{mercadoId}")
	 public String deleteMercado(@PathVariable String mercadoId) {

	        Mercado mercado = mercadoService.findById(mercadoId);

	        if(mercado == null) {
	            throw new RuntimeException("Mercado desconocido id:"+mercadoId);
	        }

	        mercadoService.deleteById(mercadoId);

	        return "Mercado borrado con id - "+mercadoId;
	    }	 
	 
}
