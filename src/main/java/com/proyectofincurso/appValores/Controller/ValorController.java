package com.proyectofincurso.appValores.Controller;

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

import com.proyectofincurso.appValores.Service.DivisaService;
import com.proyectofincurso.appValores.Service.MercadoService;
import com.proyectofincurso.appValores.Service.ValorService;
import com.proyectofincurso.appValores.Service.ValorhistService;
import com.proyectofincurso.appValores.entity.Valor;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiValores")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class ValorController {

	@Autowired
	private ValorService valorService;
	@Autowired	
	private MercadoService mercadoService;
	@Autowired	
	private DivisaService divisaService;	
	
	// PETICONES GET
	
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "valorCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad valor.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/valores")
    public List<Valor> findAll(){

        return valorService.findAll();
    }
	
	@GetMapping("/valores/{valorId}")
    public Valor getValor(@PathVariable int valorId){
        Valor valor = valorService.findById(valorId);

        if(valor == null) {
            throw new RuntimeException("Valor desconocido -"+valorId);
        }

        return valor;
    }

	 // ...aquí podríamos ampliar nuestra funcionalidad añadiendo distintas búsquedas.
	 
	// PETICONES POST	 
	
	 @PostMapping("/valores")
	 public Valor addValor(@RequestBody Valor valor) {
		 
		//System.out.println("Divisa nombre: " + valor.getDivisa().getCodDivisa());
		//System.out.println("Divisa país: " + valor.getDivisa().getCodPais());				 
		 
        valorService.save(valor);
        
        return valor;	 
	 }
	 
	 @PostMapping("/load")
	 public Map<String, String> loadMercado() {
		 
		 	// Insertar objetos.
	        
	        HashMap<String, String> map = new HashMap<>();
	        Valor valor1 = new Valor(0,"Valor Teleco1","Teleco", mercadoService.findById("NDQ"), divisaService.findById("usdo"));
	        valorService.save(valor1);
	        Valor valor2 = new Valor(0,"Valor Banco1","Banca", mercadoService.findById("IBEX"), divisaService.findById("eur"));
	        valorService.save(valor2);
	        Valor valor3 = new Valor(0,"Valor Energia1","Energía", mercadoService.findById("FTSE"), divisaService.findById("eur"));
	        valorService.save(valor3);
	        Valor valor4 = new Valor(0,"Valor Inmobiliario1","Inmobiliario", mercadoService.findById("NIKK"), divisaService.findById("yen"));
	        valorService.save(valor4);
	        
		    map.put("Tabla:", "Valor");
		    map.put("Carga:", "Finalizada");	    
		    return map;
	 }	 
	
	// PETICONES PUT
	 
	 @PutMapping("/valores")
	 public Valor updateValor(@RequestBody Valor valor) {

	        valorService.save(valor);

	        return valor;
	 }

	// PETICONES DELETE
	 
	 @DeleteMapping("valores/{valorId}")
	 public String deleteValor(@PathVariable int valorId) {

	        Valor valor = valorService.findById(valorId);

	        if(valor == null) {
	            throw new RuntimeException("Valor desconocido id:"+valorId);
	        }

	        valorService.deleteById(valorId);

	        return "Valor borrado con id - "+valorId;
	  }	 	 
}
