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

import com.proyectofincurso.appValores.Service.CarteraService;
import com.proyectofincurso.appValores.Service.CarteraServiceImpl;
import com.proyectofincurso.appValores.entity.Cartera;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiCarteras")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class CarteraController {

	//Inyectamos el servicio para poder hacer uso de el	
	@Autowired
	private CarteraService carteraService;
		
	@Bean 
	public CarteraService carteraService() { 
		return new CarteraServiceImpl(); 
	}
	
	// PETICONES GET
	
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "carteraCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad cartera.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/carteras")
    public List<Cartera> findAll(){

        return carteraService.findAll();
    }

	@GetMapping("/carteras/{carteraId}")
    public Cartera getCartera(@PathVariable int carteraId){
        Cartera cartera = carteraService.findById(carteraId);

        if(cartera == null) {
            throw new RuntimeException("Cartera desconocida -"+carteraId);
        }

        return cartera;
    }
	
	// PETICONES POST	 
	
	 @PostMapping("/carteras")
	 public Cartera addCartera(@RequestBody Cartera cartera) {
		 
	        cartera.setIdCartera(0);

	        carteraService.save(cartera);

	        return cartera;	 
	 }
	
	// PETICONES PUT
	 
	 @PutMapping("/carteras")
	 public Cartera updateCartera(@RequestBody Cartera cartera) {

	        carteraService.save(cartera);

	        return cartera;
	 }
	
	// PETICONES DELETE
	 
	 @DeleteMapping("cateras/carteraId}")
	 public String deleteCartera(@PathVariable int carteraId) {

	        Cartera cartera = carteraService.findById(carteraId);

	        if(cartera == null) {
	            throw new RuntimeException("Cartera desconocida id:"+carteraId);
	        }

	        carteraService.deleteById(carteraId);

	        return "Cartera borrada con id - "+carteraId;
	 }	 	
}
