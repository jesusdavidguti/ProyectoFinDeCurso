package com.proyectofincurso.appValores.Controller;

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

import com.proyectofincurso.appValores.Service.ValorhistService;
import com.proyectofincurso.appValores.entity.Valorhist;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiValores")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class ValorhistController {

	@Autowired
	private ValorhistService valorhistService;
	
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
		 
		 	// Insertar objetos.
	        
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
	 public String deleteValorHist(@PathVariable int valorId) {

	        Valor valor = valorService.findById(valorId);

	        if(valor == null) {
	            throw new RuntimeException("Valor desconocido id:"+valorId);
	        }

	        valorService.deleteById(valorId);

	        return "Valor borrado con id - "+valorId;
	  }	 	 
}
